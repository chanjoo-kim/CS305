package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

public class MotorBike extends AbstractVehicle {
    
    private boolean isTouring; 
    
    public static final BigDecimal TOURING_FARE = new BigDecimal(5);
    
    /** parameterized constructor that has name vin, rental availability and is touring*/
    public MotorBike(final String theName, final String theVIN, final boolean theRentalAvailability, final boolean theTouring) {
        super(theName, theVIN, theRentalAvailability);
        isTouring = theTouring;
    }
    
    @Override
    /** prints out the string version of the motorbike  */
    public String toString() {
        return "MotorBike (ID:" + getMyVehicleID() + ", Name:" + getMyName() + ", VIN:" + getMyVIN() 
        + ", CanRent?:" + getMyRentalAvailability() + ", IsTouring?:" + isTouring + ")";
    }
    
    @Override
    /** calculates rental amount */
    public BigDecimal calculateRentalAmount() {
        BigDecimal totalRentalFare = BIKE_FARE;
        if(isTouring) {
            totalRentalFare = totalRentalFare.add(TOURING_FARE);
        }
        return totalRentalFare;
    }
    
    @Override
    /** compares other vehicle to motorbike*/
    public boolean equals(final Object theOtherVehicle) {
        boolean result = false; 
        if(!(theOtherVehicle instanceof MotorBike)) { /** compares the two objects othervehicle and bicycle */
            result = false;
        } else {
            final MotorBike otherM = (MotorBike) theOtherVehicle;
            result = super.equals(otherM)
                     && isTouring == otherM.isTouring;
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(isTouring);
    }
}
