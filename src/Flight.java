import java.util.Comparator;


public class Flight implements Comparator<Flight> {
	
	// starting point for the flight
	private String _start;
	// end point for the flight
	private String _end;
	// travel time for the flight
	private int _time;
	// cost of the flight
	private int _cost;

	// all of the flights
	private static final Flight[] flights = new Flight[30];
	
	public Flight(String start, String end, int time, int cost) {
		_start = start;
		_end = end;
		_time = time;
		_cost = cost;
	}
	
	public String start() { return _start; }
	public String end() { return _end; }
	public int time() { return _time; }
	public int cost() { return _cost; }
	
	public String toString() {
		return _start + "-" + _end + ": " + _time + " mins; $" + _cost;
	}
	
	
	public static Flight[] allFlights(){
		return flights;
	}
	
	static {
	
		flights[0] = new Flight("PHL", "BOS", 95, 185);
		flights[1] = new Flight("BOS", "PHL", 95, 205);
		flights[2] = new Flight("PHL", "IAD", 60, 185);
		flights[3] = new Flight("IAD", "PHL", 60, 185);
		flights[4] = new Flight("PHL", "ORD", 100, 270);
		flights[5] = new Flight("ORD", "PHL", 100, 285);
		flights[6] = new Flight("PHL", "ATL", 120, 325);
		flights[7] = new Flight("ATL", "PHL", 120, 400);
		flights[8] = new Flight("PHL", "DFW", 175, 205);
		flights[9] = new Flight("DFW", "PHL", 175, 200);
		flights[10] = new Flight("DFW", "SFO", 150, 280);
		flights[11] = new Flight("SFO", "DFW", 150, 220);
		flights[12] = new Flight("DET", "SFO", 180, 305);
		flights[13] = new Flight("SFO", "DET", 180, 305);
		flights[14] = new Flight("ORD", "SFO", 190, 180);
		flights[15] = new Flight("SFO", "ORD", 190, 200);
		flights[16] = new Flight("ORD", "SEA", 225, 300);
		flights[17] = new Flight("SEA", "ORD", 225, 300);
		flights[18] = new Flight("ATL", "BOS", 140, 220);
		flights[19] = new Flight("BOS", "ATL", 140, 240);
		flights[20] = new Flight("JFK", "LHR", 400, 605);
		flights[21] = new Flight("LHR", "JFK", 400, 605);
		flights[22] = new Flight("PHL", "JFK", 70, 105);
		flights[23] = new Flight("JFK", "PHL", 70, 110);
		flights[24] = new Flight("BOS", "LHR", 345, 550);
		flights[25] = new Flight("LHR", "BOS", 345, 580);
		flights[26] = new Flight("ATL", "CDG", 580, 440);
		flights[27] = new Flight("CDG", "ATL", 580, 440);
		flights[28] = new Flight("PHL", "CDG", 605, 535);
		flights[29] = new Flight("CDG", "PHL", 605, 525);
		
	}

	@Override
	public int compare(Flight f1, Flight f2) {
		return f1.end().compareTo(f2.end());
	}


}
