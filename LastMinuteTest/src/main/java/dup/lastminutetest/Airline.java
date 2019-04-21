package dup.lastminutetest;

/**
 *
 * @author gdupla
 */
public class Airline {
    private final String IATAcode;
    private final String name;
    private final double infantPrice;
    
    public Airline (String IATAcode, String name, double infantPrice){
        this.IATAcode = IATAcode;
        this.name = name;
        this.infantPrice = infantPrice;
    }
    
    public String getIATAcode () { return this.IATAcode; };    
    
    public String getName () { return this.name; };
    
    public double getInfantPrice () { return this.infantPrice; };    
}