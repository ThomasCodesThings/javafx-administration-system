package model.ingredients;

public class Egg extends Ingredient {
    private double weight;

    public Egg(int amount){
        super(amount);
        this.weight = amount * 0.10;
    }
}
