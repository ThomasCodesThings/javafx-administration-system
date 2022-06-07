package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
    private Scene scene;
    private Text mainText = new Text("Order details");
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
    private Text orderItemsText = new Text();
    private Font font = new Font(14);
    private Button okButton = new Button("OK");
    private Button cancelButton = new Button("Cancel Order");
    private Text date = new Text("Creation date");
    private TextField dateField = new TextField();
    private Text status = new Text("Status");
    private TextField statusTextField = new TextField();
    private VBox orderItemsVBox = new VBox();

    public VBox getOrderItemsVBox() {
        return orderItemsVBox;
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getStatusTextField() {
        return statusTextField;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public TextField getDateField() {
        return dateField;
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

    public Button getOkButton() {
        return okButton;
    }

    /**
     * Generates window for displaying order details
     */
    public OrderDetails(){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        mainText.setFont(new Font(18));
        firstName.setFont(font);
        firstNameTextField.setFont(font);
        firstNameTextField.setEditable(false);
        lastName.setFont(font);
        lastNameTextField.setFont(font);
        lastNameTextField.setEditable(false);
        telephone.setFont(font);
        telephoneNumberTextField.setFont(font);
        telephoneNumberTextField.setEditable(false);
        email.setFont(font);
        emailTextField.setFont(font);
        emailTextField.setEditable(false);
        address.setFont(font);
        addressTextField.setFont(font);
        addressTextField.setEditable(false);
        PSC.setFont(font);
        PSCTextField.setFont(font);
        PSCTextField.setEditable(false);
        town.setFont(font);
        townTextField.setFont(font);
        townTextField.setEditable(false);
        country.setFont(font);
        countryTextField.setFont(font);
        countryTextField.setEditable(false);
        Text text = new Text("Order Items");
        text.setFont(new Font(16));
        date.setFont(font);
        dateField.setFont(font);
        dateField.setEditable(false);
        status.setFont(new Font(15));
        statusTextField.setFont(new Font(15));
        statusTextField.setEditable(false);
        vBox.getChildren().addAll(mainText, detailsTable(), text, orderItemsVBox, okButton, cancelButton);
        scene = new Scene(vBox);
    }

    private void adder(List<Text> textList, List<TextField> textFieldList, VBox vbox){
        HBox hBox = new HBox();
        for(int i = 0; i < textList.size(); i++){
            GridPane gridPane = new GridPane();
            gridPane.setVgap(5);
            gridPane.setHgap(10);
            gridPane.add(textList.get(i), 0, 0);
            gridPane.add((Node) textFieldList.get(i), 0, 1);
            hBox.getChildren().add(gridPane);
            hBox.setSpacing(10);
        }
        vbox.getChildren().add(hBox);
    }

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
        textList.clear(); textFieldList.clear();

        textList.add(date);
        textFieldList.add(dateField);
        adder(textList, textFieldList, vBox);
        textList.clear(); textFieldList.clear();

        textList.add(status);
        textFieldList.add(statusTextField);
        adder(textList, textFieldList, vBox);
        return vBox;
    }
}
