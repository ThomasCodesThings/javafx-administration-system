package model.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager extends Employee {
    private List<TeamLeader> teamLeaders = new ArrayList<>();

    public List<TeamLeader> getTeamLeaders() {
        return teamLeaders;
    }

    /**
     *
     * @param employee employee to be added
     */
    public void add(Employee employee){
        if(employee instanceof TeamLeader){
            teamLeaders.add((TeamLeader) employee);
        }
    }

    /**
     *
     * @param employee employee to be removed
     */
    public void remove(Employee employee){
        teamLeaders.remove((TeamLeader) employee);
    }
    public Manager(String name, String surname, int age) {
        super(name, surname, age);
        //this.setPersonalID(new Random().nextInt(200-100+1) + 100);
    }
}
