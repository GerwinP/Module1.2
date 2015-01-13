package ss.week4.functions;

public class Polynomial implements Function {
	
	private Function[] indinges;
	
	public Polynomial(Function[] indinges){
		this.indinges = indinges;
	}

	public double apply(double arg) {
		double polyply = 0;
		for(int i=0; i < indinges.length; i++){
			polyply  += indinges[i].apply(arg);
		}
		return polyply;
	}

	public Function derivative() {
		Function function = indinges[0].derivative();
		for(int i=1; i < indinges.length; i++){
			function = new Sum(indinges[i].derivative(), function);
		}
		return function;
	}
	
	public String toString(){
		return "Polynomial " + indinges; 
	}
}
