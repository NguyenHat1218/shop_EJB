/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Customers;
import entities.OrderDetails;
import entities.Orders;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author asus
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {

    @EJB
    private CustomersFacadeLocal customersFacade;

    @PersistenceContext(unitName = "shop-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }
    // find product
    @Override
    public List<Orders> findOrder(int customerID) {
    Query query = em.createQuery("SELECT o FROM Orders o WHERE o.customerID = :CustomerID ORDER BY o.customerID DESC");
    Customers customer = customersFacade.find(customerID);
    query.setParameter("CustomerID", customer);
    return query.getResultList();
    }
    
    @Override
    public List<OrderDetails> findOrderDetails(int OrderID) {
    Query query = em.createQuery("SELECT o FROM OrderDetails o WHERE o.orderDetailsPK.orderID = :OrderID");
    query.setParameter("OrderID", OrderID);
    return query.getResultList();
    }
    
    @Override
    public List<Orders> Pagination(int pageNumber) {
        Query query = em.createQuery("SELECT o FROM Orders o ORDER BY o.orderID DESC");
        int pageSize = 10;
        query.setMaxResults(pageSize);
        query.setFirstResult((pageNumber - 1) * pageSize);
        return query.getResultList();
    }
    
    @Override
    public int statusProcessBar(int type){
        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.status = :status");
        query.setParameter("status", type);
        return query.getResultList().size();
    }
}
