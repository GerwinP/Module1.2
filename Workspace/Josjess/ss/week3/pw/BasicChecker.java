package ss.week3.pw;

public class BasicChecker implements Checker {
	
	//---------Constants
	
	public String password = "Irydsdfsd0";
	
	public boolean acceptable(String suggestion){
		return (suggestion.length() >= 6 && !(suggestion.contains(" ")));
	}
	
	public String generatePass(){
		return password;
	}
}
