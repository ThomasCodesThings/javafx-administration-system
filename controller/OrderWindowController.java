package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Residence;
import model.customer.*;
import model.database.PatisserieDatabase;
import model.infrastructure.Patisserie;
import model.ingredients.Ingredient;
import model.product.Product;
import model.product.ProductObserver;
import model.product.cake.MelonCake;
import view.OrderShowcase;
import view.amountPopUp;
import view.OrderWindow;

import java.util.ArrayList;
import java.util.List;

import static model.utilities.ObjectModeler.productFromString;

public class OrderWindowController {
    private OrderWindow orderWindow;
    private amountPopUp amountPopUp;
    private TableView tableView;
    public OrderWindowController(Stage stage, OrderWindow orderWindow) {
        this.orderWindow = orderWindow;
        stage.setScene(orderWindow.getScene());
        stage.setTitle("New order");
        stage.show();
        fillTable();
        orderWindow.getGenerateButton().setOnAction(new EventHandler<ActionEvent>() { //vlastny handler
            /**
             * Generating order buttons for each products
             * @param event
             */
            //funkcia na vygenerovanie tlacitok
            public void handle(ActionEvent event) {
                Patisserie patisserie = (Patisserie) orderWindow.getPatisserieTable().getSelectionModel().getSelectedItem();
                if(patisserie != null){
                    int size = 4;
                    int i = 0;
                    int j = 0;
                    Button [][]buttons = new Button[(patisserie.getProductList().size() % size) + 1][size];
                    VBox buttonBox = ((VBox) orderWindow.getvBox().getChildren().get(4));
                    for(Product product : patisserie.getProductList()){
                        if(j == size){
                            j = 0;
                            i++;
                        }
                        buttons[i][j++] = new Button(product.getClass().getSimpleName());
                    }
                    orderWindow.buttonTable(buttonBox, buttons, i+1, size);
                    buttonCheck(stage);
                }
            }
        });
    }

    void fillTable(){
        for(Patisserie patisserie: PatisserieDatabase.getInstance().getPatisseries()){
            this.orderWindow.getPatisserieTable().getItems().add(patisserie);
        }
    }

    /**
     * Function to check if buttons are pressed
     * @param prevStage
     */
    void buttonCheck(Stage prevStage){
        VBox buttonBox = ((VBox) orderWindow.getvBox().getChildren().get(4));
        //System.out.println(productNameList.length);
        List<Product> productList = new ArrayList<>();
        for(int i = 0; i < buttonBox.getChildren().size(); i++){
            HBox hBox = (HBox) buttonBox.getChildren().get(i);
            for(int j = 0; j < hBox.getChildren().size(); j++){
                Button button = (Button) hBox.getChildren().get(j);
                button.setOnAction(event -> { //kontrola tlacitok
                    amountPopUp amountPopUp = new amountPopUp();
                    this.amountPopUp = amountPopUp;
                    Stage stage = new Stage();
                    stage.setScene(amountPopUp.getScene());
                    stage.setTitle("Amount");
                    stage.show();
                    System.out.println("Clicked button " + button.getText());
                    amountPopUp.getOkButton().setOnAction(event2 -> { //volba mnozstva produktu
                        int amount = (int) amountPopUp.getSpinner().getValue();
                        if(amount == 0){
                            amount = Integer.parseInt(amountPopUp.getSpinner().getEditor().getText());
                        }
                        Product product = (Product) productFromString(button.getText(), amount);

                        productList.add(product);
                        refreshOrders(productList);
                        stage.close();
                    });
                });
            }
        }
        Button button = ((Button) orderWindow.getvBox().getChildren().get(5));
        button.setOnAction(event -> {
            if(createNewOrder(productList)){
                System.out.println("Sucessfully created order!");
                PatisserieDatabase.save();
            }
            prevStage.close();
        });
    }

    /**
     * Function for creating new order
     * @param productList stored products
     * @return
     */
    boolean createNewOrder(List<Product> productList){
        String customerFirstName = orderWindow.getFirstNameTextField().getText();
        String customerLastName = orderWindow.getLastNameTextField().getText();
        String customerNumber = orderWindow.getTelephoneNumberTextField().getText();
        String customerEmail = orderWindow.getEmailTextField().getText();
        String customerAddress = orderWindow.getAddressTextField().getText();
        String customerPSC = orderWindow.getPSCTextField().getText();
        String customerTown = orderWindow.getTownTextField().getText();
        String customerCountry = orderWindow.getCountryTextField().getText();
        Patisserie patisserie = (Patisserie) orderWindow.getPatisserieTable().getSelectionModel().getSelectedItem();
        Customer customer = new Customer(customerFirstName, customerLastName, new Residence(customerAddress, customerPSC, customerTown, customerCountry), customerNumber, customerEmail);
        Order newOrder  = new Order(customer, patisserie);
        if(patisserie != null) {
            for(Product product : productList) {
                for (Product p : patisserie.getProductList()) {
                    if (p.getClass().getSimpleName().equals(product.getClass().getSimpleName())) {
                        for (Ingredient ingredient : p.getProductIngredients()) {
                            product.addIngredient(ingredient);
                        }
                        break;
                    }
                }
                if (patisserie.getWarehouse().checkProductIngredientsAvailability(product)) {
                    patisserie.getWarehouse().removeIngredients(product);
                    newOrder.getOrderItems().add(product);
                } else {
                    System.out.println("We cannot make " + product.getClass().getSimpleName() + " for you right now");
                    return false;
                }
            }
            if(newOrder != null){
                patisserie.getOrderList().add(newOrder);
                return true;
            }
            //refreshOrders(newOrder);
        }
        return false;
    }

    /**
     * Refresh function
     * @param productObserverList
     */
    void refreshOrders(List<Product> productObserverList){
        orderWindow.getOrderVBox().getChildren().clear();
        for(Product product : productObserverList){
               OrderShowcase orderShowcase = new OrderShowcase(product);
               orderWindow.getOrderVBox().getChildren().add(orderShowcase.getOrderBox());
               //TODO these buttons are not working!!!!
            Button cancelButton = (Button) orderShowcase.getOrderBox().getChildren().get(2);
            cancelButton.setOnAction(event -> {
                System.out.println("Removed!");
               //orderWindow.getOrderVBox().getChildren().remove(product);
                productObserverList.remove(product);
                orderWindow.getOrderVBox().getChildren().clear();
                refreshOrders(productObserverList);
            });
        }
    }
}
