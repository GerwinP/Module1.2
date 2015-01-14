package ss.week6;

import java.util.Scanner;

public class Hallo {
	
	public static void main(String[] args) {
		boolean notEmpty = true;
		Scanner in = new Scanner(System.in);
		while(notEmpty){
			System.out.println("What is your name?");
				String name = in.next();
				if(name.isEmpty()){
					notEmpty = false;
				}else{
					System.out.println("Hello " + name);
				}
		}
		in.close();
	}

}
