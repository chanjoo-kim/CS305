package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

public class Bicycle extends AbstractVehicle {

    public static final BigDecimal ROAD_CYCLE_FARE = CYCLE_FARE;
    
    public static final BigDecimal MOUNTAIN_CYCLE_FARE = CYCLE_FARE.multiply(new BigDecimal(0.01));
    
    public static final BigDecimal CRUISER_CYCLE_FARE = CYCLE_FARE.multiply(new BigDecimal(0.02));
    
    public static final BigDecimal HYBRID_CYCLE_FARE = CYCLE_FARE.multiply(new BigDecimal(0.04));
    
    private final String myBicycleType;
    
    /** parameterized constructor that stores name, vin,rental availability and the bicycle type */
    public Bicycle(final String theName, final String theVIN, final boolean theRentalAvailability, final String theBicycleType) {
        super(theName, theVIN, theRentalAvailability);
        this.myBicycleType = theBicycleType;
        
    }
    
    @Override
    /** to string method that prints out in correct format */
    public String toString() {
        return "BiCycle (ID:" + getMyVehicleID() + ", Name:" + getMyName() + ", VIN:" + getMyVIN() 
        + ", CanRent?:" + getMyRentalAvailability() + ", CycleType:" + myBicycleType + ")";
                        
    }
    
    @Override
    /** calculates the rental amount */
    public BigDecimal calculateRentalAmount() {
        
        BigDecimal totalRentalFare = CYCLE_FARE;
        /** add specific rental fares according to the type of bike
         *  for ex. if mountain, then add mountaincyclefare to rentalfare
         *  and so on */
        if (myBicycleType.equals("Road")) {
            totalRentalFare = totalRentalFare.add(ROAD_CYCLE_FARE);
        } 
        if (myBicycleType.equals("Mountain")) {
            totalRentalFare = totalRentalFare.add(MOUNTAIN_CYCLE_FARE);
        }
        if (myBicycleType.equals("Cruiser")) {
            totalRentalFare = totalRentalFare.add(CRUISER_CYCLE_FARE);
        }
        if (myBicycleType.equals("Hybrid")) {
            totalRentalFare = totalRentalFare.add(HYBRID_CYCLE_FARE);
        }
        
        return totalRentalFare;
    }
    
    @Override
    public boolean equals(final Object theOtherVehicle) {
        boolean result = false; 
        if(!(theOtherVehicle instanceof Bicycle)) { /** compares the two objects othervehicle and bicycle */
            result = false;
        } else {
            final Bicycle otherB = (Bicycle) theOtherVehicle;
            result = super.equals(otherB)
                     && myBicycleType == otherB.myBicycleType;
        }
        return result;
    }

    @Override 
    public int hashCode() {
        return super.hashCode() + Objects.hash(myBicycleType);
    }
    
    
}


