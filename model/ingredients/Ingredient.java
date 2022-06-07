package model.ingredients;

import java.io.Serializable;

public abstract class Ingredient implements Serializable, itemInterface {
    public int amount;
    public String itemName;

    public String getName() {
        return itemName;
    }

    public int getAmount(){
        return amount;
    }

    public void setName(String itemName) {
        this.itemName = itemName;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    /**
     * Function to change amount of ingredient amount
     * @param change takes difference as parameter
     */
    public void changeAmount(int change){
        this.amount -= change;
    }

    @Override
    /**
     * Basic function to get name of ingredient
     */
    public String toString() {
        return getClass().getSimpleName();
    }

    /**
     * Ingredient´s constructor
     * @param amount takes amount as parameter
     */
    public Ingredient(int amount) {
        this.amount = amount;
    }
}
