package ss.week2.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ss.week2.hotel.Safe;

public class SafeTest {

	private static Safe activeSafe;
	private static Safe inactiveSafe;
	private static final String correctPass = "correct";
	private static final String wrongPass = "false";
	
	
	@Before
	public void setUp() {
		activeSafe = new Safe();
		activeSafe.activate(correctPass);
		inactiveSafe = new Safe();
		inactiveSafe.activate(wrongPass);
	}
	
	private void testActivate(){
		assertEquals("activeSafe.getActive")
	}

	@Test
	public void test() {
		setUp();
		fail("Not yet implemented");
	}

}
