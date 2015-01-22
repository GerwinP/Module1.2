package ss.week7.account;

public class AccountSync {
	
	public static Account account = new Account();
	
	public static void main(String[] args){
		MyThread myThread1 = new MyThread(-100, 1000, account);
		MyThread myThread2 = new MyThread(100, 1000, account);
		Thread thread1 = new Thread(myThread1);
		Thread thread2 = new Thread(myThread2);
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End balance: " + account.balance + " ;  " + "Times waited " + account.getTimesWaited());
	}
}
