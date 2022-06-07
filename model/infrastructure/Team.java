package model.infrastructure;

import model.customer.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable {
    private TeamLeader teamLeader;
    private List<Employee> teammemberList = new ArrayList<>();
    private String teamName;
    private Order assignedOrder;

    public Order getAssignedOrder(){
        return assignedOrder;
    }

    public void setAssignedOrder(Order assignedOrder) {
            this.assignedOrder = assignedOrder;
    }
    public String getTeamName() {
        return teamName;
    }

    public TeamLeader getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(TeamLeader teamLeader) {
        this.teamLeader = teamLeader;
    }

    public List<Employee> getTeammemberList() {
        return teammemberList;
    }

    public void setTeammemberList(List<Employee> teammemberList) {
        this.teammemberList = teammemberList;
    }


    /**
     * Constructor of Team
     * @param teamLeader takes team leader as parameter
     * @param teamName takes name of team as parameter
     */
    public Team(TeamLeader teamLeader, String teamName){
        this.teamLeader = teamLeader;
        this.teamName = teamName;
    }
}
