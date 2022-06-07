package model.infrastructure;

import model.ingredients.Ingredient;
import model.ingredients.Milk;
import model.product.Product;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static model.utilities.ObjectModeler.ingredientFromString;

public class Warehouse<E> implements Serializable { //generickost
    private Node head;
    private Node tail;

    /**
     * Node class(part of linked list)
     */
    public class Node implements Serializable{
        E item;
        Node next;

        /**
         * Node´s constructor
         * @param item
         */
        public Node(E item){
            //System.out.println(item);
            this.item = item;
        }

        void setData(E item){
            this.item = item;
        }

        E getData(){
            return item;
        }

        public E getItem(){
            return item;
        }
        public Node getNext(){
            return next;
        }
    }

    public Node getHead(){
        return head;
    }

    /**
     * Returns size of linked list
     * @return size of linked list
     */
    public int size(){
        int size = 0;
        Node temp = head;
        while(temp != null){
            size++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * Function to check if it´s possible to craft a product
     * @param product takes product to be crafter as parameter
     * @return return true or false
     */
    public boolean checkProductIngredientsAvailability(Product product){
        int counter = 0;
        for(Ingredient ingredient : product.getProductIngredients()) {
            Node temp = head;
            while (temp != null) {
                if(temp.getItem() instanceof Ingredient){
                    if(((Ingredient) temp.getItem()).getAmount() > (ingredient.getAmount() * product.getAmount())){
                        counter++;
                        break;
                    }
                }
                temp = temp.next;
            }
        }
        if(counter == product.getProductIngredients().size()){
            return true;
        }
        return false;
    }

    /**
     * Function to remove ingredients amounts after crafting new product
     * @param product takes product as parameter
     */
    public void removeIngredients(Product product){
        product.getProductIngredients().forEach(ingredient -> {
            Node temp = head;
            while (temp != null){
                if(temp.getItem() instanceof Ingredient) {
                    if (temp.getItem().getClass().getSimpleName().equals(ingredient.getClass().getSimpleName())) {
                        Ingredient tempIngredient = (Ingredient) temp.getItem();
                        Object obj = ingredientFromString(ingredient.getClass().getSimpleName(), tempIngredient.getAmount() - ingredient.getAmount());
                        remove((E) tempIngredient);
                        add((E) obj);
                        break;
                    }
                }
                temp = temp.next;
            }
        });
    }

    /**
     * Function to get item on certain position in linked list
     * @param index takes index as position as parameter
     * @return returns item on that particular index
     */
    public Node getOnIndex(int index){
        Node temp = head;
        int i = 0;
        while(temp != null){
            if(i == index){
                return temp;
            }
            i++;
            temp =  temp.next;
        }
        return null;
    }

    /**
     * Function to add element into linked list
     * @param item takes object to be added as parameter
     */
    public void add(Object item) {
       boolean foundDuplicate = false;
        Node temp = head;
        while(temp != null){
                if(temp.getItem().getClass().getSimpleName().equals(item.getClass().getSimpleName())){
                    //System.out.println("Not gonna happen");
                    Constructor constructor = null;
                    try {
                        constructor = item.getClass().getConstructor(int.class);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    Object obj = null;
                    try {
                        if(item instanceof Ingredient) {
                            obj = constructor.newInstance(((Ingredient) temp.getItem()).getAmount() + ((Ingredient) item).getAmount());
                        }else if(item instanceof Product){
                            obj = constructor.newInstance(((Product) temp.getItem()).getAmount() + ((Product) item).getAmount());
                        }
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    temp.setData((E)obj);
                    foundDuplicate = true;
                    break;
                }
            temp = temp.next;
        }
        if(!foundDuplicate) {
            Node newNode = new Node((E)item);
            System.out.println("Adding "  + newNode.getItem());
            //adding
            newNode.next = head;
            head = newNode;
        }
    }


    /**
     * Function to print elements in linked list
     */
    public void print(){
        Node temp = head;
        while(temp != null){
            System.out.println(temp.item);
            temp = temp.next;
        }
    }

    /**
     * Function to remove element in linked list
     * @param item takes item to be removed as parameter
     */
    public void remove(E item){
        Node temp = head;
        Node before = null;

        if(temp != null && temp.item.equals(item)){
            head = temp.next;
            return;
        }else{
            while(temp != null && !temp.item.equals(item)){
                before = temp;
                temp = temp.next;
            }
            if(temp == null){
                return;
            }

            before.next = temp.next;

        }
    }
}
