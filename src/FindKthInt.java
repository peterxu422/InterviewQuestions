/*
 *  http://www.ardendertat.com/2011/10/27/programming-interview-questions-10-kth-largest-element-in-array/
 *  
 *  Given an array of integers find the kth element in the sorted order (not the kth distinct
 *   element). So, if the array is [3, 1, 2, 1, 4] and k is 3 then the result is 2, because 
 *   it’s the 3rd element in sorted order (but the 3rd distinct element is 3).
 */

public class FindKthInt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[5];
		arr[0] = 3;
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 1;
		arr[4] = 4;
		System.out.println(findKth(arr,3) == 2);
		
		arr[0] = 2;
		arr[1] = 1;
		arr[2] = 5;
		arr[3] = 1;
		arr[4] = 4;
		System.out.println(findKth(arr,3) == 2);
	}
	
	public static int findKth(int[] arr, int k) {
		if(arr == null)
			return Integer.MIN_VALUE;
		
		return recFindK(arr, k, 0, arr.length-1);
	}
	
	public static int recFindK(int[] arr, int k, int low, int high) {
		int pivotIdx = (low + high) / 2;
		int pivot = arr[pivotIdx];
		
		int i = low;
		int j = high;
		
		while(i <= j) {
			while(arr[i] < pivot)
				i++;
			while(arr[j] > pivot)
				j--;
			
			if(i <= j) {
				pivotIdx = swap(arr, i, j, i==pivotIdx);
				i++;	// This is fundamental to quicksort. You need to review QS and get comfortable with it.
				j--;
			}
		}
		
		if((k-1) < pivotIdx)
			return recFindK(arr, k, low, pivotIdx-1);
		else if((k-1) > pivotIdx)
			return recFindK(arr, k, pivotIdx+1, high);
		
		return pivotIdx;
	}
	
	public static int swap(int[] arr, int i, int j, boolean iEqPi) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		
		return (iEqPi) ? j : i;
	}

}
