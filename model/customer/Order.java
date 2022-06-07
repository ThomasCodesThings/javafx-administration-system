package model.customer;

import model.database.PatisserieDatabase;
import model.infrastructure.Patisserie;
import model.product.Product;
import model.product.ProductObserver;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable, OrderObserver {
    private int orderID;
    private Patisserie patisserie;
    private Customer customer;
    private String orderDate;
    private String orderStatus;
    private List<ProductObserver> orderItems = new ArrayList<>();
    private double orderProgress;

    public void addProgress(double progress){
        this.orderProgress += progress;
    }

    public double getOrderProgress() {
        return orderProgress;
    }

    public String getCustomerFirstName(){
        return this.customer.getFirstName();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Patisserie getPatisserie() {
        return patisserie;
    }

    public void setPatisserie(Patisserie patisserie) {
        this.patisserie = patisserie;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProductObserver> getOrderItems() {
        return orderItems;
    }

    public void removeProductObserver(ProductObserver product) {
        this.orderItems.remove(product);
    }

    /**
     * Utility for generating random order ID
     * @return returns 9 digit number
     */
    public int generateOrderID(){
        return (int) (Math.random() * ((1000000000 - 100000000))) + 100000000;
    }

    /**
     * Function for checking new order ID, if it already exists it creates new one until it´s unique
     * @return returns 9 digit random number
     */
    public int uniqueOrderID(){
        int orderID = generateOrderID();
        ArrayList<Patisserie> patisseries = PatisserieDatabase.getInstance().getPatisseries();
        patisseries.forEach((Patisserie p) -> {
            p.getOrderList().forEach((Order o) -> {
                if(orderID == o.getOrderID()){
                    uniqueOrderID();
                    System.out.println("Duplicate order ID found: " + orderID + " = " + o.getOrderID());
                }
            });
        });
        return orderID;
    }

    public Order(Customer customer, Patisserie patisserie){
        this.customer = customer;
        this.patisserie = patisserie;
        this.orderID = uniqueOrderID();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.orderDate = dtf.format(now);
        this.orderStatus = "INCOMPLETE";
        this.orderProgress = 0;
    }

    /**
     * Function for adding products to order
     * @param productObserver takes product as main parameter
     */
    @Override
    public void addProduct(ProductObserver productObserver) {
        this.orderItems.add(productObserver);
    }

    /**
     * Function for removing products in order
     * @param productObserver takes product as main parameter
     */
    @Override
    public void removeProduct(ProductObserver productObserver) {
        this.orderItems.remove(productObserver);
    }

    @Override
    /**
     * function as part of observer for updating products
     */
    public void notifyObservers() {
        this.orderItems.forEach((ProductObserver productObserver) ->{
            productObserver.update();
        });
    }

    @Override
    /**
     * basic function for getting orderID
     */
    public String toString() {
        return String.valueOf(orderID);
    }
}
