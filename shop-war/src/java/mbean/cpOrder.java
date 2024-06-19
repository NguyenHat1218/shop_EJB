/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.OrderDetails;
import entities.Orders;
import entities.Products;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.OrdersFacadeLocal;
import sessionbean.ProductsFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "cpOrder")
@SessionScoped
public class cpOrder implements Serializable {

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    /**
     * Creates a new instance of cpOrder
     */
    private List<Orders> listOrders;
    private int currentPage = 1;
    private List<OrderDetails> listOrDetails;
    private double payment;
    private int status;
    private int OrderID;
    private Orders Order;

    public cpOrder() {
    }

    public List<Orders> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Orders> listOrders) {
        this.listOrders = listOrders;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public Orders getOrder() {
        return Order;
    }

    public void setOrder(Orders Order) {
        this.Order = Order;
    }

    
    @PostConstruct
    public void init() {
        listOrders = ordersFacade.Pagination(1);
        System.out.println("Current page " + currentPage);
    }
    // pagin
    public void showPagination(int page) {
        listOrders = ordersFacade.Pagination(page);
        currentPage = page;
        System.out.println("Hello current page " + currentPage);
    }
    
    // count
    public int countPage() {
        int sum = ordersFacade.count();
        int result = (int) Math.ceil((double) sum / 10); // lam tròn
        //System.out.println(result);
        return result;
    }
    
    // see details
    public String seeOrderDetails(int id) {
        OrderID = id;
        Order = ordersFacade.find(id);
        status = Order.getStatus();
        listOrDetails = ordersFacade.findOrderDetails(id);
        payment = returnPayment();
        return "seeOrderDetails";
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
    // update status of order
    public String updateStatus(int id){
        Orders pbj = ordersFacade.find(id);
        pbj.setStatus(status);
        ordersFacade.edit(pbj);
        listOrders = ordersFacade.Pagination(currentPage);
        return "listOrder";
    }
    
    // delete order
    public void delOrder(int id){
        Orders ord = ordersFacade.find(id);
        if(ord != null){
            ordersFacade.remove(ord);
        }
        listOrders = ordersFacade.Pagination(currentPage);
    }
}
