/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.ProductImages;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface ProductImagesFacadeLocal {

    void create(ProductImages productImages);

    void edit(ProductImages productImages);

    void remove(ProductImages productImages);

    ProductImages find(Object id);

    List<ProductImages> findAll();

    List<ProductImages> findRange(int[] range);

    int count();
    
}
