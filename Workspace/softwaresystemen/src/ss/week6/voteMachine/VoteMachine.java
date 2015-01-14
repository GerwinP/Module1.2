package ss.week6.voteMachine;

import java.util.List;
import java.util.Map;

import ss.week6.voteMachine.gui.VoteGUIView;

public class VoteMachine {
	
	private PartyList partyList;
	private VoteList voteList;
	private VoteView voteTUIView;
	
	public VoteMachine(){
		partyList = new PartyList();
		voteList = new VoteList();
		voteTUIView = new VoteTUIView(this);
		partyList.addObserver(voteTUIView);
		voteList.addObserver(voteTUIView);
	}
	
	public static void main(String[] args){
		VoteMachine voteMachine = new VoteMachine();
		voteMachine.start();
	}
	
	public void start(){
		voteTUIView.start();
	}
	
	public void addParty(String party){
		partyList.addParty(party);
	}
	
	public void vote(String party){
		voteList.makeVote(party);
	}
	
	public void getVotes(){
		Map<String, Integer> votes = voteList.getVotes();
		voteTUIView.showVotes(votes);
	}
	
	public void getParties(){
		List<String> parties = partyList.getParties();
		voteTUIView.showParties(parties);
	}
}
