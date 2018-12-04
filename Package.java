/**
 * <h1>Package</h1> Represents a package
 */
public class Package {
    private String id;
    private String product;
    private double weight;
    private double price;
    private ShippingAddress destination;

    /**
     * Default Constructor
     */
    //============================================================================
    //T\ODO
    public Package() {
        this("", "", 0, 0, new ShippingAddress());
    }
    //============================================================================
    /**
     * Constructor
     * 
     * @param id          id number of product
     * @param product     name of product in package
     * @param weight      weight of package
     * @param price       price of product
     * @param destination the destination of the package
     * 
     */
    //============================================================================
    //T\ODO
    public Package(String id, String product, double weight, double price, ShippingAddress destination) {
        this.id = id;
        this.product = product;
        this.weight = weight;
        this.price = price;
        this.destination = destination;
    }
    //============================================================================

    /**
     * @return id of package
     */
    public String getID() {
    	//T\ODO
        return this.id;
    }
    
    
    
    /**
     * @return Name of product in package
     */
    public String getProduct() {
    	//T\ODO
        return this.product;
    }
    
    
    

    /**
     * @param product the product name to set
     */
    public void setProduct(String product) {
    	//T\ODO
        this.product = product;
    }

    
    
    
    /**
     * @return price of product in package
     */
    public double getPrice() {
    	//T\ODO
        return this.price;
    }

    
    
    
    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
    	//T\ODO
        this.price = price;
    }

    
    
    
    /**
     * @return Package weight
     */
    public double getWeight() {
    	//T\ODO
        return this.weight;
    }

    
    
    
    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
    	//T\ODO
        this.weight = weight;
    }

    
    
    /**
     * @return The shipping address of package
     */
    public ShippingAddress getDestination() {
    	//T\ODO
        return this.destination;
    }

    
    
    
    /**
     * @param destination the shipping address to set
     */
    public void setDestination(ShippingAddress destination) {
    	//T\ODO
        this.destination = destination;
    }

    
    
    /**
     * @return The package's shipping label.
     */
    public String shippingLabel() {
    	//TODO
        String fin = "====================" +
                "TO: " + this.destination.getName() + "\n" +
                this.destination.getAddress() + "\n" +
                this.destination.getCity() + ", " +
                this.destination.getState() + "," +
                this.destination.getZipCode() + "\n" +
                "Weight:         " + this.weight + "\n" +
                "Price:         $" + this.price + "\n" +
                "Product: " + this.product + "\n" +
                "====================";
        return fin;
    }

}