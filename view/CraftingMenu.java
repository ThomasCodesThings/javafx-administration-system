package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CraftingMenu {
    private Scene scene;
    private ComboBox comboBox = new ComboBox();
    private TextField amountField = new TextField();
    private Button craftButton = new Button("Craft");

    public Scene getScene() {
        return scene;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public TextField getAmountField() {
        return amountField;
    }

    public Button getCraftButton() {
        return craftButton;
    }

    /**
     * Generates Crafting menu
     */
    public CraftingMenu(){
        VBox vBox = new VBox();
        Text text = new Text("Select item to craft");
        text.setFont(new Font(20));
        vBox.setSpacing(5);
        vBox.getChildren().addAll(text, comboBox);
        text = new Text("Enter amount");
        text.setFont(new Font(20));
        vBox.getChildren().addAll(text, amountField, craftButton);
        scene = new Scene(vBox);
    }
}
