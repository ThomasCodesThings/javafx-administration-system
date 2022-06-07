package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;


public class SupplyWindow {
    private Scene scene;
    private ListView listView = new ListView();
    private Button confirmButton = new Button("Confirm");

    public Scene getScene() {
        return scene;
    }

    public ListView getListView() {
        return listView;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public SupplyWindow(){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(listView,confirmButton);
        scene = new Scene(vBox);
    }
}
