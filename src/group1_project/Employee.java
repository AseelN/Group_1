/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group1_project;
 
import java.util.ArrayList;
import java.util.List;

public class Employee {
    
     String employeeId;
     private String employeeName;
     private String employeeEmail;
     private List<Comment> comments;

    public Employee(String employeeId, String employeeName, String employeeEmail) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.comments = new ArrayList<>(); 
    }

    public Employee(String employeeId) {
        this(employeeId, "defaultName", "defaultEmail"); 
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }
}

    
    

