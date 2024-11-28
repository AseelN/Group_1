package group1_project;


class Comment {
    
    private static int idCounter = 1; // Static counter to generate unique IDs
    private String commentId;         // Unique ID for the comment
    private String comment;           // The actual comment text
    private Employee employee;        // The associated employee

    // Constructor 1: Takes only the comment text
    public Comment(String comment) {
        this.commentId = generateCommentId(); // Automatically generate the comment ID
        this.comment = comment;
    }

    // Constructor 2: Takes the comment text and the associated employee
    public Comment(String comment, Employee employee) {
        this.commentId = generateCommentId(); // Automatically generate the comment ID
        this.comment = comment;
        this.employee = employee;
    }

    // Generate a unique comment ID
    private static String generateCommentId() {
        return "C" + (idCounter++); // Prefix "C" with an incrementing number
    }

    // Getters and Setters (optional)
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
