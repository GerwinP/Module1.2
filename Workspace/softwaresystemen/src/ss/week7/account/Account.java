package ss.week7.account;

public class Account {
	protected double balance = 0.0;
	public int timesWaited = 0;

	public synchronized void transaction(double amount) {
		try {
			while (balance + amount < -1000) {
				System.out.println("Balance to low");
				timesWaited++;
				wait();
			}
			balance = balance + amount;
			notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public double getBalance() {
		return balance;
	}
	
	public int getTimesWaited(){
		return timesWaited;
	}
}
