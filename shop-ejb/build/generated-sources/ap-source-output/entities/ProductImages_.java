package entities;

import entities.Products;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T18:16:25")
@StaticMetamodel(ProductImages.class)
public class ProductImages_ { 

    public static volatile SingularAttribute<ProductImages, Integer> imageID;
    public static volatile SingularAttribute<ProductImages, Products> productID;
    public static volatile SingularAttribute<ProductImages, String> imagePath;

}