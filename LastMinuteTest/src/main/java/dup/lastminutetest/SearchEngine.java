package dup.lastminutetest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author gdupla
 */
public class SearchEngine {
    private FlightsDataBase flights;
    
    private final HashMap<String,Airport> airportsCity;
    private final HashMap<String,Airport> airportsCode;
    private final HashMap<String,Airline> airlinesName;
    private final HashMap<String,Airline> airlinesCode;
    
    public SearchEngine () {
        this.airportsCity = new HashMap<>();
        this.airportsCode = new HashMap<>();
        this.airlinesName = new HashMap<>();
        this.airlinesCode = new HashMap<>();
        this.flights = new FlightsDataBase();        
    }
    
    public HashMap<String,Airport> getAirportsCity () {
        return this.airportsCity;
    }
    
    public HashMap<String,Airport> getAirportsCode () {
        return this.airportsCode;
    }
    
    public HashMap<String,Airline> getAirlinesName () {
        return this.airlinesName;
    }
    
    public HashMap<String,Airline> getAirlinesCode () {
        return this.airlinesCode;
    }
    
    public FlightsDataBase GetFlightsDataBase () {
        return this.flights;
    }
    
    public void SetFlightsDataBase (FlightsDataBase flights) {
        this.flights= flights;
    }
    
    public void AddFlight (Flight flight){
        this.flights.AddFligth(flight);
    }
    
    public void AddAirport (String IATAcode, String city){
        this.airportsCity.put(city, new Airport(IATAcode, city));
        this.airportsCode.put(IATAcode, new Airport(IATAcode, city));
    }
    
    public void AddAirline (String IATAcode, String name, double infantPrice){
        this.airlinesName.put(name, new Airline(IATAcode, name, infantPrice));
        this.airlinesCode.put(IATAcode, new Airline(IATAcode, name, infantPrice));
    }
    
    public Airport SearchAirport (String str, String type) throws AirportException{
        Airport airportSearched;
        if (type.equals("city")){
            if (!this.airportsCity.containsKey(str))
                throw new AirportException (str, type); 
            else
                airportSearched = this.airportsCity.get(str);
        }else {
            if (!this.airportsCode.containsKey(str))
                throw new AirportException (str, type); 
            else
                airportSearched = this.airportsCode.get(str);         
        }       
        
        return airportSearched;
    }
    
    public Airline SearchAirline (String str, String type) throws AirlineException{
        Airline airlineSearched;
        if (type.equals("name")){
            if (!this.airlinesName.containsKey(str))
                throw new AirlineException (str, type); 
            else
                airlineSearched = this.airlinesName.get(str);
        }else {
            if (!this.airlinesCode.containsKey(str))
                throw new AirlineException (str, type); 
            else
                airlineSearched = this.airlinesCode.get(str);         
        }       
        
        return airlineSearched;
    }
    
    /**
     * Method to search flights in the flight'sdatabase. It returns the founded flights with the total price calculated.
     * @param airportOrigin origin's Airport 
     * @param airportDestination destination's Airport 
     * @param daysToDeparture number of days of departure. Has to be >= 0
     * @param nAdults number of adults who is going to flight. Has to be >= 0
     * @param nChilds number of childs who is going to flight. Has to be >= 0
     * @param nInfants number of infants who is going to flight. Has to be >= 0
     * @return HashMap with founded Flight as a Key and Total price as a value.
     * @throws NumberFormatException if one of the int params is negative.
    */
    public HashMap<Flight,Double> searchFligths (Airport airportOrigin, Airport airportDestination, 
            int daysToDeparture, int nAdults, int nChilds, int nInfants) throws NumberFormatException{
        
        if (daysToDeparture < 0 || nAdults < 0 || nChilds < 0 || nInfants < 0)
            throw new NumberFormatException("You have to enter an entire positive number (or zero) of passengers or days to departure.");
        
        HashMap<Flight,Double> flightsResult = new HashMap<>();
        
        this.flights.searchFlights(airportOrigin, airportDestination).forEach((flight) -> {
            flightsResult.put(flight,flight.getPrice(daysToDeparture, nAdults, nChilds, nInfants));
        });
        
        return flightsResult;
    }   
    
    /**
     * Method used to Test the searhFlight method.
     * @param IATAairportOrigin IATA Code of the origin's Airport 
     * @param IATAairportDestination IATA Code of the destination's Airport 
     * @param daysToDeparture number of days of departure. Has to be >= 0
     * @param nAdults number of adults who is going to flight. Has to be >= 0
     * @param nChilds number of childs who is going to flight. Has to be >= 0
     * @param nInfants number of infants who is going to flight. Has to be >= 0
     * @return HashMap with founded FlightCode as a Key and Total price as a value.
     * @throws dup.lastminutetest.AirportException if the IATA Code of the origin or the destination doesn't exists in the database.
     * @throws NumberFormatException if one of the int params is negative.
    */
    public HashMap<String,Double> searchFligthsTest (String IATAairportOrigin, String IATAairportDestination, 
            int daysToDeparture, int nAdults, int nChilds, int nInfants) throws AirportException, NumberFormatException{
        
        Airport airportOrigin = SearchAirport (IATAairportOrigin, "code");
        Airport airportDestination = SearchAirport (IATAairportDestination, "code");
        
        HashMap<String,Double> flightsResult = new HashMap<>();
        
        searchFligths (airportOrigin, airportDestination, daysToDeparture, nAdults, nChilds, nInfants).keySet().forEach((flight) -> {
            flightsResult.put(flight.getCodeFlight(),flight.getPrice(daysToDeparture, nAdults, nChilds, nInfants));
        });
        
        return flightsResult;
    }
    
    /**
     * Method used to search Flights in the Main program. The search is made by searchFlight method.
     * @param airportOrigin origin's Airport 
     * @param airportDestination destination's Airport 
     * @param fligthDate LocalDate with the date of the flight looked for.
     * @param nAdults number of adults who is going to flight. Has to be >= 0
     * @param nChilds number of childs who is going to flight. Has to be >= 0
     * @param nInfants number of infants who is going to flight. Has to be >= 0
     * @return ArrayList with founded FlightCode + total price as items.
     * @throws NumberFormatException if one of the int params is negative.
    */
    public ArrayList<String> searchFligthsStr (Airport airportOrigin, Airport airportDestination, 
            LocalDate fligthDate, int nAdults, int nChilds, int nInfants){
        ArrayList<String> flightsStr = new ArrayList<>();
        
        int daysToDeparture = (int)ChronoUnit.DAYS.between(LocalDate.now(), fligthDate);    
        
        HashMap<Flight,Double> flightsResult = searchFligths (airportOrigin, airportDestination, 
            daysToDeparture, nAdults, nChilds, nInfants);  
                
        flightsResult.keySet().forEach((flight) -> {
            flightsStr.add(flight.GetFlightStr(daysToDeparture, nAdults, nChilds, nInfants));
        });
        
        return flightsStr;
    }  
}
