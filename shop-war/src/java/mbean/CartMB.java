/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Customers;
import entities.OrderDetails;
import entities.OrderDetailsPK;
import entities.Orders;
import entities.Products;
import entities.Shippers;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import model.Cart;
import sessionbean.CustomersFacadeLocal;
import sessionbean.OrderDetailsFacadeLocal;
import sessionbean.OrdersFacadeLocal;
import sessionbean.ProductsFacadeLocal;
import sessionbean.ShippersFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "cartMB")
@SessionScoped
public class CartMB implements Serializable {

    @EJB
    private OrderDetailsFacadeLocal orderDetailsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private ShippersFacadeLocal shippersFacade;

    @EJB
    private ProductsFacadeLocal productsFacade;

    /**
     * Creates a new instance of CartMB
     */
    private int quantity = 1;
    private int productID;
    private List<Products> products = new ArrayList<>();
    private List<Cart> cart = new ArrayList<>();
    private boolean soldout;
    private boolean success;
    // create payment
    private int shipID;
    private String shipAddress;
    private String shipCity;
    private int Freight;
    private String shipCountry;
    private String shipPhone;

    public CartMB() {

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public boolean isSoldout() {
        return soldout;
    }

    public void setSoldout(boolean soldout) {
        this.soldout = soldout;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getShipID() {
        return shipID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public int getFreight() {
        return Freight;
    }

    public void setFreight(int Freight) {
        this.Freight = Freight;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    // shipper
    public List<Shippers> listShippers() {
        return shippersFacade.findAll();
    }

    // order before
    public void orderBefore(int id) {
        quantity = 1;
        order(id);
    }

    // add product to cart
    public String addToCart(int id) {
        order(id);
        System.out.println("Cart" + cart.size());
        quantity = 1;
        return "product";
    }

    // order product
    public void order(int id) {
        System.out.println("You click product " + id);
        Products product = productsFacade.find(id);
        if (quantity < 1) {
            quantity = 1;
        }
        if (product.getQuantity() < quantity) {
            soldout = true;
            return;
        }
        List<Integer> list = new ArrayList<>();
        if (cart.size() == 0) {
            Cart objCart = new Cart();
            objCart.setProductID(id);
            objCart.setProductName(product.getProductName());
            objCart.setQuantity(quantity);
            BigDecimal bd = product.getUnitPrice();
            objCart.setUnitPrice(bd.doubleValue());
            objCart.setThumbnail(product.getThumbnail());
            cart.add(objCart);
            success = true;
            System.out.println("add to cart empty");
        } else {
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).getProductID() == id) {
                    if (cart.get(i).getQuantity() + quantity > product.getQuantity()) {
                        soldout = true;
                        success = false;
                        return;
                    }
                    int qtt = cart.get(i).getQuantity() + quantity;
                    cart.get(i).setQuantity(qtt);
                    System.out.println("Product ID" + cart.get(i).getProductID());
                    System.out.println("Item ID qtt: " + id);
                    System.out.println("add quantity");
                    System.out.println("-------------------");
                    success = true;
                }
                list.add(cart.get(i).getProductID());

            } // end loop
            if (!list.contains(id)) {
                success = true;
                Cart objCart = new Cart();
                System.out.println("Item ID" + id);
                objCart.setProductID(id);
                objCart.setProductName(product.getProductName());
                objCart.setQuantity(quantity);
                BigDecimal bd = product.getUnitPrice();
                objCart.setUnitPrice(bd.doubleValue());
                objCart.setThumbnail(product.getThumbnail());
                cart.add(objCart);
                System.out.println("add to cart new product");
                System.out.println("-------------------");
            }
        }
    }

    // reset
    public void init() {
        System.out.println("Reset-- ");
        soldout = false;
        success = false;
        quantity = 1;
    }

    // show payment
    public double getPayment() {
        double result = 0.0;
        for (Cart item : cart) {
            result += item.getUnitPrice() * item.getQuantity();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(result);
        result = Double.valueOf(dx);
        return result;
    }

    // plus or minus quantity
    public void plusOrMinusQuantity(int id, int type) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProductID() == id) {
                // plus
                if (type == 1) {
                    Products product = productsFacade.find(id); // find product in database
                    if (product.getQuantity() > cart.get(i).getQuantity()) { // check if qtt in stock greater than current qtt in cart ?
                        int qtt = cart.get(i).getQuantity() + 1;
                        cart.get(i).setQuantity(qtt);
                        System.out.println("Product ID" + cart.get(i).getProductID());
                        System.out.println("plus quantity");
                        System.out.println("-------------------");
                    } else {
                        soldout = true;
                    }
                } // minus
                else if (type == 2) {
                    if (cart.get(i).getQuantity() > 1) { // quantity is always greater than 0
                        int qtt = cart.get(i).getQuantity() - 1;
                        cart.get(i).setQuantity(qtt);
                        System.out.println("Product ID" + cart.get(i).getProductID());
                        System.out.println("minus quantity");
                        System.out.println("-------------------");
                    }
                }

            }
        }
    }

    // remove product from cart
    public void removeProduct(int id) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProductID() == id) {
                cart.remove(i);
                break;
            }
        }
    }

    // payment order
    public String paymentOrder() {
        int size = cart.size();
        FacesContext context = FacesContext.getCurrentInstance();
        int customerID = Integer.parseInt(context.getExternalContext().getSessionMap().get("user").toString());
        Orders order = new Orders();
        Customers customer = new Customers();
//      System.out.print("Customer "+customerID);
//      System.out.print("Gio hang " + size);
        // if customer have ordered any product then create the order
        if (size != 0) {
            customer = customersFacade.find(customerID);
            order.setCustomerID(customer);
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            order.setOrderDate(timestamp);
            order.setOrderDate(timestamp);
            Shippers shipper = new Shippers();
            shipper = shippersFacade.find(shipID);
            order.setShipVia(shipper);
            float f = shipper.getFreight();
            BigDecimal bd = BigDecimal.valueOf(f);
            order.setFreight(bd);
            order.setShipCountry(shipCountry);
            order.setShipCity(shipCity);
            order.setShipAddress(shipAddress);
            order.setShipPhone(shipPhone);
            order.setStatus(0);
            ordersFacade.create(order);
            System.out.println("ORDER ID" + order.getOrderID());
            for (Cart item : cart) {
                Products objPro = productsFacade.find(item.getProductID());
                int oldQtt = objPro.getQuantity();
                objPro.setQuantity(oldQtt - item.getQuantity());
                productsFacade.edit(objPro);
                System.out.println("ORDER ID" + order.getOrderID());
                OrderDetailsPK oPK = new OrderDetailsPK(order.getOrderID(), item.getProductID());
                BigDecimal bPrice = new BigDecimal(item.getUnitPrice(), MathContext.DECIMAL64);
                OrderDetails oDT = new OrderDetails(oPK, bPrice, (short) item.getQuantity(), (float) 0.1);
                orderDetailsFacade.create(oDT);
            }
            cart.removeAll(cart);
        }
        return "myOrder";
    }
    
    // khuyen mai
    public double saleOff(int s, double price){
        double sale = 100-s/100;
        double total = price-(price*sale);
        DecimalFormat df = new DecimalFormat("#.##");
        String dx = df.format(total);
        total = Double.valueOf(dx);
        return total;
    }

}
