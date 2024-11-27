package group1_project;
import java.util.Scanner;
public class Group1_project {

public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Choose category to help search for information:");
        String[] categories = { 
            "sahl system", 
            "desktop interface", 
            "security interface", 
            "ip address connfigurtion", 
            "change password" 
        };
        
        for(int i = 0; i < categories.length; i ++){
            System.out.println(categories[i]);
        }
        
        String str = in.next();
        String what = validation(categories, str);
        
    }
    
    public static String validation(String [] cat,String str){
                int t= -1;
        //input empty or digit
        if(str.isEmpty() || !str.matches("[a-zA-Z]+"))
            return "An error occured,please try again.";

        
        for(int i = 0; i < cat.length ; i++){
            if(cat[i].equalsIgnoreCase(str))
                t = i;
           
        }
        
        if(t != -1)
            return "What can we help you with?";
        else
            return "Not found, try again.";
    }
 
    
}
    

