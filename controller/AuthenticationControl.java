package controller;

import javafx.stage.Stage;
import model.infrastructure.*;
import model.ingredients.Chocolate;
import model.ingredients.Milk;
import model.utilities.ThreadRunner;
import view.*;

import java.util.List;

public class AuthenticationControl {
    private Authentication authentication;
    private PatisserieMainGUI patisserieMainGUIHelper;
    private LoginForm loginForm;
    private List<Patisserie> patisserieList;

    /**
     * Authentication control
     * @param patisserieMainGUIHelper
     * @param loginForm
     * @param patisserieList
     * @param authentication
     */
    public AuthenticationControl(PatisserieMainGUI patisserieMainGUIHelper, LoginForm loginForm, List<Patisserie> patisserieList, Authentication authentication){
        this.patisserieMainGUIHelper = patisserieMainGUIHelper;
        this.loginForm = loginForm;
        this.patisserieList = patisserieList;
        this.authentication = authentication;
        authentication.getOwnerButton().setOnAction(event -> {
            ownerLogin();
        });
        authentication.getManagerButton().setOnAction(event -> {
            loginValidation();
        });
        authentication.getTeamLeaderButton().setOnAction(event -> {
            loginValidation();
        });
        authentication.getEmployeeButton().setOnAction(event -> {
            loginValidation();
        });
        Stage stage = new Stage();
        stage.setMinWidth(250);
        stage.setScene(authentication.getScene());
        stage.setTitle("Menu");
        stage.show();
    }

    /**
     * Function for checking owner´s login details
     * @param patisserie
     * @param stage
     */
    void loginCheck(Patisserie patisserie, Stage stage){
        try {
            if (patisserie.getOwner().getLogin().getUsername().equals(loginForm.getUsernameTextField().getText()) && patisserie.getOwner().getLogin().getPassword().equals(loginForm.getPasswordField().getText())) {
                Owner owner = patisserie.getOwner();
                ThreadRunner threadRunner = new ThreadRunner(patisserie, owner);
                threadRunner.start();
                stage.close();
            }else if(patisserie.getOwner().getLogin().getUsername().equals(loginForm.getUsernameTextField().getText()) && !patisserie.getOwner().getLogin().getPassword().equals(loginForm.getPasswordField().getText())){
                BadLoginDetails badLoginDetails = new BadLoginDetails(false);
                badLoginDetails.display();
                throw new BadLoginException(loginForm.getUsernameTextField().getText());
            }else{
                BadLoginDetails badLoginDetails = new BadLoginDetails(true);
                badLoginDetails.display();
            }
        }catch (BadLoginException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function for checking owner login details
     */
    void ownerLogin(){
        try {
            if (patisserieMainGUIHelper.getListView() != null) {
                int index = patisserieMainGUIHelper.getListView().getSelectionModel().getSelectedIndex();
                if (index >= 0 && index <= patisserieList.size()) {
                    Patisserie patisserie = patisserieList.get(index); //(Patisserie) patisserieMainGUIHelper.getListView().getItems().get(index);
                    Stage stage = new Stage();
                    loginForm = new LoginForm();
                    stage.setTitle("Sign in");
                    stage.setScene(loginForm.getScene());
                    stage.show();
                    loginForm.getSignButton().setOnAction(event1 -> {
                        loginCheck(patisserie, stage);
                    });
                } else {
                    throw  new ItemNotSelected();
                }
            }
        }catch (ItemNotSelected e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function for checking other employee IDs
     */
    void loginValidation(){
        try {
            if (patisserieMainGUIHelper.getListView() != null) {
                int index = patisserieMainGUIHelper.getListView().getSelectionModel().getSelectedIndex();
                if (index >= 0 && index <= patisserieList.size()) {
                    Patisserie patisserie = patisserieList.get(index); //(Patisserie) patisserieMainGUIHelper.getListView().getItems().get(index);
                    Stage stage = new Stage();
                    validationForm validationForm = new validationForm();
                    stage.setTitle("Sign in");
                    stage.setScene(validationForm.getScene());
                    stage.show();
                    validationForm.getButton().setOnAction(event1 -> {
                        idChecker(validationForm, patisserie, stage);
                    });
                } else {
                    throw  new ItemNotSelected();
                }
            }
        }catch (ItemNotSelected e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function for checking other employee IDs
     * @param validationForm
     * @param patisserie
     * @param stage
     */
    void idChecker(validationForm validationForm, Patisserie patisserie, Stage stage){
        int userID = Integer.parseInt(validationForm.getIDTextField().getText());
        for(Manager manager: patisserie.getManagers()){
            if(manager.getPersonalID() == userID){
                ThreadRunner threadRunner = new ThreadRunner(patisserie, manager);
                threadRunner.start();
                stage.close();
                break;
            }
        }

        for(TeamLeader teamLeader : patisserie.getTeamLeaders()){
            if(teamLeader.getPersonalID() == userID){
                ThreadRunner threadRunner = new ThreadRunner(patisserie, teamLeader);
                threadRunner.start();
                stage.close();
                break;
            }
        }

        for(Employee employee : patisserie.getEmployeeList()){
            if(employee.getPersonalID() == userID){
                ThreadRunner threadRunner = new ThreadRunner(patisserie, employee);
                threadRunner.start();
                stage.close();
                break;
            }
        }
    }
}
