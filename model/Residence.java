package model;

import java.io.Serializable;

public class Residence implements Serializable {
    private String address;
    private String PSC;
    private String town;
    private String country;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPSC() {
        return PSC;
    }

    public void setPSC(String PSC) {
        this.PSC = PSC;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Residence constructor
     * @param address
     * @param PSC
     * @param town
     * @param country
     */
    public Residence(String address, String PSC, String town, String country) {
        this.address = address;
        this.PSC = PSC;
        this.town = town;
        this.country = country;
    }
}
