package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T18:16:25")
@StaticMetamodel(GeneralQuestions.class)
public class GeneralQuestions_ { 

    public static volatile SingularAttribute<GeneralQuestions, Integer> questionID;
    public static volatile SingularAttribute<GeneralQuestions, String> answer;
    public static volatile SingularAttribute<GeneralQuestions, String> topic;
    public static volatile SingularAttribute<GeneralQuestions, Integer> status;

}