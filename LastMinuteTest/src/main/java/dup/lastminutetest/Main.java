package dup.lastminutetest;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author gdupla
 */
public class Main {
    public static void main (String[] args) throws Exception{
        SearchEngine searchEngine = new SearchEngine ();
        FeedDataTest.FeedSearchEngine (searchEngine);   
        
        String IATAairportOrigin = "AMS";
        String IATAairportDestination = "FRA";
        int daysToDeparture = 31;
        int nAdults = 1;
        int nChilds = 0;
        int nInfants = 0;
        HashMap<String, Double> result = searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
        
        
        System.out.println ("Welcome to Guillermo's Flight Seach Engine.");
        do
        {
            SearchFlight (searchEngine);
        }
        while(GetStringTyped()!= null);
    }  
    
    public static void SearchFlight (SearchEngine searchEngine){
        System.out.println ("Introduce your Flight's Origin:");
        try{
            Airport airportOrigin = searchEngine.SearchAirport(GetStringTyped(), "city");        
        
            System.out.println ("Introduce your Flight's Destination:");
            Airport airportDestination = searchEngine.SearchAirport(GetStringTyped(), "city");
        
            System.out.println ("Introduce your Flight's Date (dd/mm/yyyy):");
            LocalDate fligthDate = GetDate();
        
            System.out.println ("Introduce the number of adults:");        
            int nAdults = GetNumberOfPassengers ();
        
            System.out.println ("Introduce the number of childs:");      
            int nChilds = GetNumberOfPassengers ();
        
            System.out.println ("Introduce the number of infants:");      
            int nInfants = GetNumberOfPassengers (); 
        
            System.out.println ("We've found these Flights:");
            ArrayList<String> flightsStr = searchEngine.searchFligthsStr (airportOrigin, airportDestination, fligthDate, nAdults, nChilds, nInfants);
        
            flightsStr.forEach((flightStr) -> {
                System.out.println (flightStr);
            });  
        } catch(AirportException ex){
            System.out.println ("No flights available: " + ex.getMessage());        
        } catch(NumberFormatException | DateTimeException | IOException ex){
            System.out.println ("Error. " + ex.getMessage());
        }
        System.out.println ("Press ENTER to search another flight");       
    }  
    
    public static int GetNumberOfPassengers () throws IOException, NumberFormatException{
        String numberIntroduced = GetStringTyped ();
        int number = Integer.parseInt(numberIntroduced);
        if (number < 0)
            throw new NumberFormatException("You have to enter an entire positive number (or zero) of passengers.");
        return number;
    }
    
    public static LocalDate GetDate () throws IOException, DateTimeException{
        String dateIntroduced = GetStringTyped ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        LocalDate date = LocalDate.parse(dateIntroduced, formatter);
        if (date.isBefore(LocalDate.now()))
            throw new DateTimeException("Flight date must be in the future.");
        return  date;
    }
    
    public static String GetStringTyped () throws IOException{
        StringBuilder str=new StringBuilder();
        char c;
        while ((c=(char)System.in.read())!='\n'){
            str.append(c);
        }
        
        return str.toString();        
    }
}
