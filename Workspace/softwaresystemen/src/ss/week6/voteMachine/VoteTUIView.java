package ss.week6.voteMachine;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;

public class VoteTUIView implements VoteView{

	private String VOTE = "VOTE";
	private String ADDPARTY = "ADDPARTY";
	private String VOTES = "VOTES";
	private String PARTIES = "PARTIES";
	private String EXIT = "EXIT";
	private String HELP = "HELP";
	private VoteMachine voteMachine;
	
	public VoteTUIView(VoteMachine voteMachine){
		this.voteMachine = voteMachine;
	}
	
	public void start(){
		boolean notEmpty = true;
		System.out.println("Welcome to this VoteMachine");
		Scanner in = new Scanner(System.in);
		while(notEmpty){
			String command = in.next();
			if(command.equals(VOTE)){
				voteMachine.vote(in.next());
			}if(command.equals(ADDPARTY)){
				voteMachine.addParty(in.next());
			}if(command.equals(VOTES)){
				voteMachine.getVotes();
			}if(command.equals(PARTIES)){
				voteMachine.getParties();
			}if(command.equals(EXIT)){
				System.out.println("Thanks for using this vote system");
				notEmpty = false;
			}if(command.equals(HELP)){
				System.out.println("The following commands can be used:");
				System.out.println("VOTE: To vote for a party");
				System.out.println("ADDPARTY: To add a party to the list");
				System.out.println("VOTES: To retrieve all the votes for all the parties");
				System.out.println("PARTIES: To retrieve a list of all the parties in the system");
				System.out.println("EXIT: To exit the system");
				System.out.println("HELP: To get this menu");
			}
		}
		in.close();
	}
	
	public void showVotes(Map<String, Integer> votes){
		System.out.println(votes);
	}
	
	public void showParties(List<String> parties){
		System.out.println(parties);
	}
	
	public void showError(String error){
		System.out.println(error);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
			if(arg1.equals("vote")){
				System.out.println("A vote was succesfully added");
			}if(arg1.equals("party")){
				System.out.println("A party was succesfulle added");
			}
	}
}
