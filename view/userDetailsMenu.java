package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class userDetailsMenu {

    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    public DatePicker getBirthDateDatePicker() {
        return birthDateDatePicker;
    }

    public TextField getAddressTextField() {
        return addressTextField;
    }

    public TextField getPSCTextField() {
        return PSCTextField;
    }

    public TextField getTownTextField() {
        return townTextField;
    }

    public TextField getCountryTextField() {
        return countryTextField;
    }

    private Font normalFont =  Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 14);
    private Text  firstName = new Text("First Name");
    private TextField firstNameTextField = new TextField();
    private Text lastName = new Text("Last name");
    private TextField lastNameTextField = new TextField();
    private Text birthDate = new Text("Date of birth");
    private DatePicker birthDateDatePicker = new DatePicker();
    private Text address = new Text("Address");
    private TextField addressTextField = new TextField();
    private Text PSC = new Text("PSC");
    private TextField PSCTextField = new TextField();
    private Text town = new Text("Town");
    private TextField townTextField = new TextField();
    private Text country = new Text("Country");
    private TextField countryTextField = new TextField();
    private Button addButton = new Button("Add");
    private CheckBox employeeBox;
    private CheckBox managerBox;
    private CheckBox teamLeaderBox;

    private VBox vbox;
    private Scene scene;

    public Scene getScene(){
        return scene;
    }

    /**
     * Helper function
     * @param textList
     * @param objectList
     * @param vbox
     */
    private void adder(List<Text> textList, List<Object> objectList, VBox vbox){

        HBox hBox = new HBox();
        for(int i = 0; i < textList.size(); i++){
            GridPane gridPane = new GridPane();
            gridPane.setVgap(5);
            gridPane.setHgap(10);
            textList.get(i).setFont(normalFont);
            gridPane.add(textList.get(i), 0, 0);
            gridPane.add((Node) objectList.get(i), 0, 1);
            hBox.getChildren().add(gridPane);
            hBox.setSpacing(10);
        }
        vbox.getChildren().add(hBox);
    }

    public VBox getVbox() {
        return vbox;
    }

    public Button getAddButton() {
        return addButton;
    }

    public CheckBox getEmployeeBox() {
        return employeeBox;
    }

    public CheckBox getManagerBox() {
        return managerBox;
    }

    public CheckBox getTeamLeaderBox() {
        return teamLeaderBox;
    }

    /**
     * Helper function to return selected position of employee
     * @param managerBox
     * @param teamLeaderBox
     * @param employeeBox
     * @return
     */
    public String getPos(CheckBox managerBox, CheckBox teamLeaderBox, CheckBox employeeBox){
        if(!employeeBox.isSelected() && managerBox.isSelected()){
            return managerBox.getText();
        }else if(!employeeBox.isSelected() && teamLeaderBox.isSelected()){
            return teamLeaderBox.getText();
        }else {
            return employeeBox.getText();
        }
    }

    /**
     * Employee register form
     * @param registeringOwner
     */
    public userDetailsMenu(boolean registeringOwner){
        vbox = new VBox();
        vbox.setSpacing(10);
        if(!registeringOwner) {
            employeeBox = new CheckBox("Employee");
            employeeBox.setSelected(true);
            teamLeaderBox = new CheckBox("Team Leader");
            managerBox = new CheckBox("Manager");
            vbox.getChildren().add(employeeBox);
            vbox.getChildren().add(teamLeaderBox);
            vbox.getChildren().add(managerBox);
        }
        List<Text> textList = new ArrayList<Text>();
        List<Object> objectList = new ArrayList<Object>();

        textList.add(firstName);
        textList.add(lastName);
        objectList.add(firstNameTextField);
        objectList.add(lastNameTextField);
        adder(textList, objectList, vbox);
        textList.clear(); objectList.clear();
        textList.add(birthDate);
        textList.add(address);
        objectList.add(birthDateDatePicker);
        objectList.add(addressTextField);
        adder(textList, objectList, vbox);
        textList.clear(); objectList.clear();

        PSCTextField.setPrefWidth(55);
        textList.add(PSC);
        textList.add(town);
        textList.add(country);
        objectList.add(PSCTextField);
        objectList.add(townTextField);
        objectList.add(countryTextField);
        adder(textList, objectList, vbox);
        textList.clear(); objectList.clear();

        if(!registeringOwner) {
            vbox.getChildren().addAll(addButton);
        }
        scene = new Scene(vbox);
    }

}
