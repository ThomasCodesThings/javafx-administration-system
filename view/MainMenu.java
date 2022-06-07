package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class MainMenu {
    private Scene scene;
    private VBox vBox;
    private Text text;
    private Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 21);
    private Button staffButton = new Button("STAFF");
    private Button customerButton = new Button("CUSTOMER");

    public Scene getScene() {
        return scene;
    }

    public Button getStaffButton() {
        return staffButton;
    }

    public Button getCustomerButton() {
        return customerButton;
    }

    /**
     * Generates Main Menu Box
     */
    public MainMenu(){
        vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setMargin(staffButton, new Insets(20, 20, 20, 20));
        vBox.setMargin(customerButton , new Insets(20, 20, 20, 20));
        vBox.getChildren().addAll(staffButton, customerButton);
        scene = new Scene(vBox);
    }
}
