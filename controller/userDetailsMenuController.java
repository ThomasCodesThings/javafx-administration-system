package controller;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.infrastructure.Employee;
import model.infrastructure.Manager;
import model.Residence;
import model.infrastructure.TeamLeader;
import view.userDetailsMenu;

import java.util.List;

import static model.utilities.Utilitties.calculatePersonAge;

public class userDetailsMenuController {
    public Scene getUserDetailsMenuScene() {
        return userDetailsMenuScene;
    }
    Scene userDetailsMenuScene;
    private Employee createdEmployee;

    /**
     * Function for adding new Employee
     * @param userDetailsMenu
     * @param stage
     * @param employeeList
     * @param teamLeaders
     * @param managers
     * @param employeeTableView
     */
    //pridavanie noveho pouzivatela
    public userDetailsMenuController(userDetailsMenu userDetailsMenu, Stage stage, List<Employee> employeeList, List<TeamLeader> teamLeaders, List<Manager> managers, TableView<Employee> employeeTableView){
        userDetailsMenuScene = userDetailsMenu.getScene();
        userDetailsMenu.getAddButton().setOnAction(event -> {
            String employeePosition = userDetailsMenu.getPos(userDetailsMenu.getManagerBox(), userDetailsMenu.getTeamLeaderBox(), userDetailsMenu.getEmployeeBox()); //position = getPos(managerBox, teamLeaderBox, employeeBox);
            if(employeePosition != null && userDetailsMenu.getBirthDateDatePicker() != null){
                String newEmployeeFirstName = userDetailsMenu.getFirstNameTextField().getText();
                String newEmployeeLastName = userDetailsMenu.getLastNameTextField().getText();
                int newEmployeeAge = calculatePersonAge(userDetailsMenu.getBirthDateDatePicker());
                Residence newEmployeeResidence = new Residence(userDetailsMenu.getAddressTextField().getText(), userDetailsMenu.getPSCTextField().getText(), userDetailsMenu.getTownTextField().getText(), userDetailsMenu.getCountryTextField().getText());
                Employee newEmployee = new Employee(newEmployeeFirstName, newEmployeeLastName, newEmployeeAge);
                newEmployee.setPosition(employeePosition);
                newEmployee.setResidence(newEmployeeResidence);
                if(employeePosition.equals("Employee")){
                    employeeList.add(newEmployee);
                }else if(employeePosition.equals("Team Leader")){
                    TeamLeader teamLeader = new TeamLeader(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getAge());
                    teamLeader.setResidence(newEmployee.getResidence());
                    teamLeader.setPosition("Team Leader");
                    teamLeaders.add(teamLeader);
                }else{
                    Manager manager = new Manager(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getAge());
                    manager.setResidence(newEmployee.getResidence());
                    manager.setPosition("Manager");
                    managers.add(manager);
                }
                employeeTableView.getItems().add(newEmployee);
                stage.close();
            }
        });
    }

    public void updateTable(List<Employee> employeeList){
        for(Employee employee : employeeList){

        }
    }

}
