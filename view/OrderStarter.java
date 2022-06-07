package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OrderStarter {

    private Scene scene;
    private ComboBox comboBox = new ComboBox();
    private Button startButton = new Button("Start process");

    public Scene getScene() {
        return scene;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public Button getStartButton() {
        return startButton;
    }

    /**
     * Small window to assign order to team
     */
    public OrderStarter(){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        Text text = new Text("Select order");
        text.setFont(new Font(16));
        vBox.getChildren().addAll(text, comboBox, startButton);
        scene = new Scene(vBox);
    }
}
