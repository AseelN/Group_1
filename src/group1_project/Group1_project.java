package group1_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

        if (isMatchFound) {
            result.append("No information found for the keyword: ").append(keyword);
        }

        reader.close(); 
        
        return result.toString();
    }
}
    

