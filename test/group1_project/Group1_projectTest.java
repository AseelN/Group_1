package group1_project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import org.junit.*;
import static org.junit.Assert.*;


public class Group1_projectTest {
    Group1_project instance;
        Scanner in;
        File testFile;
        
    public Group1_projectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        
        instance = new Group1_project();
        in = new Scanner(System.in);
        testFile = File.createTempFile("testFile", ".txt");
         try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Employee ID: 1234\n");
            writer.write("- This is a sample comment. (Comment ID: 1)\n");
            writer.write("Employee ID: 5678\n");
            writer.write("- Another comment for testing. (Comment ID: 2)\n");
        }
    }
    
    @After
    public void tearDown() {
    }

    
     @Test
    public void EmptyKeyword() {
        System.out.println("Empty");
        String s = "";
        String expResult = "An error occured,please try again :";
        String result = instance.validation(s, in);
        assertEquals(expResult, result);
        
    }
    
    
     @Test
    public void keywordValidition() {
        System.out.println("Valid");
        String s = "desktop interface";
        String expResult = "Searching for solutions...";
        String result = instance.validation(s, in);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void keywordNotString(){
        String s="1";
        String expResult = "An error occured,please try again :";
        String result = instance.validation(s, in);
        assertEquals(expResult, result);
    }
    
    @Test 
    public void testNotValidKeyword() throws IOException { 
        System.out.println("not valid");
        String keyword = "what time it is";
        String result = instance.searchInfo(testFile.getPath(),keyword); 
        
        String expResult = "No information found for the keyword: what time it is";
        assertEquals(expResult, result);
    }
    
        @Test 
    public void testKeywordNotFound() throws IOException {
        System.out.println("Keyword Not Found");
        String keyword = "nonexistent"; 
        String result = instance.searchInfo(testFile.getPath(), keyword); 
        
        String expResult =  "No information found for the keyword: nonexistent"; 
        assertEquals(expResult, result); 
    }
    
    @Test
    public void commentAdded() throws IOException {
    
        Employee emp = new Employee("1234");
        Scanner in = new Scanner("This is a test comment\n");
        File testFile = new File("test_comments.txt");
        FileWriter writer = new FileWriter(testFile, true);

        String result = instance.addComment(writer, in, emp);
        writer.flush();
        writer.close();

        assertTrue(result.contains("Comment added successfully with Comment ID:"));
    }
    
    @Test
    public void foundEmployeeId() throws IOException {
        Employee emp = new Employee("5678");
        String testComment = "sample comment";
        Scanner in = new Scanner(testComment + "\n");
        FileWriter writer = new FileWriter("test_comments.txt", true);
    
        instance.addComment(writer, in, emp);
        writer.flush();
        writer.close();

        List<String> fileContent = Files.readAllLines(new File("test_comments.txt").toPath());
        assertTrue(fileContent.contains("Employee ID: 5678"));
    }
    
    @Test
    public void testReviewComments() throws IOException {
        List<String> comments = instance.reviewComments(testFile.getPath());

        assertEquals(2, comments.size());
        assertTrue(comments.contains("Employee ID: 1234 | Comment: This is a sample comment. (Comment ID: 1) | Has Feedback: false"));
        assertTrue(comments.contains("Employee ID: 5678 | Comment: Another comment for testing. (Comment ID: 2) | Has Feedback: false"));
    }

    @Test
    public void testReviewCommentsWithEmployeeIds() throws IOException {
        List<String> comments = instance.reviewComments(testFile.getPath());

        assertEquals(2, comments.size());
        boolean foundFirstEmployee = false;
        boolean foundSecondEmployee = false;

        for (String comment : comments) {
            if (comment.contains("Employee ID: 1234")) {
                foundFirstEmployee = true;
            }
            if (comment.contains("Employee ID: 5678")) {
                foundSecondEmployee = true;
            }
        }

        assertTrue(foundFirstEmployee);
        assertTrue(foundSecondEmployee);
    }

}
