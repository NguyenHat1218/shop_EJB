/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "ProductReviews")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductReviews.findAll", query = "SELECT p FROM ProductReviews p")
    , @NamedQuery(name = "ProductReviews.findByReviewID", query = "SELECT p FROM ProductReviews p WHERE p.reviewID = :reviewID")
    , @NamedQuery(name = "ProductReviews.findByRating", query = "SELECT p FROM ProductReviews p WHERE p.rating = :rating")
    , @NamedQuery(name = "ProductReviews.findByMessage", query = "SELECT p FROM ProductReviews p WHERE p.message = :message")
    , @NamedQuery(name = "ProductReviews.findByCreatedat", query = "SELECT p FROM ProductReviews p WHERE p.createdat = :createdat")})
public class ProductReviews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private Integer reviewID;
    @Column(name = "Rating")
    private Integer rating;
    @Size(max = 250)
    @Column(name = "Message")
    private String message;
    @Column(name = "Created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdat;
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    @ManyToOne
    private Customers customerID;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne
    private Products productID;

    public ProductReviews() {
    }

    public ProductReviews(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewID != null ? reviewID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductReviews)) {
            return false;
        }
        ProductReviews other = (ProductReviews) object;
        if ((this.reviewID == null && other.reviewID != null) || (this.reviewID != null && !this.reviewID.equals(other.reviewID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProductReviews[ reviewID=" + reviewID + " ]";
    }
    
}
