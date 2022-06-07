package controller;

public class ItemNotSelected extends Exception{
    /**
     * Own exception in case of not selecting any patisserie
     * @return
     */
    public String getMessage(){
        return "Item not selected, Try again!";
    }
    ItemNotSelected(){
    }
}
