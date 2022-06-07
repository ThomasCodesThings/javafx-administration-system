package controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.*;

public class MainMenuController {
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public MainMenuController(MainMenu mainMenu){
        mainMenu.getStaffButton().setOnAction(event -> {
            PatisserieMainGUI patisserieMainGUI = new PatisserieMainGUI();
            PatisserieMainMenuController patisserieMainMenuController = new PatisserieMainMenuController(patisserieMainGUI);
            if(patisserieMainMenuController != null) {
                patisserieMainMenuController.refresh();
            }
            Stage stage = new Stage();

            stage.setScene(patisserieMainGUI.getScene());
            stage.setTitle("MyPatisserie v1.30");
            stage.show();
        });

        mainMenu.getCustomerButton().setOnAction(event -> {
            CustomerOptionsMenu customerOptionsMenu = new CustomerOptionsMenu();
            stage = new Stage();
            stage.setScene(customerOptionsMenu.getScene());
            stage.show();
            customerOptionsMenu.getCreateNewOrderButton().setOnAction(event1 -> {
                OrderWindow orderWindow = new OrderWindow();
                OrderWindowController orderWindowController = new OrderWindowController(stage, orderWindow);
            });

            customerOptionsMenu.getCheckOrderButton().setOnAction(event1 -> {
                CheckOrderMenu checkOrderMenu = new CheckOrderMenu();
                stage.setScene(checkOrderMenu.getScene());
                        checkOrderMenu.getCheckButton().setOnAction(event2 -> {
                            String parseStr = checkOrderMenu.getOrderIDTexrField().getText();
                            if(parseStr.length() != 0) {
                                int orderID = Integer.parseInt(checkOrderMenu.getOrderIDTexrField().getText());
                                if (orderID > 0) {
                                    OrderDetails orderDetails = new OrderDetails();
                                    OrderDetailsController orderDetailsController = new OrderDetailsController(orderID, orderDetails, stage);
                                }
                            }
                        });
            });

        });
    }
}
