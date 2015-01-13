package ss.week2.hotel;

public class Safe {
	public boolean active;
	public boolean state;
	public String woord;
	public Password code;
	
	//-----Constructor-------
	public Safe(){
		active = false;
		state = false;
		code = new Password();
	}
	
	//--------Commands-------
	//@ ensures isActive() == true || isActive() == false;
	public void activate(String password){
		if (code.testWord(password)) {
			active = true;
		}
		
	}
	
	//@ ensures isActive() == false && isOpen() == false;
	public void deactivate(){
			active = false;
			state = false;
	}
	
	//@ requires password != null;
	//@ ensures isOpen() == true || isOpen() == false;
	public void open(String password){
		assert password != null;
		if (active == true && code.testWord(password)) {
			state = true;
		}
	}
	
	//@ ensures isOpen() == false;
	public void close(){
		state = false;
	}
	
	
	//---------Queries---------
	

	/*@ pure */public boolean isActive(){
		return active; 
	}
	
	/*@ pure */public boolean isOpen(){
		return state;
	}
	
	/*@ pure */public String getPassword(){
		return woord;
	}
	public static void main(String[] args){
		System.out.println("Log of " + Safe.class + ", " + new java.util.Date());
		Safe test;
		test = new Safe();
		test.open(null);
	}
}
