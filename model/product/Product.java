package model.product;

import model.customer.Order;
import model.ingredients.Ingredient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements ProductObserver, Serializable {
    private Product product;
    private int amount;
    private List<Ingredient> productIngredients = new ArrayList<>();

    public List<Ingredient> getProductIngredients() {
        return productIngredients;
    }

    /**
     * Function to add ingredient to ingredient list
     * @param ingredient
     */
    public void addIngredient(Ingredient ingredient){
        this.productIngredients.add(ingredient);
    }

    /**
     * Function to remove ingredient from ingredient list
     * @param ingredient
     */
    public void removeIngredient(Ingredient ingredient){
        this.productIngredients.remove(ingredient);
    }
    public Product(int amount) {
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    /**
     * Function to change amount
     * @param change
     */
    public void changeAmount(int change){
        this.amount -= change;
    }

    public String toString() {
        return getClass().getSimpleName() + " " + amount;
    }

    public Product getProduct(){
        return this.product;
    }
    @Override
    /**
     * Function to update products
     */
    public void update() {
        this.product.setAmount(this.amount);
    }
}
