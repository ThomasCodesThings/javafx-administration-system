package controller;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.customer.Order;
import model.infrastructure.*;
import model.product.ProductObserver;
import view.*;

public class PatisserieMenuController {
    private Stage stage = new Stage();
    private PatisserieMenu patisserieMenu;
    private Patisserie patisserie;
    private TableView employeeTableView;
    public PatisserieMenuController(Patisserie patisserie, PatisserieMenu patisserieMenu, Employee employeeType){
        this.patisserieMenu = patisserieMenu;
        this.patisserie = patisserie;
        employeeTableView = patisserieMenu.getEmployeeTable();
        if(employeeTableView != null){
           load();
        }

        patisserieMenu.getInfoText().setText("You are currently logged as:\n" + employeeType.getClass().getSimpleName() + " \nwith ID: " + employeeType.getPersonalID());
            patisserieMenu.getAddEmployee().setOnAction(event -> {
                if(employeeType instanceof Owner) {
                    createNew();
                }else{
                    System.out.println("You " + employeeType.getClass().getSimpleName() + " are not allowed to do this!!");
                    System.out.println("Ask your supervisor");
                }
            });

            patisserieMenu.getRemoveEmployee().setOnAction(event -> {
                if(employeeType instanceof Owner) {
                    removeEmployee();
                }else{
                    System.out.println("You " + employeeType.getClass().getSimpleName() + " are not allowed to do this!!");
                    System.out.println("Ask your supervisor");
                }
            });

                patisserieMenu.getCreateTeam().setOnAction(event -> {
                    if(employeeType instanceof Owner || employeeType instanceof Manager || employeeType instanceof TeamLeader) {
                        if (patisserie.getTeamLeaders().size() != 0) {
                            createTeam();
                        }
                    } else{
                        System.out.println("You " + employeeType.getClass().getSimpleName() + " are not allowed to do this!!");
                        System.out.println("Ask your supervisor");
                    }
                });


                patisserieMenu.getWarehouseButton().setOnAction(event -> {
                    if(employeeType instanceof Owner || employeeType instanceof Manager) {
                        WarehouseDisplay warehouseDisplay = new WarehouseDisplay(patisserie.getWarehouse());
                        WarehouseController warehouseController = new WarehouseController(warehouseDisplay, patisserie);
                        Stage stage = new Stage();
                        stage.setScene(warehouseDisplay.getScene());
                        stage.show();
                    }else{
                        System.out.println("You " + employeeType.getClass().getSimpleName() + " are not allowed to do this!!");
                        System.out.println("Ask your supervisor");
                    }
                });

            patisserieMenu.getOrderButton().setOnAction(event -> {
                OrderDisplayWindow orderDisplayWindow = new OrderDisplayWindow();
                Stage stage = new Stage();
                stage.setTitle("Orders");
                Scene oldScene = orderDisplayWindow.getScene();
                stage.setScene(oldScene);
                for(Order order : patisserie.getOrderList()){
                    orderDisplayWindow.getTableView().getItems().add(order);

                }
                stage.show();
                orderDisplayWindow.getShowButton().setOnAction(event1 -> {
                    Order order = (Order) orderDisplayWindow.getTableView().getSelectionModel().getSelectedItem();
                    ShowOrderItems showOrderItems = new ShowOrderItems(order.getOrderID());
                    stage.setScene(showOrderItems.getScene());
                    stage.setMinHeight(300);
                    for(ProductObserver productObserver : order.getOrderItems()){
                        showOrderItems.getvBox().getChildren().add(new Text(productObserver.toString()));
                    }
                    showOrderItems.getBackButton().setOnAction(event2 -> {
                        stage.setScene(oldScene);
                    });
                });
            });
                patisserieMenu.getRecipeButton().setOnAction(event -> {
                    if(employeeType instanceof Owner || employeeType instanceof Manager) {
                        RecipeWindow recipeWindow = new RecipeWindow();
                        RecipeWindowController recipeWindowController = new RecipeWindowController(recipeWindow, patisserie);
                    }else{
                        System.out.println("You " + employeeType.getClass().getSimpleName() + " are not allowed to do this!!");
                        System.out.println("Ask your supervisor");
                    }
                });

                patisserieMenu.getCraftButton().setOnAction(event -> {
                    CraftingMenu craftingMenu = new CraftingMenu();
                    Stage stage = new Stage();
                    stage.setTitle("Crafting");
                    stage.setScene(craftingMenu.getScene());
                    stage.show();
                    CraftingMenuController craftingMenuController = new CraftingMenuController(craftingMenu, patisserie, stage);
                });
                patisserieMenu.getSpecialButton().setOnAction(event -> {
                    if(employeeType instanceof TeamLeader){
                        OrderStarter orderStarter = new OrderStarter();
                        Stage stage = new Stage();
                        stage.setTitle("Start an order");
                        stage.setScene(orderStarter.getScene());
                        stage.show();
                        OrderStarterController orderStarterController = new OrderStarterController(orderStarter, patisserie, (TeamLeader) employeeType);
                    }else if(!(employeeType instanceof Owner) && !(employeeType instanceof Manager) && !(employeeType instanceof TeamLeader) && employeeType instanceof Employee){
                        OrderMaker orderMaker = new OrderMaker();
                        Stage stage = new Stage();
                        stage.setTitle("Work on order");
                        stage.setScene(orderMaker.getScene());
                        stage.show();
                        OrderMakerController orderMakerController = new OrderMakerController(orderMaker, patisserie, employeeType);
                    }

                });
        stage.setScene(patisserieMenu.getScene());
        stage.setTitle(patisserie.getPatisserieName());
        stage.show();
    }

    void load(){
        VBox detailsBox = (VBox) patisserieMenu.getvBox().getChildren().get(0);
        if(detailsBox != null){
            ((Text) detailsBox.getChildren().get(2)).setText(patisserie.getPatisserieName());
            ((Text) detailsBox.getChildren().get(3)).setText(patisserie.getResidence().getAddress());
            ((Text) detailsBox.getChildren().get(4)).setText(patisserie.getResidence().getPSC() + "\t" + patisserie.getResidence().getTown() + "\t" + patisserie.getResidence().getCountry());
            ((Text) detailsBox.getChildren().get(6)).setText(patisserie.getOwner().getFirstName() + "\t" + patisserie.getOwner().getLastName());
            ((Text) detailsBox.getChildren().get(7)).setText( String.valueOf(patisserie.getOwner().getAge()));
            ((Text) detailsBox.getChildren().get(8)).setText(patisserie.getOwner().getOwnerResidence().getAddress());
            ((Text) detailsBox.getChildren().get(9)).setText(patisserie.getOwner().getOwnerResidence().getPSC() + "\t" + patisserie.getOwner().getOwnerResidence().getTown() + "\t" + patisserie.getOwner().getOwnerResidence().getCountry());

        }
        for(TeamLeader teamLeader : patisserie.getTeamLeaders()){
            employeeTableView.getItems().add(teamLeader);
        }
        for(Manager manager: patisserie.getManagers()){
            employeeTableView.getItems().add(manager);
        }
        for(Employee employee : patisserie.getEmployeeList()){
            employeeTableView.getItems().add(employee);
        }
        VBox teamBox = (VBox) ( (VBox) patisserieMenu.gethBox().getChildren().get(1)).getChildren().get(2);
        for(int i = 0; i < patisserie.getTeamList().size(); i++){
            Team team = patisserie.getTeamList().get(i);
            ((Text) ((HBox) teamBox.getChildren().get(i)).getChildren().get(0)).setText(patisserie.getTeamList().get(i).getTeamName());
            ((Button) ((HBox) teamBox.getChildren().get(i)).getChildren().get(1)).setOnAction(event -> {
                teamDetails(team);
            });
        }

        patisserie.getOrderList().forEach(o ->{
            //do stuff with o
        });
    }

    void teamDetails(Team team){
        TeamDetails teamDetails = new TeamDetails();
        TeamDetailsController teamDetailsController = new TeamDetailsController(teamDetails, team, patisserie.getEmployeeList(), patisserie);
    }
    void createNew(){
        Stage stage = new Stage();
        userDetailsMenu userDetailsMenu = new userDetailsMenu(false);
        userDetailsMenuController userDetailsMenuController = new userDetailsMenuController(userDetailsMenu, stage, patisserie.getEmployeeList(), patisserie.getTeamLeaders(), patisserie.getManagers(), employeeTableView);
        stage.setScene(userDetailsMenu.getScene());
        stage.setTitle("Add new employee");
        stage.show();
    }

    void removeEmployee(){
        int index = employeeTableView.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            Owner owner = patisserie.getOwner();
            Employee employee = (Employee) employeeTableView.getItems().get(index);
            owner.kick(employee);
            //patisserie.getEmployeeList().remove(index);
            employeeTableView.getItems().remove(index);
        }
    }

    void createTeam(){
        TeamCreateWindow teamCreateWindow = new TeamCreateWindow();
        TeamCreateWindowController teamCreateWindowController = new TeamCreateWindowController(teamCreateWindow, patisserie, patisserieMenu);
    }
    void print(){
        Owner owner = patisserie.getOwner();
        owner.print(patisserie);
    }
}
