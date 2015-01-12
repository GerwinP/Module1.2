package ss.week6;

import java.util.Scanner;

public class Words {

	private static String words(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter sentence: ");
		int counter = 1;
		if(in.hasNextLine()){
			Scanner line = new Scanner(in.nextLine());
			while(line.hasNext()){
				if(in.hasNext("end")){
					in.close();
					return "End of program";
				}
				System.out.println("Word " + counter + " : " + in.next());
				counter++;
			}
			
		}else{
			words();
		}
		in.close();
		return "Done";
	}
	
	public static void main(String[] args) {
		System.out.println(words());
	}

}
