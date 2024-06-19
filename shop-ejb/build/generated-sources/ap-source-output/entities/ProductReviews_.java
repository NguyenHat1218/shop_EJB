package entities;

import entities.Customers;
import entities.Products;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T18:16:25")
@StaticMetamodel(ProductReviews.class)
public class ProductReviews_ { 

    public static volatile SingularAttribute<ProductReviews, Date> createdat;
    public static volatile SingularAttribute<ProductReviews, Products> productID;
    public static volatile SingularAttribute<ProductReviews, Integer> rating;
    public static volatile SingularAttribute<ProductReviews, Customers> customerID;
    public static volatile SingularAttribute<ProductReviews, String> message;
    public static volatile SingularAttribute<ProductReviews, Integer> reviewID;

}