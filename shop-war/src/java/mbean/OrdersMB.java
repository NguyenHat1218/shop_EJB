/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.OrderDetails;
import entities.Orders;
import entities.Products;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import sessionbean.OrdersFacadeLocal;
import sessionbean.ProductsFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "ordersMB")
@RequestScoped
public class OrdersMB {

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;
    private List<OrderDetails> listOrDetails;
    private double payment;

    /**
     * Creates a new instance of OrdersMB
     */
    public OrdersMB() {
    }

    public List<OrderDetails> getListOrDetails() {
        return listOrDetails;
    }

    public void setListOrDetails(List<OrderDetails> listOrDetails) {
        this.listOrDetails = listOrDetails;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    // my orders
    public List<Orders> myListOrder() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getExternalContext().getSessionMap().get("user") != null) {
            int customerID = Integer.parseInt(context.getExternalContext().getSessionMap().get("user").toString());
            return ordersFacade.findOrder(customerID);
        } else {
            return null;
        }

    }

    // find details
    public void seeOrderDetails(int id) {
        listOrDetails = ordersFacade.findOrderDetails(id);
        Orders od = ordersFacade.find(id);
        payment = returnPayment();
        BigDecimal bd =  od.getFreight();
        payment = payment + bd.doubleValue();
        System.out.println("Freight " + bd.doubleValue());
        System.out.println("Payment " + payment);
    }

    // total order
    public double returnPayment() {
        double result = 0.0;
        for (OrderDetails item : listOrDetails) {
            BigDecimal bd = item.getUnitPrice();
            result += item.getQuantity() * bd.doubleValue();
            System.out.println("Double value " + bd.doubleValue());
            System.out.println("Bill" + result);
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(result);
        result = Double.valueOf(dx);
        return result;
    }
    
    // return name
    public String getName(int id, int type){
        Products product = productsFacade.find(id);
        if(type == 0){
            return product.getProductName();
        }
        else
        {
            return product.getThumbnail();
        }
    }
    // get list of order
    
    
}
