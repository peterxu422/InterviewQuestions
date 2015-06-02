/*
 * https://www.interviewcake.com/question/find-duplicate-optimize-for-space
 * Beast Mode: https://www.interviewcake.com/question/find-duplicate-optimize-for-space-beast-mode
 * 
 * Find a duplicate, Space Edition™.
We have an array of integers, where:

The integers are in the range 1..n1..n
The array has a length of n+1n+1
It follows that our array has at least one integer which appears at least twice. But it may have 
several duplicates, and each duplicate may appear more than twice.

Write a function which finds any integer that appears more than once in our array.

We're going to run this function on our new, super-hip Macbook Pro With Retina Display™. Thing is, 
the damn thing came with the RAM soldered right to the motherboard, so we can't upgrade our RAM. 
So we need to optimize for space!

Bonus
This function always returns one duplicate, but there may be several duplicates. Write a function 
that returns all duplicates
 */

public class FindDuplicateSpaceEdition {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1, 3, 2, 6, 8, 7, 5, 4, 4, 9, 10};
		System.out.println(findDuplicateV4(arr));
		System.out.println(findDuplicateV6(arr));
		System.out.println(findDuplicateV5(arr));
		
		int[] arr2 = {1, 2, 3, 2, 4, 6, 7, 10, 4, 8, 5};
		System.out.println(findDuplicateV4(arr2));
		System.out.println(findDuplicateV6(arr2));
		System.out.println(findDuplicateV5(arr2));
		
		int[] arr3 = {1, 2, 3, 2, 10, 6, 7, 10, 4, 8, 9};
		System.out.println(findDuplicateV4(arr3));
		System.out.println(findDuplicateV6(arr3));
		System.out.println(findDuplicateV5(arr3));
	}

	/*
	 * Method1:
	 * 	1. Sort in-place
	 * 	2. Iterate through array until duplicate found
	 * 
	 * Time: O(n log n)
	 * 		- Mergesort: No. Not space friendly
	 * 		- Quicksort: Iterative version. Recursive could take too much stack space.
	 * Space: O(1)
	 */
	
	/*
	 * Method2:
	 * 	Double loop each
	 * 		compare if two ints are equal
	 * 
	 * 	Time: O(n^2)
	 * 	Space: O(1)
	 * 
	 */
	public static int findDuplicateV2(int[] arr) {
		if(arr == null)
			return -1;
		
		for(int i=0; i < arr.length; i++) {
			for(int j=i+1; j < arr.length; j++) {
				if(arr[j] == arr[i])
					return arr[i];
			}
		}
		
		return -1;
	}
	
	/*
	 * Method3:
	 * 	Walk through and use a hash map
	 * Time: O(n)
	 * Space: O(n)
	 */
	
	/*
	 * Method4:
	 * 	Similar to binary search. Except, divide the range of possible answers in half, rather
	 * than dividing the actual array in half/
	 * 
	 * 	Recursively break up into two buckets 1..n/2 and n/2+1..n. Establish int ranges
	 * 		Iterate through all numbers and count them towards one of the ranges
	 * 		By pigeonhole principle, the range where count > |range| has the "needle"
	 * 		Set new ranges and do this recursively
	 * 
	 * Time: O(n log n)
	 * Space: O(1)
	 */
	public static int findDuplicateV4(int[] arr) {
		if(arr == null)
			return -1;
		
		int firstLow = 1;	// Important! Don't mistakenly set to 0
		int firstHigh = arr.length / 2;
		int secondLow = (arr.length / 2) + 1;
		int secondHigh = arr.length - 1;
		
		int countLeft = 0;
		int countRight = 0;
		
		while(firstLow < secondHigh) {
			firstHigh = (firstLow + secondHigh) / 2;
			secondLow = firstHigh + 1;
			
			countLeft = 0;
			countRight = 0;
			for(int i=0; i < arr.length; i++) {
				if(firstLow <= arr[i] && arr[i] <= firstHigh)
					countLeft++;
				else if(secondLow <= arr[i] && arr[i] <= secondHigh)
					countRight++;
			}
			
			int numDistinctLeft = firstHigh - firstLow + 1;
			int numDistinctRight = secondHigh - secondLow + 1;
			
			if(countLeft > numDistinctLeft)
				secondHigh = firstHigh;
			else if(countRight > numDistinctRight)
				firstLow = secondLow;
		}
		
		//return (countRight > countLeft) ? secondHigh : firstLow;
		return firstLow;	// Better than above statement. or return secondHigh
	}
	
	// Beast Mode
	/*
	 * Imagine each item in the array as a node in a linked list. In any linked list , 
	 * each node has a value and a "next" pointer. In this case:

		The value is the integer from the array.
		The "next" pointer points to the value-eth node in the list (numbered starting 
		from 1). For example, if our value was 3, the "next" node would be the third node.
	 * 
	 * arr: 	[2, 3, 1, 3]
	 * pos:		 1  2  3  4 
	 * LL:		
	 * 
	 * Method5:
	 * Patterns:
	 * 	- nth digit is the entry point into the graph. nth digit ALWAYS points backwards
	 * 		i.e. the last node never has any incoming pointers. Treat it as the "head"
	 * 	- Duplicate nodes have multiple incoming pointers
	 *  - The list doesn't end. Always has cycles
	 *  - Connection between cycles and multiple incoming pointers
	 * 	
	 * 	Keep track of prev and cur values.
	 * 	Set the cur element to 0, go to the next one, update prev
	 * 	if(cur == 0)
	 * 		return prev
	 * 
	 * Time: O(n)
	 * Space: O(1)
	 * 
	 * Solved this without looking much at solution. Looked at a few examples and tried to
	 * find a pattern.
	 * 
	 * Problem: This corrupts the input array. Can we do better and not corrupt the input?
	 */
	public static int findDuplicateV5(int[] arr) {
		if(arr == null)
			return -1;
		
		int prev = arr[arr.length-1];
		int cur = arr[prev-1];
		int tmp;
		while(cur != 0) {
			tmp = prev;
			prev = cur;
			arr[tmp-1] = 0;
			cur = arr[prev-1];
		}
		
		return prev;
	}
	
	/*
	 * Method:
	 * 	Find the cycle and the cycle length
	 * 	Then use LinkedList cycle finding algorithm to find the head of the cycle
	 * 		Start back from beginning and use two pointers
	 * 	The head of the cycle MUST have multiple incoming pointers.
	 */
	public static int findDuplicateV6(int[] arr) {
		if(arr == null)
			return -1;
		
		// Get into the cycle
		int nCycle = 0;
		int cur = arr[arr.length-1];
		for(int i=0; i < arr.length-1; i++) {
			cur = arr[cur-1];
		}
		
		// Find the cycle length
		int firstVal = cur;
		do {
			cur = arr[cur-1];
			nCycle++;
		} while(cur != firstVal);
		
		// Use head of cycle finding LL algorithm
		int ptr1, ptr2;
		ptr1 = arr[arr.length-1];
		ptr2 = arr[arr.length-1];
		for(int i=0; i < nCycle; i++) {
			ptr2 = arr[ptr2-1];
		}
		
		while(ptr1 != ptr2) {
			ptr1 = arr[ptr1-1];
			ptr2 = arr[ptr2-1];
		}
		
		return ptr1;
	}
}
