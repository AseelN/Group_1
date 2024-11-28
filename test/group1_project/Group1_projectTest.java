package group1_project;

import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;


public class Group1_projectTest {
    Group1_project instance;
    
        String[] categories = { 
            "sahl System", 
            "desktop interface", 
            "security interface", 
            "ip address connfigurtion", 
            "change password" 
        };
        
    public Group1_projectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        instance = new Group1_project();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void caseDiff() {
        System.out.println("caseDiff");
        String s = "Sahl System";
        String expResult = "What can we help you with?";
        String result = instance.validation(categories,s);
        assertEquals(expResult, result);
        
        
        
    }
    
     @Test
    public void EmptyCaseDiff() {
        System.out.println("Empty");
        String s = "";
        String expResult = "An error occured,please try again.";
        String result = instance.validation(categories,s);
        assertEquals(expResult, result);
        
    }
    
     @Test
    public void NotValid() {
        System.out.println("NotValid");
        String s = "crash";
        String expResult = "Not found, try again.";
        String result = instance.validation(categories,s);
        assertEquals(expResult, result);
        
    }
    
     @Test
    public void Valid() {
        System.out.println("Valid");
        String s = "desktop interface";
        String expResult = "What can we help you with?";
        String result = instance.validation(categories,s);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void notString(){
        String s="1";
        String expResult = "An error occured,please try again.";
        String result = instance.validation(categories,s);
        assertEquals(expResult, result);
    }
    
    @Test 
    public void testEmptyKeyword() throws IOException {
        System.out.println("Empty Keyword"); 
        String keyword = ""; 
        String result = instance.searchSolution(keyword);
        String expResult = "Keyword is empty. Please provide a valid keyword.\n"; 
        assertEquals(expResult, result); 
    }
    @Test 
    public void testValidKeyword() throws IOException { 
        String keyword = "solutions";
        String result = instance.searchSolution(keyword); 
        System.out.println("Actual Output:\n" + result);
        String expResult = "Searching for solutions...\n\nSolution for: solutions\nIt contains some solutions.\nEnd of solution.\n\n";
        assertEquals(expResult, result);
    }
    
        @Test 
    public void testKeywordNotFound() throws IOException {
        System.out.println("Keyword Not Found");
        String keyword = "nonexistent"; 
        String result = instance.searchSolution(testFile.getPath(), keyword); 
        String expResult = "Searching for solutions...\nNo solution found for the keyword: nonexistent\n"; 
        assertEquals(expResult, result); 
    }
    @Test 
    public void testMultipleOccurrences() throws IOException { 
        System.out.println("Multiple Occurrences");
        String keyword = "solution"; 
        String result = instance.searchSolution(testFile.getPath(), keyword); 
        System.out.println("actual result: \n"+result);
        String expResult = "Searching for solutions...\n\nSolution for: solution\nHere is another solution for testing.\n*\nEnd of solution.\n\n"; 
        assertEquals(expResult, result);
    }
}
