package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Authentication {
    private Scene scene;
    private VBox vBox;
    private Text text = new Text("Login as");
    private Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 21);
    private Button ownerButton = new Button("Owner");
    private Button managerButton = new Button("Manager");
    private Button teamLeaderButton = new Button("Team Leader");
    private Button employeeButton = new Button("Employee");

    public Scene getScene() {
        return scene;
    }

    public Button getOwnerButton() {
        return ownerButton;
    }

    public Button getManagerButton() {
        return managerButton;
    }

    public Button getTeamLeaderButton() {
        return teamLeaderButton;
    }

    public Button getEmployeeButton() {
        return employeeButton;
    }

    /**
     * Generates Login Employee classes
     */
    public Authentication(){
        vBox = new VBox();
        text.setFont(font);
        vBox.setSpacing(10);
        vBox.setMargin(text, new Insets(20, 20, 0, 20));
        vBox.setMargin(ownerButton, new Insets(0, 20, 0, 20));
        vBox.setMargin(managerButton, new Insets(0, 20, 0, 20));
        vBox.setMargin(teamLeaderButton, new Insets(0, 20, 0, 20));
        vBox.setMargin(employeeButton, new Insets(0, 20, 20, 20));
        vBox.getChildren().addAll(text, ownerButton, managerButton, teamLeaderButton, employeeButton);
        scene = new Scene(vBox);
    }
}
