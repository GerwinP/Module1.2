package ss.week6;
import java.util.Scanner;

public class Hallo {
	
	public static String voerNaamIn (){
		Scanner in = new Scanner( System.in );
        System.out.print("Voer naam in: ");
        in.close();
        return "Hello " + in.nextLine() + "!";
	}

	 public static void main(String[] args) {
		    System.out.println(voerNaamIn());
	 }
	 
}
