package view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.infrastructure.Employee;

public class employeeListView {

    private TableView employeeTable;
    private TableColumn<Employee, String> employeePosition = new TableColumn<>("Position");
    private TableColumn<Employee, Integer> employeeID = new TableColumn<>("ID");
    private TableColumn<Employee, String> employeeFirstName = new TableColumn<>("First Name");
    private TableColumn<Employee, String> employeeLastName = new TableColumn<>("Last Name");
    private TableColumn<Employee, Integer> employeeAge= new TableColumn<>("Age");

    public TableView getEmployeeTable(){
        return employeeTable;
    }

    /**
     * Generates Table to display Employee Details
     */
        public employeeListView(){
        employeeTable = new TableView();
        employeePosition.setCellValueFactory(new PropertyValueFactory<>("position"));
            employeePosition.setPrefWidth(80);
            employeeID.setCellValueFactory(new PropertyValueFactory<>("personalID"));
            employeeID.setPrefWidth(70);
            employeeFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            employeeFirstName.setPrefWidth(100);
            employeeLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            employeeLastName.setPrefWidth(100);
            employeeAge.setCellValueFactory(new PropertyValueFactory<>("age"));
            employeeAge.setPrefWidth(50);
            employeeTable.getColumns().addAll(employeePosition, employeeID, employeeFirstName, employeeLastName, employeeAge);
            employeeTable.setPrefWidth(employeePosition.getPrefWidth() + employeeID.getPrefWidth() + employeeFirstName.getPrefWidth() + employeeLastName.getPrefWidth() + employeeAge.getPrefWidth());
            employeeTable.setMaxHeight(250);
        }
}
