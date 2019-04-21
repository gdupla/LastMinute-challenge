package dup.lastminutetest;

/**
 *
 * @author gdupl
 */
public class FeedDataTest {
    public static void FeedSearchEngine (SearchEngine searchEngine) throws Exception {
        FeedTestAirports (searchEngine);
        FeedTestAirlines (searchEngine);
        FeedTestFlights (searchEngine);
    } 
    
    private static void FeedTestAirports (SearchEngine searchEngine) {
        searchEngine.AddAirport("MAD", "Madrid");
        searchEngine.AddAirport("BCN", "Barcelona");
        searchEngine.AddAirport("LHR", "London");
        searchEngine.AddAirport("CDG", "Paris");
        searchEngine.AddAirport("FRA", "Frakfurt");
        searchEngine.AddAirport("IST", "Istanbul");
        searchEngine.AddAirport("AMS", "Amsterdam");
        searchEngine.AddAirport("FCO", "Rome");
        searchEngine.AddAirport("CPH", "Copenhagen");
    }
    
    private static void FeedTestAirlines (SearchEngine searchEngine) {
        searchEngine.AddAirline("IB", "Iberia", 1);        
        searchEngine.AddAirline("BA", "British Airways", 15);
        searchEngine.AddAirline("LH", "Lufthansa", 7);
        searchEngine.AddAirline("FR", "Ryanair", 20);
        searchEngine.AddAirline("VY", "Vueling", 10);
        searchEngine.AddAirline("TK", "Turkish Airlines", 5);
        searchEngine.AddAirline("U2", "Easyjet", 19.90);
    }
    
    private static void FeedTestFlights (SearchEngine searchEngine) throws AirportException, AirlineException {
        String flightsStr = "CPH,FRA,IB2818,186\n" +
            "CPH,LHR,U23631,152\n" +
            "CDG,MAD,IB8482,295\n" +
            "BCN,FRA,FR7521,150\n" +
            "CPH,FCO,TK4667,137\n" +
            "CPH,FCO,U24631,268\n" +
            "FCO,CDG,VY4335,158\n" +
            "LHR,IST,TK8891,250\n" +
            "FRA,AMS,U24107,237\n" +
            "CPH,BCN,U22593,218\n" +
            "BCN,IST,VY9890,178\n" +
            "AMS,CPH,TK4927,290\n" +
            "FCO,MAD,BA1164,118\n" +
            "CPH,LHR,BA7710,138\n" +
            "BCN,AMS,U24985,191\n" +
            "MAD,CDG,IB9961,128\n" +
            "LHR,FRA,LH2118,165\n" +
            "IST,FRA,IB8911,180\n" +
            "AMS,FRA,TK2372,197\n" +
            "FRA,IST,LH4145,169\n" +
            "MAD,CDG,IB6112,112\n" +
            "CPH,FRA,LH1678,298\n" +
            "LHR,CPH,LH6620,217\n" +
            "MAD,LHR,TK4199,186\n" +
            "MAD,CDG,IB7403,253\n" +
            "FRA,CPH,BA4369,109\n" +
            "BCN,MAD,IB2171,259\n" +
            "IST,LHR,LH6412,197\n" +
            "IST,MAD,LH1115,160\n" +
            "LHR,LHR,VY8162,285\n" +
            "FRA,LHR,BA8162,205\n" +
            "AMS,FCO,BA7610,168\n" +
            "LHR,IST,LH1085,148\n" +
            "FCO,FRA,U21423,274\n" +
            "CPH,MAD,U23282,113\n" +
            "CDG,CPH,LH5778,263\n" +
            "CPH,CDG,BA2777,284\n" +
            "BCN,LHR,TK4375,208\n" +
            "MAD,FCO,LH8408,149\n" +
            "AMS,IST,IB4563,109\n" +
            "LHR,FCO,LH5174,251\n" +
            "MAD,BCN,BA9569,232\n" +
            "AMS,FRA,TK2659,248\n" +
            "LHR,CDG,IB2771,289\n" +
            "IST,MAD,IB8688,150\n" +
            "CPH,AMS,TK8355,137\n" +
            "FCO,CDG,VY2974,111\n" +
            "AMS,FRA,LH5909,113\n" +
            "CPH,BCN,FR7949,176\n" +
            "BCN,CPH,U27858,237\n" +
            "FRA,AMS,LH2320,288\n" +
            "LHR,BCN,VY4633,149\n" +
            "AMS,IST,IB7289,163\n" +
            "FRA,LHR,IB9443,254\n" +
            "IST,FCO,LH4948,176\n" +
            "IST,BCN,TK5558,211\n" +
            "BCN,BCN,BA9409,215\n" +
            "IST,AMS,FR9261,267\n" +
            "CDG,IST,IB7181,227\n" +
            "LHR,BCN,TK1446,217\n" +
            "FCO,FRA,TK2793,175\n" +
            "AMS,CPH,FR1491,284\n" +
            "IST,BCN,IB9219,279\n" +
            "MAD,AMS,TK7871,159\n" +
            "FCO,AMS,VY4840,260\n" +
            "MAD,FRA,BA8982,171\n" +
            "IST,LHR,U23526,254\n" +
            "FRA,MAD,BA6773,157\n" +
            "CDG,CPH,IB5257,299\n" +
            "CPH,CDG,LH8545,230\n" +
            "LHR,AMS,IB4737,110\n" +
            "BCN,MAD,LH5496,293\n" +
            "CDG,LHR,U29718,103\n" +
            "LHR,AMS,BA9561,253\n" +
            "FRA,LHR,TK3167,118\n" +
            "IST,FRA,FR4727,108\n" +
            "CPH,IST,LH6320,115\n" +
            "LHR,AMS,BA6657,122\n" +
            "LHR,FRA,TK5342,295\n" +
            "IST,LHR,IB4938,226\n" +
            "CDG,BCN,VY9791,289\n" +
            "MAD,LHR,IB4124,272\n" +
            "FRA,MAD,BA7842,121\n" +
            "AMS,FCO,VY5092,178\n" +
            "CDG,LHR,BA9813,171\n" +
            "FRA,IST,BA2421,226\n" +
            "IST,CPH,U28059,262\n" +
            "MAD,AMS,LH7260,191\n" +
            "CDG,CPH,TK2044,186";
        String[] flightsList = flightsStr.split("\n");
        
        for (String flightStr:flightsList){
            String[] dataFlight = flightStr.split(",");
            Airport origin = searchEngine.SearchAirport(dataFlight[0], "code");
            Airport destination = searchEngine.SearchAirport(dataFlight[1], "code");
            
            String airlineCode = dataFlight[2].substring(0,2);
            int flightCode = Integer.parseInt(dataFlight[2].substring(2));
            Airline airline = searchEngine.SearchAirline(airlineCode, "code");
            
            double basePrice = Double.parseDouble(dataFlight[3]);
            
            Flight flight = new Flight(origin, destination, airline, flightCode, basePrice);
            searchEngine.AddFlight(flight);
        }
    }
}
