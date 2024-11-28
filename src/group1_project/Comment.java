package group1_project;


class Comment {
    String commentId;
    String comment;
    Employee employee;
    boolean feedbackResponded; 

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(String commentId, Employee employee, boolean feedbackResponded) {
        this.commentId = commentId;
        this.employee = employee;
        this.feedbackResponded = false;
    }
    
    
    
    
}
