package ss.week6;

import java.util.Scanner;

public class WordsNew {

	private static void words() {
		boolean notEmpty = true;
		Scanner in = new Scanner(System.in);
		while (notEmpty) {
			System.out.println("Enter sentence");
			Scanner line = new Scanner(in.nextLine());
			int i = 1;
			while (line.hasNext() && notEmpty) {
				if (line.hasNext("end") || line.hasNext("End")) {
					notEmpty = false;
				} else {
					System.out.println("Word " + i + " : " + line.next());
					i++;
				}
			}
			line.close();
		}
		System.out.println("End of program");
		in.close();
	}

	public static void main(String[] args) {
		words();
	}
}
