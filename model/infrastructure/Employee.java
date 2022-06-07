package model.infrastructure;

import model.customer.Order;

import java.io.Serializable;
import java.util.Random;

public class Employee extends Person implements Serializable, InfrastructureInterface {
    private int personalID;
    private String position;
    private boolean isSelected;


    public Employee() {

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPersonalID() {
        return personalID;
    }

    public void setPersonalID(int personalID) {
        this.personalID = personalID;
    }

    /**
     * Function to add new employee, it´s the function that is overriden by child classes
     * @param employee employee to be added
     */
    public void add(Employee employee){
        System.out.println("I cannot add myself");
        return;
    }

    /**
     * Function to remove employee, it´s the function that is overriden by child classes
     * @param employee employee to be removed
     */
    public void remove(Employee employee){
        System.out.println("Do you think I can do that lol");
        return;
    }

    /**
     * Constuctor for Employee class
     * @param firstName first name of employee
     * @param lastName last name of employee
     * @param age employee´s age
     */
    public Employee(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        //this.personalID = new Random().nextInt(100);
    }
    /**
     * Constuctor for Employee class
     * @param firstName first name of employee
     * @param lastName last name of employee
     */
    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
