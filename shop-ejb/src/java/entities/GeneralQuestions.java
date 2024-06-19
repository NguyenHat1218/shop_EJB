/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "GeneralQuestions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneralQuestions.findAll", query = "SELECT g FROM GeneralQuestions g")
    , @NamedQuery(name = "GeneralQuestions.findByQuestionID", query = "SELECT g FROM GeneralQuestions g WHERE g.questionID = :questionID")
    , @NamedQuery(name = "GeneralQuestions.findByTopic", query = "SELECT g FROM GeneralQuestions g WHERE g.topic = :topic")
    , @NamedQuery(name = "GeneralQuestions.findByAnswer", query = "SELECT g FROM GeneralQuestions g WHERE g.answer = :answer")
    , @NamedQuery(name = "GeneralQuestions.findByStatus", query = "SELECT g FROM GeneralQuestions g WHERE g.status = :status")})
public class GeneralQuestions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionID")
    private Integer questionID;
    @Size(max = 150)
    @Column(name = "Topic")
    private String topic;
    @Size(max = 2147483647)
    @Column(name = "Answer")
    private String answer;
    @Column(name = "Status")
    private Integer status;

    public GeneralQuestions() {
    }

    public GeneralQuestions(Integer questionID) {
        this.questionID = questionID;
    }

    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionID != null ? questionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneralQuestions)) {
            return false;
        }
        GeneralQuestions other = (GeneralQuestions) object;
        if ((this.questionID == null && other.questionID != null) || (this.questionID != null && !this.questionID.equals(other.questionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.GeneralQuestions[ questionID=" + questionID + " ]";
    }
    
}
