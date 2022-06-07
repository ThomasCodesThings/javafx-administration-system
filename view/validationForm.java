package view;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class validationForm {
    private Scene scene;
    private VBox vBox = new VBox();
    private TextField IDTextField = new TextField();
    private Button button = new Button("Ok");

    public Scene getScene() {
        return scene;
    }

    public TextField getIDTextField() {
        return IDTextField;
    }

    public Button getButton() {
        return button;
    }

    /**
     * Creating validation form for employees
     */
    public validationForm(){
        Text text = new Text("Enter your ID:");
        text.setFont(new Font(17));
        vBox.setSpacing(5);
        vBox.getChildren().addAll(text, IDTextField, button);
        scene = new Scene(vBox);
    }
}
