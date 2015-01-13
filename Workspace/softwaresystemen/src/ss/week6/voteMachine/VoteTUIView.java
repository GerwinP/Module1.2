package ss.week6.voteMachine;

import java.util.Scanner;

public class VoteTUIView {

	private String VOTE = "VOTE";
	private String ADDPARTY = "ADD PARTY";
	private String VOTES = "VOTES";
	private String PARTIES = "PARTIES";
	private String EXIT = "EXIT";
	private String HELP = "HELP";
	
	public void start(){
		boolean notEmpty = true;
		Scanner in = new Scanner(System.in);
		while(notEmpty){
			String command = in.next();
			if(command.equals(VOTE)){
				
			}if(command.equals(ADDPARTY)){
				
			}if(command.equals(VOTES)){
				
			}if(command.equals(PARTIES)){
				
			}if(command.equals(EXIT)){
				
			}if(command.equals(HELP)){
				
			}
		}
		in.close();
	}
	
	
}
