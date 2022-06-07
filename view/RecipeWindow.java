package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RecipeWindow {

    private Scene scene;
    private VBox vBox;
    private Button addButton = new Button("Add more");
    private Button createButton = new Button("Create");
    private Button showButton = new Button("Show list of available products");
    private Button backButton = new Button("Back");
    private ComboBox comboBox = new ComboBox();

    public Button getBackButton() {
        return backButton;
    }

    public Button getShowButton() {
        return showButton;
    }

    public ComboBox getComboBox() {
        return comboBox;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public Scene getScene() {
        return scene;
    }

    public VBox getvBox() {
        return vBox;
    }

    public RecipeWindow(){
        vBox = new VBox();
        vBox.setSpacing(10);
        Text mainText = new Text("Recipe crafting");
        mainText.setFont(new Font(20));
        Text text = new Text("Ingredient");
        text.setFont(new Font(16));
        HBox titleBox = new HBox();
        titleBox.setSpacing(10);
        titleBox.getChildren().add(text);
        text = new Text("Amount");
        text.setFont(new Font(16));
        titleBox.getChildren().add(text);
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(addButton, createButton);
        buttonBox.setSpacing(10);
        vBox.getChildren().addAll(mainText, showButton, comboBox, buttonBox, titleBox);
        scene = new Scene(vBox);
    }

    public HBox createRecipeLine(){
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        ComboBox comboBox = new ComboBox();
        TextField textField = new TextField();
        Button deleteButton = new Button("X");
        hBox.getChildren().addAll(comboBox, textField, deleteButton);
        return hBox;
    }

    public VBox createList(){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        Text text = new Text("Products");
        text.setFont(new Font(18));
        VBox tempBox = new VBox();
        tempBox.setSpacing(5);
        vBox.getChildren().addAll(text, tempBox, backButton);
        return vBox;
    }
}
