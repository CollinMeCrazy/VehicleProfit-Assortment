/**
 * Class Vehicle used as parent class for Classes Train, Boat, and Coach
 *
 * @author Collins Ramsey
 * @version 11/9/2022 @ 5:43 pm
 */
public abstract class Vehicle
{
    // instance variables
    private String destination;
    private double income;
    private static double revenue = 0.0;

    /**
     * Standard Constructor for objects of class Vehicle
     */
    protected Vehicle()
    {
        this.destination = "";
        this.income = 0.0;
    }
    
    /**
     * 2nd Constructor for objects of class Vehicle
     * 
     * @param String
     * @param double
     */
    protected Vehicle(String destination, double income) {
        this.destination = destination;
        this.income = income;
    }

    /**
     * Method getDestination that returns this.destination as a String
     *
     * @param  none
     * @return String
     */
    public String getDestination() {
        return this.destination;
    }
    
    /**
     * Method getIncome that returns this.income as a double
     *
     * @param  none
     * @return String
     */
    public double getIncome() {
        return this.income;
    }
    
    /**
     * Method setRevenue that takes in a double and adds that value to double static revenue
     *
     * @param  double
     * @return nothing
     */
    public static void setRevenue(double addRevenue) {
        revenue += addRevenue;
    }
    
    /**
     * Method getRevenue that returns revenue as a double
     *
     * @param  none
     * @return double
     */
    public static double getRevenue() {
        return revenue;
    }
    
    /**
     * Method addToTotalProfit used by subclasses to override
     *
     * @param  none
     * @return nothing
     */
    public abstract void addToTotalProfit();
    
    /**
     * Method calculateVehicleProfit used by subclasses to override
     *
     * @param  
     * @return 
     */
    public abstract double calculateVehicleProfit();
}