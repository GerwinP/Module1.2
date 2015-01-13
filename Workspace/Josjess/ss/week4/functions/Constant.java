package ss.week4.functions;

public class Constant implements Function{

	private int constant;
	
	//------Constructor-------
	public Constant(int constant){
		this.constant = constant;
	}
	
	public double apply(double arg) {	
		return constant;
	}

	
	public Function derivative() {
		return new Constant(0);
	}
	
	public String toString(){
		return "Constant" + constant;
	}
}
