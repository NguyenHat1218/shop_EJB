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
import javax.persistence.Lob;
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
@Table(name = "Blogs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Blogs.findAll", query = "SELECT b FROM Blogs b")
    , @NamedQuery(name = "Blogs.findByBlogID", query = "SELECT b FROM Blogs b WHERE b.blogID = :blogID")
    , @NamedQuery(name = "Blogs.findByTitle", query = "SELECT b FROM Blogs b WHERE b.title = :title")
    , @NamedQuery(name = "Blogs.findByAccountID", query = "SELECT b FROM Blogs b WHERE b.accountID = :accountID")
    , @NamedQuery(name = "Blogs.findByThumbnail", query = "SELECT b FROM Blogs b WHERE b.thumbnail = :thumbnail")
    , @NamedQuery(name = "Blogs.findByCreatedat", query = "SELECT b FROM Blogs b WHERE b.createdat = :createdat")})
public class Blogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BlogID")
    private Integer blogID;
    @Size(max = 100)
    @Column(name = "Title")
    private String title;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Description")
    private String description;
    @Column(name = "AccountID")
    private Integer accountID;
    @Size(max = 150)
    @Column(name = "Thumbnail")
    private String thumbnail;
    @Column(name = "Created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdat;

    public Blogs() {
    }

    public Blogs(Integer blogID) {
        this.blogID = blogID;
    }

    public Integer getBlogID() {
        return blogID;
    }

    public void setBlogID(Integer blogID) {
        this.blogID = blogID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blogID != null ? blogID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Blogs)) {
            return false;
        }
        Blogs other = (Blogs) object;
        if ((this.blogID == null && other.blogID != null) || (this.blogID != null && !this.blogID.equals(other.blogID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Blogs[ blogID=" + blogID + " ]";
    }
    
}
