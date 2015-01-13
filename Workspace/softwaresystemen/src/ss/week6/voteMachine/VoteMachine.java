package ss.week6.voteMachine;

public class VoteMachine {
	
	private PartyList partyList;
	private VoteList voteList;
	
	public VoteMachine(){
		partyList = new PartyList();
		voteList = new VoteList();
	}
	
	public static void main(String[] args){
		VoteMachine voteMachine = new VoteMachine();
		voteMachine.start();
	}
	
	public void start(){
		
	}
	
	public void addParty(String party){
		partyList.addParty(party);
	}
	
	public void vote(String party){
		voteList.makeVote(party);
	}
}
