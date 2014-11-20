package ss.week1;

public class DollarCentCounter {

	private int dollarCount = 0;
	private int centCount = 0;

	public int getDollars() {
		return dollarCount;
	}

	public int getCents() {
		return centCount;
	}

	public void add(int dollars, int cents) {
		centCount = centCount + cents;
		dollarCount = dollarCount + dollars;
		int centjes = centCount % 100;
		int extraDollars = (centCount - centjes) / 100;
		dollarCount = dollarCount + extraDollars;
		centCount = centjes;
	}

	public void reset() {
		this.dollarCount = 0;
		this.centCount = 0;
	}
}
