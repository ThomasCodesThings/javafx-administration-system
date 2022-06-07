package model.utilities;

import model.product.Product;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class ObjectModeler {

    /**
     * Function to create any Product based on input string
     * @param selectedProductString name of product to be created
     * @param amount product amount
     * @return product object
     */
    public static Object productFromString(String selectedProductString, int amount){
        Class cls = null;
        if(selectedProductString.contains("Cake")){
            try {
                cls = Class.forName("model.product.cake." + selectedProductString);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if(selectedProductString.contains("IceCream")){
            try {
                cls = Class.forName("model.product.icecream." + selectedProductString);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            try {
                cls = Class.forName("model.product.desert." + selectedProductString);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Object obj = null;
        if(cls != null){

            try {
                Constructor constructor = cls.getConstructor(int.class);
                obj = constructor.newInstance(amount);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(obj != null) {
            return obj;
        }
        return null;
    }

    /**
     * Function to create any Ingredient based on input string
     * @param selectedIngredientString name of ingredient to be created
     * @param amount ingredient amount
     * @return ingredient object
     */
    public static Object ingredientFromString(String selectedIngredientString, int amount){
        Class cls = null;
            try {
                cls = Class.forName("model.ingredients." + selectedIngredientString);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        Object obj = null;
        if(cls != null){

            try {
                Constructor constructor = cls.getConstructor(int.class);
                obj = constructor.newInstance(amount);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(obj != null) {
            return obj;
        }
        return null;
    }
}
