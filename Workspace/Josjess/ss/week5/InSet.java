package nh.week5;

public class InSet {
	
	private boolean[] isIn;
	private int maxElem;
	
	//@ requires maxElement >= 0
	public IntSet(int maxElement){
		this.maxElem = maxElement;
		isIn = new boolean[maxElem + 1];
		for(int i = 0; i < maxElem; i++){
			isIn[i] = false;
		}
		
	}
	
	public void addElement(int element){
		if (element <= maxElem && element >=0){
			isIn[element] = true;
		}
	}
	
	public void removeElement(int element){
		if (element <= maxElem && element >=0){
			isIn[element] = false;
	
		}
	}
	
	public IntSet union(IntSet i){
		
		
		
		
		
		return null;
		
	}
	
	
	public boolean isIn(){
		
		return false;
	//geeft aan of integer in set zit
	}
	
	public int maxElement(){
		return isIn.length-1;//lengte van de array - 1;
	}
}
