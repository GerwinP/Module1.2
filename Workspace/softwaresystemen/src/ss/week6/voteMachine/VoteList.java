package ss.week6.voteMachine;

import java.util.LinkedHashMap;
import java.util.Map;

public class VoteList {

	Map<String, Integer> votes = new LinkedHashMap<String, Integer>();
	
	public void makeVote(String party){
		if(votes.containsKey(party)){
			votes.put(party, votes.get(party)+1);
		}else{
			votes.put(party, 1);
		}
	}
	
	public Map<String,Integer> getVotes(){
		return votes;
	}
}
