
package model;

import java.util.Objects;

/**
 * Represents a single user for registration or sign-in. User is an immutable
 * object.
 * 
 * Constructors and methods of this class throw NullPointerException if required
 * parameters are null.
 * 
 * @author username
 * @version Fall 2019
 */
public final class User
{
    
    private String myName;
    
    private String myPassword;
    
    private boolean myVIPStatus;
    
    public User(final String theName, final String thePassword){
        this(theName, thePassword, false);
    }
    
    public User(String theName, String thePassword, boolean theVIPStatus){
        myName = Objects.requireNonNull(theName);
        myPassword = Objects.requireNonNull(thePassword);
        myVIPStatus = Objects.requireNonNull(theVIPStatus);
        if(theName.isEmpty()||thePassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        
    }
    
    public String getMyName() {
        return myName;
    }
    
    public String getMyPassword() {
        return myPassword;
    }
    
    public boolean getMyVIPStatus() {
        return myVIPStatus;
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()); // the class name without the package name
        sb.append(" (");
        sb.append(this.getMyName());
        sb.append(", ");
        sb.append(this.getMyPassword());
        sb.append(", ");
        sb.append(this.getMyVIPStatus());
        sb.append(')');
        
        return sb.toString();
    }
    
    public boolean equals(final Object theOtherObject) {
        boolean result = false;
        if(this == theOtherObject) {
            result = true; 
        } else if (theOtherObject == null) {
            result = false; 
        } else if (this.getClass() != theOtherObject.getClass()) {
            result = false;
        } else {
            final User other = (User) theOtherObject;
            result = Objects.equals(myName, other.myName)
                     && Objects.equals(myPassword, other.myPassword)
                     && Objects.equals(myVIPStatus, other.myVIPStatus);
        }
        return result;
    }
    
    public int hashCode() {
        return Objects.hash(myName, myPassword, myVIPStatus);
    }
    
    
    
}
