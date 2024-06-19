/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Categories;
import entities.ProductImages;
import entities.Products;
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
public class ProductsFacade extends AbstractFacade<Products> implements ProductsFacadeLocal {

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @PersistenceContext(unitName = "shop-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }
    // latest product
    
    @Override
    public List<Products> getLatestProducts(){
        Query query = em.createQuery("SELECT p FROM Products p ORDER BY p.productID DESC");
        return query.setMaxResults(9).getResultList();
    }
    
    // product related
    @Override
    public List<Products> getRelatedProducts(int CategoryId, int productID){
        Query query = em.createQuery("SELECT p FROM Products p WHERE p.categoryID = :categoryID AND p.productID != :productID ORDER BY p.productID DESC");
        Categories category = categoriesFacade.find(CategoryId);
        query.setParameter("categoryID", category);
        query.setParameter("productID", productID);
        query.setMaxResults(4);
        return query.getResultList();
    }
    // pagination
    @Override
    public List<Products> showPagination(int CategoryId, int pageNumber) {
        Query query = em.createQuery("SELECT p FROM Products p WHERE p.status = 0 AND p.categoryID = :categoryID ORDER BY p.productID DESC");
        Categories category = categoriesFacade.find(CategoryId);
        query.setParameter("categoryID", category);
        int pageSize = 8;
        query.setMaxResults(pageSize);
        query.setFirstResult((pageNumber - 1) * pageSize);
        return query.getResultList();
    }
    // find product
    @Override
    public List<Products> findWithName(String name) {
    Query query = em.createQuery("SELECT p FROM Products p WHERE p.productName LIKE :productName");
    query.setParameter("productName", "%"+name+"%");
    return query.getResultList();
    }
    @Override
    public List<Products> Pagination(int pageNumber) {
        Query query = em.createQuery("SELECT p FROM Products p ORDER BY p.productID DESC");
        int pageSize = 10;
        query.setMaxResults(pageSize);
        query.setFirstResult((pageNumber - 1) * pageSize);
        return query.getResultList();
    }
    
    @Override
    public List<ProductImages> getImages(int id){
         Query query = em.createQuery("SELECT i FROM ProductImages i WHERE i.productID = :productID");
         Products product = ProductsFacade.super.find(id);
         query.setParameter("productID", product);
         return query.getResultList();
    }
}
