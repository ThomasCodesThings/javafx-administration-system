package controller;

import javafx.stage.Stage;
import model.database.PatisserieDatabase;
import model.infrastructure.*;
import view.PatisserieMenu;
import view.TeamCreateWindow;

import java.util.ArrayList;
import java.util.List;

import static view.PopUp.displayAlert;

public class TeamCreateWindowController {
    private TeamCreateWindow teamCreateWindow;
    private PatisserieMenu patisserieMenu;
    private Patisserie patisserie;
    TeamCreateWindowController(TeamCreateWindow teamCreateWindow, Patisserie patisserie, PatisserieMenu patisserieMenu){
        this.patisserieMenu = patisserieMenu;
        this.teamCreateWindow = teamCreateWindow;
        this.patisserie = patisserie;
        fillComboBox(patisserie);
        fillTable(patisserie);
        Stage stage = new Stage();
        teamCreateWindow.getConfirmButton().setOnAction(e -> newTeam(stage));
        stage.setScene(teamCreateWindow.getScene());
        stage.setTitle("New Team");
        stage.show();
    }
    void newTeam(Stage stage){
        List<Employee> memberList = new ArrayList<>();
        for(int i = 0; i < teamCreateWindow.getTableView().getItems().size(); i++){
            Employee employee = (Employee) teamCreateWindow.getTableView().getItems().get(i);
            if(employee.isSelected()){
                memberList.add(employee);
            }
        }

        if(memberList.size() != 0){
            String teamName = teamCreateWindow.getTeamNameTextField().getText();
            String teamLeaderNameAndSurname = (String) teamCreateWindow.getTeamLeaderBox().getSelectionModel().getSelectedItem();
            TeamLeader teamLeader = null;
            for(TeamLeader tempLeader : patisserie.getTeamLeaders()){
                if(tempLeader.toString().equals(teamLeaderNameAndSurname)){
                    teamLeader = tempLeader;
                }
            }
            if(teamLeader != null) {
                Team team = new Team(teamLeader, teamName);
                teamLeader.setTeam(team);
                team.setTeammemberList(memberList);
                Owner owner = patisserie.getOwner();
                owner.addTeam(team);
                PatisserieDatabase.save();
                stage.close();
            }
            //patisserieMenu.teamTableCreate(patisserie.getTeamList().size()); //TODO here
        }
        PatisserieDatabase.save();
    }
    void fillComboBox(Patisserie patisserie){
        for(TeamLeader teamLeader : patisserie.getTeamLeaders()){
            teamCreateWindow.getTeamLeaderBox().getItems().add(teamLeader.toString());
        }
    }
    void fillTable(Patisserie patisserie){
        for(Employee employee : patisserie.getEmployeeList()){
            teamCreateWindow.getTableView().getItems().add(employee);
        }
    }
}
