package ss.week6;
import java.util.Scanner;

public class Words {
	public static void breekWoord(){
		Scanner in = new Scanner( System.in );
	    System.out.print("Voer zin in: "); 
	    while(in.hasNextLine()){
		    String inString = in.nextLine();
		    //System.out.println(inString);
		    //System.out.println("ja");
		    String words[] = inString.split(" ");
		    if (words[0].equals("end")){
		    	System.out.println( "End of programm");
		    }
		    else {
		    	int i = 1;
		    	for (int x = 0; x < words.length; x++){
			    	System.out.println("Woord" + " " + i + ":" + " " + words[x] );
			    	i++;
		    	}
		    	
		    }
	    }
	    in.close();
	}
		
	public static void main(String[] args) {
	    breekWoord();
	}
}
