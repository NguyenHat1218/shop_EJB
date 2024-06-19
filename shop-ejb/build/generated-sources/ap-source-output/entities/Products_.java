package entities;

import entities.Categories;
import entities.OrderDetails;
import entities.ProductImages;
import entities.ProductReviews;
import entities.Products;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-18T18:16:25")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, BigDecimal> unitPrice;
    public static volatile SingularAttribute<Products, String> thumbnail;
    public static volatile SingularAttribute<Products, Integer> quantity;
    public static volatile SingularAttribute<Products, Products> products1;
    public static volatile SingularAttribute<Products, Integer> productID;
    public static volatile CollectionAttribute<Products, ProductReviews> productReviewsCollection;
    public static volatile CollectionAttribute<Products, OrderDetails> orderDetailsCollection;
    public static volatile SingularAttribute<Products, String> description;
    public static volatile SingularAttribute<Products, Float> discount;
    public static volatile SingularAttribute<Products, String> productName;
    public static volatile CollectionAttribute<Products, ProductImages> productImagesCollection;
    public static volatile SingularAttribute<Products, Products> products;
    public static volatile SingularAttribute<Products, Categories> categoryID;
    public static volatile SingularAttribute<Products, Integer> status;

}