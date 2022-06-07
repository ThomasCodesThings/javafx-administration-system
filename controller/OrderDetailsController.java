package controller;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.customer.Order;
import model.database.PatisserieDatabase;
import model.infrastructure.Patisserie;
import model.product.Product;
import model.product.ProductObserver;
import view.OrderDetails;

import java.util.ArrayList;

public class OrderDetailsController {

    OrderDetails orderDetails;
    Stage stage;

    /**
     *
     * @param orderID
     * @param orderDetails
     * @param stage
     */
    public OrderDetailsController(int orderID, OrderDetails orderDetails, Stage stage){
        this.orderDetails = orderDetails;
        this.stage = stage;
        checkOrder(orderID);

    }

    /**
     * Function for finding order by ID
     * @param orderID
     */
    //funkcia na vypis informacii od objednavke na zaklade jej ID
    void checkOrder(int orderID){
        Order showOrder = null;
        ArrayList<Patisserie> patisseries = PatisserieDatabase.getInstance().getPatisseries();;
        Patisserie p = null;
        for(Patisserie patisserie : patisseries){
            for(Order order : patisserie.getOrderList()){
                if(orderID == order.getOrderID()){
                    showOrder = order;
                    p = patisserie;
                    break;
                }
            }
        } //tu sa vypisu ostatne detaily do okien
        if(showOrder != null){
            stage.setScene(orderDetails.getScene());
            orderDetails.getFirstNameTextField().setText(showOrder.getCustomer().getFirstName());
            orderDetails.getLastNameTextField().setText(showOrder.getCustomer().getLastName());
            orderDetails.getAddressTextField().setText(showOrder.getCustomer().getResidence().getAddress());
            orderDetails.getPSCTextField().setText(showOrder.getCustomer().getResidence().getPSC());
            orderDetails.getTownTextField().setText(showOrder.getCustomer().getResidence().getTown());
            orderDetails.getCountryTextField().setText(showOrder.getCustomer().getResidence().getCountry());
            orderDetails.getTelephoneNumberTextField().setText(showOrder.getCustomer().getTelephoneNumber());
            orderDetails.getEmailTextField().setText(showOrder.getCustomer().getEmail());
            orderDetails.getDateField().setText(showOrder.getOrderDate());
            orderDetails.getStatusTextField().setText(showOrder.getOrderStatus());
            for(ProductObserver product : showOrder.getOrderItems()){
                orderDetails.getOrderItemsVBox().getChildren().add(new Text(product.toString()));
            }
            if(p != null) {
                Order finalShowOrder = showOrder;
                Patisserie finalP = p;
                orderDetails.getCancelButton().setOnAction(event -> { //pripadne ak by sme chceli zmazat objednavku
                    finalP.getOrderList().remove(finalShowOrder);
                    PatisserieDatabase.save();
                    System.out.println("Removed order " + orderID);
                    stage.close();
                });
            }
            orderDetails.getOkButton().setOnAction(event -> {
                stage.close();
            });
            //fillthetable
        }else{
            System.out.println("Order " + orderID + " was not found!");
        }
    }
}
