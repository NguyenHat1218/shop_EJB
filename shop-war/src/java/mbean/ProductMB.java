/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Categories;
import entities.Customers;
import entities.ProductImages;
import entities.ProductReviews;
import entities.Products;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import sessionbean.CategoriesFacadeLocal;
import sessionbean.CustomersFacadeLocal;
import sessionbean.ProductImagesFacadeLocal;
import sessionbean.ProductReviewsFacadeLocal;
import sessionbean.ProductsFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "productMB")
@SessionScoped
public class ProductMB implements Serializable {

    @EJB
    private ProductImagesFacadeLocal productImagesFacade1;

    @EJB
    private ProductImagesFacadeLocal productImagesFacade;

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private ProductReviewsFacadeLocal productReviewsFacade;

    @EJB
    private ProductsFacadeLocal productsFacade;

    // properties
    private int productID;
    private Products product;
    private String cmtReview;
    private int rating;
    private List<ProductReviews> listReview;
    private List<ProductImages> listImages;
    private List<Products> listAllProducts;
    private int currentPage = 1;
    private Part file;
    private Part file1;
    private String productName;
    private String Description;
    private double unitPrice;
    private int Quantity;
    private int categoryID;
    private String fileName;
    private int Discount;
    private int Status;
    private String msgError;
    private String msgSuccess;

    /**
     * Creates a new instance of ProductMB
     */
    public ProductMB() {
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getCmtReview() {
        return cmtReview;
    }

    public void setCmtReview(String cmtReview) {
        this.cmtReview = cmtReview;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<ProductReviews> getListReview() {
        return listReview;
    }

    public void setListReview(List<ProductReviews> listReview) {
        this.listReview = listReview;
    }

    public List<Products> getListAllProducts() {
        return listAllProducts;
    }

    public void setListAllProducts(List<Products> listAllProducts) {
        this.listAllProducts = listAllProducts;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int Discount) {
        this.Discount = Discount;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getMsgSuccess() {
        return msgSuccess;
    }

    public void setMsgSuccess(String msgSuccess) {
        this.msgSuccess = msgSuccess;
    }

    public List<ProductImages> getListImages() {
        return listImages;
    }

    public void setListImages(List<ProductImages> listImages) {
        this.listImages = listImages;
    }

    public String seeProduct(int id) {
        productID = id;
        product = productsFacade.find(id);
        CartMB cartMB = new CartMB();
        cartMB.setSoldout(true);
        listReview = (List<ProductReviews>) product.getProductReviewsCollection();
        return "product";

    }

    // get images preview of product 
    public List<ProductImages> getPreview() {
        return productsFacade.getImages(productID);

    }

    // delete img preview
    public void delPreview(int id) {
        ProductImages pr = productImagesFacade1.find(id);
        if (pr != null) {
            productImagesFacade1.remove(pr);
            listImages = productsFacade.getImages(productID);
        }
    }

    // get list new latest product
    public List<Products> getNewProducts() {
        return productsFacade.getLatestProducts();
    }

    // get product related
    public List<Products> relatedProducts() {
        Products product = productsFacade.find(productID);
        return productsFacade.getRelatedProducts(product.getCategoryID().getCategoryID(), productID);
    }

    // list review comment
    public List<ProductReviews> productComment() {
        Products product = productsFacade.find(productID);
        return (List<ProductReviews>) product.getProductReviewsCollection();
    }

    // action comment
    public void doComment() {
        FacesContext context = FacesContext.getCurrentInstance();
        Products product = productsFacade.find(productID);
        if (context.getExternalContext().getSessionMap().get("user") != null) {
            int customerID = Integer.parseInt(context.getExternalContext().getSessionMap().get("user").toString());
            Customers customer = customersFacade.find(customerID);
            ProductReviews review = new ProductReviews();
            review.setRating(rating);
            review.setProductID(product);
            review.setMessage(cmtReview);
            review.setCustomerID(customer);
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            review.setCreatedat(timestamp);
            productReviewsFacade.create(review);
            listReview.add(review);
            cmtReview = "";
        }
    }

    // loop for star
    public List<Integer> loopStar(int loop) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < loop; i++) {
            list.add(i);
        }
        return list;
    }

    public List<Integer> loopNot(int loop) {
        List<Integer> list = new ArrayList<>();
        for (int i = 5; i > loop; i--) {
            list.add(i);
        }
        return list;
    }

    public int countPage() {
        int sum = productsFacade.count();
        int result = (int) Math.ceil((double) sum / 10); // lam tròn
        //System.out.println(result);
        return result;
    }

    public void showPagination(int page) {
        listAllProducts = productsFacade.Pagination(page);
        currentPage = page;
        System.out.println("Hello current page " + currentPage);
    }

    @PostConstruct
    public void init() {
        listAllProducts = productsFacade.Pagination(currentPage);
        System.out.println("Current page " + currentPage);
    }

    // create product
    public String createProduct() throws IOException, ServletException {
        String filename = file.getSubmittedFileName();
        int names = (int) new Date().getTime();
        String extension = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i + 1);
            extension = extension.toLowerCase();
        }
        String newName = names + "." + extension;
        InputStream input = file.getInputStream();
        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
            FileOutputStream output = new FileOutputStream("D:\\DO_AN_EJB_HK4\\shop_a18059_ejb\\shop\\shop-war\\web\\resources\\thumbnail\\" + newName);
            byte[] buf = new byte[1024];
            int len;
            while ((len = input.read(buf)) > 0) {
                output.write(buf, 0, len);
            }
            output.close();
            input.close();
            Products product = new Products();
            product.setProductName(productName);
            product.setDescription(Description);
            BigDecimal bPrice = BigDecimal.valueOf(unitPrice);
            product.setUnitPrice(bPrice);
            product.setQuantity(Quantity);
            Categories category = categoriesFacade.find(categoryID);
            product.setCategoryID(category);
            product.setThumbnail(newName);
            product.setStatus(Status);
            productsFacade.create(product);
            listAllProducts = productsFacade.Pagination(currentPage);
            if (file1 != null) {
                for (Part part : getAllParts(file1)) {
                    int j = filename.lastIndexOf('.');
                    if (j > 0) {
                        extension = filename.substring(j + 1);
                        extension = extension.toLowerCase();
                    }
                    String newPhoto = names + "." + extension;
                    InputStream fileContent = part.getInputStream();
                    if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
                        FileOutputStream output1 = new FileOutputStream("D:\\DO_AN_EJB_HK4\\shop_a18059_ejb\\shop\\shop-war\\web\\resources\\preview\\" + newPhoto);
                        byte[] buf1 = new byte[1024];
                        int len1;
                        while ((len1 = fileContent.read(buf1)) > 0) {
                            output1.write(buf1, 0, len1);
                        }
                        output1.close();
                        fileContent.close();
                        ProductImages review = new ProductImages();
                        review.setImagePath(newPhoto);
                        review.setProductID(product);
                        productImagesFacade.create(review);
                    }
                    names++;
                }
            }
            productName = "";
            Description = "";
            unitPrice = 0;
            Quantity = 1;
            Discount = 0;
            Status = 0;
            file = null;
            file1 = null;
            return "listProduct";
        } else {
            msgError = "Format of file upload is not valid, accept extention with .PNG, .JPEG, .JPG";
        }
        return "createProduct";
    }

    // get file name
    public String getFileNameFromPart(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=')
                        + 1).trim().replace("\"", "");

                return fileName;
            }
        }
        return null;
    }

    // muliti part
    public static Collection<Part> getAllParts(Part part) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (part != null) {
            return request.getParts().stream().filter(p -> part.getName().equals(p.getName())).collect(Collectors.toList());
        }
        return null;
    }

    // init reset value
    public void initReset() {
        msgError = "";
        msgSuccess = "";
    }

    // edit product
    public String editProduct(int id) {
        product = productsFacade.find(id);
        listImages =  productsFacade.getImages(id);
        Products product1 = productsFacade.find(id);
        productID = id;
        productName = product1.getProductName();
        Description = product1.getDescription();
        unitPrice = product1.getUnitPrice().doubleValue();
        categoryID = product.getCategoryID().getCategoryID();
        Quantity = product1.getQuantity();
        Status = product1.getStatus();
        return "editProduct";
    }

    // save product
    public String saveProduct() throws FileNotFoundException, IOException, ServletException {
        if (file != null) {
            String filename = file.getSubmittedFileName();
            int names = (int) new Date().getTime();
            String extension = "";
            int i = filename.lastIndexOf('.');
            if (i > 0) {
                extension = filename.substring(i + 1);
                extension = extension.toLowerCase();
            }
            String newName = names + "." + extension;
            InputStream input = file.getInputStream();
            if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
                FileOutputStream output = new FileOutputStream("D:\\DO_AN_EJB_HK4\\shop_a18059_ejb\\shop\\shop-war\\web\\resources\\thumbnail\\" + newName);
                byte[] buf = new byte[1024];
                int len;
                while ((len = input.read(buf)) > 0) {
                    output.write(buf, 0, len);
                }
                this.product.setThumbnail(newName);
            }
        }
        product.setProductName(productName);
        product.setDescription(Description);
        BigDecimal bPrice = BigDecimal.valueOf(unitPrice);
        product.setUnitPrice(bPrice);
        product.setQuantity(Quantity);
        Categories category = categoriesFacade.find(categoryID);
        product.setCategoryID(category);
        product.setStatus(Status);
        productsFacade.edit(product);
        msgSuccess = "Update data success !";
        listAllProducts = productsFacade.Pagination(currentPage);
        String filename = "";
        String extension = "";
        int names = (int) new Date().getTime();
        if (file1 != null) {
            for (Part part : getAllParts(file1)) {
                int j = part.getSubmittedFileName().lastIndexOf('.');
                if (j > 0) {
                    extension = part.getSubmittedFileName().substring(j + 1);
                    extension = extension.toLowerCase();
                }
                String newPhoto = names + "." + extension;
                InputStream fileContent = part.getInputStream();
                if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
                    FileOutputStream output = new FileOutputStream("D:\\DO_AN_EJB_HK4\\shop_a18059_ejb\\shop\\shop-war\\web\\resources\\preview\\" + newPhoto);
                    System.out.println("Yes");
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = fileContent.read(buf)) > 0) {
                        output.write(buf, 0, len);
                    }
                    ProductImages review = new ProductImages();
                    review.setImagePath(newPhoto);
                    review.setProductID(product);
                    productImagesFacade.create(review);
                }
                names++;
            }
            listImages = productsFacade.getImages(productID);
        }
        productName = "";
        Description = "";
        unitPrice = 0;
        Quantity = 1;
        Discount = 0;
        Status = 0;
        file = null;
        file1 = null;
        product = productsFacade.find(productID);
        return "listProduct";
    }
    // delete product

    public String delProduct(int id) {
        Products product = productsFacade.find(id);
        if (product != null) {
            productsFacade.remove(product);
            listAllProducts = productsFacade.findAll();
            listImages = (List<ProductImages>) product.getProductImagesCollection();
        }
        return "listProduct";
    }
}
