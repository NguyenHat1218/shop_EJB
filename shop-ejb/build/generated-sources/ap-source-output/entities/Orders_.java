package entities;

import entities.Customers;
import entities.OrderDetails;
import entities.Orders;
import entities.Shippers;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T18:16:25")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Orders> orders1;
    public static volatile SingularAttribute<Orders, String> shipCity;
    public static volatile SingularAttribute<Orders, Integer> orderID;
    public static volatile CollectionAttribute<Orders, OrderDetails> orderDetailsCollection;
    public static volatile SingularAttribute<Orders, BigDecimal> freight;
    public static volatile SingularAttribute<Orders, String> shipCountry;
    public static volatile SingularAttribute<Orders, String> shipAddress;
    public static volatile SingularAttribute<Orders, String> shipPhone;
    public static volatile SingularAttribute<Orders, Shippers> shipVia;
    public static volatile SingularAttribute<Orders, Customers> customerID;
    public static volatile SingularAttribute<Orders, Orders> orders;
    public static volatile SingularAttribute<Orders, Date> orderDate;
    public static volatile SingularAttribute<Orders, Integer> status;

}