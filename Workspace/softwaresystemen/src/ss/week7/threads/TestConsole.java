package ss.week7.threads;

public class TestConsole extends Thread{

	private String name;
	
	public void run(){
		sum();
	}
	
	private void sum(){
		int number1 = Console.readInt(getName() + ": Get number 1? ");
		int number2 = Console.readInt(getName() + ": Get number 2? ");
		Console.println(getName() + ": " + number1 + " + " + number2 + " = " + (number1+number2));
	}
	
	public static void main(String[] args){
		new TestConsole().start();
		new TestConsole().start();
	}
}
