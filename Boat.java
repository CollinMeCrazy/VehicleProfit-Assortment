/**
 * Class Boat used as subclass of Class Vehicle 
 *
 * @author Collins Ramsey
 * @version 11/9/2022 @ 5:43 pm
 */
public class Boat extends Vehicle
{
    // instance variables
    private int crew;

    /**
     * Standard Constructor for objects of class Boat
     */
    public Boat()
    {
        this("", 0.0, 0.0);
    }
    
    /**
     * 2nd Constructor for objects of class Boat, does some math with incoming weight to calculate crew members
     * 
     * @param String
     * @param double
     */
    public Boat(String destination, double weight, double income) {
        super(destination, income);
        // depending on if weight is over 500, will accomidate needed crew members
        if (weight > 500) {
            if (weight % 500.00 == 0) {
                this.crew = (int)(weight / 500);
            }
            else
            this.crew = (int)(weight / 500) + 1;
        }
        else
        this.crew = 1;
    }

    /**
     * Method getCrew returns this.crew as an int
     *
     * @param  none
     * @return int
     */
    public int getCrew() {
        return this.crew;
    }
    
    /**
     * Method calculateVehicleProfit overrides abstract version in Class Vehicle and is specific
     * to this subclass 
     *
     * @param  none
     * @return double
     */
    @Override
    public double calculateVehicleProfit() {
        // use crew and multiply cost per crew and add base cost depending on location
        // then subtract by income
        // oh boy math!
        double calcProfit = 0.0;
        
        if (super.getDestination().equals("Lancre")) {
            calcProfit = super.getIncome() - (this.crew * 50 + 1000);
        }
        else if (super.getDestination().equals("Ueberwald") || super.getDestination().equals("Borogravia")) {
            calcProfit = super.getIncome() - (this.crew * 150 + 3000);
        }
        else
            calcProfit = super.getIncome() - (this.crew * 60 + 1200);
            
        return calcProfit; // will return some value of profit
    }
    
    /**
     * Method addToTotalProfit calls on calculateVehicleProfit in this subclass and then calls on the Class Vehicle
     * method setRevenue to add to the total profit
     *
     * @param  none
     * @return nothing
     */
    @Override
    public void addToTotalProfit() {
        // setter for revenue variable, use setRevenue
        super.setRevenue(this.calculateVehicleProfit());
    }
}