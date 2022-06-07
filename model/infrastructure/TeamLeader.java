package model.infrastructure;

import model.customer.Order;
import model.infrastructure.Employee;
import model.infrastructure.Team;

import java.util.Random;

public class TeamLeader extends Employee {
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     *
     * @param employee employee to be added
     */
    public void add(Employee employee){
        team.getTeammemberList().add(employee);
    }

    /**
     *
     * @param employee employee to be removed
     */
    public void remove(Employee employee){
        team.getTeammemberList().remove(employee);
    }

    @Override
    public String toString() {
       return getFirstName() + " " + getLastName();
    }

    public TeamLeader(String name, String surname, int age) {
        super(name, surname, age);
        //this.setPersonalID(new Random().nextInt(300-200+1) +200);
    }
}
