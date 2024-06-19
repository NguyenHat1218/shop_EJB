/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Blogs;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionbean.BlogsFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "blogMB")
@RequestScoped
public class blogMB {

    @EJB
    private BlogsFacadeLocal blogsFacade;

    /**
     * Creates a new instance of blogMB
     */
    private Blogs blog;
    
    public blogMB() {
    }

    public Blogs getBlog() {
        return blog;
    }

    public void setBlog(Blogs blog) {
        this.blog = blog;
    }
    
    public String viewBlog(int id){
        blog = blogsFacade.find(id);
        return "blog";
    }
}
