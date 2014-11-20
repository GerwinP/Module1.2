package ss.week2.hotel;

public class Safe {

	private boolean active = false;
	private boolean open = false;
	private Password code;
	
	//Constructor
	public Safe(){
		code = new Password();
	}
	
	//Commands
	/**
	 * activates the safe is password is correct
	 * @param password
	 */
	//@ ensures isActive() == true || isActive() == false;
	public void activate(String password){
		if(code.testWord(password)){
			active = true;
		}
	}
	
	/**
	 * closes safe and deactivates it
	 */
	//@ ensures isActive() == false && isOpen() == false;
	public void deactivate(){
		active = false;
	}
	
	/**
	 * opens safe if password correct and safe is active
	 * @param password
	 */
	//@ requires password != null;
	//@ ensures isOpen() == true || isOpen() == false;
	public void open(String password){
		assert password != null;
		if(code.testWord(password) && isActive()){
			open = true;
		}
	}
	
	/**
	 * close the safe
	 */
	//@ ensures isOpen() == false;
	public void close(){
		open = false;
	}
	
	//Queries
	
	/**
	 * returns the state of the safe
	 * @return
	 */
	/*@pure*/public boolean isActive(){
		return active;
	}
	
	/**
	 * returns if safe is open
	 * @return
	 */
	/*@pure*/public boolean isOpen(){
		return open;
	}
	
	/**
	 * returns the password object
	 * @return
	 */
	/*@pure*/public Password getPassword(){
		return code;
	}
	
	public static void main(String[] args){
		System.out.println("Log of " + Safe.class + ", " + new java.util.Date());
		Safe test = new Safe();
		test.open(null);
	}
}
