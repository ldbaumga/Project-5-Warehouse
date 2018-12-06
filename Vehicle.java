import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * <h1>Vehicle</h1> Represents a vehicle
 */

public class Vehicle implements Profitable {
    private String licensePlate;
    private double maxWeight;
    private double currentWeight;
    private int zipDest;
    private ArrayList<Package> packages;


    /**
     * Default Constructor
     */
    //============================================================================
    //T\ODO
    public Vehicle() {
        this.licensePlate = "";
        this.maxWeight = 0;
        this.currentWeight = 0;
        this.zipDest = 0;
        this.packages = new ArrayList<>();
    }

    //============================================================================


    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight of vehicle
     */
    //============================================================================
    //T\ODO
    public Vehicle(String licensePlate, double maxWeight) {
        this();
        this.licensePlate = licensePlate;
        this.maxWeight = maxWeight;
    }

    //============================================================================


    /**
     * Returns the license plate of this vehicle
     *
     * @return license plate of this vehicle
     */
    public String getLicensePlate() {
        //T\ODO
        return this.licensePlate;
    }


    /**
     * Updates the license plate of vehicle
     *
     * @param licensePlate license plate to be updated to
     */
    public void setLicensePlate(String licensePlate) {
        //T\ODO
        this.licensePlate = licensePlate;
    }


    /**
     * Returns the maximum weight this vehicle can carry
     *
     * @return the maximum weight that this vehicle can carry
     */
    public double getMaxWeight() {
        //T\ODO
        return this.maxWeight;
    }


    /**
     * Updates the maximum weight of this vehicle
     *
     * @param maxWeight max weight to be updated to
     */
    public void setMaxWeight(double maxWeight) {
        //T\ODO
        this.maxWeight = maxWeight;
    }


    /**
     * Returns the current weight of all packages inside vehicle
     *
     * @return current weight of all packages inside vehicle
     */
    public double getCurrentWeight() {
        //T\ODO
        return this.currentWeight;
    }


    /**
     * Returns the current ZIP code desitnation of the vehicle
     *
     * @return current ZIP code destination of vehicle
     */
    public int getZipDest() {
        //T\ODO
        return this.zipDest;
    }


    /**
     * Updates the ZIP code destination of vehicle
     *
     * @param zipDest ZIP code destination to be updated to
     */
    public void setZipDest(int zipDest) {
        //T\ODO
        this.zipDest = zipDest;
    }


    /**
     * Returns ArrayList of packages currently in Vehicle
     *
     * @return ArrayList of packages in vehicle
     */
    public ArrayList<Package> getPackages() {
        //T\ODO
        return this.packages;
    }


    /**
     * Adds Package to the vehicle only if has room to carry it (adding it would not
     * cause it to go over its maximum carry weight).
     *
     * @param pkg Package to add
     * @return whether or not it was successful in adding the package
     */
    public boolean addPackage(Package pkg) {
        //T\ODO
        if (pkg.getWeight() + this.currentWeight <= this.maxWeight) {
            this.packages.add(pkg);
            return true;
        }
        return false;
    }


    /**
     * Clears vehicle of packages and resets its weight to zero
     */
    public void empty() {
        //T\ODO
        for (int i = 0; i < this.packages.size(); i++) {
            this.packages.remove(i);
        }
        this.currentWeight = 0;
    }


    /**
     * Returns true if the Vehicle has reached its maximum weight load, false
     * otherwise.
     *
     * @return whether or not Vehicle is full
     */
    public boolean isFull() {
        //T\ODO
        return this.currentWeight == this.maxWeight;
    }


    /**
     * Fills vehicle with packages with preference of date added and range of its
     * destination zip code. It will iterate over the packages initially at a range
     * of zero and fill it with as many as it can within its range without going
     * over its maximum weight. The amount the range increases is dependent on the
     * vehicle that is using this. This range it increases by after each iteration
     * is by default one.
     *
     * @param warehousePackages List of packages to add from
     */
    public void fill(ArrayList<Package> warehousePackages) {
        //T\ODO
        int i = 0;
        int increment;
        if (this instanceof CargoPlane) {
            increment = 10;
        } else {
            increment = 1;
        }
        int currentRange = 0;
        while (warehousePackages.get(i).getWeight() + this.currentWeight <= this.maxWeight && i < warehousePackages.size()) {
            for (int j = 0; j < warehousePackages.size(); j++) {
                int range = Math.abs(warehousePackages.get(i).getDestination().getZipCode() - zipDest);
                if (range == currentRange) {
                    addPackage(warehousePackages.get(j));
                }
            }
            currentRange += increment;
            i++;
        }


    }

    @Override
    public double getProfit() {
        int count = 0;
        double profit = 0.0;
        for (Package p : this.packages) {
            profit += p.getPrice();
            count++;
        }
        return profit - count * 1.33;
    }

    @Override
    public String report() {
        return null;
    }

}
