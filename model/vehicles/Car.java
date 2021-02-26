package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

public class Car extends AbstractVehicle{
    
    private boolean isLuxury;
    
    private boolean isNavigation;
    
    private boolean isDrivingAssistance;
    
    public static final BigDecimal LUXURY_FARE = new BigDecimal(10);
    
    public static final BigDecimal NAVIGATION_FARE = new BigDecimal(1);
    
    public static final BigDecimal DRIVINGASSISTANCE_FARE = new BigDecimal(2);
    
    /** parameterized constructor that stores name, vin,rental availability and the 3 booleans */
    public Car(final String theName, final String theVIN, final boolean theRentalAvailability, final boolean theLuxury, 
               final boolean theNavigation, final boolean theDrivingAssistance) {
        super(theName, theVIN, theRentalAvailability);
        this.isLuxury = theLuxury;
        this.isNavigation = theNavigation;
        this.isDrivingAssistance = theDrivingAssistance;
    }
    
    @Override
    /** returns a string of an expected output */
    public String toString() {
        return "Car (ID:" + getMyVehicleID() + ", Name:" + getMyName() + ", VIN:" + getMyVIN() 
        + ", CanRent?:" + getMyRentalAvailability() + ", IsLuxury?:" + isLuxury 
        + ", HasNavigation?:" + isNavigation + ", HasAssistance?:" + isDrivingAssistance + ")";
    }
    
    @Override
    /** calculates the rental amount*/
    public BigDecimal calculateRentalAmount() {
        BigDecimal totalRentalFare = CAR_FARE;
        if(isLuxury) {
            totalRentalFare = totalRentalFare.add(LUXURY_FARE);
        }
        if(isNavigation) {
            totalRentalFare = totalRentalFare.add(NAVIGATION_FARE);
        }
        if(isDrivingAssistance) {
            totalRentalFare = totalRentalFare.add(DRIVINGASSISTANCE_FARE);
        }
        return totalRentalFare;
    }
    
    /** compares the newly made car object to the other vehicle*/
    public boolean equals(final Object theOtherVehicle) {
        boolean result = false; 
        if(!(theOtherVehicle instanceof Car)) { /** compares the two objects othervehicle and bicycle */
            result = false;
        } else {
            final Car otherC = (Car) theOtherVehicle;
            result = super.equals(otherC)
                     && isLuxury == otherC.isLuxury
                     && isNavigation == otherC.isNavigation
                     && isDrivingAssistance == otherC.isDrivingAssistance;
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(isLuxury, isNavigation, isDrivingAssistance);
    }
    
    
    
}
