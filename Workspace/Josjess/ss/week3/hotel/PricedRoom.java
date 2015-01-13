package ss.week3.hotel;

public class PricedRoom extends ss.week2.hotel.Room implements Bill.Item {
	
	private double priceRoom;

	public PricedRoom(int number, double priceRoom, double safePrice){
		super(number, new PricedSafe(safePrice));
		this.priceRoom = priceRoom;
	}
	
	public double getAmount() {
		return priceRoom;
		}
	}
