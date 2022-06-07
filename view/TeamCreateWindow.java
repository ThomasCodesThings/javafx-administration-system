package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import model.infrastructure.Employee;

public class TeamCreateWindow {
    private Scene scene;
    VBox vBox = new VBox();
    private Font font = Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 17);
    private Text teamLeader = new Text("Team Leader");
    private ComboBox teamLeaderBox = new ComboBox();
    private Text teamName = new Text("Name of team");
    private TableColumn<Employee, String> employeeFirstName = new TableColumn<>("First Name");
    private TableColumn<Employee, String> employeeLastName = new TableColumn<>("Last Name");
    private TableColumn<Employee, Integer> employeeAge= new TableColumn<>("Age");
    private TableColumn<Employee, CheckBox> employeeSelected = new TableColumn("Selected");
    private TextField teamNameTextField = new TextField();
    private Text members = new Text("Members");
    private TableView tableView;
    private Button confirmButton = new Button("Confirm");

    public Scene getScene() {
        return scene;
    }

    public ComboBox getTeamLeaderBox() {
        return teamLeaderBox;
    }

    public TextField getTeamNameTextField() {
        return teamNameTextField;
    }

    public TableView getTableView() {
        return tableView;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    TableView createTable(){
        tableView = new TableView();
        employeeSelected.setCellValueFactory(arg0 -> { //https://stackoverflow.com/questions/7217625/how-to-add-checkboxs-to-a-tableview-in-javafx
            Employee employee = arg0.getValue();
            CheckBox checkBox = new CheckBox();
            checkBox.selectedProperty().setValue(employee.isSelected());
            checkBox.selectedProperty().addListener((ov, old_val, new_val) -> employee.setSelected(new_val));
            return new SimpleObjectProperty<>(checkBox);
        });
        //employeeSelected.setCellValueFactory(c -> new SimpleBooleanProperty(c.getValue().setSelected(true)));
        //employeeSelected.setCellValueFactory( f-> f.getValue().g)//new PropertyValueFactory<>("isSelected"));
        employeeSelected.setPrefWidth(60);
        employeeFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        employeeFirstName.setPrefWidth(100);
        employeeLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        employeeLastName.setPrefWidth(100);
        employeeAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        employeeAge.setPrefWidth(50);
        tableView.getColumns().addAll(employeeSelected, employeeFirstName, employeeLastName, employeeAge);
        tableView.setPrefWidth(employeeSelected.getPrefWidth() + employeeFirstName.getPrefWidth() + employeeLastName.getPrefWidth() + employeeAge.getPrefWidth());
        tableView.setMaxHeight(250);
        return tableView;
    }
    public TeamCreateWindow(){
        vBox.setSpacing(10);
        teamLeader.setFont(font);
        teamName.setFont(font);
        members.setFont(font);
        vBox.getChildren().add(teamLeader);
        vBox.getChildren().add(teamLeaderBox);
        vBox.getChildren().add(teamName);
        vBox.getChildren().add(teamNameTextField);
        vBox.getChildren().add(members);
        vBox.getChildren().add(createTable());
        vBox.getChildren().add(confirmButton);
        scene = new Scene(vBox);
    }
}
