package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ShowOrderItems {

    private Scene scene;
    private VBox vBox = new VBox();
    private int size;
    private Button backButton= new Button("Back");

    public Scene getScene() {
        return scene;
    }

    public VBox getvBox() {
        return vBox;
    }

    public Button getBackButton() {
        return backButton;
    }

    public ShowOrderItems(int orderID){
        this.size = size;
        vBox.setSpacing(10);
        Text text = new Text("Products for order no. " + orderID);
        text.setFont(new Font(20));
        vBox.getChildren().add(text);
        vBox.getChildren().add(backButton);
        scene = new Scene(vBox);
    }
}
