import java.util.ArrayList;


/**
 * <h1>CargoPlane</h1> Represents a Cargo Plane
 */
public class CargoPlane extends Vehicle {
    final double GAS_RATE = 2.33;

    /**
     * Default Constructor
     */
    //============================================================================
    //T\ODO
    public CargoPlane() {
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
    //T\ODO
    public CargoPlane(String no, double maxWeight) {
        super(no, maxWeight);
    }
    //============================================================================

    /**
     * Overides its superclass method. Instead, after each iteration, the range will
     * increase by 10.
     *
     * @param warehousePackages List of packages to add from
     */
    @Override
    public void fill(ArrayList<Package> warehousePackages) {
    	//T\ODO
        int i = 0;
        int currentRange = 0;
        while (warehousePackages.get(i).getWeight() + this.currentWeight <= this.maxWeight && i < warehousePackages.size()) {
            for (int j = 0; j < warehousePackages.size(); j++) {
                int range = Math.abs(warehousePackages.get(i).getDestination().getZipCode() - zipDest);
                if (range == currentRange) {
                    addPackage(warehousePackages.get(j));
                }
            }
            currentRange += 10;
            i++;
        }
        
    }

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */

    /**
     * Returns the profits generated by the packages currently in the Cargo Plane.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 2.33)
     * </p>
     */
    @Override
    public double getProfit() {
    	//T\ODO
        double tot = 0.0;
        int a = 0;
        for (int i = 0; i < this.getPackages().size(); i++) {
            tot += this.getPackages().get(i).getPrice();
            a += 10;
        }
        double cost = a * this.GAS_RATE;
        return tot - cost;
    }

    /**
     * Generates a String of the Cargo Plane report. Cargo plane report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in cargo plane</li>
     * </ul>
     *
     * @return Cargo Plane Report
     */
    @Override
    public String report() {
    	//T\ODO
        String fin = "========Cargo Plane Report========" +
                "License Plae No.: " + this.getLicensePlate() + "\n" +
                "Destination: " + this.getZipDest() + "\n" +
                "Weight Load: " + this.getCurrentWeight() + "/" + this.getMaxWeight() + "\n" +
                "Net Profit: ($" + this.getProfit() + ")\n"+
                "==============================";
        return fin;
    }

   
   
}
