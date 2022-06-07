package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerOptionsMenu {

    private Scene scene;
    private Text text = new Text("What do you want to do?");
    private Button createNewOrderButton = new Button("Create new order");
    private Button checkOrderButton = new Button("Check order");
    private VBox vBox = new VBox();

    public Scene getScene() {
        return scene;
    }

    public Button getCreateNewOrderButton() {
        return createNewOrderButton;
    }

    public Button getCheckOrderButton() {
        return checkOrderButton;
    }

    public VBox getvBox() {
        return vBox;
    }

    /**
     * Generates customer menu
     */
    public CustomerOptionsMenu(){
        text.setFont(new Font(15));
        vBox.getChildren().add(text);
        HBox hBox = new HBox();
        vBox.setSpacing(20);
        hBox.setSpacing(30);
        hBox.getChildren().addAll(createNewOrderButton, checkOrderButton);
        vBox.getChildren().add(hBox);
        scene = new Scene(vBox);
    }
}
