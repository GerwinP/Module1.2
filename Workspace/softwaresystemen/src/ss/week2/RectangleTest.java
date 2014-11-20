package ss.week2;

import ss.week1.hotel.GuestTest;

/** 
 * Testprogram for Rectangle.
 * @author Gerwin Puttenstein
 */
public class RectangleTest {
	
	private static Rectangle normal;
	private static Rectangle negativeW;
	private static Rectangle negativeL;
	
	public void start(){
		setUp();
		testInitial();
		setUp();
		testArea();
		setUp();
		testPerimeter();
		
	}
	
	private void setUp(){
		//Correct rectangle
		normal = new Rectangle(2,3);
		//Rectangle with negative length
		negativeL = new Rectangle(-1,2);
		//Rectangle with negative width
		negativeW = new Rectangle(2,-1);
	}
	
	/**
	 * Test the initial state
	 */
	
	private void testInitial(){
		beginTest("Test initial condition");
		assertEquals("normal.length()", "2", normal.length());
		assertEquals("normal.width()", "3", normal.width());
		assertEquals("negativeL.length()", "-1", negativeL.length());
		assertEquals("negativeL.width()", "2", negativeL.width());
		assertEquals("negativeW.length", "2", negativeW.length());
		assertEquals("negativeW.width()", "-1", negativeW.width());
	}
	
	/**
	 *  Test the area of the different rectangles
	 */
	
	private void testArea(){
		beginTest("Test area of rectangle");
		assertEquals("normal.area()", "6", normal.area());
		assertEquals("negativeL.area()", "-2", negativeL.area());
		assertEquals("negativeR.area()", "-2", negativeW.area());
	}
	
	/**
	 *  Test the perimeter of the different rectangles
	 */
	
	private void testPerimeter(){
		beginTest("Test the perimeter of an area");
		assertEquals("normal.perimeter()", "10", normal.perimeter());
		assertEquals("negativeL.perimeter()", "2", negativeL.perimeter());
		assertEquals("negativeW.perimeter()", "2", negativeW.perimeter());
	}
	
    /**
     * Print the testmethod's description.
     * @param text The description to be printed
     */
    private void beginTest(String text) {
    	System.out.println("    Test: " + text);
    }

    /**
     * Tests if the resulting value of a tested expression equals the 
     * expected (correct) value. This implementation prints both values, 
     * with an indication of what was tested, to the standard output. The 
     * implementation does not actually do the comparison.
     */
    private void assertEquals(String text, Object expected, Object result) {
    	System.out.println("        " + text);
    	System.out.println("            Expected:  " + expected);
    	System.out.println("            Result: " + result);
    }
    
    public static void main(String[] args){
    	System.out.println("Log of " + GuestTest.class + 
                ", " + new java.util.Date());
    	new RectangleTest().start();
    }
}
