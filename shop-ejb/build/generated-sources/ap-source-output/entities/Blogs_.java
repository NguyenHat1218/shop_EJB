package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T18:16:25")
@StaticMetamodel(Blogs.class)
public class Blogs_ { 

    public static volatile SingularAttribute<Blogs, Integer> accountID;
    public static volatile SingularAttribute<Blogs, Date> createdat;
    public static volatile SingularAttribute<Blogs, String> thumbnail;
    public static volatile SingularAttribute<Blogs, String> description;
    public static volatile SingularAttribute<Blogs, String> title;
    public static volatile SingularAttribute<Blogs, Integer> blogID;

}