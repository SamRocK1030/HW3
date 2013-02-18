import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FlightFinder {

	private ArrayList<Flight> _directFlights = new ArrayList<Flight>();
	private ArrayList<Flight[]> _indirectFlights = new ArrayList<Flight[]>();
	
	public ArrayList<Flight> directFlights() { return _directFlights; }
	public ArrayList<Flight[]> indirectFlights() { return _indirectFlights; }
	
	/**
	 * Look through the (hard-coded) list of flights and return the number
	 * of flights from the home airport to the destination. If direct is true,
	 * then only consider direct flights; if it is false, count flights that consist
	 * of two segments, too. However, in those cases, only report flights in which
	 * the total time is less than or equal to the specified timeLimit. 
	 */
	public int numFlights(String home, String dest, boolean direct, int timeLimit)
	{
		// keep track of the number of flights
		int count = 0;
		// clear the ArrayLists
		_directFlights.clear();
		_indirectFlights.clear();
		
		// the array of all flights
		Flight[] allFlights = Flight.allFlights();
		
		// first, find direct flights
		for (int i = 0; i < allFlights.length; i++) {
			Flight f = allFlights[i];
			if (f.start().equals(home) && f.end().equals(dest)) {
				_directFlights.add(f);
				count++;
			}
		}
	
		// then, find indirect flights (max two segments)
		if (!direct) {
			for (int i = 0; i < allFlights.length; i++) {
				if (allFlights[i].start().equals(home)) {
					for (int j = 0; j < allFlights.length; j++) {
						if (allFlights[i].end().equals(allFlights[j].start()) && allFlights[i].end().equals(dest) && allFlights[i].time()+allFlights[j].time() < timeLimit) {
							Flight indirectFlight[] = { allFlights[i], allFlights[j] }; 
							_indirectFlights.add(indirectFlight);
							count++;
						}
					}
				}
			}
		}
		
		return count;
		
	}
	
	public enum SortCriteria {
		TIME, COST, DESTINATION;
	}
	
	/**
	 * Returns a List of all flights that originate from the specified airport.
	 * The List should be sorted according to the sort criteria, i.e. by flight
	 * time (non-decreasing), cost (non-decreasing), or destination airport (alphabetically).
	 */
	public List<Flight> allFlights(String orig, SortCriteria criteria) {
		
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		// the array of all flights
		Flight[] allFlights = Flight.allFlights();
		
		// first, find the flights
		for (int i = 0; i < allFlights.length; i++) {
			Flight f = allFlights[i];
			if (f.start().equals(orig)) {
				flights.add(f);
			}
		}
		
		if (criteria == SortCriteria.TIME) {
			// bubble sort! w00t!
			for (int i = 0; i < flights.size(); i++) {
				for (int j = i; j < flights.size()-1; j++) {
					if (flights.get(j).time() < flights.get(j+1).time()) {
						Flight temp = flights.get(j);
						flights.set(j, flights.get(j+1));
						flights.set(j+1, temp);
					}
				}
			}
		}
		else if (criteria == SortCriteria.COST) {
			ArrayList<Flight> sorted = new ArrayList<Flight>();
			// find the minimum and then populate the sorted list in order
			int minIndex = 0;
			int lastMin = 0;
			for (int j = 0; j < flights.size(); j++) {
				int min = 1000000;
				for (int i = 0; i < flights.size(); i++) {
					if (flights.get(i).cost() < min && flights.get(i).cost() > lastMin) {
						min = flights.get(i).cost();
						minIndex = i;
					}
				}
				sorted.add(flights.get(minIndex));
				lastMin = flights.get(minIndex).cost();
			}
			flights = sorted;
		}
		else if (criteria == SortCriteria.DESTINATION) {
			// use the built-in Java sorting functionality
			Collections.sort(flights, flights.get(0));
		}
		
		return flights;
	}
	

}
