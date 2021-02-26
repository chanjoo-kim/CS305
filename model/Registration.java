
/*
 * This file is the registration class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */

package model;
import java.util.Objects;
import java.util.Map;
import java.util.Scanner;
import utility.FileLoader;

/**
 * Represents User Sign-in Object.
 * 
 * Methods of this class throw NullPointerException if required parameters are
 * null.
 * 
 * @author Athirai
 * @version Fall 2019
 */

public class Registration
{

    public static final Scanner in = new Scanner(System.in);
    /**
     * User Storage File.
     */
    public static final String USERFILE_NAME = "./resources/registeredusers.txt";

    /**
     * The registered user list for signin.
     */
    private final Map<String, User> myUserList;

    /**
     * Constructs a sigin/registration system.
     * 
     * 
     */
    public Registration()
    {
        myUserList = FileLoader.readItemsFromFile(USERFILE_NAME);
    }

    /**
     * getter for myUserList.
     * 
     * @return myUserList
     */
    public Map<String, User> getMyUserList()
    {
        return myUserList;
    }

    /**
     * display sign-in or registration options.
     */
    public boolean printSignin()
    {
        // ------------Fill in--------------------//
        
        boolean result = false;
        System.out.print("Enter 1 or 2 (1. New Registration 2. Login): ");
        final int input = in.nextInt();
        System.out.println("You entered option " + input);
        System.out.println(" ");
        System.out.println("**********************");
        System.out.println(" Enter Details");
        System.out.println("**********************");
        if(input == 1) {
            System.out.print("Username: ");
            String userName = in.next();
            while(myUserList.containsKey(userName)) {
                System.out.print("User already exists, enter different user name:");
                userName = in.next();
            }
            System.out.print("Password: ");
            final String passWord = in.next();
            
            System.out.print("isVIP (true/false): ");
            String vipStatus = in.next();
            final boolean vipStatusYes;
            
            //"true".equals(vipStatus)
            if(vipStatus.equals("true")) {
                vipStatusYes = true;
            } else {
                vipStatusYes = false;
            }
            final User newUser = new User(userName, passWord, vipStatusYes);
            if (register(newUser)) {
                System.out.println("Registration Successfull");
            }
        } else if(input == 2) {
            System.out.println("Username: ");
            String userName = in.next();
            System.out.println("Password: ");
            String passWord = in.next();
            while(!login(userName, passWord)) {
                System.out.println(" ");
                System.out.println("Wrong Credentials");
                System.out.println("Enter User Name: ");
                userName = in.next();
                System.out.println("Enter Password: ");
                passWord = in.next();
            }
            System.out.println("Login Successfull");
            result = true; 
        }
        return result; 
    }

    /**
     * Verify Sign-in procedure.
     * 
     * @param theUsername username for sign-in
     * @param thePassword password for signin
     * @return sign-in success
     */
    public boolean login(final String theUsername, final String thePassword)
    {

        // ------------Fill in--------------------//
        Objects.requireNonNull(theUsername);
        Objects.requireNonNull(thePassword);
        if(theUsername.isEmpty()||thePassword.isEmpty()) {
            throw new IllegalArgumentException();
        } else if(theUsername == null || thePassword == null) {
            throw new NullPointerException();
        }
        
        final User inSide = myUserList.get(theUsername);
        if(inSide != null && inSide.getMyPassword().equals(thePassword)) {
            return true;
        } else {
            return false;
        }
        
    }

    /**
     * Adds a user to the registered user list.
     * 
     * @param theUser an order to add to this shopping cart
     * @return true/false returns if registration is successfull
     */
    public boolean register(final User theUser)
    {
        
        // ------------Fill in--------------------//
        if(theUser == null) {
            throw new NullPointerException();
        }
        myUserList.put(theUser.getMyName(), theUser);
        FileLoader.writeUserToFile(USERFILE_NAME,theUser);

        return true;

    }

    /**
     * Empties the user list.
     */
    public void clear()
    {
        // ------------Fill in--------------------//
        myUserList.clear();
    }

    @Override
    /**
     * String representation of the object
     * 
     */
    public String toString()
    {

        // ------------Fill in--------------------//
        return "Registered Userlist " + myUserList.toString();
    }

}
