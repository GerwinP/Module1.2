package ss.week2.hotel;

public class Hotel {
	//--------Variables------
	private String name;
	private Password password;
	private Room room1;
	private Room room2;
	
	//-------Constructor----

	public Hotel(String name){
		this.name = name;
		password = new Password();
		room1 = new Room(101, null);
		room2 = new Room(102, null);
		room1.setSafe(new Safe());
		room2.setSafe(new Safe());
	}
	
	
	
	//-------Commands------
	public Room checkIn(String password, String nameGuest){
		if (!this.password.testWord(password)) {
			return null;
		}
		Room freeRoom = getFreeRoom();
		if (freeRoom != null){
			Guest guest = new Guest(nameGuest);
			freeRoom.setGuest(guest);
			guest.checkin(freeRoom);
			//wijst gast toe aan kamer en kamer toe aan gast
		}
		return freeRoom;
		
		//returns Room object with (new) nameGuest ||
		//Null if password is wrong || already Guest with this name || hotel == full
	}
	
	public void checkOut(String nameGuest){
		Room inRoom = getRoom(nameGuest);
		if (inRoom != null){
			Guest guest = inRoom.getGuest();
			if (inRoom != null){
				inRoom.setGuest(null);
				guest.checkout();
			}
		}
		//Guest is checked out && safe == deactive
		//No guest with this name --> nothing happens
	}
	
	//-------Queries------
	public Room getFreeRoom(){
		if (room1.getGuest() == null){
			return room1;
		}
		else if (room2.getGuest() == null){
			return room2;
		}
		return null;
		//return roomnumber if no guest is checked in
		//return null if no room free
	}
	
	public Room getRoom(String guest){
		if (room1.getGuest() != null && room1.getGuest().getName().equals(guest)){
			return room1;
		}
		else if (room2.getGuest() != null && room2.getGuest().getName().equals(guest)){
			return room2;
		}
		return null;
		//returns roomnumber of guest
		//returns null if no such room
	}
	
	public Password getPassword(){
		return password;
		//return the Password object of the hotel (used in checkIn)
	}
	
	public String toString(){
		String result = this.name;
		result += this.room1;
		if (room1.getGuest() != null){
			result  += room1.getGuest().getName();
		}
		result += this.room2;
		if (room2.getGuest() != null){
			 result += room2.getGuest().getName();
		}
		
		return result;
		//textual discription of all rooms in hotel, including name of Guest and SafeState
	}
}
