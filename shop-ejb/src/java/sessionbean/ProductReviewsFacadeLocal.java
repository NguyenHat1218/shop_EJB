/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.ProductReviews;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface ProductReviewsFacadeLocal {

    void create(ProductReviews productReviews);

    void edit(ProductReviews productReviews);

    void remove(ProductReviews productReviews);

    ProductReviews find(Object id);

    List<ProductReviews> findAll();

    List<ProductReviews> findRange(int[] range);

    int count();
    
}
