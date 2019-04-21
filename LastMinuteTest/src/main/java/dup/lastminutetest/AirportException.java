package dup.lastminutetest;

/**
 *
 * @author gdupla
 */
public class AirportException extends Exception{
    AirportException(String str, String type) {
        super(String.format("Error. %1$s %2$s does not exists.", str, type.equals("city") ? "City" : "Airport IATA Code"));               
    }    
}
