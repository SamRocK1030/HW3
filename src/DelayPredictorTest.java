import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class DelayPredictorTest {

	FlightRecord[] FR; 
	@Before
	public void setUp() throws Exception {
		
		FR = new FlightRecord[8];
		
		FR[0] = new FlightRecord(false,false,false, 0);
		FR[1] = new FlightRecord(false,false,true, 20);
		FR[2] = new FlightRecord(false,true,false, 16);
		FR[3] = new FlightRecord(true,false,false, 7);
		FR[4] = new FlightRecord(false,true,true, 75);
		FR[5] = new FlightRecord(true,false,true, 80);
		FR[6] = new FlightRecord(true,true,false, 35);
		FR[7] = new FlightRecord(true,true,true, 17);
	}

	@Test
	public void testDelay_1() {
		// test null array
		int actual = DelayPredictor.predict(null, true, true, true);
		
		assertEquals(-1, actual);
	}
	
	@Test
	public void testDelay_2() {
		//test empty array
		FlightRecord[] f = new FlightRecord[0]; 
		int actual = DelayPredictor.predict(f, true, true, true);
		
		assertEquals(0, actual);
	}
	
	@Test
	public void testDelay_3() {
		// test array with null element
		FR[6] = null; 
		int actual = DelayPredictor.predict(FR, true, true, true);
		
		assertEquals(-1, actual);
	}
	
	@Test
	public void testDelay_4() {
		//test normal delay predictions
		int actual = DelayPredictor.predict(FR, false, false, false);
		
		assertEquals(12, actual);
	}
	
	@Test
	public void testDelay_5() {
		
		int actual = DelayPredictor.predict(FR, true, true, true);
		
		assertEquals(44, actual);
	}
	
	@Test
	public void testDelay_6() {
		//test flight record with - delay
		FR[0] = new FlightRecord(false, false, false, -5);
		int actual = DelayPredictor.predict(FR, false, false, false);
		
		assertEquals(-1, actual);
	}

	@Test
	public void testDelay_7() {
		//test array all scores are 0
		FR = new FlightRecord[2];
	
		FR[0] = new FlightRecord(false,false,false, 2);
		FR[1] = new FlightRecord(false,false,false, 2);
		
		int actual = DelayPredictor.predict(FR, true, true, true);
		
		assertEquals(0 ,actual);
	}
	
	@Test
	public void testDelay_8() {
		//test array where only two scores are above 0
		FR = new FlightRecord[3];
	
		FR[0] = new FlightRecord(false,false,false, 2);
		FR[1] = new FlightRecord(true,true,true, 2);
		FR[2] = new FlightRecord(false,false,false, 0);
		
		int actual = DelayPredictor.predict(FR, false, false, false);
		
		assertEquals(1 ,actual);
	}
}
