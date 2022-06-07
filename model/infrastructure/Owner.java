package model.infrastructure;

import model.*;
import model.ingredients.Ingredient;
import model.resources.SystemManager;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

public class Owner extends Employee implements Serializable, SystemManager {
    private Login login;
    private Residence ownerResidence;
    private Patisserie patisserie;

    public Residence getOwnerResidence() {
        return ownerResidence;
    }

    public Patisserie getPatisserie() {
        return patisserie;
    }

    public void setPatisserie(Patisserie patisserie) {
        this.patisserie = patisserie;
    }

    public void setOwnerResidence(Residence ownerResidence) {
        this.ownerResidence = ownerResidence;
    }
    @Override
    public void print(Patisserie patisserie) {
        System.out.println("Now printing all employees of patisserie:");
        System.out.println(patisserie.getResidence().getAddress() + " " + patisserie.getResidence().getPSC() + " " + patisserie.getResidence().getTown() + " " + patisserie.getResidence().getCountry());
        System.out.println("Owner: ");
        System.out.println(patisserie.getOwner().getFirstName() + " " + patisserie.getOwner().getLastName() + " " + patisserie.getOwner().getAge());
        System.out.println();
        System.out.println("ID\tPosition\tFirst Name\tLast Name\tAge\t");
        for(Employee employee : patisserie.getEmployeeList()){
            System.out.println(employee.getPersonalID() + " " + employee.getPosition() + " " + employee.getFirstName() + " " +employee.getLastName() + " " + employee.getAge());
        }
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }

    /**
     * Function to kick Employee from Patisserie
     * @param employee takes employee as parameter
     */
    public void kick(Employee employee){
        if(employee instanceof TeamLeader){
            patisserie.getTeamLeaders().remove(employee);
            return;
        }else if(employee instanceof Manager){
            patisserie.getManagers().remove(employee);
            return;
        }else {
            patisserie.getEmployeeList().remove(employee);
        }
        }

    /**
     *
     * @param employee employee to be added
     */
    public void add(Employee employee){
        if(employee instanceof TeamLeader){
            employee.setPersonalID(200 + patisserie.getTeamLeaders().size());
            patisserie.getTeamLeaders().add((TeamLeader) employee);
            return;
        }
        if(employee instanceof Manager){
            employee.setPersonalID(300 + patisserie.getManagers().size());
            patisserie.getManagers().add((Manager) employee);
            return;
        }
        employee.setPersonalID(100 + patisserie.getEmployeeList().size());
        patisserie.getEmployeeList().add(employee);
    }

    public void addTeam(Team team){
        patisserie.getTeamList().add(team);
    }

    /**
     * Function to remove team from team list
     * @param team takes team to be removed as parameter
     */
    public void disabandTeam(Team team){
        patisserie.getTeamList().remove(team);
        System.out.println("Team disabanded");
    }

    /**
     * Constructor of Owner class
     * @param name name of owner
     * @param surname surname of owner
     */
    public Owner(String name, String surname) {
        super(name, surname);
        this.setPersonalID(1000);
    }

}
