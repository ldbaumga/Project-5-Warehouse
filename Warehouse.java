import java.io.File;
import java.util.Scanner;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
	final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;

    private static String menue = "==========Options==========\n"+
            "1) Add Package\n"+
            "2) Add Vehicle\n"+
            "3) Activate Prime Day\n"+
            "4) Send Vehicle\n"+
            "5) Print Statistics\n"+
            "6) Exit\n"+
            "===========================";
    private static String error = "Error: Option not available.";

    private static String vehicleType = "Options:\n" +
            "1) Send Truck\n" +
            "2) Send Drone\n" +
            "3) Send Cargo Plane\n" +
            "4) Send First Available";
    private static String vehicleError = "Error: No vehicles of selected type are available.";

    private static String zipOptions = "ZIP Code Options:\n" +
            "1) Send to first ZIP Code\n" +
            "2) Send to mode of ZIP Codes";


    /**
     * Main Method
     * 
     * @param args list of command line arguements
     */
    public static void main(String[] args) {
    	//TODO
        boolean cont = true;
        int userChoice;
        Scanner n = new Scanner(System.in);
    	
    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
    	
    	
    	
    	//2) Show menu and handle user inputs
    	while (cont) {
            System.out.println(menue);

            if (!n.hasNextInt()) {
                System.out.println(error);
                continue;
            } else {
                userChoice = Integer.parseInt(n.nextLine());
            }

            switch (userChoice) {
                case 1: addPackage(n);
                    break;
                case 2: addVehicle(n);
                    break;
                case 3: primeDay();
                    break;
                case 4: sendVehcle();
                    break;
                case 5: printStatistics();
                    break;
                case 6: cont = false;
                    break;

                    default:
                        System.out.println(error);
                        break;
            }
        }
    	//3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
    	
    
    }

    private static void addPackage(Scanner n) {
        String id;
        String name;
        double weight;
        double price;
        String buyer;
        String adress;
        String city;
        String state;
        int zip;

        System.out.println("Enter Package ID:");
        id = n.nextLine();
        System.out.println("Enter Product Name:");
        name = n.nextLine();
        System.out.println("Enter Weight:");
        weight = Double.parseDouble(n.nextLine());
        System.out.println("Enter Price:");
        price = Double.parseDouble(n.nextLine());
        System.out.println("Enter Buyer Name:");
        buyer = n.nextLine();
        System.out.println("Enter Address:");
        adress = n.nextLine();
        System.out.println("Enter City:");
        city = n.nextLine();
        System.out.println("Enter State:");
        state = n.nextLine();
        System.out.println("Ener ZIP Code:");
        zip = Integer.parseInt(n.nextLine());

        ShippingAddress ship = new ShippingAddress(buyer, adress, city, state, zip);
        Package p = new Package(id, name, weight, price, ship);
        System.out.println(p.shippingLabel());

        //TODO - ADD PACKAGE TO LISTS!!!
    }

    private static void addVehicle(Scanner n) {
        String license;
        double maxWeight;
        int userChoice;

        System.out.println(vehicleType);

        if (!n.hasNextInt()) {
            System.out.println(vehicleError);
            return;
        } else {
            userChoice = Integer.parseInt(n.nextLine());
        }
        //TODO - ADD VEHICLES TO LISTS
        switch (userChoice) {
            case 1:  System.out.println("Enter License Plate No.:");
                license = n.nextLine();
                System.out.println("Enter Maximum Carry Weight:");
                maxWeight = Double.parseDouble(n.nextLine());
                break;
            case 2: System.out.println("Enter License Plate No.:");
                license = n.nextLine();
                System.out.println("Enter Maximum Carry Weight:");
                maxWeight = Double.parseDouble(n.nextLine());
                break;
            case 3: System.out.println("Enter License Plate No.:");
                license = n.nextLine();
                System.out.println("Enter Maximum Carry Weight:");
                maxWeight = Double.parseDouble(n.nextLine());
                break;

                default:
                    System.out.println(vehicleError);
                    break;
        }
    }

    private static void primeDay() {
        //TODO - IMPLIMENT PRIME DAY SWITCHING
    }

    private static void sendVehcle() {
        //TODO - IMPLIMENT SEND VEHICLES
    }

    private static void printStatistics() {
        //TODO - PRINT STATISTICS
    }
}