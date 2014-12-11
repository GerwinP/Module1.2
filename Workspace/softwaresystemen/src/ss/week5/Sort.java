package ss.week5;

import java.util.Arrays;
import java.util.LinkedList;

public class Sort {

	// Opdracht 5.5
	public static LinkedList<Integer> bubble(LinkedList<Integer> toSort) {
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < toSort.size() - 1; i++) {
				int temp = toSort.get(i);
				if (temp > toSort.get(i + 1)) {
					sorted = false;
					toSort.set(i, toSort.get(i + 1));
					toSort.set(i + 1, temp);
				}
			}
		}
		return toSort;
	}

	// Opdracht 5.6
	public static LinkedList<Integer> merge(LinkedList<Integer> toSort) {
		boolean sorted = false;
		LinkedList<Integer> left = new LinkedList<Integer>();
		LinkedList<Integer> right = new LinkedList<Integer>();
		if (!sorted && toSort.size() <= 1) {
			sorted = true;
		} else if (!sorted) {
			int middle = toSort.size() / 2;
			// Create left part
			for (int x = 0; x < middle; x++) {
				left.add(toSort.get(x));
			}
			// Create right part
			for (int x = middle; x < toSort.size(); x++) {
				right.add(toSort.get(x));
			}
			merge(left);
			merge(right);
			while (left.size() <= 0 && right.size() <= 0) {
				if (left.getFirst() > right.getFirst()) {
					toSort.add(right.poll());
				} else {
					toSort.add(left.poll());
				}
			}
		}
		return toSort;
	}

	public static void main(String[] args) {
		LinkedList<Integer> toTest = new LinkedList<Integer>(Arrays.asList(3,
				2, 4, 6, 9, 7, 8, 5, 1));
		Sort s = new Sort();
		System.out.println("Bubble");
		System.out.println(s.bubble(toTest));
		System.out.println("Merge");
		System.out.println(s.merge(toTest));
	}
}
