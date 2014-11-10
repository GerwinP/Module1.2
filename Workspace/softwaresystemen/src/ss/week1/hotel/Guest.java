package ss.week1.hotel;

import ss.week1.hotel.Room;

/**
 * The Guest
 * @author Gerwin Puttenstein
 * @version 1
 */

public class Guest {
	
	// ------------------ Instance variables ----------------
	public Room room;
	public String guest;
	public String name;
	
	// ------------------ Constructor ------------------------
	/**
	 * Creates a <code>Guest</code> with the given name.
	 * @param name, the name of the new <code>Guest</code>
	 */
	public Guest(String name){
		this.name = name;
	}
	
	// ------------------ Queries --------------------------
	
	/**
	 * Returns the name of this <code>Guest</code>
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * Returns the <code>Room</code> of this <code>Guest</code>
	 * @return the roomnumber of this <code>Guest</code>
	 * 			<code>null</code> if this <code>Guest</code>
	 * 			has no <code>Room</code>
	 */
	public Room getRoom(){
		return room;
	}
	
	
	// ------------------ Commands --------------------------
	
	/**
	 * Assigns a <code>Room</code> to this <code>Guest</code>
	 * @param room the room the <code>Guest</code> will be renting;
	 * @return if return <code>false</code>, the <code>Guest</code> 
	 * 			is not checked in because the room was booked.
	 */
	public boolean checkin(Room room){
		if(room.getGuest() == null && this.getRoom() == null){
			this.room = room;
			room.setGuest(this);
			return true;
		}
		return false;
	}
	
	/**
	 * Checks this <code>Guest</code> out of the room it is checked in
	 * @return
	 */
	public boolean checkout(){
		if(this.room != null && room.getGuest() != null){
			room.setGuest(null);
			this.room = null;
			return true;
		}
		return false;
	}
	
	public String toString(){
		guest = new String("Guest " + name);
		return guest;
	}
}
