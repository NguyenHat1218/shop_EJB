/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Customers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author asus
 */
@Stateless
public class CustomersFacade extends AbstractFacade<Customers> implements CustomersFacadeLocal {

    @PersistenceContext(unitName = "shop-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomersFacade() {
        super(Customers.class);
    }
    public int login(String username, String password){
        Query query = em.createQuery("SELECT c FROM Customers c WHERE c.username = :username AND c.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Customers> list = query.getResultList();
        if(list.size() == 0){
            return 0;
        }
        else{
            return list.get(0).getCustomerID();
        }
    }
    // find customer by username
    @Override
    public int findByUsername(String username){
        Query query = em.createNamedQuery("Customers.findByUsername");
        query.setParameter("username", username);
        List<Customers> list = query.getResultList();
        return list.size();
    }
    @Override
    public List<Customers> Pagination(int pageNumber) {
        Query query = em.createQuery("SELECT p FROM Customers p ORDER BY p.customerID DESC");
        int pageSize = 10;
        query.setMaxResults(pageSize);
        query.setFirstResult((pageNumber - 1) * pageSize);
        return query.getResultList();
    }
}
