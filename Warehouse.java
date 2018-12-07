import java.io.File;
import java.util.ArrayList;
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

    private static boolean isPrimeDay = false;
    private static double totProfit;
    private static ArrayList<Package> packages;
    private static ArrayList<Vehicle> vehicles;
    private static int numPackages;

    //totprofit variable

    private static String menu = "==========Options==========\n" +
            "1) Add Package\n" +
            "2) Add Vehicle\n" +
            "3) Activate Prime Day\n" +
            "4) Send Vehicle\n" +
            "5) Print Statistics\n" +
            "6) Exit\n" +
            "===========================";
    private static String error = "Error: Option not available.";

    private static String generalVehicleError = "Error: No vehicles available.";

    private static String generalPackageError = "Error: No packages available.";

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

        boolean isPrimeDay = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);
        ;
        double totProfit = DatabaseManager.loadProfit(PROFIT_FILE);
        ;
        ArrayList<Package> packages = DatabaseManager.loadPackages(PACKAGE_FILE);
        ArrayList<Vehicle> vehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
        int numPackages = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
        //TODO
        boolean cont = true;
        int userChoice;
        Scanner n = new Scanner(System.in);

        //1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager


        //2) Show menu and handle user inputs
        while (cont) {
            System.out.println(menu);

            if (!n.hasNextInt()) {
                System.out.println(error);
                continue;
            } else {
                userChoice = Integer.parseInt(n.nextLine());
            }

            switch (userChoice) {
                case 1:
                    addPackage(n);
                    break;
                case 2:
                    addVehicle(n);
                    break;
                case 3:
                    primeDay();
                    break;
                case 4:
                    sendVehcle();
                    break;
                case 5:
                    printStatisticsReport(totProfit, numPackages, packages.size());
                    break;
                case 6:
                    cont = false;
                    break;

                default:
                    System.out.println(error);
                    break;
            }

        }
        //3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
        DatabaseManager.savePackages(PACKAGE_FILE, packages);
        DatabaseManager.savePackagesShipped(N_PACKAGES_FILE, numPackages);
        DatabaseManager.savePrimeDay(PRIME_DAY_FILE, isPrimeDay);
        DatabaseManager.saveVehicles(VEHICLE_FILE, vehicles);
        DatabaseManager.saveProfit(PROFIT_FILE, totProfit);


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
        System.out.println("Enter ZIP Code:");
        zip = Integer.parseInt(n.nextLine());

        ShippingAddress ship = new ShippingAddress(buyer, adress, city, state, zip);
        Package p = new Package(id, name, weight, price, ship);
        System.out.println(p.shippingLabel());

        //T\ODO - ADD PACKAGE TO LISTS!!!
        packages.add(p);

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
            case 1:
                System.out.println("Enter License Plate No.:");
                license = n.nextLine();
                System.out.println("Enter Maximum Carry Weight:");
                maxWeight = Double.parseDouble(n.nextLine());
                Vehicle v = new Truck(license, maxWeight);
                vehicles.add(v);
                break;
            case 2:
                System.out.println("Enter License Plate No.:");
                license = n.nextLine();
                System.out.println("Enter Maximum Carry Weight:");
                maxWeight = Double.parseDouble(n.nextLine());
                Vehicle v2 = new Drone(license, maxWeight);
                vehicles.add(v2);
                break;
            case 3:
                System.out.println("Enter License Plate No.:");
                license = n.nextLine();
                System.out.println("Enter Maximum Carry Weight:");
                maxWeight = Double.parseDouble(n.nextLine());
                Vehicle v3 = new CargoPlane(license, maxWeight);
                vehicles.add(v3);
                break;

            default:
                System.out.println(vehicleError);
                break;
        }
    }

    private static void primeDay() {
        //TODO - IMPLEMENT PRIME DAY SWITCHING
        isPrimeDay = !isPrimeDay;
        if (isPrimeDay) {
            menu = "==========Options==========\n" +
                    "1) Add Package\n" +
                    "2) Add Vehicle\n" +
                    "3) Deactivate Prime Day\n" +
                    "4) Send Vehicle\n" +
                    "5) Print Statistics\n" +
                    "6) Exit\n" +
                    "===========================";
        } else {
            menu = "==========Options==========\n" +
                    "1) Add Package\n" +
                    "2) Add Vehicle\n" +
                    "3) Activate Prime Day\n" +
                    "4) Send Vehicle\n" +
                    "5) Print Statistics\n" +
                    "6) Exit\n" +
                    "===========================";
        }

    }

    public static ArrayList<Vehicle> getVehicle() {
        return vehicles;
    }

    public static ArrayList<Package> getPackages() {
        return packages;
    }

    private static void sendVehcle() {
        //TODO - IMPLEMENT SEND VEHICLES
        if (getVehicle() == null || getVehicle().isEmpty()) {
            System.out.println(generalVehicleError);
        } else if (getPackages() == null || getPackages().isEmpty()) {
            System.out.println(generalPackageError);
        } else {
            System.out.println(vehicleType);
            int userChoice;
            Scanner n = new Scanner(System.in);
            if (!n.hasNextInt()) {
                System.out.println(vehicleError);
                return;
            } else {
                userChoice = Integer.parseInt(n.nextLine());
            }
            switch (userChoice) {
                case 1:
                    for (Vehicle v : getVehicle()) {
                        if (v instanceof Truck) {
                            zip(v);
                            return;
                        }

                    }
                    System.out.println(vehicleError);
                    break;
                case 2:
                    for (Vehicle v : getVehicle()) {
                        if (v instanceof Drone) {
                            zip(v);
                            return;
                        }

                    }
                    System.out.println(vehicleError);
                    break;
                case 3:
                    for (Vehicle v : getVehicle()) {
                        if (v instanceof CargoPlane) {
                            zip(v);
                            return;
                        }

                    }
                    System.out.println(vehicleError);
                    break;
                case 4:
                    zip(vehicles.get(0));
                    break;

                default:
                    System.out.println(error);
            }
        }

        //if 1: see if there  is a truck
        //2: drone
        //3: cargo
        //4: send first available


    }

    private static void zip(Vehicle v) {
        Scanner n = new Scanner(System.in);
        System.out.println(zipOptions);
        int userChoice = n.nextInt();
        switch (userChoice) {
            case 1:
                int zip = getPackages().get(0).getDestination().getZipCode();
                v.setZipDest(zip);
                v.fill(packages);
                System.out.println(v.report());
                totProfit += v.getProfit();
                numPackages += v.getPackages().size();
                v.empty();
                break;
            case 2:
                ArrayList<Integer> zipCodes = new ArrayList<>();
                for (Package p : packages) {
                    zipCodes.add(p.getDestination().getZipCode());
                }
                int maxCount = 0;
                int maxzip = 0;
                for (int i = 0; i < zipCodes.size(); i++) {
                    int zip1 = zipCodes.get(i);
                    int count = 0;
                    for (int j = 0; j < zipCodes.size(); j++) {
                        if (zipCodes.get(j) == zip1) {
                            count++;
                        }
                    }
                    if (count > maxCount) {
                        maxCount = count;
                        maxzip = zip1;
                        count = 0;
                    }

                }
                v.setZipDest(maxzip);
                v.fill(packages);
                System.out.println(v.report());
                totProfit += v.getProfit();
                numPackages += v.getPackages().size();
                v.empty();
                break;
            default:
        }

    }

    public static void printStatisticsReport(double profits, int packagesShipped, int numberOfPackages) {
        //TODO - PRINT STATISTICS: FORMAT!!!
        String stat = "==========Statistics==========\n" +
                "Profits:                 $" + profits +
                "\nPackages Shipped:                " + packagesShipped +
                "\nPackages in Warehouse:           " + numberOfPackages +
                "\n==============================";
        System.out.println(stat);

    }
}