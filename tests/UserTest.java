/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import model.User;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

/**
 * @author chanjookim
 *
 */
public class UserTest
{

    /** private user instance variable */
    private User myUser1;
    /** private user instance variable */
    private User myUser2;


    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() 
    {
        myUser1 = new User("name", "password", true);
        myUser2 = new User("name2", "password2", false);
    }

    /**
     * Test method for {@link model.User#hashCode()}.
     * Tests whether or not the hash code will return the right value, 
     * Creates a new user that has exact same value and compare them
     */
    @Test
    public void testHashCode()
    {
        assertEquals("hashcode() failed", Objects.hash(myUser1.getMyName(), myUser1.getMyPassword(), myUser1.getMyVIPStatus()), myUser1.hashCode());
        
        final User myUser3 = new User("name", "password", true);
        
        assertEquals("hashcode() failed", myUser1.hashCode(), myUser3.hashCode());
        
    }
    
    /**
     * Test method for {@link model.User#User(java.lang.String, java.lang.String)}.
     * Checks to see if a new user is the same as a user from private variable
     */
    @Test
    public void testUser()
    {
        User myUsertesting = new User("name2", "password2");
        assertEquals("not equal", myUsertesting, myUser2);
    }

    /**
     * Test method for {@link model.User#User(java.lang.String, java.lang.String)}.
     * See if user name and pass word (which is passed as parameter) is null, and if it's
     * the expected value.  
     */
    @Test
    public void testUserStringString()
    {
        assertNotNull("The object is null", myUser1.getMyName());
        assertNotNull("The object is null", myUser1.getMyPassword());
        assertEquals("default values not correct", "name", myUser1.getMyName());
        assertEquals("default values not correct", "password", myUser1.getMyPassword());
    }
    

    /**
     * Test method for {@link model.User#User(java.lang.String, java.lang.String, boolean)}.
     * See if user name and pass word is null, and if it's the expected value
     */
    @Test
    public void testUserStringStringBoolean()
    {
        assertNotNull("The object is null", myUser1.getMyName());
        assertNotNull("The object is null", myUser1.getMyPassword());
        assertEquals("parameterized constructor failed", myUser1.getMyName(), "name");
        assertEquals("parameterized constructor failed", myUser1.getMyPassword(), "password");
        assertEquals("parameterized constructor failed", myUser1.getMyVIPStatus(), true);
    }
    
    /**
     * testing illegal argument for a empty user name
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalArgumentName() {
        new User("", "password", true);

    }
    
    /**
     * testing illegal argument for a empty pass word
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalArgumentPassword() {
        new User("name", "", true);

    }
    /**
     * testing illegal argument for a empty user name without vi status 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalArgumentNoName() {
        new User("", "password2");

    }
    
    /**
     * testing illegal argument for a empty pass word without vi status 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalArgumentNoPassword() {
        new User("name2", "");

    }

    /**
     * Test method for {@link model.User#getMyName()}.
     * See if it returns the right user name 
     */
    @Test
    public void testGetMyName()
    {
        assertNotNull("user name is null", myUser1);
        assertNotNull("user name is null", myUser2);
        assertEquals("user getter failed", "name", myUser1.getMyName());
    }

    /**
     * Test method for {@link model.User#getMyPassword()}.
     * See if it returns the right password
     */
    @Test
    public void testGetMyPassword()
    {
        assertNotNull("password is null", myUser1);
        assertNotNull("password is null", myUser2);
        assertEquals("password getter failed", myUser1.getMyPassword(), "password");
    }

    /**
     * Test method for {@link model.User#getMyVIPStatus()}.
     * See if it returns the right vip status
     */
    @Test
    public void testGetMyVIPStatus()
    {
        assertEquals("vip status getter failed", myUser1.getMyVIPStatus(), true);
    }

    /**
     * Test method for {@link model.User#toString()}.
     * See if it returns the correct format of string 
     */
    @Test
    public void testToString()
    {
        assertEquals("toString() failed", "User (name, password, true)",  myUser1.toString());
    }

    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     * compares two different user object and see if they are equal
     */
    @Test
    public void testEqualsObjectPositive()
    {
        final User myUser4 = new User("name", "password", true);
        assertEquals("equals() failed", myUser1, myUser1);
        assertEquals("equals() failed", myUser1, myUser4);
    }
    
    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     * compares between the two private variables, compares one variable to null
     * and compares to an empty arrarylist of integer
     */
    @Test
    public void testEqualsObjectNegative()
    {
        assertNotEquals("equals() failed", myUser1, myUser2);
        assertNotEquals("equals() failed", myUser1, null);
        assertNotEquals("equals() failed", myUser1, new ArrayList<Integer>());
    }


}
