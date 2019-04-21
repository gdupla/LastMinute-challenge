package dup.lastminutetest;

/**
 *
 * @author gdupla
 */
public class Flight {
    private final Airport origin;
    private final Airport destination;
    private final Airline airline;
    private final int codeNumber;
    private final double basePrice;
    
    public Flight (Airport origin, Airport destination, Airline airline, int codeNumber, double basePrice){
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.codeNumber = codeNumber;
        this.basePrice = basePrice;
    }    
    
    public Airport getOrigin() { return this.origin; }
    
    public Airport getDestination() { return this.destination; }
    
    public Airline getAirline() { return this.airline; }
    
    public String getCodeFlight() { return this.airline.getIATAcode() + this.codeNumber; }
    
    public double getPrice(int daysToDeparture, int nAdults, int nChilds, int nInfants) { 
        double individualPrice=0;
        if(daysToDeparture<= 2){
            individualPrice = this.basePrice * 1.5;                
        } else if(daysToDeparture<= 15){
            individualPrice = this.basePrice * 1.2;                
        } else if(daysToDeparture<= 30){
            individualPrice = this.basePrice;                
        } else if(daysToDeparture>=31){
            individualPrice = this.basePrice * 0.8;    
        }
        
        double infantPrice = getAirline().getInfantPrice();        
        
        return nAdults*individualPrice + nChilds*individualPrice*0.67 + nInfants * infantPrice;
    } 
    
    public String GetFlightStr (int daysToDeparture, int nAdults, int nChilds, int nInfants) {
        return getCodeFlight() + ", " + getPrice(daysToDeparture, nAdults, nChilds, nInfants) + " €";
    }
}
