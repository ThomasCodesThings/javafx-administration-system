package controller;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import model.database.PatisserieDatabase;
import model.infrastructure.*;
import view.*;

import java.util.*;

import static model.utilities.Utilitties.calculatePersonAge;

public class PatisserieMainMenuController{
    private PatisserieMenuController patisserieMenuController;
    private PatisserieMainGUI patisserieMainGUIHelper;
    private registerMenu registerMenu;
    private registerMenuController registerMenuController;
    private LoginForm loginForm;
    private List<Patisserie> patisserieList = new ArrayList<Patisserie>();

    public List<Patisserie> getPatisserieList() {
        return patisserieList;
    }

    public PatisserieMainMenuController(PatisserieMainGUI patisserieMainGUI){
        patisserieMainGUIHelper = patisserieMainGUI;
        patisserieMainGUI.getCreateButton().setOnAction(event -> {
            registerMenu = new registerMenu();
            registerMenuController = new registerMenuController(registerMenu);
            Stage stage = new Stage();
            stage.setScene(registerMenu.getScene());
            stage.setTitle("Add new patisserie");
            stage.show();
            registerMenu.getConfirmButton().setOnAction(event1 -> {
                createNewPatisserie(stage);
            });
        });
        
        patisserieMainGUI.getLoadButton().setOnAction(event -> {
            Authentication authentication = new Authentication();
            AuthenticationControl authenticationControl = new AuthenticationControl(patisserieMainGUIHelper, loginForm, patisserieList, authentication);
        });

        patisserieMainGUI.getRemoveButton().setOnAction(event -> {
            if(patisserieMainGUIHelper.getListView() != null) {
                int index = patisserieMainGUIHelper.getListView().getSelectionModel().getSelectedIndex();
                Patisserie patisserie = patisserieList.get(index);
                patisserieList.remove(patisserie);
                patisserieMainGUIHelper.getListView().getItems().remove(index);
                System.out.println("Removed!");
                PatisserieDatabase.save();
            }
        });

    }

    void createNewPatisserie(Stage stage){

        VBox ownerRegisterForm = registerMenu.getOwnerRegisterForm();
        String ownerFirstName = ((TextField)((GridPane) (((HBox) ownerRegisterForm.getChildren().get(0)).getChildren().get(0))).getChildren().get(1)).getText();
        String ownerLastName = ((TextField)((GridPane) (((HBox) ownerRegisterForm.getChildren().get(0)).getChildren().get(1))).getChildren().get(1)).getText();
        DatePicker ownerDatePicker = ((DatePicker)((GridPane) (((HBox) ownerRegisterForm.getChildren().get(1)).getChildren().get(0))).getChildren().get(1));
        String ownerAddress = ((TextField)((GridPane) (((HBox) ownerRegisterForm.getChildren().get(1)).getChildren().get(1))).getChildren().get(1)).getText();
        String ownerPSC = ((TextField)((GridPane) (((HBox) ownerRegisterForm.getChildren().get(2)).getChildren().get(0))).getChildren().get(1)).getText();
        String ownerTown = ((TextField)((GridPane) (((HBox) ownerRegisterForm.getChildren().get(2)).getChildren().get(1))).getChildren().get(1)).getText();
        String ownerCountry = ((TextField)((GridPane) (((HBox) ownerRegisterForm.getChildren().get(2)).getChildren().get(2))).getChildren().get(1)).getText();

        Owner patisserieOwner = new Owner(ownerFirstName, ownerLastName);
        Residence ownerResidence = new Residence(ownerAddress, ownerPSC, ownerTown, ownerCountry);
        patisserieOwner.setAge(calculatePersonAge(ownerDatePicker));
        patisserieOwner.setOwnerResidence(ownerResidence);

        Login ownerLogin = new Login(registerMenu.getOwnerUsernameTextField().getText(), registerMenu.getOwnerPasswordField().getText(), registerMenu.getOwnerConfirmPasswordField().getText());
        patisserieOwner.setLogin(ownerLogin);
        Residence patisserieResidence = new Residence(registerMenu.getPatisserieAddressTextField().getText(), registerMenu.getPatisseriePSCTextField().getText(), registerMenu.getPatisserieTownTextField().getText(), registerMenu.getPatisserieCountryTextField().getText());

        if(ownerLogin != null){
            Patisserie patisserie = new Patisserie(registerMenu.getPatisserieNameTextField().getText(), patisserieResidence, patisserieOwner);
            if(patisserie != null && patisserieOwner != null){
                System.out.println("Created new patisserie!");
                patisserieOwner.setPatisserie(patisserie);
                for(Employee employee: registerMenuController.getEmployeeList()){
                    patisserieOwner.add(employee);
                }
                for(Manager manager: registerMenuController.getManagerList()){
                    patisserieOwner.add(manager);
                }
                for(TeamLeader teamLeader : registerMenuController.getTeamLeaders()){
                    patisserieOwner.add(teamLeader);
                }
                patisserieList.add(patisserie);
                update(patisserie);
               patisserieMainGUIHelper.getListView().getItems().add(patisserie.getPatisserieName() + " -> " + patisserie.getOwner().getFirstName() + " " + patisserie.getOwner().getLastName());
            }
        }
        stage.close();
    }

    public void refresh(){
        ArrayList<Patisserie> patisseries = PatisserieDatabase.getInstance().getPatisseries();
        for(Patisserie patisserie: patisseries){
            if(patisserieMainGUIHelper != null) {
                patisserieList.add(patisserie);
                patisserieMainGUIHelper.getListView().getItems().add(patisserie.getPatisserieName() + " -> " + patisserie.getOwner().getFirstName() + " " + patisserie.getOwner().getLastName());
            }
        }
    }
    void update(Patisserie patisserie){
        PatisserieDatabase.getInstance().getPatisseries().add(patisserie);
        PatisserieDatabase.save();
        System.out.println("Saved!");
    }
}
