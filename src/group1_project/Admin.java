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
public class Admin {
     String adminId;
     String adminName;
     String adminEmail;

    public Admin(String adminId, String adminName, String adminEmail) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminEmail = adminEmail;
    }
    
    public void updateSolution(Solution solution, String newContent) {
        solution.setContent(newContent);
        
    }
    
    public void alertEmployees(Notification notification) {
        
    }

    public void sendNotification(Notification notification) {
        
    }

    public String getAdminId() {
        return adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }
    
    
}

