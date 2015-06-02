/*
 * There is an array of non-negative integers. A second array is formed by shuffling the elements 
 * of the first array and deleting a random element. Given these two arrays, find which element is
 * missing in the second array. Here is an example input, the first array is shuffled and the 
 * number 5 is removed to construct the second array.
 * 
 * firstArray = [4,1,0,2,9,6,8,7,5,3]
 * secondArray = [6,4,7,2,1,0,8,3,9]
 * 
 * http://www.ardendertat.com/2011/09/27/programming-interview-questions-4-find-missing-element/
 */

import java.util.Arrays;
import java.util.Hashtable;

public class FindMissingElement {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] first = {4,1,0,2,9,6,8,7,5,3};
		int[] second = {6,4,7,2,1,0,8,3,9};
		/*
		int[] first = {4,1,0,4,9,4,8,7,5,3};
		int[] second = {4,4,7,4,1,0,8,3,9};
		*/
		System.out.println(findMissingElt4(first, second));
		System.out.println(findMissingElt3a(first, second));
		System.out.println(findMissingElt3(first, second));
		System.out.println(findMissingElt2(first, second));
		System.out.println(findMissingElt1(first, second));
	}
	
	/*
	 * Method4:
	 * 	Start with a variable = 0. XOR every number in first and second with the variable.
	 * 	The same numbers in both arrays will cancel each other out, resulting in the missing elt
	 * 	to be the value of the variable.
	 * 
	 *  Fact: n ^ 0 = n (identity)
	 *  	  n ^ n = 0
	 *  	  n xor'd with itself even number of times will yield no change.
	 * 	Time: O(n)
	 * 	Space: O(n)
	 * 	No overflow issues
	 */
	public static int findMissingElt4(int[] f, int[] s) {
		if(f == null || s == null || f.length == 0)
			return Integer.MIN_VALUE;
		
		int num = 0;
		for(int i=0; i < f.length; i++) {
			num ^= f[i];
			num ^= (i < s.length) ? s[i] : 0;
		}

		return num;
	}
	
	/*
	 * Method3a:
	 * 	Scan and sum elements of first and sum elements of second.
	 * 	Return difference between sumFirst and sumSecond
	 * 	Problem: For large arrays, and summing large values. Overflow occurs
	 * 	Time: O(n)
	 * 	Space: O(1)
	 */
	public static int findMissingElt3a(int[] f, int[] s) {
		if(f == null || s == null || f.length == 0)
			return Integer.MIN_VALUE;
		
		int diff = 0;

		for(int i=0; i < f.length; i++) {
			diff += f[i];
			diff -= (i < s.length) ? s[i] : 0;
		}

		return diff;
	}
	
	/*
	 * Method3:
	 * 	Scan and sum elements of first and sum elements of second.
	 * 	Return difference between sumFirst and sumSecond
	 * 	Problem: For large arrays, and summing large values. Overflow occurs
	 * 	Time: O(n)
	 * 	Space: O(1)
	 */
	public static int findMissingElt3(int[] f, int[] s) {
		if(f == null || s == null || f.length == 0)
			return Integer.MIN_VALUE;
		
		int sumF = 0;
		int sumS = 0;

		for(int i=0; i < f.length; i++) {
			sumF += f[i];
			sumS += (i < s.length) ? s[i] : 0;
		}

		return sumF - sumS;
	}
	
	/*
	 * Method2:
	 * 	Sort both arrays
	 * 	Scan until elements don't match
	 * 	Time: O(nlogn + n) = O(nlogn)
	 * 	Space: O(1)
	 */
	public static int findMissingElt2(int[] f, int[] s) {
		if(f == null || s == null || f.length == 0)
			return Integer.MIN_VALUE;

		Arrays.sort(f);
		Arrays.sort(s);

		for(int i=0; i < s.length; i++) {
			if(f[i] != s[i])
				return f[i];
		}

		return f[f.length - 1];
	}
	
	/*
	 * Method1: 
	 * 	Pre-process 2nd array by storing its values in a map/set/table along with count
	 * 	Check every element in 1st array if it's is in map/set. If count in table is 0, it's 
	 * the missing element
	 * 	Time: O(2n) = O(n)
	 * 	Space: O(n)
	 */
	public static int findMissingElt1(int[] f, int[] s) {
		if(f == null || s == null || f.length == 0)
			return Integer.MIN_VALUE;

		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();

		for(int i=0; i < s.length; i++) {
			Integer val = table.get(s[i]);
			if(val == null)
				table.put(s[i], 1);
			else
				table.put(s[i], val++);
		}

		for(int i=0; i < f.length; i++) {
			Integer count = table.get(f[i]);
			if(count == null || count == 0)
				return f[i];
			else
				table.put(f[i], count--);
		}

		return Integer.MIN_VALUE;
	}
	
	/*
	 *  Naive: check every element of first array exists in 2nd array in dbl loop.
	 *  Time: O(n^2)
	 *  Space: O(n)
	 */

}
