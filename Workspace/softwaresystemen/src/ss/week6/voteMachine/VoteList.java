package ss.week6.voteMachine;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;

public class VoteList extends Observable{

	Map<String, Integer> votes = new LinkedHashMap<String, Integer>();
	
	public void makeVote(String party){
		if(votes.containsKey(party)){
			votes.put(party, votes.get(party)+1);
		}else{
			votes.put(party, 1);
		}
		setChanged();
		notifyObservers("vote");
	}
	
	public Map<String,Integer> getVotes(){
		System.out.print("All the votes:");
		return votes;
	}
}
