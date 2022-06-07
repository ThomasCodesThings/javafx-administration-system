package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class amountPopUp {
    private Scene scene;
    private Button okButton = new Button("Ok");
    private Spinner spinner = new Spinner();
    private Text text = new Text("Amount");
    private Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 16);
    private VBox vBox;

    public Scene getScene() {
        return scene;
    }

    public Button getOkButton() {
        return okButton;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    /**
     * Generates Pop Up Window for entering amount
     */
    public amountPopUp(){
        vBox = new VBox();
        vBox.setSpacing(5);
        text.setFont(font);
        spinner.setEditable(true);
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000000));
        vBox.getChildren().addAll(text, spinner, okButton);
        scene = new Scene(vBox);
    }
}
