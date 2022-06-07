package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.List;

public class PatisserieMenu {
    private Scene scene;
    private Font titleFont = Font.font("System", FontWeight.NORMAL, 21);
    private Font semiFont =  Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 17);
    private Font normalFont =  Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 14);
    private Text text = new Text();
    private VBox vBox = new VBox();
    private VBox secondPanel = new VBox();
    private HBox hBox = new HBox();
    private TableView employeeTable;
    private Button addEmployee = new Button("New");
    private Button removeEmployee = new Button("Remove");
    private int numberOfTeams;
    private Button createTeam = new Button("Create Team");
    private Button warehouseButton = new Button("Show warehouse");
    private Button orderButton = new Button("Show orders");
    private Button recipeButton = new Button("Recipes");
    private Button craftButton = new Button("Craft");
    private Button specialButton = new Button("Special button");
    private Text infoText = new Text();

    public Button getCraftButton() {
        return craftButton;
    }

    public Text getInfoText() {
        return infoText;
    }

    public Button getSpecialButton() {
        return specialButton;
    }

    public Button getRecipeButton() {
        return recipeButton;
    }

    public Button getWarehouseButton() {
        return warehouseButton;
    }

    public Button getOrderButton() {
        return orderButton;
    }

    public Button getCreateTeam() {
        return createTeam;
    }

    public Button getRemoveEmployee() {
        return removeEmployee;
    }

    public Button getAddEmployee() {
        return addEmployee;
    }

    public Scene getScene() {
        return scene;
    }

    public TableView getEmployeeTable() {
        return employeeTable;
    }

    public VBox getvBox() {
        return vBox;
    }

    public HBox gethBox() {
        return hBox;
    }

    public PatisserieMenu(TableView employeeTableView, int numberOfTeams){
        this.numberOfTeams = numberOfTeams;
        vBox.setSpacing(5);
        vBox.getChildren().add(detailsBox());
        Line line = new Line();
        employeeTable = employeeTableView;
        line.setEndX(employeeTableView.getPrefWidth());
        line.setStartY(100);
        line.setEndY(100);
        vBox.getChildren().add(line);
        vBox.getChildren().add(employeeTable);
        HBox buttonHBOX = new HBox();
        buttonHBOX.setSpacing(employeeTable.getPrefWidth() - 110);
        buttonHBOX.getChildren().add(addEmployee);
        buttonHBOX.getChildren().add(removeEmployee);
        vBox.getChildren().add(buttonHBOX);
        Text text = new Text("Patisserie Teams");
        text.setFont(semiFont);
        secondPanel.getChildren().add(text);
        secondPanel.setSpacing(5);
        secondPanel.getChildren().add(createTeam);
        secondPanel.getChildren().add(teamTableCreate(numberOfTeams));
        text = new Text("Warehouse");
        text.setFont(semiFont);
        secondPanel.getChildren().addAll(text, warehouseButton);
        text = new Text("Orders");
        text.setFont(semiFont);
        secondPanel.getChildren().addAll(text, orderButton);
        text = new Text("Recipes");
        text.setFont(semiFont);
        secondPanel.getChildren().addAll(text, recipeButton);
        text = new Text("Crafting");
        text.setFont(semiFont);
        secondPanel.getChildren().addAll(text, craftButton);
        text = new Text("Specials");
        text.setFont(semiFont);
        secondPanel.getChildren().addAll(text, specialButton);
        infoText.setFont(semiFont);
        infoText.setFill(Color.RED);
        secondPanel.getChildren().add(infoText);
        /*vBox.getChildren().add(buttonHBOX);
        vBox.getChildren().add(createTeam);
        vBox.getChildren().add(teamTableCreate(numberOfTeams));*/
        hBox.getChildren().add(vBox);
        hBox.setSpacing(10);
        hBox.getChildren().add(secondPanel);
        scene = new Scene(hBox);
    }

    public VBox teamTableCreate(int numberOfTeams){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        for(int i = 0; i < numberOfTeams; i++){
            HBox hBox = new HBox();
            hBox.setSpacing(20);
            Text text = new Text();
            text.setFont(normalFont);
            Button clickButton = new Button("Team details");
            hBox.getChildren().add(text);
            hBox.getChildren().add(clickButton);
            vBox.getChildren().add(hBox);
        }
        return vBox;
    }
    HBox hboxCreate(List<String > stringList){
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        int i = 0;
        for(String str : stringList){
            Text text;
            if(i % 2 == 0){
                text = new Text(stringList.get(i));
            }else{
                text = new Text();
            }
            i++;
            hBox.getChildren().add(text);
        }
        return hBox;
    }

    /**
     * Generates vbox
     * @return vbox
     */
    VBox detailsBox(){
        VBox vBox = new VBox();
        text.setText("Patisserie details");
        text.setFont(titleFont);
        vBox.getChildren().add(text);
        text = new Text();
        text.setFont(semiFont);
        text.setText("Patisserie");
        vBox.getChildren().add(text);
        text = new Text();
        text.setFont(normalFont);
        for(int i = 0; i < 3; i++){ //patisserie details
            text = new Text();
            vBox.getChildren().add(text);
        }
        text = new Text();
        text.setFont(semiFont);
        text.setText("Owner");
        vBox.getChildren().add(text);
        for(int i = 0; i < 4; i++){ //owner details
            text = new Text();
            text.setFont(normalFont);
            vBox.getChildren().add(text);
        }
        return vBox;
    }
}
