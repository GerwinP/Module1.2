package ss.week7.threads;

public class TestConsole extends Thread{
	
	public void run(){
		sum();
	}

	private int sum(){
		int n1 = Console.readInt(getName() + ": Get number 1? ");
		int n2 = Console.readInt(getName() + ": Get number 2? ");
		Console.println(getName() + ": "+ n1 + " + " + n2 + " = " + (n1+n2));
		return 0;
	}
	
	public static void main(String[] args){
		new TestConsole().start();	
	}
}
