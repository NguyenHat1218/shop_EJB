/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Customers;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.CustomersFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "cpCustomer")
@SessionScoped
public class cpCustomer implements Serializable {

    @EJB
    private CustomersFacadeLocal customersFacade;

    private int currentPage = 1;
    private List<Customers> listCustomers;
    private int customerID;
    private String email;
    private String gender;
    private String fullname;
    private String birthday;
    private String address;
    private String phone;
    private String rights;

    /**
     * Creates a new instance of cpCustomer
     */
    public cpCustomer() {
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Customers> getListCustomers() {
        return listCustomers;
    }

    public void setListCustomers(List<Customers> listCustomers) {
        this.listCustomers = listCustomers;
    }

    // count page
    public int countPage() {
        int sum = customersFacade.count();
        int result = (int) Math.ceil((double) sum / 10); // lam tròn
        //System.out.println(result);
        return result;
    }

    public void showPagination(int page) {
        listCustomers = customersFacade.Pagination(page);
        currentPage = page;
        System.out.println("Hello current page " + currentPage);
    }

    @PostConstruct
    public void init() {
        listCustomers = customersFacade.Pagination(currentPage);
        System.out.println("Current page " + currentPage);
    }

    // block
    public void blockUser(int id) {
        Customers customer = customersFacade.find(id);
        if (customer.getBlock() == 0) {
            customer.setBlock(1);
        } else {
            customer.setBlock(0);
        }
        customersFacade.edit(customer);
        listCustomers = customersFacade.Pagination(currentPage);

    }

    // edit customer
    public String editCus(int id) {
        Customers custom = customersFacade.find(id);
        customerID = custom.getCustomerID();
        email = custom.getEmail();
        gender = custom.getGender();
        fullname = custom.getFullname();
        birthday = custom.getBirthday();
        address = custom.getAddress();
        phone = custom.getPhone();
        rights = custom.getRights();
        return "editCustomer";
    }
    
    // save info of customer
    public String saveCus() {
        Customers custom = customersFacade.find(customerID);
        custom.setEmail(email);
        custom.setGender(gender);
        custom.setFullname(fullname);
        custom.setBirthday(birthday);
        custom.setAddress(address);
        custom.setPhone(phone);
        custom.setRights(rights);
        customersFacade.edit(custom);
        listCustomers = customersFacade.Pagination(currentPage);
        return "listCustomer";
    }
}
