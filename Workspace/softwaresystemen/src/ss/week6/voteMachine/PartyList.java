package ss.week6.voteMachine;

import java.util.LinkedList;
import java.util.Observable;

public class PartyList extends Observable{
	
	LinkedList<String> parties = new LinkedList<String>();
	
	public void addParty(String party){
		parties.add(party);
		setChanged();
		notifyObservers("party");
	}
	
	public LinkedList<String> getParties(){
		System.out.print("All the parties:");
		return parties;
	}
}
