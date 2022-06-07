package controller;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.database.PatisserieDatabase;
import model.infrastructure.Employee;
import model.infrastructure.Patisserie;
import model.infrastructure.Team;
import model.infrastructure.TeamLeader;
import view.TeamDetails;
import view.TeamMemberWindow;

import java.util.List;

public class TeamDetailsController {

    private Stage stage = new Stage();
    private TeamDetails teamDetails;
    private Team team;
    private List<Employee> employeeList;
    private Patisserie patisserie;
    public TeamDetailsController(TeamDetails teamDetails, Team team, List<Employee> employeeList, Patisserie patisserie){
        this.teamDetails = teamDetails;
        this.team = team;
        this.employeeList = employeeList;
        this.patisserie = patisserie;
        load();
        teamDetails.getAddButton().setOnAction(event -> {
            add();
        });
        teamDetails.getRemoveButton().setOnAction(event -> {
            int index = teamDetails.getTableView().getSelectionModel().getSelectedIndex();
            team.getTeammemberList().remove(index);
            teamDetails.getTableView().getItems().remove(index);
        });
        stage.setScene(teamDetails.getScene());
        stage.setTitle(team.getTeamName());
        stage.show();
    }

    void load(){
        ((Text)((HBox) teamDetails.getvBox().getChildren().get(1)).getChildren().get(1)).setText(team.getTeamName());
        ((Text)((HBox) teamDetails.getvBox().getChildren().get(2)).getChildren().get(1)).setText(team.getTeamLeader().getFirstName() + " " + team.getTeamLeader().getLastName());
        for(Employee employee : team.getTeammemberList()){
            teamDetails.getTableView().getItems().add(employee);
        }
    }
    boolean exist(Employee employee, List<Employee> employeeSecondList){
            if(employee.getPosition().equals("Employee")){
                for(Employee secondEmployee : employeeSecondList){
                    if(employee.getFirstName().equals(secondEmployee.getFirstName()) && employee.getLastName().equals(secondEmployee.getLastName()) && (employee.getAge() == secondEmployee.getAge())){
                        return true;
                    }
                }
            }
        return false;
    }
    void add(){
        TeamMemberWindow teamMemberWindow = new TeamMemberWindow();
        Stage stageWindow = new Stage();
        stageWindow.setScene(teamMemberWindow.getScene());
        stageWindow.setTitle("New Team member");
        stageWindow.show();
        for(Employee employee : employeeList){
            if(!exist(employee, team.getTeammemberList())){
                teamMemberWindow.getComboBox().getItems().add(employee.getFirstName() + " " + employee.getLastName());
            }
        }


            teamMemberWindow.getOkButton().setOnAction(event -> {
                Employee selectedEmployee = null;
                String str = (String) teamMemberWindow.getComboBox().getSelectionModel().getSelectedItem();
                String[] split = str.split(" ");
                for(Employee employee : patisserie.getEmployeeList()){
                    if(employee.getFirstName().equals(split[0]) && employee.getLastName().equals(split[1])){
                        selectedEmployee = employee;
                        break;
                    }
                }
                if(selectedEmployee != null) {
                    TeamLeader teamLeader = team.getTeamLeader();
                    teamLeader.add(selectedEmployee);
                    teamLeader.setTeam(team);
                    //team.getTeammemberList().add(selectedEmployee);
                    teamDetails.getTableView().getItems().add(selectedEmployee);
                    PatisserieDatabase.save();
                    stageWindow.close();
                }
        });
    }
}
