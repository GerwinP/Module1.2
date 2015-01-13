package ss.week4.functions;

public class Exponent implements Function {
	//resembles x^n
	//n should be passed to the constructor
	
	private int exponent;
	
	public Exponent(int n){
		exponent = n;
	}

	public double apply(double arg) {
		return Math.pow(arg, exponent);
	}

	public Function derivative() {
		return new Product(new Constant(exponent), new Exponent(exponent - 1));
	}
	 
	public String toString(){
		return "Exponent" + exponent;
	}
}
