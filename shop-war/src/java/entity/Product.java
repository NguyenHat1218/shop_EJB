/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author HP
 */
public class Product {
    private int productID;
    private String productName;
    private String description;
    private Double unitPrice;
    private int quantity;
    private int caterogyID;
    private String thumbnail;

    public Product() {
    }

    public Product(int productID, String productName, String description, Double unitPrice, int quantity, int caterogyID, String thumbnail) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.caterogyID = caterogyID;
        this.thumbnail = thumbnail;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCaterogyID() {
        return caterogyID;
    }

    public void setCaterogyID(int caterogyID) {
        this.caterogyID = caterogyID;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", description=" + description + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", caterogyID=" + caterogyID + ", thumbnail=" + thumbnail + '}';
    }

  
    
}
