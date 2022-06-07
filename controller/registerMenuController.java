package controller;

import javafx.stage.Stage;
import model.infrastructure.Employee;
import model.infrastructure.Manager;
import model.infrastructure.TeamLeader;
import view.registerMenu;
import view.userDetailsMenu;

import java.util.ArrayList;
import java.util.List;

public class registerMenuController {
    private Stage stage;
    private registerMenuController registerMenuController;
    private List<Employee> employeeList = new ArrayList<Employee>();
    private List<Manager> managerList = new ArrayList<>();
    private List<TeamLeader> teamLeaders = new ArrayList<>();

    public List<Manager> getManagerList() {
        return managerList;
    }

    public List<TeamLeader> getTeamLeaders() {
        return teamLeaders;
    }

    public Stage getStage() {
        return stage;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public registerMenuController(registerMenu registerMenu){
        registerMenu.getAddNewEmployeeButton().setOnAction(event -> {
            stage = new Stage();
            userDetailsMenu userDetailsMenu = new userDetailsMenu(false);
            userDetailsMenuController userDetailsMenuController = new userDetailsMenuController(userDetailsMenu , stage, employeeList, teamLeaders, managerList, registerMenu.getEmployeeTableView());
            stage.setScene(userDetailsMenuController.getUserDetailsMenuScene());
            stage.setTitle("Add new employee");
            stage.show();
        });
    }
}
