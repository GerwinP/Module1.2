package ss.week3.hotel;

import java.io.*;

public class Bill {
	
	private PrintStream output;
	private double sumPrice;
	
	public interface Item {
		public double getAmount();	
	}
	
	//------Constructor--------
	
	public Bill(PrintStream theOutStream){
		this.output = theOutStream;
		this.sumPrice = 0;
	}
	
	public void newItem(Bill.Item item){
		double price = item.getAmount();
		this.sumPrice += price;
		this.output.println(String.format("%-10s%10.2f\n", item, price));
	}
	
	public void close(){
		this.output.println(String.format("%-10s%10.2f\n", "Total", this.getSum()));
	}
	
	public double getSum(){
		return this.sumPrice;
	}
}
