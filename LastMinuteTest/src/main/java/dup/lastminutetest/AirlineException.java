package dup.lastminutetest;

/**
 *
 * @author gdupla
 */
public class AirlineException extends Exception{
    AirlineException(String str, String type) {
        super(String.format("Error. %1$s %2$s does not exists.", str, type.equals("name") ? "Airline" : "Airline IATA Code"));               
    }    
}
