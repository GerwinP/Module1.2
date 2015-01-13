package ss.week6.voteMachine;

import java.util.LinkedList;

public class PartyList {
	
	LinkedList<String> parties = new LinkedList<String>();
	
	public void addParty(String party){
		parties.add(party);
	}
	
	public LinkedList<String> getParties(){
		return parties;
	}
}
