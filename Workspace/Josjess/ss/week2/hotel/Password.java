package ss.week2.hotel;

import ss.week3.pw.BasicChecker;
import ss.week3.pw.Checker;

public class Password {
	
	private Checker checker;
	private String initPass;
	public static final String INITIAL = "welkom";
	private String password = INITIAL;
	
	// Constructor
	public Password(Checker password){
		checker = password;
	}
	
	public Password(){
		this(new BasicChecker());
	}

	public boolean acceptable(String suggestion) {
		return (suggestion.length() >= 6 && !(suggestion.contains(" ")));

	}

	public boolean testWord(String test) {
		return test.equals(password);
	}

	public boolean setWord(String oldpass, String newpass) {
		if (acceptable(newpass) && testWord(oldpass)){
			password = newpass;
			return true;
		}
		return false;
	}
	
	//3.5
	public Checker getChecker(){
		return checker;
	}


}
