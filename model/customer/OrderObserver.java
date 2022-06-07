package model.customer;

import model.product.ProductObserver;

public interface OrderObserver {
    void addProduct(ProductObserver productObserver); // pripoj pozorovateľa
    void removeProduct(ProductObserver productObserver); // odpoj pozorovateľa
    void notifyObservers(); // pošli notifikáciu pozorovateľom
}
