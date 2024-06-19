/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Feedbacks;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.FeedbacksFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "cpFeedback")
@SessionScoped
public class cpFeedback implements Serializable {

    @EJB
    private FeedbacksFacadeLocal feedbacksFacade;

    /**
     * Creates a new instance of cpFeedback
     */
    private int currentPage = 1;
    private List<Feedbacks> listFeedback;
    private Feedbacks feedback;
    
    
    public cpFeedback() {
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Feedbacks> getListFeedback() {
        return listFeedback;
    }

    public void setListFeedback(List<Feedbacks> listFeedback) {
        this.listFeedback = listFeedback;
    }

    public Feedbacks getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedbacks feedback) {
        this.feedback = feedback;
    }
    @PostConstruct
    public void init() {
        listFeedback = feedbacksFacade.Pagination(currentPage);
    }
    
    public void showPagination(int page) {
        listFeedback = feedbacksFacade.Pagination(page);
        currentPage = page;
    }
    // count page, dem trang
    public int countPage() {
        int sum = feedbacksFacade.count();
        int result = (int) Math.ceil((double) sum / 10); // lam tròn
        //System.out.println(result);
        return result;
    }
    // delete feedback
    public void delFeedback(int id){
        Feedbacks fb = feedbacksFacade.find(id);
        if(fb != null){
            feedbacksFacade.remove(fb);
        }
        listFeedback = feedbacksFacade.Pagination(currentPage);
    }
    // see details
    public String seeDetails(int id){
        feedback = feedbacksFacade.find(id);
        return "seeFeedback";
    }
}
