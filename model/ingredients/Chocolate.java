package model.ingredients;

public class Chocolate extends Ingredient {
    public double weight;

    public Chocolate(int amount){
        super(amount);
        this.weight = amount * 0.5;
    }
}
