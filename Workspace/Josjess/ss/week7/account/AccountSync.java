package ss.week7.account;

public class AccountSync extends Thread {
    public static void main(String[] args) {
        new AccountSync().run();
    }

    public void run() {
        try {
            Account account = new Account();
            MyThread myThread1 = new MyThread(-100, 10000, account);
            MyThread myThread2 = new MyThread(100, 10000, account);

            /*
            ### Thread implementatie ###
            myThread1.start();
            myThread2.start();
            myThread1.join();
            myThread2.join();
            */

            /*
            ### runnable implementatie ###
             */
            Thread thread1 = new Thread(myThread1);
            Thread thread2 = new Thread(myThread2);
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            System.out.println("End balance: " + account.getBalance() + ", Times waited: " + account.getTimesWaited());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print(String message) {
        System.out.println(message);
    }
}
