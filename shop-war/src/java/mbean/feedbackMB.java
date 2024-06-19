/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entities.Feedbacks;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionbean.FeedbacksFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "feedbackMB")
@RequestScoped
public class feedbackMB {

    @EJB
    private FeedbacksFacadeLocal feedbacksFacade;

    /**
     * Creates a new instance of feedbackMB
     */
    private String Email;
    private String Title;
    private String Description;
    private String Message;

    public feedbackMB() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
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

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    // send feedback
    public void sendFeedback() {
        Feedbacks feedback = new Feedbacks();
        feedback.setEmail(Email);
        feedback.setTopic(Title);
        feedback.setMessage(Description);
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
        feedback.setCreatedat(timestamp);
        feedbacksFacade.create(feedback);
        Email = "";
        Title = "";
        Description = "";
        Message = "Thank for your feedback. We will contact with you soon !";
    }

}
