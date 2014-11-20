package ss.week1;

import ss.week1.DollarCentCounter;

public class TestDollarCentCount {

	private static final int cents = 512;
	private static final int dollars = 2;
	
	public static void main(String[] args){
		DollarCentCounter dcc = new DollarCentCounter();
		System.out.println("Aantal Cents ingevoerd: " + cents);
		System.out.println("Aantal Dollars ingevoerd: " + dollars);
		dcc.add(dollars, cents);
		System.out.println("Aantal Cents: " + dcc.getCents());
		System.out.println("Aantal Dollars: " + dcc.getDollars());
		
	}
}
