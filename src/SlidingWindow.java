import java.util.Collections;
import java.util.PriorityQueue;


public class SlidingWindow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1, 3, -1, -3, 5, 3, 6, 7};
		slidingWindowMin(array, 3);
		
		//MinHeapTests();
	}

	/*
	 * MaxHeap w/ k elts
	 * Remove i-k+1 elt and insert i elt
	 * 	Print out min in each iteration
	 */
	public static void slidingWindowMin(int[] arr, int k) {
		if(arr == null)
			return;
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		for(int i=0; i < arr.length; i++) {
			if(i < k) {
				minHeap.add(arr[i]);
				if(i==k-1)
					System.out.print(minHeap.peek() + " ");
			}
			else {
				int removeVal = arr[i-k];
				int curVal = arr[i];
				if(removeVal != curVal) {
					minHeap.remove(removeVal);
					minHeap.add(curVal);
				}
				System.out.print(minHeap.peek() + " ");
			}
		}
	}
	
	/*
	 * w is window size
	 * k smallest ints in the window
	 */
	public static void slidingWindowMin2(int[] arr, int w, int k) {
		if(arr == null)
			return;
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k);
		for(int i=0; i < arr.length; i++) {
			if(i < w) {
				minHeap.add(arr[i]);
				if(i == k-1)
					System.out.print(arr[i] + " ");
			} else {
				int curVal = arr[i];
				int removeVal = arr[i-w];
				int minVal = minHeap.peek();
				if(removeVal != curVal) {
					minHeap.remove(removeVal);
					if(curVal <= minVal)
						minHeap.add(curVal);
				}
				System.out.print(minHeap.peek() + " ");
				
			}		
		}
	}
	
	// Tests
	public static void MinHeapTests() {
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(4);
		minHeap.add(5);
		minHeap.add(4);
		minHeap.add(6);
		minHeap.add(7);
		
		for(Integer i : minHeap)
			System.out.print(i + " ");
		
		
	}
	
}
