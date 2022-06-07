package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PatisserieMainGUI{
    private AnchorPane anchorPane = new AnchorPane();
    private Text mainText = new Text("MyPatisserie");
    private Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 21);
    private ListView listView = new ListView();
    private Button createButton = new Button("Create new");
    private Button removeButton = new Button("Remove");
    private Button loadButton = new Button("Load Patisserie");
    private Scene scene;
    public ListView getListView() {
        return listView;
    }


    public Scene getScene() {
        return scene;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    /**
     * Generates main gui
     */
    public PatisserieMainGUI(){
        //listView = new ListView();
        mainText.setFont(font);
        mainText.setY(font.getSize());
        listView.setPrefHeight(20);
        listView.setPrefWidth(200);
        anchorPane.setPrefHeight(400.0);
        anchorPane.setPrefWidth(600.0);
        loadButton.setLayoutX((anchorPane.getPrefWidth()/2) - 64.0);
        AnchorPane.setTopAnchor(listView, 32.0);
        AnchorPane.setLeftAnchor(listView, 32.0);
        AnchorPane.setRightAnchor(listView, 32.0);
        AnchorPane.setBottomAnchor(listView, 64.0);
        AnchorPane.setLeftAnchor(createButton, 32.0);
        AnchorPane.setBottomAnchor(createButton, 32.0);
        AnchorPane.setRightAnchor(removeButton, 32.0);
        AnchorPane.setBottomAnchor(removeButton, 32.0);
        AnchorPane.setBottomAnchor(loadButton, 32.0);
        anchorPane.getChildren().addAll(mainText, listView, createButton, removeButton, loadButton);
        scene = new Scene(anchorPane);
    }


}
