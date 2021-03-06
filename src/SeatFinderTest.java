import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SeatFinderTest {

	Flight[] flights;
	SeatFinder sf;
	
	@Before
	public void setUp() throws Exception {
		// initialize a new FlightFinder object before EVERY test
		flights = Flight.allFlights();
		sf = new SeatFinder();
	}
	
	@Test
	public void test_1() {
		// test null plane
		int actual = sf.numSeats(null, true, true, true, 2);
		assertEquals(-1, actual);
	}
	
	@Test
	public void test_2() {
		// test plane less than 2
		byte[] plane = new byte[1];
		int actual = sf.numSeats(plane, true, true, true, 2);
		assertEquals(-1, actual);
	}
	
	@Test
	public void test_3() {
		//test max row less than 1
		byte[] plane = new byte[3];
		int actual = sf.numSeats(plane, true, true, true, -1);
		assertEquals(0, actual);
	}
	
	@Test
	public void test_4() {
		//test middle 
		byte[] plane = new byte[]{(byte)0, (byte)0x39};
		
		int actual = sf.numSeats(plane, false, false, true, 2);
		assertEquals(1, actual);
	}
	
	@Test
	public void test_5() {
		//test window 
		byte[] plane = new byte[]{(byte)0, (byte)0x39};
		
		int actual = sf.numSeats(plane, true, false, false, 2);
		assertEquals(3, actual);
	}
	
	@Test
	public void test_6() {
		//test aisle
		byte[] plane = new byte[]{(byte)0, (byte)0x39};
		
		int actual = sf.numSeats(plane, false, true, false, 2);
		assertEquals(6, actual);
	}
	
	
	@Test
	public void test_7() {
	//test empty plane, window, aisle, middle all ok
		byte[] plane = new byte[]{(byte)0, (byte)0, (byte)0};
		
		int actual = sf.numSeats(plane, true, true, true, 2);
		assertEquals(14, actual);
	}
	
	@Test
	public void test_8() {
	//test empty plane, window, aisle, middle all not ok
		byte[] plane = new byte[]{(byte)0, (byte)0, (byte)0};
		
		int actual = sf.numSeats(plane, false, false, false, 2);
		assertEquals(0, actual);
	}
	
	@Test
	public void test_9() {
	//test full plane, window, aisle, middle all ok
		byte[] plane = new byte[]{(byte)0x7F, (byte)0x7F, (byte)0x7F};
		
		int actual = sf.numSeats(plane, true, true, true, 2);
		assertEquals(0, actual);
	}
	
	@Test
	public void test_10() {
	//test full plane,  max row less than plane 
		byte[] plane = new byte[]{(byte)0x7F, (byte)0x7F, (byte)0x7F, (byte)0x7F, (byte)0x39};
		
		int actual = sf.numSeats(plane, true, true, true, 2);
		assertEquals(0, actual);
	}

}
