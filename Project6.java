/**
 * Class Project6 uses Class Vehicle and all it's subclasses to formulate an unsorted and sorted compliation of incoming
 * cargo for separate destinations and vehicles, listing their profits/losses and their grand total
 *
 * @author Collins Ramsey
 * @version 11/9/2022 @ 5:43 pm
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;

public class Project6 {
    //DATE AND TIME YOU FIRST START WORKING ON THIS ASSIGNMENT (date AND time): <--------------------
    //ANSWER:  11/9/2022 @ 5:43 pm    <--------------------
    
    //DO NOT ALTER THE MAIN METHOD
    public static void main( String[] args ) throws FileNotFoundException {
        //input stream
        Scanner in = new Scanner( new File( "info1.txt" ) );
        
        //read the input file and populate the array list
        ArrayList< Vehicle > allUnsorted = readData( in );
        
        //output for half credit
        printVehicles( allUnsorted );
        
        //sort the array list by destination 
        //1. Lancre, 2. Ueberwald, 3. Borogravia, 4. Klatch
        //and for each destination 1. boats, 2. trains, 3. coaches
        ArrayList< Vehicle > allSorted = sortVehicles( allUnsorted );
        
        //output for half credit
        printVehicles( allSorted );
        
        //print revenue
        System.out.printf( "Today's Ankh-Morpork Post Office Profit:  $%,10.2f%n%n", Vehicle.getRevenue() );
    } //DO NOT ALTER THE MAIN METHOD
    
    /**
     * Method readData scans and reads incoming text files specifically formatted, creates objects, and places those
     * objects into an unsorted ArrayList of type Vehicle. Also adds their profits to the total profit based on type of 
     * vehicle
     *
     * @param  Scanner
     * @return ArrayList
     */
    public static ArrayList< Vehicle > readData( Scanner in ) {
        ArrayList< Vehicle > unsorted = new ArrayList<>();
        
        int j = 0;
        
        while (in.hasNext()) {
            // variables for sorting data
            String location = "";
            String type = "";
            double weight = 0.0;
            int trainCars = 0;
            double income = 0.0;
            
            // checks the next 4 lines to then place each corresponding piece of info into the right variable
            for (int i = 0; i < 4; i++) {
                if (in.hasNextDouble() && i == 2) {
                    if (type.equals("train")) {
                        trainCars = in.nextInt();
                    }
                    else
                    weight = in.nextDouble();
                }
                else if (in.hasNextDouble() && i == 3) {
                    income = in.nextDouble();
                }
                else if (i == 0) {
                    location = in.next();
                }
                else
                    type = in.next();
            }
            
            // adding each corresponding object to the ArrayList, then calling addToTotalProfit to add up
            // the total revenue for all objects of this ArrayList
            if (type.equals("train")) {
                unsorted.add(new Train(location, trainCars, income));
                unsorted.get(j).addToTotalProfit();
                j++;
            }
            else if (type.equals("boat")) {
                unsorted.add(new Boat(location, weight, income));
                unsorted.get(j).addToTotalProfit();
                j++;
            }
            else {
                unsorted.add(new Coach(location, weight, income));
                unsorted.get(j).addToTotalProfit();
                j++;
            }
        }
        
        return unsorted;
    }
    
    /**
     * Method sortVehicles takes existing unsorted ArrayList and places the list into sorted ArrayList in a specified order
     * for location and type of vehicles. Temporarily creates an ArrayList for every location, deals with lots of logic
     * (future reference; don't use enhanced loops, unless for very simple use)
     *
     * @param  ArrayList
     * @return ArrayList
     */
    public static ArrayList< Vehicle > sortVehicles( ArrayList< Vehicle > unsorted ) {
        ArrayList< Vehicle > sorted = new ArrayList<>();
        
        // separate ArrayLists for each location to then add in order to the sorted ArrayList
        ArrayList<Vehicle> lancre = new ArrayList<Vehicle>();
        ArrayList<Vehicle> ueberwald = new ArrayList<Vehicle>();
        ArrayList<Vehicle> borogravia = new ArrayList<Vehicle>();
        ArrayList<Vehicle> klatch = new ArrayList<Vehicle>();
        
        // loops through unsorted ArrayList and adds any elements of Lancre to the lancre ArrayList and orders them accordingly
        for (Vehicle vehicle : unsorted) {
            if (vehicle.getDestination().equals("Lancre")) {
                if (vehicle instanceof Train) {
                    lancre.add(0, vehicle);
                }
                else if (vehicle instanceof Boat) {
                    // if initially the ArrayList is empty, and a Boat is being added, then it should just add the Boat
                    if (lancre.isEmpty()) {
                        lancre.add(vehicle);
                    }
                    else {
                    // checks if last element of lancre ArrayList is instanceof Coach to then place Boat above it
                    // if there are multiple Coaches above, then it loops until it finds one that is not a Coach in the ArrayList
                        int i = 1;
                        while (lancre.get(lancre.size() - i) instanceof Coach) {
                            i++;
                        }
                        // if there are no Coaches in an already populated ArrayList, then Boat is added next at the bottom
                        if (i == 1) {
                            lancre.add(vehicle);
                        }
                        else if (lancre.get(lancre.size() - (i - 1)) instanceof Coach) {
                        lancre.add(lancre.size() - (i - 1), vehicle);
                        }
                        else
                        lancre.add(vehicle);
                    }
                }
                else {
                    lancre.add(vehicle);
                }
            }
        }
        
        // loops through lancre ArrayList and adds each element to sorted ArrayList in its intended order
        for (Vehicle vehicle : lancre) {
            sorted.add(vehicle);
        }
        
        // loops through unsorted ArrayList and adds any elements of Ueberwald to the ueberwald ArrayList and orders them accordingly
        for (Vehicle vehicle : unsorted) {
            if (vehicle.getDestination().equals("Ueberwald")) {
                if (vehicle instanceof Train) {
                    ueberwald.add(0, vehicle);
                }
                else if (vehicle instanceof Boat) {
                    // if initially the ArrayList is empty, and a Boat is being added, then it should just add the Boat
                    if (ueberwald.isEmpty()) {
                        ueberwald.add(vehicle);
                    }
                    else {
                    // checks if last element of ueberwald ArrayList is instanceof Coach to then place Boat above it
                    // if there are multiple Coaches above, then it loops until it finds one that is not a Coach in the ArrayList
                        int i = 1;
                        while (ueberwald.get(ueberwald.size() - i) instanceof Coach) {
                            i++;
                        }
                        // if there are no Coaches in an already populated ArrayList, then Boat is added next at the bottom
                        if (i == 1) {
                            ueberwald.add(vehicle);
                        }
                        else if (ueberwald.get(ueberwald.size() - (i - 1)) instanceof Coach) {
                        ueberwald.add(ueberwald.size() - (i - 1), vehicle);
                        }
                        else
                        ueberwald.add(vehicle);
                    }
                }
                else {
                    ueberwald.add(vehicle);
                }
            }
        }
        
        // loops through ueberwald ArrayList and adds each element to sorted ArrayList in its intended order
        for (Vehicle vehicle : ueberwald) {
            sorted.add(vehicle);
        }
        
        // loops through unsorted ArrayList and adds any elements of Borogravia to the borogravia ArrayList and orders them accordingly
        for (Vehicle vehicle : unsorted) {
            if (vehicle.getDestination().equals("Borogravia")) {
                if (vehicle instanceof Train) {
                    borogravia.add(0, vehicle);
                }
                else if (vehicle instanceof Boat) {
                    // if initially the ArrayList is empty, and a Boat is being added, then it should just add the Boat
                    if (borogravia.isEmpty()) {
                        borogravia.add(vehicle);
                    }
                    else {
                    // checks if last element of borogravia ArrayList is instanceof Coach to then place Boat above it
                    // if there are multiple Coaches above, then it loops until it finds one that is not a Coach in the ArrayList
                        int i = 1;
                        while (borogravia.get(borogravia.size() - i) instanceof Coach) {
                            i++;
                        }
                        // if there are no Coaches in an already populated ArrayList, then Boat is added next at the bottom
                        if (i == 1) {
                            borogravia.add(vehicle);
                        }
                        else if (borogravia.get(borogravia.size() - (i - 1)) instanceof Coach) {
                        borogravia.add(borogravia.size() - (i - 1), vehicle);
                        }
                        else
                        borogravia.add(vehicle);
                    }
                }
                else {
                    borogravia.add(vehicle);
                }
            }
        }
        
        // loops through borogravia ArrayList and adds each element to sorted ArrayList in its intended order
        for (Vehicle vehicle : borogravia) {
            sorted.add(vehicle);
        }
        
        // loops through unsorted ArrayList and adds any elements of Klatch to the klatch ArrayList and orders them accordingly
        for (Vehicle vehicle : unsorted) {
            if (vehicle.getDestination().equals("Klatch")) {
                if (vehicle instanceof Train) {
                    klatch.add(0, vehicle);
                }
                else if (vehicle instanceof Boat) {
                    // if initially the ArrayList is empty, and a Boat is being added, then it should just add the Boat
                    if (klatch.isEmpty()) {
                        klatch.add(vehicle);
                    }
                    else {
                    // checks if last element of klatch ArrayList is instanceof Coach to then place Boat above it
                    // if there are multiple Coaches above, then it loops until it finds one that is not a Coach in the ArrayList
                        int i = 1;
                        while (klatch.get(klatch.size() - i) instanceof Coach) {
                            i++;
                        }
                        // if there are no Coaches in an already populated ArrayList, then Boat is added next at the bottom
                        if (i == 1) {
                            klatch.add(vehicle);
                        }
                        else if (klatch.get(klatch.size() - (i - 1)) instanceof Coach) {
                        klatch.add(klatch.size() - (i - 1), vehicle);
                        }
                        else
                        klatch.add(vehicle);
                    }
                }
                else {
                    klatch.add(vehicle);
                }
            }
        }
        
        // loops through klatch ArrayList and adds each element to sorted ArrayList in its intended order
        for (Vehicle vehicle : klatch) {
            sorted.add(vehicle);
        }
        
        return sorted;
    }
    
    /**
     * Method printVehicles takes a sorted or unsorted ArrayList and prints it's contents 
     *
     * @param  ArrayList
     * @return nothing
     */
    public static void printVehicles( ArrayList< Vehicle > v ) {
        System.out.println("Ankh-Morpork Post Office mail dispatches:");
        
        // variable to keep track whether an object's calculated profit is a gain or a loss, used in each if branch
        String lossOrProfit = "";
        
        for ( Vehicle vehicle : v ) {
            if( vehicle instanceof Boat ) {
                // checks if the calculated profit is a gain or loss
                if (vehicle.calculateVehicleProfit() < 0) {
                    lossOrProfit = "LOSS";
                }
                else
                    lossOrProfit = "PROFIT";
                
                System.out.printf("%-6sto %10s (%3d crew) %9s: $ %,9.2f %n","Boat", vehicle.getDestination(), ((Boat)vehicle).getCrew(),
                lossOrProfit, Math.abs(vehicle.calculateVehicleProfit()));
            }
            else if( vehicle instanceof Train ) {
                // checks if the calculated profit is a gain or loss
                if (vehicle.calculateVehicleProfit() < 0) {
                    lossOrProfit = "LOSS";
                }
                else
                    lossOrProfit = "PROFIT";
                
                System.out.printf("%-6sto %10s (%3d cars) %9s: $ %,9.2f %n", "Train", vehicle.getDestination(), ((Train)vehicle).getCars(),
                lossOrProfit, Math.abs(vehicle.calculateVehicleProfit()));
            }
            else {
                // checks if the calculated profit is a gain or loss
                if (vehicle.calculateVehicleProfit() < 0) {
                    lossOrProfit = "LOSS";
                }
                else
                    lossOrProfit = "PROFIT";
                    
                System.out.printf("%-6sto %10s (%3d horses) %7s: $ %,9.2f %n", "Coach", vehicle.getDestination(), ((Coach)vehicle).getHorses(),
                lossOrProfit, Math.abs(vehicle.calculateVehicleProfit()));
            }
        }
        
        System.out.println("");
    }
}