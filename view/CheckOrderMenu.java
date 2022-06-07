package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CheckOrderMenu {
    private Scene scene;
    private Text text = new Text("Enter order ID");
    private TextField orderIDTexrField = new TextField();
    private Button checkButton = new Button("Check");

    public Scene getScene() {
        return scene;
    }

    public TextField getOrderIDTexrField() {
        return orderIDTexrField;
    }

    public Button getCheckButton() {
        return checkButton;
    }

    /**
     * Generates menu for checking order ID
     */
    public CheckOrderMenu(){
        text.setFont(new Font(15));
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(text, orderIDTexrField, checkButton);
        scene = new Scene(vBox);
    }
}
