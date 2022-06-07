package view;

import javafx.scene.control.Alert;

public class BadLoginDetails {
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    public BadLoginDetails(boolean noMatch){
        if(noMatch) {
            alert.setTitle("Error!");
            alert.setContentText("Incorrect username or password!");
        }else{
            alert.setTitle("Error!");
            alert.setContentText("Incorrect password");
        }
    }

    public void display(){
        alert.show();
    }
}
