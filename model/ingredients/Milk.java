package model.ingredients;

public class Milk extends Ingredient {
    public double litres;

    public Milk(int amount){
        super(amount);
        this.litres = amount * 0.75;
    }
}
