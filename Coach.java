/**
 * Class Coach used as subclass of Class Vehicle 
 *
 * @author Collins Ramsey
 * @version 11/9/2022 @ 5:43 pm
 */
public class Coach extends Vehicle
{
    // instance variables
    private int crew;
    private int horses;

    /**
     * Standard Constructor for objects of class Coach
     */
    public Coach()
    {
        this("", 0.0, 0.0);
    }
    
    /**
     * 2nd Constructor for objects of class Coach, does some math with weight and calculates crew members and horses
     * 
     * @param String
     * @param double
     */
    public Coach(String destination, double weight, double income) {
        super(destination, income);
        // depending on if weight is over 100, will accommodate needed horses
        if (weight > 100) {
            if (weight % 100.00 == 0) {
                this.horses = (int)(weight / 100);
            }
            else
            this.horses = (int)(weight / 100) + 1;
        }
        else
        this.horses = 1;
        
        // accommodates crew members if there are over 4 horses
        if (this.horses > 4) {
            this.crew = 2;
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
     * Method getHorses returns this.horses as an int
     *
     * @param  none
     * @return int
     */
    public int getHorses() {
        return this.horses;
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
        // use horses and crew to multiply by cost per horse and per crew and add base costs depending on location
        // then subtract by income
        // oh boy math!
        double calcProfit = 0.0;
        
        if (super.getDestination().equals("Lancre")) {
            calcProfit = super.getIncome() - ((this.crew * 50) + (this.horses * 30) + 200);
        }
        else if (super.getDestination().equals("Ueberwald") || super.getDestination().equals("Borogravia")) {
            calcProfit = super.getIncome() - ((this.crew * 150) + (this.horses * 100) + 200);
        }
        else
            calcProfit = super.getIncome() - ((this.crew * 60) + (this.horses * 50 ) + 200);
        
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