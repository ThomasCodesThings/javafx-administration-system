package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class LoginForm {

    private Scene scene;
    private GridPane gridPane = new GridPane();
    private Font defaultFont = Font.font("System", FontWeight.NORMAL, 20);
    private Text welcomeText = new Text("Login");
    private Text usernameText = new Text("Username");
    private TextField usernameTextField = new TextField();
    private Text passwordText = new Text("Password");
    private PasswordField passwordField = new PasswordField();
    private Button signButton = new Button("Sign in");

    public Scene getScene() {
        return scene;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getSignButton() {
        return signButton;
    }

    /**
     * Generates Login Box for Owner
     */
    public LoginForm(){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        welcomeText.setFont(defaultFont);
        gridPane.add(welcomeText, 0, 0, 2, 1);
        gridPane.add(usernameText, 0, 1);

        gridPane.add(usernameTextField, 1, 1);

        gridPane.add(passwordText, 0, 2);
        gridPane.add(passwordField, 1, 2);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(signButton);
        gridPane.add(hBox, 1, 4);
        scene = new Scene(gridPane);
    }
}
