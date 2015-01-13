package ss.week4.functions;

public class Sum implements Function {
	
	private Function fx;
	private Function gx;
	
	public Sum(Function fx, Function gx){
		this.fx = fx;
		this.gx = gx;
	}

	public double apply(double arg) {
		return fx.apply(arg) + gx.apply(arg);
	}

	public Function derivative() {
		return new Sum(fx.derivative(), gx.derivative());
	}
	
	public String toString(){
		return "Sum " + fx + " " + gx;
	}
}
