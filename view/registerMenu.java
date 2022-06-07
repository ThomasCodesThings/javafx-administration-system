package view;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.infrastructure.Employee;

import java.util.ArrayList;
import java.util.List;


public class registerMenu {
    private VBox vBox = new VBox();

    private Font paragraphFont = Font.font("System", FontWeight.LIGHT, FontPosture.REGULAR, 16);
    private Font normalFont =  Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 14);
    private Text patisserieMenu = new Text("Patisserie´s menu");
    private Text patisserieName = new Text("Patisserie name");
    private TextField patisserieNameTextField = new TextField();
    private Text patisserieAddress = new Text("Address");
    private TextField patisserieAddressTextField = new TextField();
    private Text patisseriePSC = new Text("PSC");
    private TextField patisseriePSCTextField = new TextField();
    private Text patisserieTown = new Text("Town");
    private TextField patisserieTownTextField = new TextField();
    private Text patisserieCountry = new Text("Country");
    private TextField patisserieCountryTextField = new TextField();

    private Text ownerDetails = new Text("Patisserie´s owner details");
    private Text employeeListText = new Text("Patisserie´s list of employees");

    private Button addNewEmployee = new Button("Add new employee");

    private Text ownerLoginDetails = new Text("Patisserie´s owner login details");
    private Text ownerUsername = new Text("Username");
    private TextField ownerUsernameTextField = new TextField();
    private Text ownerPassword = new Text("Password");
    private PasswordField ownerPasswordField = new PasswordField();
    private Text ownerConfirmPassword = new Text("Confirm password");
    private PasswordField ownerConfirmPasswordField = new PasswordField();
    private Button confirmButton = new Button("Confirm");

    private VBox ownerRegisterForm;
    private TableView<Employee> employeeTableView;

    public TableView<Employee> getEmployeeTableView() {
        return employeeTableView;
    }

    public void setEmployeeTableView(TableView<Employee> employeeTableView) {
        this.employeeTableView = employeeTableView;
    }
    public Button getConfirmButton() {
        return confirmButton;
    }

    public VBox getOwnerRegisterForm() {
        return ownerRegisterForm;
    }

    private Scene scene;
    private void adder(List<Text> textList, List<Object>  textFieldList, VBox vbox){
        HBox hBox = new HBox();
        for(int i = 0; i < textList.size(); i++){
            GridPane gridPane = new GridPane();
            gridPane.setVgap(5);
            gridPane.setHgap(10);
            textList.get(i).setFont(normalFont);
            gridPane.add(textList.get(i), 0, 0);
            gridPane.add((Node) textFieldList.get(i), 0, 1);
            hBox.getChildren().add(gridPane);
            hBox.setSpacing(10);
        }
        vbox.getChildren().add(hBox);
    }


    private Line liner(){
        Line line = new Line();
        line.setScaleY(0.5);
        line.setStartX(0);
        line.setEndX(400);
        line.setStartY(0);
        return line;
    }

    public Button getAddNewEmployeeButton() {
        return addNewEmployee;
    }

    public TextField getPatisserieNameTextField() {
        return patisserieNameTextField;
    }

    public TextField getPatisserieAddressTextField() {
        return patisserieAddressTextField;
    }

    public TextField getPatisseriePSCTextField() {
        return patisseriePSCTextField;
    }

    public TextField getPatisserieTownTextField() {
        return patisserieTownTextField;
    }

    public TextField getPatisserieCountryTextField() {
        return patisserieCountryTextField;
    }

    public TextField getOwnerUsernameTextField() {
        return ownerUsernameTextField;
    }

    public PasswordField getOwnerPasswordField() {
        return ownerPasswordField;
    }

    public PasswordField getOwnerConfirmPasswordField() {
        return ownerConfirmPasswordField;
    }

    public registerMenu(){

        vBox.setSpacing(10);
        patisserieMenu.setFont(paragraphFont);

        patisseriePSCTextField.setPrefWidth(55);

        vBox.getChildren().add(patisserieMenu);
        List<Text> textList = new ArrayList<Text>();
        List<Object> textFieldList = new ArrayList<Object>();

        textList.add(patisserieName);
        textList.add(patisserieAddress);
        textFieldList.add(patisserieNameTextField);
        textFieldList.add(patisserieAddressTextField);
        adder(textList, textFieldList, vBox);
        textList.clear(); textFieldList.clear();

        textList.add(patisseriePSC);
        textList.add(patisserieTown);
        textList.add(patisserieCountry);
        textFieldList.add(patisseriePSCTextField);
        textFieldList.add(patisserieTownTextField);
        textFieldList.add(patisserieCountryTextField);
        adder(textList, textFieldList, vBox);
        textList.clear(); textFieldList.clear();


        vBox.getChildren().add(liner());

        ownerDetails.setFont(paragraphFont);
        vBox.getChildren().add(ownerDetails);

        ownerRegisterForm = new userDetailsMenu(true).getVbox();
        vBox.getChildren().add(ownerRegisterForm);
        vBox.getChildren().add(liner());

        employeeListText.setFont(paragraphFont);
        vBox.getChildren().add(employeeListText);

        employeeTableView = new employeeListView().getEmployeeTable();
        vBox.getChildren().add(employeeTableView);
        vBox.getChildren().add(addNewEmployee);

        vBox.getChildren().add(liner());

        ownerLoginDetails.setFont(paragraphFont);
        vBox.getChildren().addAll(ownerLoginDetails);

        textList.add(ownerUsername);
        textFieldList.add(ownerUsernameTextField);
        adder(textList, textFieldList, vBox);
        textList.clear(); textFieldList.clear();

        textList.add(ownerPassword);
        textList.add(ownerConfirmPassword);
        textFieldList.add(ownerPasswordField);
        textFieldList.add(ownerConfirmPasswordField);
        adder(textList, textFieldList, vBox);
        textList.clear(); textFieldList.clear();

        vBox.getChildren().addAll(confirmButton);

        scene = new Scene(vBox);
    }

    public Scene getScene() {
        return scene;
    }
}

