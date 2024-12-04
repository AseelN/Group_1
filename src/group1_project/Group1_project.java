package group1_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Group1_project {

public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Welcome!");
    System.out.print("Enter your user ID (A for Admin, E for Employee): ");
    String userId = in.nextLine().trim();
    
    //admin or employee
    userAccess(userId, in);
}

//---------------------------Check who is entering the system----------------------
public static void userAccess(String userId, Scanner in) {
    if (userId != null && !userId.isEmpty()) {
        char userType = userId.charAt(0);
        if (userType == 'E') {
            employeeMenu(userId, in);//employee
        } else if (userType == 'A') {
            adminMenu(in, new Admin(userId));//admin
        } else {
            System.out.println("Invalid user ID prefix.");
        }
    } else {
        System.out.println("Invalid user ID.");  
        }
}

//-------------------------------------Menu-------------------------------------
//Employee
public static void employeeMenu(String employeeId, Scanner in) {
    Employee employee = new Employee(employeeId, "defaultName", "defaultEmail");

    while (true) {
        displayEmployeeMenu(); 

        String choice = in.nextLine().trim();
        if (choice.equals("1")) {
            handleSearchInformation(in); 
        } else if (choice.equals("2")) {
            handleSendComment(in, employee); 
        } else if (choice.equals("3")) {
            System.out.println("Thank you! Hope you have a great experience.");
            break; 
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}

public static void displayEmployeeMenu() {
    System.out.println("\nEmployee Menu:");
    System.out.println("1. Search for Information");
    System.out.println("2. Send a Comment");
    System.out.println("3. Exit");
    System.out.print("Enter your choice: ");
}
//Admin
 public static void adminMenu(Scanner in, Admin admin) {
    while (true) {
        displayAdminMenu();
        String choice = in.nextLine().trim();

        if (choice.equals("1")) {
            viewCommentsWithoutFeedback(in, admin);
        } else if (choice.equals("2")) {
            exitAdminMenu();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}
 
private static void displayAdminMenu() {
    System.out.println("\nAdmin Menu:");
    System.out.println("1. View Comments Without Feedback");
    System.out.println("2. Exit");
    System.out.print("Enter your choice: ");
}

//-------------------------------------Search-----------------------------------
public static void handleSearchInformation(Scanner in) {
    while (true) {
        System.out.print("Enter a keyword to search for information: ");
        String keyword = in.nextLine().trim().toLowerCase();

        if (validation(keyword, in).equals("Searching for solutions...")) {
            try {
                System.out.println(searchInfo("information.txt", keyword));
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        }

        System.out.print("Do you want to search for another keyword? (yes/no): ");
        if (!in.nextLine().trim().equalsIgnoreCase("yes")) {
            break;
        }
    }
}

public static String validation(String str, Scanner in){
        //input empty or digit
        if(str.isEmpty() || !str.matches("[a-zA-Z ]+")){
            return "An error occured,please try again :"; 

        }
        else 
            return "Searching for solutions...";
            
    }
    
public static String searchInfo(String fileName, String keyword) throws IOException {

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        StringBuilder result = new StringBuilder();
        boolean isPrinting = false;  
        boolean isMatchFound = false; 

        String[] searchWords = keyword.split("\\s+");  

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();


            if (isHeader(line)) {
                String sectionKeyword = getHeaderKeyword(line);
                isPrinting = containsAllWords(sectionKeyword, searchWords);
                if (isPrinting) {
                    isMatchFound = true;
                    addHeaderToResult(result, keyword, sectionKeyword);
                }
                continue; 
            }


            if (isPrinting) {
                if (line.contains("*")) {
                    isPrinting = false;  // Stop printing when "*" is found
                }
                result.append(line).append("\n");
            }
        }


        if (!isMatchFound) {
            result.append("No information found for the keyword: ").append(keyword);
        }

        return result.toString();
    }
}

//find header
private static boolean isHeader(String line) {
    return line.startsWith("&") && line.endsWith("&");
}

//find keyword in header
private static String getHeaderKeyword(String line) {
    return line.substring(1, line.length() - 1).toLowerCase();  
}


//check if keyword matches in the header
private static boolean containsAllWords(String sectionKeyword, String[] searchWords) {
    for (String word : searchWords) {
        if (!sectionKeyword.contains(word)) {
            return false;  
        }
    }
    return true;
}


    //send comment
private static void handleSendComment(Scanner in, Employee employee) {
    try (FileWriter writer = new FileWriter("comments.txt", true)) {
        addComment(writer, in, employee);
    } catch (IOException e) {
        System.out.println("An error occurred while saving the comment: " + e.getMessage());
    }
}
  
    //view comments without feedback
    private static void viewCommentsWithoutFeedback(Scanner in, Admin admin) {
    try {
        List<String> comments = reviewComments("comments.txt");

        if (comments.isEmpty()) {
            System.out.println("No comments available for review.");
            return;
        }

        displayComments(comments);

        if (askForFeedback(in)) {
            String result = provideFeedback("comments.txt", in, admin);
            System.out.println(result);
        }
    } catch (IOException e) {
        System.out.println("An error occurred while reviewing comments: " + e.getMessage());
    }
}

    //exit menu
    private static void exitAdminMenu() {
    System.out.println("Exiting Admin Menu...");
    System.exit(0); 
}

    //display comments 
    private static void displayComments(List<String> comments) {
    System.out.println("Comments without feedback:");
    for (String entry : comments) {
        System.out.println(entry);
    }
}

    //ask for feedback 
    private static boolean askForFeedback(Scanner in) {
    System.out.print("Do you want to provide feedback for any comment? (yes/no): ");
    return in.nextLine().trim().equalsIgnoreCase("yes");
}
///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////   


private static void addHeaderToResult(StringBuilder result, String keyword, String sectionKeyword) {
    result.append("\nInformation found for keyword: ").append(keyword).append("\n\n");
    result.append(sectionKeyword).append("\n\n");
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
  public static String provideFeedback(String filePath, Scanner in, Admin admin) throws IOException {
    if (admin == null || admin.getAdminId() == null) {
        return "Admin details are missing. Unable to provide feedback.";
    }

    File file = new File(filePath);
    if (!file.exists()) {
        return "File not found: " + filePath;
    }

    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
    }

    System.out.println("Enter the Comment ID to provide feedback:");
    String commentId = in.nextLine().trim();

    if (!commentId.matches("C\\d+")) {
        return "Invalid Comment ID format";
    }

    boolean commentFound = false;
    for (int i = 0; i < lines.size(); i++) {
        if (lines.get(i).contains("(Comment ID: " + commentId + ")")) {
            commentFound = true;

            // Add feedback below the comment
            System.out.println("Enter your feedback:");
            String feedback = in.nextLine().trim();

            String feedbackLine = "  Admin Feedback by Admin ID: " + admin.getAdminId() + "\n" +
                                  "  Feedback: " + feedback;
            lines.add(i + 1, feedbackLine);
            break;
        }
    }

    if (!commentFound) {
        return "Comment ID not found";
    }

    // Write the updated lines back to the file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
    }

    return "Feedback added successfully for Comment ID: " + commentId;
}
}
    

