/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Blogs;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.Part;
import sessionbean.BlogsFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "cpBlog")
@SessionScoped
public class cpBlog implements Serializable {

    @EJB
    private BlogsFacadeLocal blogsFacade;
    private int currentPage = 1;
    private List<Blogs> showAllBlog;
    private int blogID;
    private String Title;
    private String Description;
    private String Thumbnail;
    private Part File;

    /**
     * Creates a new instance of cpBlog
     */
    public cpBlog() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Part getFile() {
        return File;
    }

    public void setFile(Part File) {
        this.File = File;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String Thumbnail) {
        this.Thumbnail = Thumbnail;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Blogs> getShowAllBlog() {
        return showAllBlog;
    }

    public void setShowAllBlog(List<Blogs> showAllBlog) {
        this.showAllBlog = showAllBlog;
    }

    @PostConstruct
    public void init() {
        showAllBlog = blogsFacade.Pagination(currentPage);
        System.out.println("Current page " + currentPage);
    }

    // save
    public String createBlog() throws IOException {
        Blogs blog = new Blogs();
        String filename = File.getSubmittedFileName();
        int names = (int) new Date().getTime();
        String extension = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i + 1);
            extension = extension.toLowerCase();
        }
        String newName = names + "." + extension;
        InputStream input = File.getInputStream();
        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
            FileOutputStream output = new FileOutputStream("E:\\EJB\\shop\\shop-war\\web\\resources\\blog\\" + newName);
            byte[] buf = new byte[1024];
            int len;
            while ((len = input.read(buf)) > 0) {
                output.write(buf, 0, len);
            }
            output.close();
            input.close();
            blog.setTitle(Title);
            blog.setDescription(Description);
            blog.setThumbnail(newName);
            blog.setAccountID(1);
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            blog.setCreatedat(timestamp);
            blogsFacade.create(blog);
            showAllBlog = blogsFacade.Pagination(currentPage);
            Title = "";
            Description = "";
            File = null;
        }
        return "listBlog";
    }

    // edit blog
    public String editBlog(int id) {
        Blogs blog = blogsFacade.find(id);
        blogID = blog.getBlogID();
        Title = blog.getTitle();
        Thumbnail = blog.getThumbnail();
        Description = blog.getDescription();
        return "editBlog";
    }

    // save blog
    public String saveBlog() throws IOException {
        Blogs blog = blogsFacade.find(blogID);
        if (File != null) {
            String filename = File.getSubmittedFileName();
            int names = (int) new Date().getTime();
            String extension = "";
            int i = filename.lastIndexOf('.');
            if (i > 0) {
                extension = filename.substring(i + 1);
                extension = extension.toLowerCase();
            }
            String newName = names + "." + extension;
            InputStream input = File.getInputStream();
            if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
                FileOutputStream output = new FileOutputStream("E:\\EJB\\shop\\shop-war\\web\\resources\\blog\\" + newName);
                byte[] buf = new byte[1024];
                int len;
                while ((len = input.read(buf)) > 0) {
                    output.write(buf, 0, len);
                }
                blog.setThumbnail(newName);
            }
        }
        blog.setTitle(Title);
        blog.setDescription(Description);
        blogsFacade.edit(blog);
        showAllBlog = blogsFacade.Pagination(currentPage);
        Title = "";
        Description = "";
        File = null;
        return "listBlog";
    }

    // delete blog
    public String delBlog(int id) {
        Blogs blog = blogsFacade.find(id);
        try {
            blogsFacade.remove(blog);
            showAllBlog = blogsFacade.Pagination(currentPage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "listBlog";
    }

    public int countPage() {
        int sum = blogsFacade.count();
        int result = (int) Math.ceil((double) sum / 10); // lam tròn
        //System.out.println(result);
        return result;
    }

    public void showPagination(int page) {
        showAllBlog = blogsFacade.Pagination(page);
        currentPage = page;
        System.out.println("Hello current page " + currentPage);
    }
}
