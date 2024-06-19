/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.ProductImages;
import entities.Products;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface ProductsFacadeLocal {

    void create(Products products);

    void edit(Products products);

    void remove(Products products);

    Products find(Object id);

    List<Products> findAll();

    List<Products> findRange(int[] range);

    int count();

    public List<Products> getLatestProducts();

    public List<Products> getRelatedProducts(int CategoryId, int productID);

    public List<Products> showPagination(int CategoryId, int pageNumber);

    public List<Products> findWithName(String name);

    public List<Products> Pagination(int pageNumber);

    public List<ProductImages> getImages(int id);
    
}
