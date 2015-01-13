package ss.week3.hotel;

public class PricedSafe extends ss.week2.hotel.Safe implements Bill.Item{
	
	private double priceSafe;
	
	public PricedSafe(double priceSafe){
		this.priceSafe = priceSafe;
	}
	
	public String toString(){
		return ("Price Safe:" + priceSafe);
	}

	@Override
	public double getAmount() {
		return priceSafe;
	}
}
