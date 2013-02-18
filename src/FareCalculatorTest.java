import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class FareCalculatorTest {

	Flight[] flights;
	
	@Before
	public void setUp() throws Exception {
		// initialize a new FlightFinder object before EVERY test
		flights = Flight.allFlights();
	}
	
	@Test
	public void testCalculateFare_1() {
		double actual = FareCalculator.calculateFare(null);
		
		double expected = -1;
		assertEquals(expected, actual, 0);
	}
	
	@Test
	public void testCalculateFare_2() {
		//test roundtrip where flights cost different values
		ArrayList<Flight> f = new ArrayList<Flight>();
		f.add(flights[0]);
		f.add(flights[1]);
		
		double actual = FareCalculator.calculateFare(f);
		
		assertEquals(370, actual, 0);
	}
	
	@Test
	public void testCalculateFare_3() {
		//test roundtrip where flights cost the same
		ArrayList<Flight> f = new ArrayList<Flight>();
		f.add(flights[2]);
		f.add(flights[3]);
		
		double actual = FareCalculator.calculateFare(f);
		
		assertEquals(370, actual, 0);
	}
	
	@Test
	public void testCalculateFare_4() {
		
		// test the 10% surcharge less than 5 hours (1st flight more expensive)
		ArrayList<Flight> f = new ArrayList<Flight>();
		f.add(flights[1]);
		f.add(flights[2]);
		
		double actual = FareCalculator.calculateFare(f);
		
		assertEquals(410.5, actual, 1);
	}
	
	@Test
	public void testCalculateFare_5() {
		
		// test the 10% surcharge less than 5 hours (2nd flight more expensive)
		ArrayList<Flight> f = new ArrayList<Flight>();
		f.add(flights[3]);
		f.add(flights[4]);
		
		double actual = FareCalculator.calculateFare(f);
		
		assertEquals(482, actual, 0);
	}
	
	@Test
	public void testCalculateFare_6() {
		
		// test no 10% surcharge for flight time over 5 hours
		ArrayList<Flight> f = new ArrayList<Flight>();
		f.add(flights[15]);
		f.add(flights[16]);
		
		double actual = FareCalculator.calculateFare(f);
		
		assertEquals(500, actual, 0);
	}
	
	@Test
	public void testCalculateFare_7() {
		
		// test null start
		ArrayList<Flight> f = new ArrayList<Flight>();
		f.add(new Flight(null,"ORD",190,200));
		f.add(flights[16]);
		
		double actual = FareCalculator.calculateFare(f);
		
		assertEquals(-1, actual, 0);
	}
	
	@Test
	public void testCalculateFare_8() {
		
		// test null end
		ArrayList<Flight> f = new ArrayList<Flight>();
		f.add(new Flight("SFO",null,190,200));
		f.add(flights[16]);
		
		double actual = FareCalculator.calculateFare(f);
		
		assertEquals(-1, actual, 0);
	}
	
	@Test
	public void testCalculateFare_9() {
		
		// test two-segment roundtrip
		ArrayList<Flight> f = new ArrayList<Flight>();
		f.add(flights[1]);
		f.add(flights[6]);
		f.add(flights[7]);
		f.add(flights[0]);
		
		double actual = FareCalculator.calculateFare(f);
		
		assertEquals(1020, actual, 0);
	}
	
	

}
