package group1_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Group1_project {

public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        
        System.out.println("Enter a keyword or phrase to search for information:");
        String keyword = in.nextLine().trim().toLowerCase(); // Read the keyword from the user, convert to lowercase
        
        System.out.println(validation(keyword, in));
        
        try {
            System.out.println(searchInfo("information.txt", keyword)); // Call the search method with the file name and keyword
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        
        
    
    ////////////////////////////////////////////////////////////////////////////
        File comments = new File("comments.txt");
     
        try (FileWriter write = new FileWriter(comments, true)){

            System.out.print("Welcome, please enter your ID: ");
            String employeeId = in.nextLine();
           
            Employee emp = new Employee(employeeId, "defaultName", "defaultEmail");

            System.out.print("Would you like to add a comment? (yes/no): ");
            String answer = in.nextLine();

            if (answer.equalsIgnoreCase("yes")) {
                
                addComment(write, in, emp);
            } else {
                System.out.println("Thank you, hope you have a great experience!");
            }} 
        catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
         String fileName = "comments.txt";

    try {
        // Call the reviewComments method
        List<String> adminComments = reviewComments(fileName);

        // Display comments for admin review
        if (adminComments.isEmpty()) {
            System.out.println("No comments available for review.");
        } else {
            System.out.println("Comments for review:");
            for (String entry : adminComments) {
                System.out.println(entry);
            }
        }
    } catch (IOException e) {
        System.out.println("An error occurred while reviewing comments: " + e.getMessage());
    }
    
        in.close();
}
    ////////////////////////////////////////////////////////////////////////////
    public static String validation(String str, Scanner in){
        //input empty or digit
        if(str.isEmpty() || !str.matches("[a-zA-Z ]+")){
            return "An error occured,please try again :"; 

        }
        else 
            return "Searching for solutions...";
            
    }
    
    public static String searchInfo(String fileName, String keyword) throws IOException {
        
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        
        StringBuilder result = new StringBuilder();
        String line;
        boolean isPrinting = false;        
        boolean isMatchFound = false;      
        
        String[] searchWords = keyword.split("\\s+"); //Split the keyword into words 

        
        while ((line = reader.readLine()) != null) {
            
            line = line.trim();
             if (line.startsWith("&") && line.endsWith("&")) {
                String sectionKeyword = line.substring(1, line.length() - 1).toLowerCase(); 
                boolean allWordsMatch = true;
                
                for (String word : searchWords) {
                    if (!sectionKeyword.contains(word)) {//If any word is missing, set the flag to false
                        allWordsMatch = false;
                        break;
                    }
                }

                if (allWordsMatch) {
                    isPrinting = true; //Start printing after the match
                    isMatchFound = true; //Found at least one match
                    result.append("\nInformation found for keyword: ").append(keyword).append("\n\n");
                    result.append(sectionKeyword).append("\n\n"); 
                } else {
                    isPrinting = false; //Do not print
                }
                continue;
            }

            
            if (isPrinting) {
                if (line.contains("*")) {
                    isPrinting = false; //Stop printing after the * symbol
                    result.append(line).append("\n"); 
                } else {
                    result.append(line).append("\n");
                }
            }
        }

        if (!isMatchFound) {
            result.append("No information found for the keyword: ").append(keyword);
        }

        reader.close(); 
        
        return result.toString();
    }
    ////////////////////////////////////////////////////////////////////////////
    public static String addComment(FileWriter writer, Scanner in, Employee emp) throws IOException {
    System.out.println("Please enter your comment:");
    String commentText = in.nextLine();

    Comment comment = new Comment(commentText, emp);

    emp.addComment(comment);
    
    writer.append("Employee ID: ").append(emp.getEmployeeId()).append("\n");
    for (Comment c : emp.getComments()) {
        writer.append("- ").append(c.getComment()).append(" (Comment ID: ").append(c.getCommentId()).append(")\n");
    }

    System.out.println("Thank you. Your comment ID is: " + comment.getCommentId());

    return "Comment added successfully with Comment ID: " + comment.getCommentId();
}
    
    public static List<String> reviewComments(String fileName) throws IOException {
    List<String> reviewEntries = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        String currentEmployeeId = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("Employee ID: ")) {
                currentEmployeeId = line.substring(13);
            } else if (line.startsWith("- ") && currentEmployeeId != null) {
                String commentText = line.substring(2);
                boolean hasFeedback = commentText.contains("(Feedback)"); // Check feedback flag
                reviewEntries.add("Employee ID: " + currentEmployeeId + " | Comment: " + commentText +
                        " | Has Feedback: " + hasFeedback);
            }
        }
    }

    return reviewEntries;
}
}
    

