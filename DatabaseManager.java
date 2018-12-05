import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * <h1>Database Manager</h1>
 * <p>
 * Used to locally save and retrieve data.
 */
public class DatabaseManager {

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) {
        //T\ODO
        ArrayList<Vehicle> arr = new ArrayList<>();
        String line;
        Vehicle v = new Vehicle();
        String[] ar;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                ar = line.split(",");
                if (ar[0].equalsIgnoreCase("drone")) {
                    v = new Drone(ar[1], Double.parseDouble(ar[2]));
                } else if (ar[0].equalsIgnoreCase("truck")) {
                    v = new Truck(ar[1], Double.parseDouble(ar[2]));
                } else if (ar[0].equalsIgnoreCase("cargo plane")) {
                    v = new CargoPlane(ar[1], Double.parseDouble(ar[2]));
                }
                arr.add(v);
            }
        } catch (IOException e) {
            return arr;
        }
        return arr;
    }


    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * <p>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of packages
     */
    public static ArrayList<Package> loadPackages(File file) {
        //T\ODO
        ArrayList<Package> arr = new ArrayList<>();
        String[] ar;
        String line;
        Package p;
        ShippingAddress sa;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                ar = line.split(",");
                sa = new ShippingAddress(ar[4], ar[5], ar[6], ar[7], Integer.parseInt(ar[8]));
                p = new Package(ar[0], ar[1], Double.parseDouble(ar[2]), Double.parseDouble(ar[3]), sa);
                arr.add(p);
            }
        } catch (IOException e) {
            return arr;
        }
        return arr;

    }


    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     *
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
        //T\ODO
        try {
            String line;
            double profit = 0.0;
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                profit += Double.parseDouble(line);
            }
            return profit;
        } catch (IOException e) {
            return 0;
        }
    }


    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     *
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
        //TODO
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.read();
        } catch (IOException e) {
            return 0;
        }
    }


    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     *
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {
        //TODO
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.read() == 1;
        } catch (IOException e) {
            return false;
        }
    }


    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     *
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        //T\ODO
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            String type = "";
            for (Vehicle v : vehicles) {
                if (v instanceof Drone) {
                    type = "Drone";
                } else if (v instanceof Truck) {
                    type = "Truck";
                } else if (v instanceof CargoPlane) {
                    type = "Cargo Plane";
                }
                bw.write(type + "," + v.getLicensePlate() + "," +
                        v.getMaxWeight());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
        //TODO
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Package p : packages) {
                bw.write(p.getID() + "," + p.getProduct() + "," +
                        p.getWeight() + "," + p.getPrice() + "," +
                        p.getDestination().getName() + "," +
                        p.getDestination().getAddress() + "," +
                        p.getDestination().getCity() + "," +
                        p.getDestination().getState() + "," +
                        p.getDestination().getZipCode());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves profit to text file.
     *
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
        //TODO
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(String.valueOf(profit)); //TODO: Is this right?
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves number of packages shipped to text file.
     *
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
        //T\ODO
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(nPackages); //TODO: Write number of packages or profit?
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * written, otherwise "0" will be written.
     *
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        //T\ODO
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            if (primeDay) {
                bw.write(1);
            } else {
                bw.write(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
