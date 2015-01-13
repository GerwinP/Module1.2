package ss.week1.hotel;

public class Guest {
	private String name;
	private Room room; 
	
	//----Constructor----
	public Guest(String n){
		name = n;
	}
	
	//------Queries--------
	public String getName () {
		return name;
	}
	
	public Room getRoom () {
		return room;
	}
	
	public boolean checkin(Room r) {
		if (r.getGuest() == null && this.room == null){
			this.room = r;
			r.setGuest(this);
			return true;
		}
		return false;
	}
	
	public boolean checkout(){
		if (this.room != null && room.getGuest() != null){
			room.setGuest(null);
			this.room = null;
			return true;
		}
		return false;
	}
	
	public String toString (){
		return ("Guest: " +getName()); 
	}
}
