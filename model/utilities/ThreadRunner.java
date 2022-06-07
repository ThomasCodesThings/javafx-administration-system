package model.utilities;

import controller.PatisserieMenuController;
import javafx.application.Platform;
import model.infrastructure.Employee;
import model.infrastructure.Patisserie;
import view.PatisserieMenu;
import view.employeeListView;

public final class ThreadRunner extends Thread{
    private Thread thread;
    private Patisserie patisserie;
    private Employee employeeType;

    /**
     * Starts new Thread
     */
    public void start(){

        if (thread == null) {
            thread = new Thread (this, patisserie.getPatisserieName());
            thread.start();
            System.out.println("Starting new Thread for patisserie: " + patisserie.getPatisserieName()  + " ,on thread " + thread.getId());
        }
    }
    /**
     * Runs new Thread
     */
    public void run(){
        Platform.runLater(() -> {
            PatisserieMenu patisserieMenu = new PatisserieMenu(new employeeListView().getEmployeeTable(), patisserie.getTeamList().size());
            PatisserieMenuController patisserieMenuController = new PatisserieMenuController(patisserie, patisserieMenu, employeeType);
        });
    }

    /**
     * TreadRunner´s constructor
     * @param patisserie takes patisserie´s object as parameter
     * @param employee takes employee´s object as parameter
     */
    public ThreadRunner(Patisserie patisserie, Employee employee){
        this.patisserie = patisserie;
        this.employeeType = employee;
    }
}
