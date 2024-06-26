/**
 * Class Train used as subclass of Class Vehicle 
 *
 * @author Collins Ramsey
 * @version 11/9/2022 @ 5:43 pm
 */
public class Train extends Vehicle
{
    // instance variables
    private int cars;

    /**
     * Standard Constructor for objects of class Train
     */
    public Train()
    {
        this("", 0, 0.0);
    }
    
    /**
     * 2nd Constructor for objects of class Train
     * 
     * @param String
     * @param int
     * @param double
     */
    public Train(String destination, int cars, double income) {
        super(destination, income);
        this.cars = cars;
    }

    /**
     * Method getCars returns this.cars as an int
     *
     * @param  none
     * @return int
     */
    public int getCars() {
        return this.cars;
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
        // use cars multiplied by cost per car and add base cost depending on location
        // then subtract by income
        // oh boy math!
        double calcProfit = 0.0;
        
        if (super.getDestination().equals("Lancre")) {
            calcProfit = super.getIncome() - (this.cars * 100 + 600);
        }
        else if (super.getDestination().equals("Ueberwald") || super.getDestination().equals("Borogravia")) {
            calcProfit =  super.getIncome() - (this.cars * 100 + 4000);
        }
        else
            calcProfit = super.getIncome() - (this.cars * 100 + 2100);
        
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