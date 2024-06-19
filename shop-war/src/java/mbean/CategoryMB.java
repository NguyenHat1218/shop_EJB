/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Categories;
import entities.Products;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import sessionbean.CategoriesFacadeLocal;
import sessionbean.ProductsFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "categoryMB")
@SessionScoped
public class CategoryMB implements Serializable {

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    private int CategoryID;
    private List<Categories> list;
    private List<Products> listProducts;
    private int currentPage = 1;
    private String categoryName;
    private int Status;
    private String title;

    /**
     * Creates a new instance of CategoryMB
     */
    public CategoryMB() {

    }

    public List<Categories> getList() {
        return list;
    }

    public void setList(List<Categories> list) {
        this.list = list;
    }

    public List<Products> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Products> listProducts) {
        this.listProducts = listProducts;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    

    @PostConstruct
    public void init() {
        list = categoriesFacade.findAll();
    }

    // redirect to category
    public String toCategory(int id) {
        listProducts = productsFacade.showPagination(id, currentPage);
        Categories cate = categoriesFacade.find(id);
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("category", id);
        title = cate.getCategoryName();
        return "category";
    }

    // count product in catgỏy
    public int countProductsInCate() {
        //System.out.print("category " + CategoryId);
        FacesContext context = FacesContext.getCurrentInstance();
        int cate = Integer.parseInt(context.getExternalContext().getSessionMap().get("category").toString());
        try {
            Categories category = categoriesFacade.find(cate);
            if (category.getProductsCollection().isEmpty()) {
                return 0;
            }
            int count = category.getProductsCollection().size();
            return count;
        } catch (Exception ex) {
            return 0;
        }
    }

    // count product of categories
    public int countProducts(int CategoryId) {
        //System.out.print("category " + CategoryId);
        try {
            Categories category = categoriesFacade.find(CategoryId);
            if (category.getProductsCollection().isEmpty()) {
                return 0;
            }
            int count = category.getProductsCollection().size();
            return count;
        } catch (Exception ex) {
            return 0;
        }
    }

    // list products of category
    public List<Products> findProductByCategoryId(int id) {
        List<Products> list = new ArrayList<>();
        Categories category = categoriesFacade.find(id);
        if (category != null) {
            list = (List<Products>) category.getProductsCollection();
            return list;
        }
        return null;
    }

    /// list product pagination
    public void showPagination(int page) {
        FacesContext context = FacesContext.getCurrentInstance();
        int cate = Integer.parseInt(context.getExternalContext().getSessionMap().get("category").toString());
        System.out.println("Show categoryID " + cate);
        Categories category = categoriesFacade.find(cate);
        currentPage = page;
        System.out.println("Hello current page " + currentPage);
        if (category != null) {
            listProducts = productsFacade.showPagination(cate, currentPage);
        }

    }

    // count page, dem trang
    public int countPage() {
        FacesContext context = FacesContext.getCurrentInstance();
        int cate = Integer.parseInt(context.getExternalContext().getSessionMap().get("category").toString());
        try {
            Categories category = categoriesFacade.find(cate);
            if (category.getProductsCollection().isEmpty()) {
                return 0;
            }
            int count = category.getProductsCollection().size();
            int result = (int) Math.ceil((double) count / 8); // lam tròn
            return result;
        } catch (Exception ex) {
            return 0;
        }
    }
    // 
    // create new category
    public String createNew() {
        try {
            Categories category = new Categories();
            category.setCategoryName(categoryName);
            category.setStatus(Status);
            categoriesFacade.create(category);
            list.add(category);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        categoryName = "";
        Status = 0;
        return "listCategory";
    }
    // delêt category
    public void deleteCategory(int id){
        Categories category = categoriesFacade.find(id);
        if(category != null){
            try{
                categoriesFacade.remove(category);
                list = categoriesFacade.findAll();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    // edit category
    public String editCategory(int categoryID){
        CategoryID = categoryID;
        try{
            Categories category = categoriesFacade.find(categoryID);
            categoryName = category.getCategoryName();
            Status = category.getStatus();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return "editCategory";
    }
    // save category
     public String saveCategory(){
        try{
            Categories category = categoriesFacade.find(CategoryID);
            category.setCategoryName(categoryName);
            category.setStatus(Status);
            categoriesFacade.edit(category);
            list = categoriesFacade.findAll();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        categoryName = "";
        Status = 0;
        return "listCategory";
    }
    
}
