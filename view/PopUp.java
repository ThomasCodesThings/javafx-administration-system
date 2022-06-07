package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

public abstract class PopUp {

    public static void displayAlert(){
        Alert alert;
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Logout and login to save changes!");
        alert.show();
    }
}
