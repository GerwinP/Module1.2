package ss.week7.threads;

public class TestSyncConsole extends Thread{
	
	public TestSyncConsole(String name){
		this.setName(name);
	}
	
	public void run(){
		sum();
	}
	
	synchronized private void sum(){
		int number1 = SyncConsole.readInt(getName() + ": Get number 1? ");
		int number2 = SyncConsole.readInt(getName() + ": Get number 2? ");
		SyncConsole.println(getName() + ": " + number1 + " + " + number2 + " = " + (number1+number2));
	}
	
	public static void main(String[] args){
		TestSyncConsole threadA = new TestSyncConsole("Thread A");
		TestSyncConsole threadB = new TestSyncConsole("Thread B");
		threadA.start();
		try {
			threadA.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadB.start();
	}
}
