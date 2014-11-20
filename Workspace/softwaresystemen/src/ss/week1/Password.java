package ss.week1;

public class Password {
	
	public static final String INITIAL = "WELKOM"; 
	private String password;
	
	public Password(){
		password = INITIAL;
	}
	
	public boolean acceptable(String suggestion){
		return (suggestion.length() >= 6 && !(suggestion.contains(" ")));
	}
	
	public boolean setWord(String oldpass, String newpass){
		if(acceptable(newpass) && testWord(oldpass)){
			password = newpass;
			return true;
		}
		return false;
	}
	
	public boolean testWord(String test){
		return test.equals(password);
	}
	
}
