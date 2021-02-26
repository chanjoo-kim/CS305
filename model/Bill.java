package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import model.vehicles.AbstractVehicle;

public class Bill
{
    
    /** unique integer value. */
    private final int myBillID;
    
    /** a user object.*/
    private final User myPrimaryUser;
    
    /** a vehicle object. */
    private final AbstractVehicle myVehicle;
    
    /** integer representing the number of days vehicle is rented. */
    private final int myNumDays;
    
    /** total bill amount in big decimal value. */
    private BigDecimal myBillAmount;
    
    /** formats the numbers into us currency. */
    public static final NumberFormat NF = NumberFormat.getCurrencyInstance(Locale.US);
    
    /** parameterized constructor that initializes all fields. */
    public Bill(final int theBillID, final User thePrimaryUser, 
                final AbstractVehicle theVehicle, final int theNumDays) 
    {
        this.myBillID = theBillID;
        this.myPrimaryUser = thePrimaryUser;
        this.myVehicle = theVehicle; 
        this.myNumDays = theNumDays;
        this.myBillAmount = new BigDecimal("0");
    }
    
    /** computes & prints my bill amount in a specific format. */
    public void computeAndPrintAmount() 
    {
        
        System.out.println("----Cost INFormation----");
        System.out.println("RentalPerDay:");
        
        final BigDecimal rentalTotal = myVehicle.calculateRentalAmount();
        System.out.println("Cost Per Day: " + NF.format(rentalTotal));
        final BigDecimal numOfDays = new BigDecimal(myNumDays);
        System.out.println("No. of Rental days: " + myNumDays);
        final BigDecimal rentalAmt = rentalTotal.multiply(numOfDays);
        System.out.println("Total Amount: " + NF.format(rentalAmt));
        final BigDecimal extraAmt = new BigDecimal("0.01");
        final BigDecimal extraAmtCalc = rentalAmt.multiply(extraAmt);
        System.out.println("Insurance: " + NF.format(extraAmtCalc));
        
        BigDecimal vipDiscount = new BigDecimal("0.00");
        if (myPrimaryUser.getMyVIPStatus()) 
        {
            vipDiscount = rentalAmt.multiply(extraAmt);
            System.out.println("VIP Discount: -" + NF.format(vipDiscount));
        } 
        else 
        {
            System.out.println("VIP Discount: " + NF.format(vipDiscount));
        }
        
        final BigDecimal taxAmt = rentalAmt.multiply(new BigDecimal("0.1"));
        
        System.out.println("Insurance: " + NF.format(taxAmt));
        
        myBillAmount = rentalAmt.add(extraAmt).subtract(vipDiscount).add(taxAmt);
        
        System.out.println("Total Rent: " + NF.format(myBillAmount));
        
    }
    /** gets my bill id */
    public int getMyBillID() 
    {
        return myBillID;
    }
}
