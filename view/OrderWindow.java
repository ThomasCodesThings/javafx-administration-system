package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.infrastructure.Patisserie;

import java.util.ArrayList;
import java.util.List;

public class OrderWindow {
    private Scene scene;
    private TableView patisserieTable;
    private TableColumn<Patisserie, String> patisserieName = new TableColumn<>("Patisserie´s name");
    private Text text = new Text("New order");
    private Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 21);
    private Button generateButton = new Button("Show available products");
    private Button confirmButton = new Button("Confirm");
    private VBox vBox;
    private VBox orderVBox;
    private Font normalFont =  Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 14);
    private Text  firstName = new Text("First Name");
    private TextField firstNameTextField = new TextField();
    private Text lastName = new Text("Last name");
    private TextField lastNameTextField = new TextField();
    private Text telephone = new Text("Telephone number");
    private TextField telephoneNumberTextField = new TextField();
    private Text email = new Text("Email");
    private TextField emailTextField = new TextField();
    private Text address = new Text("Address");
    private TextField addressTextField = new TextField();
    private Text PSC = new Text("PSC");
    private TextField PSCTextField = new TextField();
    private Text town = new Text("Town");
    private TextField townTextField = new TextField();
    private Text country = new Text("Country");
    private TextField countryTextField = new TextField();
    private int counter = 0;
    public Button getGenerateButton() {
        return generateButton;
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    public TextField getTelephoneNumberTextField() {
        return telephoneNumberTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
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

    public TableView getPatisserieTable() {
        return patisserieTable;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    public VBox getvBox() {
        return vBox;
    }

    public VBox getOrderVBox() {
        return orderVBox;
    }

    /**
     * Generates window where user can create put order details
     */
    public OrderWindow() {
        vBox = new VBox();
        vBox.setSpacing(5);
        text.setFont(font);
        patisserieTable = new TableView();
        patisserieName.setCellValueFactory(new PropertyValueFactory<>("patisserieName"));
        patisserieName.setPrefWidth(260);
        patisserieTable.getColumns().add(patisserieName);
        patisserieTable.setMaxHeight(150);
        orderVBox = new VBox();
        orderVBox.setSpacing(5);
        Text cartText = new Text("Cart");
        cartText.setFont(font);
        VBox buttonBox = new VBox();
        buttonBox.setSpacing(5);
        vBox.getChildren().addAll(text, detailsTable(),patisserieTable, generateButton, buttonBox, confirmButton, cartText, orderVBox);
        scene = new Scene(vBox);
    }

    /**
     * Helper function to generate Buttons
     * @param vBox
     * @param buttons
     * @param n
     * @param m
     */
    public void buttonTable(VBox vBox, Button[][] buttons, int n, int m){
        for(int i = 0; i < n; i++){
            HBox hBox = new HBox();
            hBox.setSpacing(5);
            for(int j = 0; j < m; j++){
                Button button = buttons[i][j];
                if(button != null) {
                    hBox.getChildren().add(buttons[i][j]);
                }else{
                    break;
                }
            }
            vBox.getChildren().add(hBox);
        }
    }

    /**
     * Helper Function to add textList and textFieldList to vbox
     * @param textList
     * @param textFieldList
     * @param vbox
     */
    private void adder(List<Text> textList, List<TextField> textFieldList, VBox vbox){
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

    /**
     * Function to generate VBox
     * @return vbox
     */
    VBox detailsTable(){
        VBox vBox = new VBox();
        vBox.setSpacing(5);

        List<Text> textList = new ArrayList<Text>();
        List<TextField> textFieldList = new ArrayList<>();
        textList.add(firstName);
        textList.add(lastName);
        textFieldList.add(firstNameTextField);
        textFieldList.add(lastNameTextField);
        adder(textList, textFieldList, vBox);
        textList.clear(); textFieldList.clear();

        textList.add(telephone);
        textList.add(email);
        textFieldList.add(telephoneNumberTextField);
        textFieldList.add(emailTextField);
        adder(textList, textFieldList, vBox);
        textList.clear(); textFieldList.clear();

        textList.add(address);
        textFieldList.add(addressTextField);
        adder(textList, textFieldList, vBox);
        textList.clear(); textFieldList.clear();

        textList.add(PSC);
        textList.add(town);
        textList.add(country);
        textFieldList.add(PSCTextField);
        textFieldList.add(townTextField);
        textFieldList.add(countryTextField);
        adder(textList, textFieldList, vBox);
        return vBox;
    }
}
