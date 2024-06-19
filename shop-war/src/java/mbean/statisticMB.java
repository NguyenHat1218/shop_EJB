/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionbean.BlogsFacadeLocal;
import sessionbean.CustomersFacadeLocal;
import sessionbean.OrdersFacadeLocal;
import sessionbean.ProductsFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "statisticMB")
@RequestScoped
public class statisticMB {

    @EJB
    private BlogsFacadeLocal blogsFacade;

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    /**
     * Creates a new instance of statisticMB
     */
    public statisticMB() {
    }
    
    public int countUser(){
        return customersFacade.count();
    }
    public int countProduct(){
        return productsFacade.count();
    }
    public int countOrder(){
        return ordersFacade.count();
    }
    public int countBlog(){
        return blogsFacade.count();
    }
    public int barStatus(int type){
        int st = ordersFacade.statusProcessBar(type);
        return (int) Math.ceil((double)st/ordersFacade.count()*100);
    }
    
}
