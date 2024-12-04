package group1_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        FileWriter writer;
        BufferedReader reader;
        
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
            writer.write("- This is a sample comment. (Comment ID: C1)\n");
            writer.write("Employee ID: 5678\n");
            writer.write("- Another comment for testing. (Comment ID: C2)\n");
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
public void testValidKeyword() throws IOException { 
    // Source file path
    File sourceFile = new File("C:\\Users\\aseel\\Documents\\NetBeansProjects\\Group_1\\Information.txt");
    
    // Temporary test file path
    File testFile = new File("tempTestData.txt");
    
    // Copy content from source file to temp test file
    try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
         FileWriter writer = new FileWriter(testFile)) {
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line + System.lineSeparator());
        }
    } // This ensures the writer is properly closed and data is flushed

    System.out.println("valid keyword test");
    
    // Example of a valid keyword
    String keyword = "email";  
    String result = instance.searchInfo(testFile.getPath(), keyword); 
    
    
    // Update the expected result to match the actual content in the Information.txt file
    String expResult = "\n" +
"Information found for keyword: email\n" +
"\n" +
"handling the offline work signal in the email application:\n" +
"\n" +
"1-open the Outlook email application.\n" +
"2-Select the \"Send/Receive\" tab.\n" +
"3-Click on the â€œWork Offlineâ€? icon to make it  unshaded.*\n" +
"\n" +
"Information found for keyword: email\n" +
"\n" +
"email archiving:\n" +
"\n" +
"1. open the Outlook email application.\n" +
"2. Select the â€œFileâ€? tab.\n" +
"3. Select â€œInformationâ€? from the right menu.\n" +
"4. Select â€œArchiveâ€? from the â€œCleanup Toolsâ€? icon.\n" +
"5. A pop-up window titled â€œArchiveâ€? will appear.\n" +
"6. Select â€œArchive this folder and all subfoldersâ€? and select the folder you want to archive. Note: When you select the first folder (usually named the userâ€™s email), all subfolders under it will be archived.\n" +
"7. Specify the date after which you do not want to archive in the â€œArchive items older than:â€? option.\n" +
"8. If you want to change the location of saving the archive file, this is modified in the â€œArchive file:â€? option by clicking on the â€œBrowse...â€? button and selecting a new location.\n" +
"9. Click on the â€œOKâ€? button, where the archiving process will begin and will appear at the bottom of the application.\n" +
"10. When the archiving process is complete, the email application will open the archive file in the right menu of the application.*\n" +
"\n" +
"Information found for keyword: email\n" +
"\n" +
"add a folder in the email app:\n" +
"\n" +
"1. open the email application (Outlook).\n" +
"2. Select the main folder to which the folder will be added.\n" +
"3. Right-click and select â€œNew Folderâ€¦â€? from the pop-up menu.\n" +
"4. Enter the folder name in the \"Name:\" field.\n" +
"5. Press the \"OK\" button.*\n" +
"";  
    assertEquals(expResult, result);

    // Clean up by deleting the temporary test file
    testFile.delete();
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
        assertTrue(comments.contains("Employee ID: 1234 | Comment: This is a sample comment. (Comment ID: C1) | Has Feedback: false"));
        assertTrue(comments.contains("Employee ID: 5678 | Comment: Another comment for testing. (Comment ID: C2) | Has Feedback: false"));
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
    
    @Test
    public void ProvideFeedbackSuccess() throws IOException {
        Scanner scanner = new Scanner("C1\nThis is feedback for comment C1\n");
        Admin a = new Admin("A001");

        String result = instance.provideFeedback(testFile.getPath(), scanner, a);
        
        assertEquals("Feedback added successfully for Comment ID: C1", result);

        List<String> lines = Files.readAllLines(testFile.toPath());
        assertTrue(lines.contains("  Admin Feedback by Admin ID: A001"));
        assertTrue(lines.contains("  Feedback: This is feedback for comment C1"));
    }

    @Test
    public void ProvideFeedbackCommentIdNotFound() throws IOException {
        Scanner scanner = new Scanner("C11\n");
        Admin a = new Admin("A001");

        String result = instance.provideFeedback(testFile.getPath(), scanner, a);
        assertEquals("Comment ID not found", result);

        List<String> lines = Files.readAllLines(testFile.toPath());
        assertEquals(4, lines.size()); 
    }
}
