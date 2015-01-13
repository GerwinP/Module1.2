package ss.week5;

import java.util.Arrays;
import java.util.LinkedList;

public class Sort {
	//public static int[] array = {1,5,3,6,3,5,7,9,6,9,4,2,4};
	
	//5.5 bubblesort
	public static int[] bubblesort(int[] numbers){
		int oi = numbers.length - 1;
		int[] result = numbers;
		while(oi > 0){
			int wi = 0;
			int i = 0;
			while(i < oi){
				if(result[i]>result[i+1]){
					int tmp = result[i];
					result[i] = result[i + 1];
					result[i + 1] = tmp;
					wi = i;
				}
				i++;
			}
			oi = wi;
		}
		return result;
	}
	
	//5.6 mergesort
	public static LinkedList<Integer> mergesort(LinkedList<Integer> numbers){
		int h = 0;
		int lengte = numbers.size();
		LinkedList<Integer> result = new LinkedList<Integer>();
		LinkedList<Integer> resultLinks = new LinkedList<Integer>();
		LinkedList<Integer> resultRechts = new LinkedList<Integer>();
		if(lengte == 0 ||lengte == 1){
			return numbers;
		}
		else{
			int i = (((lengte - h)/2)+h);
			for(int j = 0; j < i; j++){
				resultLinks.add(numbers.get(j));
			}
			for(int k = i; k < lengte; k++){
				resultRechts.add(numbers.get(k));
			}
			resultLinks = mergesort(resultLinks);
			resultRechts = mergesort(resultRechts);
			while(resultLinks.size() > 0 && resultRechts.size() > 0){
				if(resultLinks.get(0) < resultRechts.get(0)){
					result.add(resultLinks.removeFirst());
				}
				else{
					result.add(resultRechts.removeFirst());
				}
			}
			if(resultLinks.size() > 0){
				result.addAll(resultLinks);
			}
			else if(resultRechts.size() > 0){
				result.addAll(resultRechts);
			}
			return result;
		}
	}
	
	public static int[] shift (int[] numbers){
		int k = 1;
		int temp = numbers[numbers.length - 1];
		while (k <= numbers.length - 1){
			int l = k + 1;
			numbers[numbers.length - k] = numbers[numbers.length - l];
			k++;
		}
		numbers[0] = temp;
		return numbers;
	}
	
	public static void main(String[]args){
		int[] array = {1,5,13,67,3,45,72,9,16,9,64,2,4};
		int[] numbers = {1,2,3,4,5,6,7,8};
	    LinkedList<Integer> intList = new LinkedList<Integer>();
	    for (int index = 0; index < array.length; index++){
	        intList.add(array[index]);
	    }
	    System.out.println(intList);
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(bubblesort(array)));
		System.out.println(mergesort(intList));
		System.out.println(Arrays.toString(shift(numbers)));
	}
}
