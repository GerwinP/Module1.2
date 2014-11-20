package ss.week2;

public class Rectangle {

	//@ public invariant length() > 0;
	//@ public invariant width() > 0;
	//@ public invariant area() > 0;
	//@ public invariant perimeter() > 0;
	int width = 0;
	int length = 0;
	
	//Constructor
	
	//@ requires length >= 0 && width >= 0;
	public Rectangle(int length, int width){
		this.width = width;
		this.length = length;
	}
	
	//Queries
	
	/*@pure*/public int length(){
		return length;
	}
	
	/*@pure*/public int width(){
		return width;
	}
	
	/*@pure*/public int area(){
		int area = width * length;
		return area;
	}
	
	/*@pure*/public int perimeter(){
		int perimeter = (2*width) + (2*length);
		return perimeter;
	}
}
