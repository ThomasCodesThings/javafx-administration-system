package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TeamDetails {
    private Scene scene;
    private Font headerFont = Font.font("System", FontWeight.NORMAL, 20);
    private Font textFont = Font.font("System", FontWeight.NORMAL, 16);
    private TableView tableView = new TableView();
    private Button addButton = new Button("Add new member");
    private Button removeButton = new Button("Remove");
    private VBox vBox;

    public TableView getTableView() {
        return tableView;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Scene getScene() {
        return scene;
    }

    public VBox getvBox() {
        return vBox;
    }

    public TeamDetails(){
        vBox = new VBox();
        vBox.setSpacing(5);
        Text text = new Text("Team details");
        text.setFont(headerFont);
        vBox.getChildren().add(text);
        Text normalText = new Text("Team name");
        normalText.setFont(textFont);
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().add(normalText);
        normalText = new Text();
        hBox.getChildren().add(normalText);
        vBox.getChildren().add(hBox);
        hBox = new HBox();
        hBox.setSpacing(10);
        normalText = new Text("Team Leader");
        normalText.setFont(textFont);
        hBox.getChildren().add(normalText);
        normalText = new Text();
        hBox.getChildren().add(normalText);
        vBox.getChildren().add(hBox);
        tableView = new employeeListView().getEmployeeTable();
        vBox.getChildren().add(tableView);
        hBox = new HBox();
        hBox.setSpacing(tableView.getPrefWidth() - 100);
        hBox.getChildren().addAll(addButton, removeButton);
        vBox.getChildren().add(hBox);
        scene = new Scene(vBox);
    }
}
