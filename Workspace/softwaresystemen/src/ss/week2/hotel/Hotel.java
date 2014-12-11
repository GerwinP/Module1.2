package ss.week2.hotel;

public class Hotel {

	private String name = "Welcome";
	private Room room1;
	private Room room2;
	private Password password;
	
	public Hotel(String name){
		this.name = name;
		room1 = new Room(101);
		room2 = new Room(102);
		password = new Password();
	}
	
	//Commands
	public Room checkIn(String pass, String name){
		Room checkedRoom;
		if(!password.testWord(pass) || getFreeRoom() == null /*|| room1.getGuest().equals(name) || room2.getGuest().equals(name)*/){
			checkedRoom = null;
		}else{
			Room freeRoom = getFreeRoom();
			Guest guest = new Guest(name);
			guest.checkin(freeRoom);
			checkedRoom = freeRoom;
		}
		return checkedRoom;
	}
	
	public void checkOut(String name){
		Room checkedRoom = getRoom(name);
		if(checkedRoom != null){
			Guest guest = checkedRoom.getGuest();
			guest.checkout();
			Safe safe = checkedRoom.getSafe();
			safe.deactivate();
		}
	}
	
	//Queries
	public Room getFreeRoom(){
		Room returnRoom = null;
		if(room1.getGuest() == null){
			returnRoom = room1;
		}else if(room2.getGuest() == null){
			returnRoom = room2;
		}
		return returnRoom;
	}
	
	public Room getRoom(String guest){
		Room returnRoom = null;
		if(room1.getGuest() != null && room1.getGuest().getName().equals(guest)){
			returnRoom = room1;
		}else if(room1.getGuest() != null && room1.getGuest().getName().equals(guest)){
			returnRoom = room2;
		}
		return returnRoom;
	}
	
	public Password getPassword(){
		return password;
	}
	
	public String toString(){
		String description = name + "\n";
		description += room1.toString() + "\n";
		if(room1.getGuest() != null){
			description += "Guest Room1: " + room1.getGuest().getName() + "\n";
		}
		description += room2.toString() + "\n";
		if(room2.getGuest() != null){
			description += "Guest Room2: " + room2.getGuest().getName() + "\n";
		}
		description += "State safe room1: " + room1.getSafe().isActive() + "\n";
		description += "State safe room2: " + room2.getSafe().isActive() + "\n";
		System.out.println(description);
		return description;
	}
	
	public String getName(){
		return name;
	}
}
