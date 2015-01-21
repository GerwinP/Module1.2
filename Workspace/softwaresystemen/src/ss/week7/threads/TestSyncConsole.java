package ss.week7.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSyncConsole extends Thread{
	
	private static Lock lock;
	
	public TestSyncConsole(String name){
		this.setName(name);
	}
	
	public void run(){
		sum();
	}
	
	private void sum(){
		lock.lock();
		int number1 = SyncConsole.readInt(getName() + ": Get number 1? ");
		int number2 = SyncConsole.readInt(getName() + ": Get number 2? ");
		SyncConsole.println(getName() + ": " + number1 + " + " + number2 + " = " + (number1+number2));
		lock.unlock();
	}
	
	public static void main(String[] args){
		lock = new ReentrantLock();
		TestSyncConsole threadA = new TestSyncConsole("Thread A");
		TestSyncConsole threadB = new TestSyncConsole("Thread B");
		threadA.start();
		threadB.start();
		
	}
}
