/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group1_project;

/**
 *
 * @author LayanM
 */
public class Feedback {
     Admin admin;
     Comment comment;
     int feedbackId;

    public Feedback(Admin admin, Comment comment, int feedbackId) {
        this.admin = admin;
        this.comment = comment;
        this.feedbackId = feedbackId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }
    
    
    
}
