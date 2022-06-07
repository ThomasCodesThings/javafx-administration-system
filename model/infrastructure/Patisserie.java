package model.infrastructure;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.Residence;
import model.customer.*;
import model.product.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Patisserie implements Serializable {
    private String patisserieName;
    private Residence residence;
    private Owner owner;
    private List<Employee> employeeList = new ArrayList<>();
    private List<TeamLeader> teamLeaders = new ArrayList<>();
    private List<Manager> managers = new ArrayList<>();
    private List<Team> teamList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    private List<Product> productList = new ArrayList<Product>();
    private Warehouse<Object> warehouse = new Warehouse<>();
    public Warehouse<Object> getWarehouse() {
        return warehouse;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order){
        orderList.add(order);
    }

    public List<Product> getProductList() {
        return productList;
    }

    /**
     * Function to add product to product list
     * @param product takes product to be added as main parameter
     */
    public void addProduct(Product product){
        this.productList.add(product);
    }

    /**
     * same thing as before but with removing
     * @param product takes product to be removed as parameter
     */
    public void removeProduct(Product product){
        this.productList.remove(product);
    }
    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public void setTeamLeaders(List<TeamLeader> teamLeaders) {
        this.teamLeaders = teamLeaders;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public List<TeamLeader> getTeamLeaders() {
        return teamLeaders;
    }

    public String getPatisserieName() {
        return patisserieName;
    }

    public void setPatisserieName(String patisserieName) {
        this.patisserieName = patisserieName;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    /**
     * Constructor of Patisserie
     * @param patisserieName parameter of patisserie´s name
     * @param residence parameter of Patisserie´s address
     * @param owner parameter of Patisserie´s owner
     */
    public Patisserie(String patisserieName, Residence residence, Owner owner) {
        this.patisserieName = patisserieName;
        this.residence = residence;
        this.owner = owner;
    }
}
