package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TeamMemberWindow {
    private Text text = new Text("New team member");
    private Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 16);
    private VBox vBox;
    private ComboBox comboBox = new ComboBox();
    private Button okButton = new Button("Ok");
    private Scene scene;

    public ComboBox getComboBox() {
        return comboBox;
    }

    public Scene getScene() {
        return scene;
    }

    public Button getOkButton() {
        return okButton;
    }

    /**
     * Window for adding new Team Member
     */
    public TeamMemberWindow(){
        vBox = new VBox();
        text.setFont(font);
        vBox.getChildren().addAll(text, comboBox, okButton);
        scene = new Scene(vBox);
    }
}
