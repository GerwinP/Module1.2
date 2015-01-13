package ss.week2.test;
import ss.week2.hotel.Safe;


public class SafeTest {
	
	// Variabelen opstellen
	public Safe wachtwoordfout;
	public Safe wachtwoordgoed;
	public String goed = "yesyesyes";
	public String fout = "no no";
	
	//Setup voor test
	public void setUp(){
		wachtwoordgoed = new Safe();
		wachtwoordfout = new Safe();
	}
	
	//De runtest
	public void runTest(){
		System.out.println("Test class: " + this.getClass());
		setUp();
		testActivate();
		setUp();
		testDeActivate();
		setUp();
		testOpenActief();
		setUp();
		testOpenInactief();
		setUp();
		testClosing();
		setUp();
	}	
	
	//Test activation
	public void testActivate(){
		System.out.println("Test activation");
		wachtwoordgoed.activate(goed);
		wachtwoordfout.activate(fout);
		assertEquals("wachtwoordgoed.isActive()", true, wachtwoordgoed.isActive());
		assertEquals("wachtwoordfout.isActive()", false, wachtwoordfout.isActive());
		
	}
	
	//Test deactivation
	public void testDeActivate(){
		System.out.println("Test deactivation");
		wachtwoordgoed.activate(goed);
		wachtwoordgoed.deactivate();
		wachtwoordfout.deactivate();
		assertEquals("wachtwoordgoed.isActive()", false, wachtwoordgoed.isActive());
		assertEquals("wachtwoordfout.isActive()", false, wachtwoordfout.isActive());
	}
	
	//Test opening
	public void testOpenActief(){
		System.out.println("Test opening and active");
		wachtwoordgoed.activate(goed);
		wachtwoordgoed.open(goed);
		wachtwoordfout.open(fout);
		assertEquals("wachtwoordgoed.isOpen()", true, wachtwoordgoed.isOpen());
		assertEquals("wachtwoordfout.isOpen()", false, wachtwoordfout.isOpen());
	}
	
	public void testOpenInactief(){
		System.out.println("Test opening and inactive");
		wachtwoordfout.activate(fout);
		wachtwoordgoed.open(goed);
		wachtwoordfout.open(fout);
		assertEquals("wachtwoordgoed.isOpen()", false, wachtwoordgoed.isOpen());
		assertEquals("wachtwoordfout.isOpen()", false, wachtwoordfout.isOpen());
	}
	
	//Test closing
	public void testClosing(){
		System.out.println("Test closing");
		wachtwoordgoed.activate(goed);
		wachtwoordgoed.open(goed);
		wachtwoordgoed.close();
		wachtwoordfout.close();
		assertEquals("wachtwoordgoed.isOpen()", false, wachtwoordgoed.isOpen());
		assertEquals("wachtwoordfout.isOpen()", false, wachtwoordfout.isOpen());
	}
	
	//Output and equals test
	private void assertEquals(String text, boolean expected, boolean result) {
    	System.out.println("        " + text);
    	System.out.println("            Expected:  " + expected);
    	System.out.println("            Result: " + result);
    }
	
	//The main method
	public static void main(String[] args) {
		System.out.println("Log of " + SafeTest.class + ", " + new java.util.Date());
		SafeTest test;
		test = new SafeTest();
		test.runTest();

	}

}
