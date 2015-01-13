package ss.week7.account;

public class MyThread /*extends Thread*/ implements Runnable{
    private double amount;
    private int frequency;
    private Account account;


    public MyThread(double amount, int frequency, Account account) {
        this.amount = amount;
        this.frequency = frequency;
        this.account = account;
    }

    public void run(){
        for(int i = 1; i <= frequency; i++){
            account.transaction(amount);
            System.out.println("Performing transaction: (" + i + "/" + frequency + ") " + frequency  + "\t*\t" + amount);
        }
    }
}
