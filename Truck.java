import java.util.ArrayList;


/**
 * <h1>Truck</h1> Represents a Truck
 */
public class Truck extends Vehicle {

    private final double GAS_RATE = 1.66;
//    private int licensePlate;
//    private int destination;
//    private String weightload;
//    private double netProfit;

    /**
     * Default Constructor
     */
    //============================================================================
    //TODO
    public Truck() {
        //TO\DO
        super("0", 0);
    }
    //============================================================================

    /**
     * Constructor
     * 
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    //============================================================================
    //TODO
    public Truck(String plateNo, double maxWeight) {
        //T\ODO
        super(plateNo, maxWeight);
//        this.licensePlate = plateNo;
//        this.destination = destination;
//        this.weightload = weight;
//        this.netProfit = netProfit;
    }
    //============================================================================

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */
    /**
     * Returns the profits generated by the packages currently in the Vehicle.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 1.66)
     * </p>
     */
    @Override
    public double getProfit() {
        //T\ODO
    	double tot = 0.0;
    	int a = 0;
    	for (int i = 0; i < this.getPackages().size(); i++) {
    	    tot += this.getPackages().get(i).getPrice();
    	    a += 1;
        }
        double cost = a * this.GAS_RATE;
    	return tot - cost;
    }

    /**
     * Generates a String of the truck report. Truck report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in truck</li>
     * </ul>
     * 
     * @return Truck Report
     */
    @Override
    public String report() {
        //T\ODO
    	String fin = "==========Truck Report==========" +
                "License Plae No.: " + this.getLicensePlate() + "\n" +
                "Destination: " + this.getZipDest() + "\n" +
                "Weight Load: " + this.getCurrentWeight() + "/" + this.getMaxWeight() + "\n" +
                "Net Profit: $" + this.getProfit() + "\n";
    	return fin;
    }



}