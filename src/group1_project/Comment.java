package group1_project;


class Comment {
    
    private static int idCounter = 1; 
    private String commentId;         
    private String comment;           
    private Employee employee;        

    
    public Comment(String comment) {
        this.commentId = generateCommentId(); 
        this.comment = comment;
    }

    
    public Comment(String comment, Employee employee) {
        this.commentId = generateCommentId(); 
        this.comment = comment;
        this.employee = employee;
    }

    
    private static String generateCommentId() {
        return "C" + (idCounter++); 
    }

    
    public String getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
