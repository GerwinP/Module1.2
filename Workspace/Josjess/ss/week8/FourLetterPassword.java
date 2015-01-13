package ss.week8;

public class FourLetterPassword {
	
	public static String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

	public FourLetterPassword(String a){
		allPosibleCombinations(a);
	}
	
	public void allPosibleCombinations(String password){
		for(int a = 0; a < 26; a++){
			for(int b = 0; b < 26; b++){
				for(int c = 0; c < 26; c++){
					for(int d = 0; d < 26; d++){
						String tryword =  abc[a] + abc[b] + abc[c] + abc[d];
						if(password.equals(tryword)){
							System.out.println(System.currentTimeMillis());
						}
					}
				}
			}
		}
	}
	
	static public void main(String[] args) {
		new FourLetterPassword("aaaa");
	}
}
