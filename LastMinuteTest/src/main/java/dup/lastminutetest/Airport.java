package dup.lastminutetest;

/**
 *
 * @author gdupla
 */
public class Airport {
    private final String IATAcode;
    private final String city;
    
    public Airport (String IATAcode, String city){
        this.IATAcode = IATAcode;
        this.city = city;        
    }
    
    public String getCity () { return this.city; };
    
    public String getIATAcode () { return this.IATAcode; };    
}
