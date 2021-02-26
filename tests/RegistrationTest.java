package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.Registration;
import model.User;
import utility.FileLoader;

public class RegistrationTest
{
    
    /** private user instance variable */
    private Registration regis;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        regis = new Registration();
        
    }
    
    /**
     * Test method for {@link model.Registration#Registration()}.
     * compares if a new registration and a private registration is equal 
     */
    @Test
    public void testRegistration()
    {
        
        Registration ooo = new Registration();
        assertEquals("not equal", ooo.getMyUserList(), regis.getMyUserList());
        
    }
    
    /**
     * Test method for {@link model.Registration#getMyUserList()}.
     * comparing and seeing if the user list from the resources folder is the same as the one from this private variable
     */
    @Test
    public void testGetMyUserList()
    {
        
        Map<String, User> compareRegis = FileLoader.readItemsFromFile("./resources/registeredusers.txt");
        assertEquals("maps not the same", compareRegis, regis.getMyUserList());
    }
    
    /**
     * Test method for {@link model.Registration#login(java.lang.String, java.lang.String)}.
     * 
     */
    @Test
    public void testLoginTrue()
    {
        User user6 = new User("kkkk", "22556", true);
        regis.register(user6);
        assertTrue("login return false", regis.login("kkkk", "22556"));
    }
    
    /**
     * Test method for {@link model.Registration#login(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testLoginFalse()
    {
        assertFalse("login return true", regis.login("rabbit", "snake"));
    }
    
    /**
     * testing null pointer for a null user name
     */
    @Test(expected = NullPointerException.class)
    public void testLoginUserName() {
        regis.login(null, "password");
        
    }
    /**
     * testing null pointer for a null password
     */
    @Test(expected = NullPointerException.class)
    public void testLoginPassWord() {
        regis.login("username", null);
        
    }
    
    /**
     * testing illegal argument for a empty user name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalArgumentUserName() {
        regis.login("", "password");

    }
    
    /**
     * testing illegal argument for a empty pass word
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalArgumentPassWord() {
        regis.login("name", "");

    }
    
    /**
     * Test method for {@link model.Registration#register(model.User)}.
     * create a new user with different username and password, see if it returns true or false
     */
    @Test
    public void testRegister()
    {
        User user6 = new User("kkkk", "22556", true);
        assertTrue("register return false", regis.register(user6));
    }
    
    
    /**
     * testing null point exception for an empty registration  
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterUserName() {
        regis.register(null);
        
    }

    /**
     * Test method for {@link model.Registration#clear()}.
     * creates a new map, creates a new user, add that user into 
     * the registration, and clear it. compares a new map and the cleared
     * userlist 
     */
    @Test
    public void testClear()
    {
        Map<String, User> emptyEx = new HashMap<String, User>();
        User user6 = new User("kkkk", "22556", true);
        regis.register(user6);
        regis.clear();
        assertEquals("clear() failed", emptyEx,regis.getMyUserList());
        
    }

    /**
     * Test method for {@link model.Registration#toString()}.
     * see if it returns correct format
     */
    @Test
    public void testToString()
    {
        assertEquals("toString() parameterless constructor failed", regis.toString(), "Registered Userlist " + regis.getMyUserList().toString());
    }

}
