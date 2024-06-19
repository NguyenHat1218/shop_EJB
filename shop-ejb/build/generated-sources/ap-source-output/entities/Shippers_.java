package entities;

import entities.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T18:16:25")
@StaticMetamodel(Shippers.class)
public class Shippers_ { 

    public static volatile SingularAttribute<Shippers, Integer> shipperID;
    public static volatile SingularAttribute<Shippers, String> phone;
    public static volatile SingularAttribute<Shippers, String> companyName;
    public static volatile SingularAttribute<Shippers, Float> freight;
    public static volatile CollectionAttribute<Shippers, Orders> ordersCollection;

}