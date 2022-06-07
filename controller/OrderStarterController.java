package controller;

import model.customer.Order;
import model.infrastructure.Employee;
import model.infrastructure.Patisserie;
import model.infrastructure.Team;
import model.infrastructure.TeamLeader;
import view.OrderStarter;

import java.util.List;

public class OrderStarterController {

    private OrderStarter orderStarter;

    /**
     * Controller for starting order solving process
     * @param orderStarter
     * @param patisserie
     * @param teamLeader
     */
    public OrderStarterController(OrderStarter orderStarter, Patisserie patisserie, TeamLeader teamLeader){
        this.orderStarter = orderStarter;
        fillBox(patisserie);
        orderStarter.getStartButton().setOnAction(event -> {
            int orderID = (int) orderStarter.getComboBox().getSelectionModel().getSelectedItem();
            Order order = null;
            for(Order tempOrder : patisserie.getOrderList()){ //hladanie a veci podobne
                if(orderID == tempOrder.getOrderID()){
                    order = tempOrder;
                    break;
                }
            }
            if(order != null && orderID > 0){
                Team team = teamLeader.getTeam();
                if(team != null) {
                    team.setAssignedOrder(order); //prida  objednavku teamu
                    order.setOrderStatus("IN_PROGRESS");
                    System.out.println("Succesfully assigned order " + order.getOrderID() + " to team " + team.getTeamName());
                }
            }
        });
    }

    void fillBox(Patisserie patisserie){
        for(Order order: patisserie.getOrderList()){
            orderStarter.getComboBox().getItems().add(order.getOrderID());
        }
    }
}
