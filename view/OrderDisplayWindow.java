package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Residence;
import model.customer.*;
import model.infrastructure.Person;


public class OrderDisplayWindow {
    private Scene scene;
    private TableView tableView = new TableView();
    private TableColumn<Order, String> orderID = new TableColumn<>("ID");
    private TableColumn<Order, String> orderStatus = new TableColumn<>("Status");
    private TableColumn<Order, String> orderDate = new TableColumn<>("Date");
    private TableColumn<Order, String> customerFirstName = new TableColumn<>("First Name");
    private TableColumn<Order, String> customerLastName = new TableColumn<>("Last Name");
    private TableColumn<Order, String> customerAddress = new TableColumn<>("Address");
    private TableColumn<Order, String> customerPSC = new TableColumn<>("PSC");
    private TableColumn<Order, String> customerTown = new TableColumn<>("Town");
    private TableColumn<Order, String> customerCountry = new TableColumn<>("Country");
    private TableColumn<Order, String> customerNumber = new TableColumn<>("Telephone Number");
    private TableColumn<Order, String> customerEmail = new TableColumn<>("Email");
    private Button showButton = new Button("Show order products");
    private Button removeButton = new Button("Remove");
    private Button okButton = new Button("OK");

    public Scene getScene() {
        return scene;
    }

    public TableView getTableView() {
        return tableView;
    }

    public Button getShowButton() {
        return showButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Button getOkButton() {
        return okButton;
    }

    /**
     * Table as part of order details
     */
    public OrderDisplayWindow(){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        orderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        orderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        customerFirstName.setCellValueFactory((cell) -> new ReadOnlyStringWrapper(cell.getValue().getCustomer().getFirstName())); // Bindings.selectString(cell.getValue().getCustomer().getFirstName()));
        customerLastName.setCellValueFactory((cell) -> new ReadOnlyStringWrapper(cell.getValue().getCustomer().getLastName()));
        customerAddress.setCellValueFactory((cell) -> new ReadOnlyStringWrapper(cell.getValue().getCustomer().getResidence().getAddress()));
        customerPSC.setCellValueFactory((cell) -> new ReadOnlyStringWrapper(cell.getValue().getCustomer().getResidence().getPSC()));
        customerTown.setCellValueFactory((cell) -> new ReadOnlyStringWrapper(cell.getValue().getCustomer().getResidence().getTown()));
        customerCountry.setCellValueFactory((cell) -> new ReadOnlyStringWrapper(cell.getValue().getCustomer().getResidence().getCountry()));
        customerEmail.setCellValueFactory((cell) -> new ReadOnlyStringWrapper(cell.getValue().getCustomer().getEmail()));
        customerNumber.setCellValueFactory((cell) -> new ReadOnlyStringWrapper(cell.getValue().getCustomer().getTelephoneNumber()));
        tableView.getColumns().addAll(orderID,orderStatus,orderDate,customerFirstName,customerLastName,customerAddress,customerPSC,customerTown,customerCountry,customerNumber,customerEmail);
        HBox hBox = new HBox();
        hBox.setSpacing(100);
        hBox.getChildren().addAll(showButton, removeButton);
        vBox.getChildren().addAll(tableView, hBox, okButton);
        scene = new Scene(vBox);
    }
}
