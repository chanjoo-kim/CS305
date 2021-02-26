package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import model.vehicles.AbstractVehicle;
import model.vehicles.Bicycle;
import model.vehicles.Car;
import model.vehicles.MotorBike;

public class RentalManager
{
    /** hash map which saves with key as my vehicle id, value as veh object. */
    private final Map<Integer, AbstractVehicle> myVehicleList;
    
    /** reference to the registration object created for registration/login. */
    private Registration myRegistration;
    
    /** counter for bill. */
    private static int counterforBill = 1; 
    
    /** hash map which saves key with bill id, value as bill object. */
    private final Map<Integer, Bill> myBills;
    
    /** scanner to use for user inputs. */
    public static final Scanner in = new Scanner(System.in);
    
    
    /** assigns initial values for my registration and initialize myvehicle list with generate inventory. */
    public RentalManager(final Registration theRegistration) 
    {
        this.myRegistration = Objects.requireNonNull(theRegistration);
        
        myBills = new HashMap<>();
        
        myVehicleList = generateInventory();
    }
    
    /** returns a map and creates 8 objects by assigning vehicle id in sequence. */
    public Map<Integer, AbstractVehicle> generateInventory()
    {
        
        final Map<Integer, AbstractVehicle> another = new HashMap<Integer, AbstractVehicle>();
        final Car car1 = new Car("Fiat", "V100", true, false, false, false);
        final Car car2 = new Car("Outback", "V1O1", true, true, true, false);
        final Car car3 = new Car("BMW", "V102", true, true, true, true);
        final MotorBike bike1 = new MotorBike("Bike1", "B100", true, false);
        final MotorBike bike2 = new MotorBike("Bike2", "B101", true, true);
        final Bicycle cycle1 = new Bicycle("Roadies", "C100", true, "Road");
        final Bicycle cycle2 = new Bicycle("Cruiser", "C101", true, "Cruiser");
        final Bicycle cycle3 = new Bicycle("Mountain", "C102", true, "Mountain");
        
        another.put(car1.getMyVehicleID(), car1);
        another.put(car2.getMyVehicleID(), car2);
        another.put(car3.getMyVehicleID(), car3);
        another.put(bike1.getMyVehicleID(), bike1);
        another.put(bike2.getMyVehicleID(), bike2);
        another.put(cycle1.getMyVehicleID(), cycle1);
        another.put(cycle2.getMyVehicleID(), cycle2);
        another.put(cycle3.getMyVehicleID(), cycle3);
        
        return another;

    }
    
    /** getter method for vehicle list. */
    public Map<Integer, AbstractVehicle> getMyVehicleList()
    {
        return myVehicleList;
    }
    
    /** prints the available cars, gives option to rent, drop, or end the program. */
    public void printOptions() 
    {
        AbstractVehicle veh; 
        boolean programRunning = true;
        while (programRunning) 
        {
            System.out.println("Enter 1 or 2 or 3 (1. Rent 2. Drop-off 3.Exit) ");
            final int input = in.nextInt();
            System.out.println("You entered option " + input);
            System.out.println(" ");
            if (input == 1) 
            {
                System.out.println("*************List of Available Vehicles:*************");
                final Iterator<Integer> iter = myVehicleList.keySet().iterator();
                /** while the rental status stays true, print out the vehice list
                 * of the ones that are still available */
                while (iter.hasNext()) 
                {
                    veh = myVehicleList.get(iter.next());
                    System.out.println(veh);
                }
                System.out.println("Enter Vehicle ID: ");
                final int veinput = in.nextInt();
                
                if (myVehicleList.containsKey(veinput)) 
                {
                    veh = myVehicleList.get(veinput);
                    int theBillID = counterforBill++;
                    System.out.println("Enter User Name: ");
                    String userName = in.next();
                    while (!(myRegistration.getMyUserList().containsKey(userName))) 
                    {
                        System.out.print("User does not exist, enter different user name:");
                        userName = in.next();
                    }
                    System.out.println("Enter NumDays to Rent: ");
                    final int numdaysInput = in.nextInt();
                    rent(veh.getMyVehicleID(), userName, numdaysInput, theBillID);
                }
            }
            if (input == 2) 
            {
                System.out.println("**********************");
                System.out.println("Enter Drop-off Details:");
                System.out.println("**********************");
                boolean dropSuccess = false;
                while (!dropSuccess) 
                {
                    System.out.println("Enter Drop-off Vehicle ID: ");
                    final int vedropID = in.nextInt();
                    if (drop(vedropID)) 
                    {
                        dropSuccess = true;
                        System.out.println("Drop-off Successful");
                    }
                }
            }
            if (input == 3) 
            {
                programRunning = false;
                break;
            }
            System.out.println("Do you want to continue? ");
            final boolean continuuu = in.nextBoolean();
            programRunning = continuuu;
        }

    }
    /** clears my bills list and my vehicle list. */
    public void clearLists() 
    {
        myVehicleList.clear();
        myBills.clear();
    }
    
    /** getter for registration. */
    public Registration getMyRegistration() 
    {
        return myRegistration;
    }
    
    /** returns true if vehicle is part of inventory. */
    public boolean isRentable(final int theVehicleID) 
    {
        final AbstractVehicle vehicle = myVehicleList.get(theVehicleID);
        return vehicle != null && vehicle.getMyRentalAvailability();
    }
    
    /** checks if vehicle id is rentable, and checks if user name is registered user
     * if it is, then vehicle availability is false, creates bill
     * call print & compute and returns true if successful. */
    public boolean rent(final int theVehicleID, final String theUserName,
                        final int theNumDays, final int theBillID) 
    {
        final boolean success; 
        
        Objects.requireNonNull(theVehicleID);
        Objects.requireNonNull(theUserName);
        Objects.requireNonNull(theNumDays);
        Objects.requireNonNull(theBillID);
        
        if (theNumDays <= 0)
        {
            throw new IllegalArgumentException();
        }
        
        
        if (isRentable(theVehicleID) && myRegistration.getMyUserList().containsKey(theUserName)) 
        {
            
            myVehicleList.get(theVehicleID).setRentalAvailability(false);

            final Bill bill = new Bill(theBillID, myRegistration.getMyUserList().get(theUserName), 
                                 myVehicleList.get(theVehicleID), theNumDays);
            bill.computeAndPrintAmount();
            myBills.put(theBillID, bill);
            success = true;
        } 
        else 
        {
            success = false;
        }
        return success;
    }
    
    /** checks if vehicle id is valid and is not rentable by calling isRentable. */
    public boolean drop(final int theVehicleID) 
    {
        final AbstractVehicle vehicle = myVehicleList.get(theVehicleID);
        if (vehicle == null) 
        {
            System.out.println("Vehicle does not exists");
            return false;
        }
        if (vehicle.getMyRentalAvailability()) 
        {
            System.out.println("Vehicle is not rented already");
            return false;
        }
        vehicle.setRentalAvailability(true);
        return true; 
    }
    
    
}
