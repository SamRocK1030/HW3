import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;




public class FlightFinderTest {

	// field to use in the various tests
	FlightFinder ff;
	Flight[] flights;
	
	@Before
	public void setUp() throws Exception {
		// initialize a new FlightFinder object before EVERY test
		ff = new FlightFinder();
		flights = Flight.allFlights();
	}

	@Test
	public void testNumFlightsDirect_1() {
		
		// test a direct flight that should exist
		// based on what's in Flight.allFlights
		
		// note that, if "direct" is true, then "timeLimit" should not be used
		int actual = ff.numFlights("PHL", "BOS", true, 0);
		
		int expected = 1;
		assertEquals(expected, actual);
		
		// don't forget to check the side effects!!
		ArrayList<Flight> direct = ff.directFlights();
		assertEquals(1, direct.size()); // check number of elements in list
		
		Flight f = direct.get(0);
		assertEquals("PHL", f.start());
		assertEquals("BOS", f.end());
			
		// check that there are no indirect flights reported
		ArrayList<Flight[]> indirect = ff.indirectFlights();
		assertEquals(0, indirect.size());

	}
	
	@Test
	public void testNumFlightsDirect_2() {
		
		// test a direct flight that should not exist
		// based on what's in Flight.allFlights
		
		// note that, if "direct" is true, then "timeLimit" should not be used
		int actual = ff.numFlights("IAD", "BOS", true, 0);
		
		int expected = 0;
		assertEquals(expected, actual);
		
		// check that there are no direct flights reported
		ArrayList<Flight> direct = ff.directFlights();
		assertEquals(0, direct.size()); // check number of elements in list
			
		// check that there are no indirect flights reported
		ArrayList<Flight[]> indirect = ff.indirectFlights();
		assertEquals(0, indirect.size());

	}
	
	@Test
	public void testNumFlightsDirect_3() {
		
		// test that timeLimit is ignored in direct flights
		// based on what's in Flight.allFlights
		
		// note that, if "direct" is true, then "timeLimit" should not be used
		int actual = ff.numFlights("PHL", "BOS", true, -1);
		
		int expected = 1;
		assertEquals(expected, actual);
		
		// check that there are no direct flights reported
		ArrayList<Flight> direct = ff.directFlights();
		assertEquals(1, direct.size()); // check number of elements in list
		
		Flight f = direct.get(0);
		assertEquals("PHL", f.start());
		assertEquals("BOS", f.end());
			
		// check that there are no indirect flights reported
		ArrayList<Flight[]> indirect = ff.indirectFlights();
		assertEquals(0, indirect.size());

	}
	
	@Test
	public void testNumFlightsIndirect_1() {
		
		// test an indirect flight that should exist
		// based on what's in Flight.allFlights
		
		int actual = ff.numFlights("IAD", "BOS", false, 60+95);
		
		int expected = 1;
		assertEquals(expected, actual);
		
		// check that there are no direct flights reported
		ArrayList<Flight> direct = ff.directFlights();
		assertEquals(0, direct.size()); // check number of elements in list
			
		// check that the correct indirect flights are reported
		ArrayList<Flight[]> indirect = ff.indirectFlights();
		assertEquals(1, indirect.size());
		
		Flight[] indirectComponents = indirect.get(0);
		assertEquals(2, indirectComponents.length);// check number of elements in list
		assertEquals("IAD", indirectComponents[0].start());
		assertEquals("PHL", indirectComponents[0].end());
		assertEquals("PHL", indirectComponents[1].start());
		assertEquals("BOS", indirectComponents[1].end());

	}
	
	@Test
	public void testNumFlightsIndirect_2() {
		
		// test an indirect flight that should exist, but isnt within the timeLimit 
		// based on what's in Flight.allFlights
		
		int actual = ff.numFlights("IAD", "BOS", false, 1);
		
		int expected = 0;
		assertEquals(expected, actual);
		
		// check that there are no direct flights reported
		ArrayList<Flight> direct = ff.directFlights();
		assertEquals(0, direct.size()); // check number of elements in list
			
		// check that the correct indirect flights are reported
		ArrayList<Flight[]> indirect = ff.indirectFlights();
		assertEquals(0, indirect.size());

	}
	
	@Test
	public void testNumFlightsIndirect_3() {
		
		// test an indirect flight that should not exist 
		// based on what's in Flight.allFlights
		
		int actual = ff.numFlights("SEA", "BOS", false, 1000);
		
		int expected = 0;
		assertEquals(expected, actual);
		
		// check that there are no direct flights reported
		ArrayList<Flight> direct = ff.directFlights();
		assertEquals(0, direct.size()); // check number of elements in list
			
		// check that the correct indirect flights are reported
		ArrayList<Flight[]> indirect = ff.indirectFlights();
		assertEquals(0, indirect.size());

	}
	
	@Test
	public void testNumFlightsIndirect_4() {
		
		// test an indirect flight that has multiple routes
		// based on what's in Flight.allFlights
		
		int actual = ff.numFlights("PHL", "ATL", false, 5000);
		
		int expected = 3;
		assertEquals(expected, actual);
		
		// check that there are no direct flights reported
		ArrayList<Flight> direct = ff.directFlights();
		assertEquals(1, direct.size()); // check number of elements in list
			
		// check that the correct indirect flights are reported
		ArrayList<Flight[]> indirect = ff.indirectFlights();
		assertEquals(2, indirect.size());
		
		//check that they're correct flights?
	}
	
	@Test
	public void testNumFlightsIndirect_5() {
		
		// test that negative timeLimit is handled
		
		int actual = ff.numFlights("IAD", "BOS", false, -3);
		
		int expected = -1;
		assertEquals(expected, actual);
		
	}

	@Test
	public void testNumFlightsHome() {
		
		// test that illegal Home string is handled
		
		int actual = ff.numFlights("CAT", "BOS", true, 0);
		
		int expected = -1;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testNumFlightsDest() {
		
		// test that illegal Dest string is handeled
		
		int actual = ff.numFlights("PHL", "DOG", true, 0);
		
		int expected = -1;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testNumFlightsHome_2() {
		
		// test that null Home string is handled
		
		int actual = ff.numFlights(null, "BOS", true, 0);
		
		int expected = -1;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testNumFlightsDest_2() {
		
		// test that null Dest string is handled
		
		int actual = ff.numFlights("PHL", null, true, 0);
		
		int expected = -1;
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testNumFlightsCase_1() {
		
		// test case insensitivity in Home
		// based on what's in Flight.allFlights
		
		// note that, if "direct" is true, then "timeLimit" should not be used
		int actual = ff.numFlights("pHl", "BOS", true, 0);
		
		int expected = 1;
		assertEquals(expected, actual);
		
		// don't forget to check the side effects!!
		ArrayList<Flight> direct = ff.directFlights();
		assertEquals(1, direct.size()); // check number of elements in list
		
		Flight f = direct.get(0);
		assertEquals("PHL", f.start());
		assertEquals("BOS", f.end());
			
		// check that there are no indirect flights reported
		ArrayList<Flight[]> indirect = ff.indirectFlights();
		assertEquals(0, indirect.size());

	}
	
	@Test
	public void testNumFlightsCase_2() {
		
		// test case insensitivity in Dest
		// based on what's in Flight.allFlights
		
		// note that, if "direct" is true, then "timeLimit" should not be used
		int actual = ff.numFlights("PHL", "bOS", true, 0);
		
		int expected = 1;
		assertEquals(expected, actual);
		
		// don't forget to check the side effects!!
		ArrayList<Flight> direct = ff.directFlights();
		assertEquals(1, direct.size()); // check number of elements in list
		
		Flight f = direct.get(0);
		assertEquals("PHL", f.start());
		assertEquals("BOS", f.end());
			
		// check that there are no indirect flights reported
		ArrayList<Flight[]> indirect = ff.indirectFlights();
		assertEquals(0, indirect.size());

	}
	
	@Test
	public void testAllFlights_1() {
		
		// Test that null input is handled
		List<Flight> actual = ff.allFlights(null, FlightFinder.SortCriteria.TIME);
		assertNull(actual);
	
	}
	
	@Test
	public void testAllFlights_2() {
		
		// Test that illegal input is handled
		List<Flight> actual = ff.allFlights("Lemur", FlightFinder.SortCriteria.TIME);
		assertNull(actual);
	
	}
	
	@Test
	public void testAllFlights_3() {
		
		// Test that normal input sorted by time
		List<Flight> actual = ff.allFlights("BOS", FlightFinder.SortCriteria.TIME);
		assertEquals(3, actual.size());
		
		assertEquals(flights[1], actual.get(0));
		assertEquals(flights[19], actual.get(1));
		assertEquals(flights[24], actual.get(2));
	
	}
	
	@Test
	public void testAllFlights_4() {
		
		// Test that normal input sorted by cost
		List<Flight> actual = ff.allFlights("BOS", FlightFinder.SortCriteria.COST);
		assertEquals(3, actual.size());
		
		assertEquals(flights[1], actual.get(0));
		assertEquals(flights[19], actual.get(1));
		assertEquals(flights[24], actual.get(2));
	
	}
	
	@Test
	public void testAllFlights_5() {
		
		// Test that normal input sorted by Destination
		List<Flight> actual = ff.allFlights("BOS", FlightFinder.SortCriteria.DESTINATION);
		assertEquals(3, actual.size());
		
		assertEquals(flights[19], actual.get(0));
		assertEquals(flights[24], actual.get(1));
		assertEquals(flights[1], actual.get(2));
	
	}
	
	@Test
	public void testAllFlights_6() {
		
		// Test case insensitivity
		List<Flight> actual = ff.allFlights("bOS", FlightFinder.SortCriteria.DESTINATION);
		assertNotNull(actual);
		assertEquals(3, actual.size());
		
		assertEquals(flights[19], actual.get(0));
		assertEquals(flights[24], actual.get(1));
		assertEquals(flights[1], actual.get(2));
	
	}
	
}