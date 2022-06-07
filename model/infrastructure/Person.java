package model.infrastructure;

import model.Residence;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int age;
    private Residence residence;

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public Person() {

    }

    /**
     * Constructor of Person
     * @param firstName takes person´s first name as parameter
     * @param lastName takes person´s last name as parameter
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructor of Person
     * @param firstName
     * @param lastName
     * @param residence
     */
    public Person(String firstName, String lastName, Residence residence) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.residence = residence;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setSurname(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Constructor of Person
     * @param firstName
     * @param lastName
     * @param age
     */
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
