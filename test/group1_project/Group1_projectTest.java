package group1_project;

import java.io.File;
import java.io.IOException;
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void caseDiff() {
        System.out.println("caseDiff");
        String s = "Sahl System";
        String expResult = "";
        String result = instance.validation(s, in);
        assertEquals(expResult, result);
        
        
        
    }
    
     @Test
    public void EmptyCaseDiff() {
        System.out.println("Empty");
        String s = "";
        String expResult = "An error occured,please try again.";
        String result = instance.validation(s, in);
        assertEquals(expResult, result);
        
    }
    
     @Test
    public void NotValid() {
        System.out.println("NotValid");
        String s = "crash";
        String expResult = "Not found, try again.";
        String result = instance.validation(s, in);
        assertEquals(expResult, result);
        
    }
    
     @Test
    public void Valid() {
        System.out.println("Valid");
        String s = "desktop interface";
        String expResult = "What can we help you with?";
        String result = instance.validation(s, in);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void notString(){
        String s="1";
        String expResult = "An error occured,please try again.";
        String result = instance.validation(s, in);
        assertEquals(expResult, result);
    }
    
    @Test 
    public void testEmptyKeyword() throws IOException {
        System.out.println("Empty Keyword"); 
        String keyword = ""; 
        String result = instance.searchInfo(testFile.getPath(),keyword);
        String expResult = "No information found for the keyword: ";
        assertEquals(expResult, result); 
    }
    
    @Test 
    public void testValidKeyword() throws IOException { 
        String keyword = "shortcut to desktop";
        String result = instance.searchInfo(testFile.getPath(),keyword); 
        System.out.println(result);
        String expResult = "\nInformation found for keyword: add a shortcut to the desktop\n\nadd a shortcut to the desktop\n\nHere is the information to add a shortcut.\n*\n";
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
    public void testMultipleOccurrences() throws IOException { 
        System.out.println("Multiple Occurrences");
        String keyword = "shortcut"; 
        String result = instance.searchInfo(testFile.getPath(), keyword); 
        String expResult = "\nInformation found for keyword: information example\n\ninformation example\n\nExample information details.\n*\n"; 
        assertEquals(expResult, result);
    }
}
