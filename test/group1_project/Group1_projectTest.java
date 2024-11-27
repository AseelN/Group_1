package group1_project;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
        String s = "Sahl";
        String expResult = "sahl";
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
        String expResult = "Input should not be a digit";
        String result = instance.validation(categories,s);
        assertEquals(expResult, result);
    }
    
}
