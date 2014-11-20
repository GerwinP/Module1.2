package ss.week1;

public class Employee {

	private int hours;
	private double rate;
	private double rateOvertime = (rate * 1.5); 

	public double calculatePay() {
		double pay = 0;
		if (hours <= 40)
			pay = (hours * rate);
		else
			pay = ((40 * rate) + ((hours - 40) * rateOvertime));
		return pay;
	}

}
