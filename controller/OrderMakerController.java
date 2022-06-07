package controller;

import model.customer.Order;
import model.database.PatisserieDatabase;
import model.infrastructure.Employee;
import model.infrastructure.Patisserie;
import model.infrastructure.Team;
import model.infrastructure.Warehouse;
import model.ingredients.Ingredient;
import model.product.Product;
import model.product.ProductObserver;
import view.OrderMaker;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static model.utilities.ObjectModeler.productFromString;

public class OrderMakerController {

    public OrderMakerController(OrderMaker orderMaker, Patisserie patisserie, Employee employee){
        Order order = null;
        Team temp = null;
        for(Team team : patisserie.getTeamList()){ //najprv musim objednavu najst
            for(Employee emp : team.getTeammemberList()){
                if(emp.equals(employee)){
                    order = team.getAssignedOrder();
                    temp = team;
                    break;
                }
            }
        }
        if(order != null){
           orderMaker.getText().setText("I´m working on order " + order.getOrderID());
            Order finalOrder = order;
            Team finalTemp = temp;
            orderMaker.getWorkButton().setOnAction(event -> {
               for(double progress = 0.0; progress <= 1.0; progress += 0.2){ //cyklus ktory trva 5 sekund kde sa simuluje spracovavanie objednavky
                   try {
                       orderMaker.getProgressBar().setProgress(progress); //update progress baru
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   if(orderMaker.getProgressBar().getProgress() == 1){
                       finalOrder.setOrderStatus("COMPLETED");
                       for(Team team : patisserie.getTeamList()){
                           if(team.equals(finalTemp)){
                               team.setAssignedOrder(null); //zmazanie pridelenej objednavky pre team
                               break;
                           }
                       }
                       //finalTemp.setAssignedOrder(null);
                       PatisserieDatabase.save();
                       removeProducts(finalOrder, patisserie);
                       System.out.println("I, " + employee.getFirstName() + " " + employee.getLastName() + " have completed order " + finalOrder.getOrderID() + "!");
                   }
               }
           });

        }
    }

    /**
     * Function to remove ingredients from warehouse after solving order
     * @param order
     * @param patisserie
     */
    //funkcia prejde cyklicky cez vsetky produkty v objednavke a updatne vsetky ingredience na sklade ktore sa spotrebovali na vyrobu
    void removeProducts(Order order, Patisserie patisserie){
        for(ProductObserver productObserver : order.getOrderItems()){
            for(Ingredient ingredient : ((Product) productObserver).getProductIngredients()) {
                Warehouse.Node temp = patisserie.getWarehouse().getHead();
                while (temp != null) {
                    if (temp.getItem() instanceof Ingredient) {
                        Constructor constructor = null;
                        try {
                            constructor = temp.getItem().getClass().getConstructor(int.class);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        int oldAmount = ingredient.getAmount();
                        Object newObj = null;
                        try {
                            newObj = constructor.newInstance(((Ingredient) temp.getItem()).getAmount() - oldAmount); //vytvaranie novych objektov
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        patisserie.getWarehouse().remove(temp.getItem());
                        patisserie.getWarehouse().add(newObj);
                        PatisserieDatabase.save();
                        break;
                    }
                    temp = temp.getNext();
                }
            }
        }
    }
}
