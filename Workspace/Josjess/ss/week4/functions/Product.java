package ss.week4.functions;

public class Product implements Function {

	private Function fx;
	private Function gx;
	
	public Product (Function fx, Function gx){
		this.fx = fx;
		this.gx = gx;
	}
		
	public double apply(double arg) {
		return fx.apply(arg)*gx.apply(arg);
	}

	public Function derivative() {
		return new Sum(new Product(fx.derivative(), gx), new Product(gx.derivative(), fx));
	}
	
	public String toString(){
		return "Product " + fx + " " + gx; 
	}
}
