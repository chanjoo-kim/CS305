package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

import model.User;

public abstract class AbstractVehicle {
    
    public static final BigDecimal BASE_FARE = new BigDecimal(10);
    
    public static final BigDecimal CYCLE_FARE = BASE_FARE;
    
    public static final BigDecimal BIKE_FARE = BASE_FARE.multiply(new BigDecimal(2));
    
    public static final BigDecimal CAR_FARE = BASE_FARE.multiply(new BigDecimal(3));
    
    private int myVehicleID;
    
    private static int counterforID = 1;
    
    private final String myVIN; 
    
    private final String myName; 
    
    private boolean rentalAvailability = true; 
    
    /** parameterized constructor that stores name , vin and whether or not it's rentable*/
    public AbstractVehicle(final String theName, final String theVIN, final boolean theRentalAvailability) {
        this.myVehicleID = counterforID++;
        this.myName = theName;
        this.myVIN = theVIN;
        this.rentalAvailability = theRentalAvailability;
        
        
    }
    
    public abstract BigDecimal calculateRentalAmount();
    
    public abstract String toString();
    
    /** equals method that checks if the vehicle and other vehicle is the same, 
     * and returns true if it's the same */
    public boolean equals(final Object theOtherVehicle) {
        boolean result = false; 
        if(!(theOtherVehicle instanceof AbstractVehicle)) { /** compares the two objects othervehicle and abstract vehicle */
            result = false;
        } else {
            final AbstractVehicle other = (AbstractVehicle) theOtherVehicle;
            result = Objects.equals(myVehicleID, other.myVehicleID)
                     && Objects.equals(myVIN, other.myVIN)
                     && Objects.equals(myName, other.myName)
                     && Objects.equals(rentalAvailability, other.rentalAvailability);
        }
        return result;
    }
    
    /** getter for vehicle id*/
    public int getMyVehicleID() {
        return myVehicleID;
    }
    
    /** getter for my name */
    public String getMyName() {
        return myName;
    }
    
    /** getter for vehicle number*/
    public String getMyVIN() {
        return myVIN;
    }
    
    /** getter for the availablility of the vehicle*/
    public boolean getMyRentalAvailability() {
        return rentalAvailability; 
    }
    
    @Override 
    public int hashCode() {
        return Objects.hash(myVehicleID, myVIN, myName, rentalAvailability);
    }
    
    public void setRentalAvailability(final boolean theRentalAvailability) {
        this.rentalAvailability = theRentalAvailability;
    }
    
}
