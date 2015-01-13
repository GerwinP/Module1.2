package ss.week3;

public class Format {
	
	public static void printLine(String text, double amount) {
		String s = String.format("%-10s%10.2f", text, amount);
		System.out.println(s);
	}
	
	public static void main(String[] args){
		printLine("test", 123);
		printLine("test", 12345);
		printLine("test", 1234);
		}
	
}
