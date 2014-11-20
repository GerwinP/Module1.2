package ss.week2.hotel;

public class Hotel {

	private String name = "Welcome";
	
	public Hotel(String name){
		this.name = name;
	}
	
	//Commands
	public Room checkIn(String password, String guest){
		return null;
	}
	
	public void checkOut(String guest){
		
	}
	
	//Queries
	public Room getFreeRoom(){
		return null;
	}
	
	public Room getRoom(String guest){
		return null;
	}
	
	public Password getPassword(){
		return null;
	}
	
	public String toString(){
		return null;
	}
	
	public String getName(){
		return name;
	}
}
