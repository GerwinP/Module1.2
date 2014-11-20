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
		assertEquals("activeSafe.isActive()", true, activeSafe.isActive());
		assertEquals("inactiveSafe.isActive", false, inactiveSafe.isActive());
	}

	private void testDeActivate(){
		activeSafe.deactivate();
		assertEquals("activeSafe.isActive()", false, activeSafe.isActive());
	}
	
	private void testOpenCorrect(){
		activeSafe.open(correctPass);
		assertEquals("activeSafe.isOpen()", true, activeSafe.isOpen());
	}
	
	private void testOpenWrong(){
		activeSafe.open(wrongPass);
		assertEquals("activeSafe.isOpen()", false, activeSafe.isOpen());
	}
	
	private void testClosed(){
		activeSafe.open(correctPass);
		assertEquals("activeSafe.isOpen()", true, activeSafe.isOpen());
		activeSafe.close();
		assertEquals("activeSafe.isOpen()", false, activeSafe.isOpen());
	}
	
	@Test
	public void test() {
		setUp();
		testActivate();
		setUp();
		testDeActivate();
		setUp();
		testOpenCorrect();
		setUp();
		testOpenWrong();
		setUp();
		testClosed();
	}

}
