package model.customer;

import model.Residence;
import model.infrastructure.Person;

public class Customer extends Person {
    private String email;
    private String telephoneNumber;

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     * @param email sets email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for telephone number for customer
     * @return returns telephoneNumber
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * setter for telephone number
     * @param telephoneNumber takes telephone number in format of string
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Constructor for Customer
     * @param firstName takes customer´s first name
     * @param lastName takes customer´s last name
     * @param residence takes customer´s residence
     * @param telephoneNumber takes customer´s telephone number
     * @param email takes customer´s email address
     */
    public Customer(String firstName, String lastName, Residence residence, String telephoneNumber, String email){
        super(firstName, lastName, residence);
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }
}
