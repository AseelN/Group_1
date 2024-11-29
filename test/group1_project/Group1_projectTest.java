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
        System.out.println(result);
        String expResult = "No information found for the keyword: what time it is";
        assertEquals(expResult, result);
    }
    
        @Test 
    public void testKeywordNotFound() throws IOException {
        System.out.println("Keyword Not Found");
        String keyword = "nonexistent"; 
        String result = instance.searchInfo(testFile.getPath(), keyword); 
        System.out.println(result);
        String expResult =  "No information found for the keyword: nonexistent"; 
        assertEquals(expResult, result); 
    }
}
