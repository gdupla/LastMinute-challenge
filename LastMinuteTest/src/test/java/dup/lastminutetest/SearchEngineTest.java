package dup.lastminutetest;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class SearchEngine is testing also class FlightDataBase and class Flight.
 * @author gdupla
 */
public class SearchEngineTest {     

    /**
     * Test of AddFlight method, of class SearchEngine.
     */
    @org.junit.jupiter.api.Test
    public void testAddFlight() {
        System.out.println("AddFlight");
        Airport a1 = new Airport("A1", "A1");
        Airport a2 = new Airport("A2", "A2");
        Airline al = new Airline("AL", "AL", 1);
        
        Flight flight = new Flight (a1,a2,al,0,1);
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.AddFlight(flight);
        
        int nFlights = searchEngine.GetFlightsDataBase().GetCountFlights();
        assertEquals(1, nFlights);
    }

    /**
     * Test of AddAirport method, of class SearchEngine.
     */
    @org.junit.jupiter.api.Test
    public void testAddAirport() {
        System.out.println("AddAirport");
        String IATAcode = "A";
        String city = "A";
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.AddAirport(IATAcode, city);
        int nAirportsCode = searchEngine.getAirportsCode().size();
        int nAirportsCity = searchEngine.getAirportsCity().size();
        assertEquals(1, nAirportsCode);
        assertEquals(1, nAirportsCity);        
    }

    /**
     * Test of AddAirline method, of class SearchEngine.
     */
    @org.junit.jupiter.api.Test
    public void testAddAirline() {
        System.out.println("AddAirline");
        String IATAcode = "AL";
        String name = "AL";
        double infantPrice = 1.0;
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.AddAirline(IATAcode, name, infantPrice);
        int nAirlinesCode = searchEngine.getAirlinesCode().size();
        int nAirlinesName = searchEngine.getAirlinesName().size();
        assertEquals(1, nAirlinesCode);
        assertEquals(1, nAirlinesName);
    }

    /**
     * Test of SearchAirport method with an existing airport.
     */
    @org.junit.jupiter.api.Test
    public void testSearchExistingAirport() throws Exception {
        System.out.println("SearchAirport with an existing airport");
        SearchEngine searchEngine = new SearchEngine();
        FeedDataTest.FeedSearchEngine (searchEngine);  
        try {
            String code = "MAD";
            Airport result = searchEngine.SearchAirport(code, "code");
            assertEquals(code, result.getIATAcode());
            String city = "Madrid";
            result = searchEngine.SearchAirport(city, "city");
            assertEquals(city, result.getCity());
        }catch (AirportException ex) {
            fail("No AirportException is expected");
        }
    }
    
    /**
     * Test of SearchAirport method with a non existing airport.
     * Expected AirportException
     */
    @org.junit.jupiter.api.Test
    public void testSearchNonExistingAirport() throws Exception{        
        System.out.println("SearchAirline with a non existing airport - Expected AirportException");
        String str = "New York";
        String type = "city";
        SearchEngine searchEngine = new SearchEngine();
        FeedDataTest.FeedSearchEngine (searchEngine);  
        try {
            searchEngine.SearchAirport(str, type);
            fail("AirportException is expected");
        }catch (AirportException ex) {
        }
    }
    
    /**
     * Test of SearchAirline method with an existing airline.
     */
    @org.junit.jupiter.api.Test
    public void testSearchExistingAirline() throws Exception {
        System.out.println("SearchAirline with an existing airline");
        SearchEngine searchEngine = new SearchEngine();
        FeedDataTest.FeedSearchEngine (searchEngine);  
        try {
            String code = "IB";
            Airline result = searchEngine.SearchAirline(code, "code");
            assertEquals(code, result.getIATAcode());
            String name = "Iberia";
            result = searchEngine.SearchAirline(name, "name");
            assertEquals(name, result.getName());
        }catch (AirlineException ex) {
            fail("No AirlineException is expected");
        }
    }

    /**
     * Test of SearchAirline method with a non existing airline.     * 
     * Expected AirlineException.
     */
    @org.junit.jupiter.api.Test
    public void testSearchNonExistingAirline() throws Exception {
        System.out.println("SearchAirline with a non existing airline - Expected AirlineException");
        String str = "Delta";
        String type = "name";
        SearchEngine searchEngine = new SearchEngine();
        FeedDataTest.FeedSearchEngine (searchEngine);  
        try {
            searchEngine.SearchAirline(str, type);
            fail("AirlineException is expected");
        }catch (AirlineException ex) {
        }
    }

    /**
     * Test 1 of searchFligths method.
     * Search for 1 adult, 31 days to the departure date, flying AMS -> FRA.
     * Expected:
     * TK2372, 157.6 €
     * TK2659, 198.4 €
     * LH5909, 90.4 €
     */
    @org.junit.jupiter.api.Test
    public void testSearchFligths_1() throws Exception{
        System.out.println("SearchFligths: 1 adult, 31 days to the departure date, flying AMS -> FRA");
        SearchEngine searchEngine = new SearchEngine();
        FeedDataTest.FeedSearchEngine (searchEngine);  
        
        String IATAairportOrigin = "AMS";
        String IATAairportDestination = "FRA";
        int daysToDeparture = 31;
        int nAdults = 1;
        int nChilds = 0;
        int nInfants = 0;
        HashMap<String, Double> result = searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
        
        assertEquals(3, result.size());
        assertTrue(result.containsKey("TK2372"));
        assertTrue(result.containsKey("LH5909"));
        assertTrue(result.containsKey("TK2659"));
        assertEquals(157.6, result.get("TK2372"),2);
        assertEquals(90.4, result.get("LH5909"),2);
        assertEquals(198.4, result.get("TK2659"),2);
    }   
    
    /**
     * Test 2 of searchFligths method.
     * Search for 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST.
     * Expected:
     * TK8891, 806 €
     * LH1085, 481.19 €
     */
    @org.junit.jupiter.api.Test
    public void testSearchFligths_2() throws Exception{
        System.out.println("SearchFligths: 2 adults, 1 child, 1 infant, 15 days to the departure date, flying LHR -> IST");
        SearchEngine searchEngine = new SearchEngine();
        FeedDataTest.FeedSearchEngine (searchEngine);  
        
        String IATAairportOrigin = "LHR";
        String IATAairportDestination = "IST";
        int daysToDeparture = 15;
        int nAdults = 2;
        int nChilds = 1;
        int nInfants = 1;
        HashMap<String, Double> result = searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
        
        assertEquals(2, result.size());
        assertTrue(result.containsKey("TK8891"));
        assertTrue(result.containsKey("LH1085"));
        assertEquals(806, result.get("TK8891"),2);
        assertEquals(481.19, result.get("LH1085"),2);
    }
    
    /**
     * Test 3 of searchFligths method.
     * Search for 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD.
     * Expected:
     * IB2171, 909.09 €
     * LH5496, 1028.43 €
     */
    @org.junit.jupiter.api.Test
    public void testSearchFligths_3() throws Exception{
        System.out.println("SearchFligths: 1 adult, 2 children, 2 days to the departure date, flying BCN -> MAD");
        SearchEngine searchEngine = new SearchEngine();
        FeedDataTest.FeedSearchEngine (searchEngine);  
        
        String IATAairportOrigin = "BCN";
        String IATAairportDestination = "MAD";
        int daysToDeparture = 2;
        int nAdults = 1;
        int nChilds = 2;
        int nInfants = 0;
        HashMap<String, Double> result = searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
        
        assertEquals(2, result.size());
        assertTrue(result.containsKey("IB2171"));
        assertTrue(result.containsKey("LH5496"));
        assertEquals(909.09, result.get("IB2171"),2);
        assertEquals(1028.43, result.get("LH5496"),2);
    }
    
    /**
     * Test 4 of searchFligths method.
     * Search for flying CDG -> FRA.
     * Expected:
     * 0 flights founded
     */
    @org.junit.jupiter.api.Test
    public void testSearchFligths_4() throws Exception{
        System.out.println("SearchFligths: flying CDG -> FRA");
        SearchEngine searchEngine = new SearchEngine();
        FeedDataTest.FeedSearchEngine (searchEngine);  
        
        String IATAairportOrigin = "CDG";
        String IATAairportDestination = "FRA";
        int daysToDeparture = 0;
        int nAdults = 0;
        int nChilds = 0;
        int nInfants = 0;
        HashMap<String, Double> result = searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
        
        assertEquals(0, result.size());
    }
    
    /**
     * Test 5 of searchFligths method.
     * Search for -1 adult then -1 child then -1 infant then -1 daysToDeparture.
     * Expected  NumberFormatException
     */
    @org.junit.jupiter.api.Test
    public void testSearchFligths_5() throws Exception{
        System.out.println("SearchFligths: flying -1 adults then flying -1 adults flying -1 adults flying -1 adults - Expected NumberFormatException");
        SearchEngine searchEngine = new SearchEngine();
        FeedDataTest.FeedSearchEngine (searchEngine);  
        
        String IATAairportOrigin = "AMS";
        String IATAairportDestination = "FRA";
        int daysToDeparture = 0;
        int nAdults = -1;
        int nChilds = 0;
        int nInfants = 0;
        try {
            searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
            fail("adults = -1 -> NumberFormatException is expected");
        }catch (NumberFormatException ex1) {   
            nAdults = 0;
            nChilds = -1;
            try {
                searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
                fail("child = -1 -> NumberFormatException is expected");            
            }catch (NumberFormatException ex2) {
                nChilds = 0;
                nInfants = -1;        
                try {
                    searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
                    fail("infants = -1 -> NumberFormatException is expected");
                }catch (NumberFormatException ex3) {            
                    nInfants = 0; 
                    daysToDeparture = -1;
                    try {
                        searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
                        fail("daysToDeparture = -1 -> NumberFormatException is expected");
                    }catch (NumberFormatException ex4) {            
                    }
                }
            }
        }  
    }
    
    /**
     * Test 6 of searchFligths method.
     * Search for NY IATA Code.
     * Expected  AirportException
     */
    @org.junit.jupiter.api.Test
    public void testSearchFligths_6() throws Exception{
        System.out.println("SearchFligths: flying from NY to FRA then from FRA to NY - Expected AirportException");
        SearchEngine searchEngine = new SearchEngine();
        FeedDataTest.FeedSearchEngine (searchEngine);  
        
        String IATAairportOrigin = "NY";
        String IATAairportDestination = "FRA";
        int daysToDeparture = 0;
        int nAdults = 0;
        int nChilds = 0;
        int nInfants = 0;
        try {
            searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
            fail("origin NY -> AirportException is expected");
        }catch (AirportException ex1) {   
            IATAairportOrigin = "FRA";
            IATAairportDestination = "NY";
            try {
                searchEngine.searchFligthsTest(IATAairportOrigin, IATAairportDestination, daysToDeparture, nAdults, nChilds, nInfants);
                fail("destination NY -> AirportException is expected");            
            }catch (AirportException ex2) {                
            }
        }  
    }
}
