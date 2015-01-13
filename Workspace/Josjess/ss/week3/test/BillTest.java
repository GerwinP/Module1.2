package ss.week3.test;

import ss.week3.hotel.Bill;
import ss.week3.hotel.BillItem;

public class BillTest {

	public Bill bill;
	
	private int errors;
    /** Notice belonging to test method. */
    private boolean isPrinted;
    /** Indication that an errors was found in test method. */
    private String description;

    /** Calls all tests in this class one-by-one. */
    public int runTest() {
        System.out.println("Testclass: " + this.getClass());
        setUp();
        testBill();
        if (errors == 0) {
            System.out.println("    OK");
        }
        return errors;
    }

    /**
     * Sets the instance variable <tt>pass</tt> to a well-defined initial value.
     * All test methods should be preceded by a call to this method.
     */
    public void setUp() {
        // initialisation of password-variable
        bill = new Bill(System.out);
    }
	
    private void beginTest(String text) {
        description = text;
        // the description hasn't been printed yet
        isPrinted = false;
    }

    public void testBill(){
    	beginTest("Test Bill");
    	assertEquals("bill.getSum()", 0.0, bill.getSum() );
    	Bill.Item item1 = new BillItem();
    	Bill.Item item2 = new BillItem();
    	Bill.Item item3 = new BillItem();
    	Bill.Item item4 = new BillItem();
    	bill.newItem(item1);
    	bill.newItem(item2);
    	bill.newItem(item3);
    	bill.newItem(item4);
    	assertEquals("bill.getSum()", 13.2, bill.getSum());
    	bill.close();
    }
    /**
     * Tests if the resulting value of a tested expression equals the 
     * expected (correct) value. This implementation prints both values, 
     * with an indication of what was tested, to the standard output. The 
     * implementation does not actually do the comparison.
     */
    private void assertEquals(String text, Object expected, Object result) {
        boolean equal;
        // tests equality between expected and result
        // accounting for null
        if (expected == null) {
            equal = result == null;
        } else {
            equal = result != null && expected.equals(result);
        }
        if (!equal) {
            // prints the description if necessary
            if (!isPrinted) {
                System.out.println("    Test: " + description);
                // now the description is printed
                isPrinted = true;
            }
            System.out.println("        " + text);
            System.out.println("            Expected:  " + expected);
            System.out.println("            Result: " + result);
            errors++;
        }
    }
    public static void main(String[] args) {
    	BillTest test;
    	test = new BillTest();
    	test.runTest();
    }
    

}
