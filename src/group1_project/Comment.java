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
class Comment {
    String commentId;
    Employee employee;
    boolean feedbackResponded; 

    public Comment(String commentId, Employee employee, boolean feedbackResponded) {
        this.commentId = commentId;
        this.employee = employee;
        this.feedbackResponded = false;
    }
    
    
    
    
}
