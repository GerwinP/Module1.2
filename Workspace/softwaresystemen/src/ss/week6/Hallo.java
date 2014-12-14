package ss.week6;

import java.util.Scanner;

public class Hallo {
	
	public static void main(String[] args) {
		boolean notEmpty = true;
		Scanner in = new Scanner(System.in);
		while(notEmpty){
			System.out.println("What is your name?");
			if(in.nextLine().isEmpty()){
				notEmpty = false;
			}else{
				String name = in.next();
				System.out.println("Hello " + name);
			}
		}
		in.close();
		System.exit(0);
	}

}
