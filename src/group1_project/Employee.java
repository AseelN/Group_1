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
public class Employee {
     String employeeId;
     String employeeName;
     String employeeEmail;

    public Employee(String employeeId, String employeeName, String employeeEmail) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
    }
    
    public void searchProblem(String category){
        
    }
    
    public void sendFeedback(Feedback feedback){
        
    }
    
    public void sendInfo(String info){
        
    }
    
    public void receiveInfo(String info){
        
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
