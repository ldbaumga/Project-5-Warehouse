/**
 * <h1>Profitable</h1>
 * 
 * This interface represents something that can be used to make a profit. Along
 * with returning total profits it must also be able to provide a report.
 */
public interface Profitable {
	
	//T\ODO
    //Vehicle may need to be abstract

    public double getProfit();
    public String report();
}