package entities;

import entities.Orders;
import entities.ProductReviews;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T18:16:25")
@StaticMetamodel(Customers.class)
public class Customers_ { 

    public static volatile SingularAttribute<Customers, String> birthday;
    public static volatile SingularAttribute<Customers, String> address;
    public static volatile SingularAttribute<Customers, String> gender;
    public static volatile CollectionAttribute<Customers, ProductReviews> productReviewsCollection;
    public static volatile CollectionAttribute<Customers, Orders> ordersCollection;
    public static volatile SingularAttribute<Customers, Date> createdat;
    public static volatile SingularAttribute<Customers, String> password;
    public static volatile SingularAttribute<Customers, Integer> post;
    public static volatile SingularAttribute<Customers, String> phone;
    public static volatile SingularAttribute<Customers, String> rights;
    public static volatile SingularAttribute<Customers, Integer> customerID;
    public static volatile SingularAttribute<Customers, Integer> block;
    public static volatile SingularAttribute<Customers, String> fullname;
    public static volatile SingularAttribute<Customers, String> favorite;
    public static volatile SingularAttribute<Customers, String> email;
    public static volatile SingularAttribute<Customers, String> username;

}