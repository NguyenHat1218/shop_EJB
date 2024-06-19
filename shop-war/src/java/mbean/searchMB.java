/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Products;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import sessionbean.ProductsFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "searchMB")
@SessionScoped
public class searchMB implements Serializable{

    @EJB
    private ProductsFacadeLocal productsFacade;

    private String s;
    private List<Products> listProducts;
    /**
     * Creates a new instance of searchMB
     */
    public searchMB() {
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List<Products> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Products> listProducts) {
        this.listProducts = listProducts;
    }
    public void localeChanged(){
        System.out.println("searching ...... " + s);
        if(!s.equals("")){
            listProducts = productsFacade.findWithName(s);
        }
    }
    
}
