package controller;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.database.PatisserieDatabase;
import model.infrastructure.Patisserie;
import model.infrastructure.Supplier;
import model.infrastructure.Warehouse;
import model.ingredients.Ingredient;
import model.product.Product;
import view.SupplyWindow;
import view.WarehouseDisplay;
import model.ingredients.*;
import view.amountPopUp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class WarehouseController {

    private WarehouseDisplay warehouseDisplay;

    /**
     * Adding and Removing Ingredients and Products in Patisserie´s warehouse
     * @param warehouseDisplay
     * @param patisserie
     */
    public WarehouseController(WarehouseDisplay warehouseDisplay, Patisserie patisserie){
        Warehouse warehouse = patisserie.getWarehouse();
        this.warehouseDisplay = warehouseDisplay;
        Warehouse.Node temp = warehouse.getHead();
        warehouseDisplay.getAddButton().setOnAction(event -> {
            SupplyWindow supplyWindow = new SupplyWindow();
            amountPopUp amountPopUp = new amountPopUp();
            SupplyController supplyController = new SupplyController(supplyWindow, amountPopUp, patisserie, warehouseDisplay);
        });

            //pridavanie a odoberanie produktov a ingrediencii
            for(int i = 0; i < warehouse.size(); i++){
                Object obj = patisserie.getWarehouse().getOnIndex(i).getItem();
                if(obj instanceof Ingredient) {
                    Button addButton = ((Button) ((HBox) warehouseDisplay.getvBox().getChildren().get(i + 2)).getChildren().get(2));
                    addButton.setOnAction(event -> {
                        Stage stage = new Stage();
                        amountPopUp amountPopUp = new amountPopUp();
                        stage.setScene(amountPopUp.getScene());
                        stage.show();
                        addItems(amountPopUp, obj, stage, patisserie);
                        //warehouseDisplay.getTextArea().appendText(createSupplierText());
                    });

                    Button removeButton = ((Button) ((HBox) warehouseDisplay.getvBox().getChildren().get(i + 2)).getChildren().get(3));
                    removeButton.setOnAction(event -> {
                        patisserie.getWarehouse().remove(obj);
                        System.out.println("Item removed!");
                    });
                }else if(obj instanceof Product){
                    Button removeButton = ((Button) ((HBox) warehouseDisplay.getvBox().getChildren().get(i + 2)).getChildren().get(2));
                    removeButton.setOnAction(event -> {
                        patisserie.getWarehouse().remove(obj);
                        System.out.println("Item removed!");
                    });
                }
            }
            //temp = temp.getNext();

        warehouseDisplay.getRemoveButton().setOnAction(event -> {
            Ingredient ingredient = (Ingredient) warehouseDisplay.getTableView().getSelectionModel().getSelectedItem();
            warehouseDisplay.getTableView().getItems().remove(ingredient);
            patisserie.getWarehouse().remove(ingredient);
        });
    }

    /**
     * Function to add products into warehouse
     * @param amountPopUp
     * @param obj
     * @param stage
     * @param patisserie
     */
    void addItems(amountPopUp amountPopUp, Object obj, Stage stage, Patisserie patisserie){
        amountPopUp.getOkButton().setOnAction(event -> {
        int amount = (int) amountPopUp.getSpinner().getValue();
        if(amount == 0){
            amount = Integer.parseInt(amountPopUp.getSpinner().getEditor().getText());
        }

        try {
            Constructor constructor = obj.getClass().getConstructor(int.class);
            Object newObj = constructor.newInstance(((Ingredient) obj).getAmount() + amount);
            patisserie.getWarehouse().remove(obj);
            patisserie.getWarehouse().add(newObj);
            warehouseDisplay.getTextArea().appendText(createSupplierText());
            PatisserieDatabase.save();
            stage.close();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        });
    }

    /**
     * Function to generate random Supplier for products
     * @return supplierData
     */
    public static String createSupplierText(){
        Supplier supplier = new Supplier();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(supplier.getCompanyName() + '\n');
        stringBuilder.append(supplier.getDate() + '\n');
        stringBuilder.append(supplier.getCompanyResidence().getAddress() + '\n');
        stringBuilder.append(supplier.getCompanyResidence().getPSC() + " " + supplier.getCompanyResidence().getTown() + " " + supplier.getCompanyResidence().getCountry() + '\n');
        for(int i = 0; i < 50; i++){
            stringBuilder.append('*');
        }
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }
}
