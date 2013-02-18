import java.util.*;

public class FareCalculator {
	
	/**
	 * Determines the total cost of all the Flights in the input ArrayList.
	 * 
	 * If there is any two-segment flight combination (in which 
	 * one flight goes from X to Y, and another goes from Y to Z), there
	 * is a 10% surcharge applied to the more expensive of the two flights. This
	 * surcharge is not applied, though, if the total travel time is five hours
	 * or longer. 
	 * 
	 * Additionally, if there is any round-trip flight combination (in which
	 * one flight goes from X to Y, and another goes from Y to X), then
	 * the fare should be twice the fare of the less expensive of the two flights.
	 * 
	 * You can assume that there will not be any three-segment flight combination,
	 * e.g. X to Y to Z to K. Our airline doesn't allow that. Or something.
	 * 
	 * However, if you have a two-segment round-trip flight combination, e.g.
	 * X to Y to Z, then Z to Y to X, then the 10% surcharge should not be applied
	 * but the rules for round-trip pricing should be applied to each individual
	 * round-trip, i.e. X-Y/Y-X and Y-Z/Z-Y. 
	 * 
	 */
	public static double calculateFare(ArrayList<Flight> myFlights) {
		
		int totalCost = 0;
		
		for (Flight flight : myFlights) {
			
			totalCost += flight.cost();
		}
		
		// now look for flights for which there is a connection
		for (Flight f1 : myFlights) {
			for (Flight f2 : myFlights) {
				if (f1.end().equals(f2.start())) {
					if ((f2.end().equals(f1.start()) == false) && (f1.time() + f2.time() >= 300)) {
						if (f1.cost() > f2.cost()) {
							totalCost += f1.cost() * 1.1;
						}
						else if (f2.cost() < f1.cost()) {
							totalCost += f2.cost() * 1.1;
						}
					}
					else if (f1.start().equals(f2.end())) {
						if (f1.cost() < f2.cost()) {
							totalCost -= f2.cost();
							totalCost += f1.cost();
						}
						else {
							totalCost -= f1.cost();
							totalCost += f2.cost();
						}
					}
				}
					
					
			}
		}
		
		return totalCost;
	}
	

}
