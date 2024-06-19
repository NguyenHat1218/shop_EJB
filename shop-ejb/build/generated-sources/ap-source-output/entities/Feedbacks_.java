package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T18:16:25")
@StaticMetamodel(Feedbacks.class)
public class Feedbacks_ { 

    public static volatile SingularAttribute<Feedbacks, Date> createdat;
    public static volatile SingularAttribute<Feedbacks, Integer> feedbackID;
    public static volatile SingularAttribute<Feedbacks, String> topic;
    public static volatile SingularAttribute<Feedbacks, String> message;
    public static volatile SingularAttribute<Feedbacks, String> email;

}