package dup.lastminutetest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author gdupla
 */
public class FlightsDataBase {
    private final HashMap<String, HashMap<String, ArrayList<Flight>>> flights;
    
    public FlightsDataBase () {
        this.flights = new HashMap<>();
    }
    
    public void AddFligth (Flight flight){
        String origin = flight.getOrigin().getIATAcode();
        String destination = flight.getDestination().getIATAcode();
        
        if (this.flights.containsKey(origin))
        {
            if(!this.flights.get(origin).containsKey(destination)){
                this.flights.get(origin).put(destination, new ArrayList<>());
            }
        }
        else
        {
            this.flights.put(origin, new HashMap<>());
            this.flights.get(origin).put(destination, new ArrayList<>());
        }
        
        this.flights.get(origin).get(destination).add(flight);       
    }
    
    public int GetCountFlights(){
        return this.flights.size();
    }
    
    public ArrayList<Flight> searchFlights (Airport origin, Airport destination) {
        ArrayList<Flight> flightsResult = new ArrayList<>();
        if(this.flights.containsKey(origin.getIATAcode()))
            if(this.flights.get(origin.getIATAcode()).containsKey(destination.getIATAcode()))        
                flightsResult = this.flights.get(origin.getIATAcode()).get(destination.getIATAcode());
        
        return flightsResult;
    }
}
