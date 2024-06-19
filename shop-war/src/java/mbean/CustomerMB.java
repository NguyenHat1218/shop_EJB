/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Customers;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import sessionbean.CustomersFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "customerMB")
@RequestScoped
public class CustomerMB {

    @EJB
    private CustomersFacadeLocal customersFacade;

    private String username;
    private String password;
    private String fullname;
    private String gender;
    private String email;
    private String address;
    private String phone;
    private String favorite;
    private String birthday;
    private String msg;
    private String redirect;

    /**
     * Creates a new instance of CustomerMB
     */
    public CustomerMB() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    

    // check login
    public String checkLogin() {
        int kq = 0;
        String hashPassword = md5(password);
        kq = customersFacade.login(username, hashPassword);
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println(kq);
        if (kq == 0) {
            msg = "These credentials is not match our records.";
            System.out.println("khong ton tai");
            return "login";
        } else {
            msg = "";
            context.getExternalContext().getSessionMap().put("user", kq);
            Customers customer = customersFacade.find(kq);
            context.getExternalContext().getSessionMap().put("right", customer.getRights());
            //System.out.println("co ton tai");
            return "index";
        }
    }

    // register
    public String registerNew() {
        if (!isValidUser(username)) {
            msg = "Username is not valid, must not contain any special character, username's length must be from 5 to 10 chars";
            return "register";
        } else if (isExistUser(username)) {
            msg = "Username is already used.";
            return "register";
        } else {
            Customers obj = new Customers();
            obj.setUsername(username);
            obj.setPassword(md5(password));
            obj.setFullname(fullname);
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            obj.setAddress("");
            obj.setPhone("");
            obj.setEmail("");
            obj.setFavorite("");
            obj.setBlock(0);
            obj.setRights("member");
            obj.setCreatedat(timestamp);
            obj.setPost(0);
            customersFacade.create(obj);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("user", obj.getCustomerID());
            return "index";
        }
    }
    // hash password = ma hoa password

    public String md5(String input) {

        String md5 = null;

        if (null == input) {
            return null;
        }

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }

    // check if user is contains special character ?
    public boolean isValidUser(String user) {
        String regex = "^[a-zA-Z0-9]{5,10}$";
        return user.matches(regex);
    }
    // check is if exists account in db a18138

    public boolean isExistUser(String user) {
        boolean flag = false;
        try {
            int kq = customersFacade.findByUsername(user);
            if (kq == 1) {
                flag = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    // log out
    public String logOut() {
        FacesContext context = FacesContext.getCurrentInstance();
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        context.getExternalContext().getSessionMap().remove("user");
        context.getExternalContext().getSessionMap().remove("right");
        return "index";
    }

    // login redirect
    public void redirectLogin() throws IOException {
        msg = "Please, login to get payment";
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml?to=cart");//add your URL here, instead of list.do
    }

    // get info
    public Customers getInfo() {
        Customers customer = null;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (context.getExternalContext().getSessionMap().get("user") != null) {
                int customerID = Integer.parseInt(context.getExternalContext().getSessionMap().get("user").toString());
                customer = customersFacade.find(customerID);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    // show profile edit
    public String showEdit(){
        FacesContext context = FacesContext.getCurrentInstance();
        Customers customer;
        if (context.getExternalContext().getSessionMap().get("user") != null) {
            int customerID = Integer.parseInt(context.getExternalContext().getSessionMap().get("user").toString());
            customer = customersFacade.find(customerID);
            email = customer.getEmail();
            fullname = customer.getFullname();
            gender = customer.getGender();
            phone = customer.getPhone();
            birthday = customer.getBirthday();
            address = customer.getAddress();
            favorite = customer.getFavorite();
        }
        return "editProfile";
    }
    // edit ptofile
    public void editProfile() {
        FacesContext context = FacesContext.getCurrentInstance();
        Customers customer;
        if (context.getExternalContext().getSessionMap().get("user") != null) {
            int customerID = Integer.parseInt(context.getExternalContext().getSessionMap().get("user").toString());
            customer = customersFacade.find(customerID);
            customer.setFullname(fullname);
            customer.setAddress(address);
            customer.setFavorite(favorite);
            customer.setPhone(phone);
            customer.setEmail(email);
            customer.setGender(gender);
            customer.setBirthday(birthday);
            System.out.println("Birthday " + birthday);
            customersFacade.edit(customer);
        }
    }

}
