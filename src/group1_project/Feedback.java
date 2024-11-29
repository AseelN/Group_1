
package group1_project;


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
