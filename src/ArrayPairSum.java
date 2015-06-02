import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Question: http://www.ardendertat.com/2011/09/17/programming-interview-questions-1-array-pair-sum/ 
 * Given an integer array, output all pairs that sum up to a specific value k. 
 */

/*
 * Resources:
 * Nested Classes: http://tutorials.jenkov.com/java/nested-classes.html
 * Hashcode, equals: http://tutorials.jenkov.com/java-collections/hashcode-equals.html
 * Sets and int pairs: http://stackoverflow.com/questions/24262897/integer-pair-add-to-hashset-java
 */
public class ArrayPairSum {
	
	// Make this static since functions using this class are in static context
	private static class IntPair {
		int mX, mY;
		public IntPair(int x, int y) {
			mX = x;
			mY = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof IntPair) {
				IntPair pair = (IntPair) o;
				return this.mX == pair.mX && mY == pair.mY;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return new Integer(mX).hashCode() * 31 + new Integer(mY).hashCode();
		}

		@Override
		public String toString() {
			return "[" + mX + ", " + mY + "]";
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Testing adding int pairs to set
		Set<IntPair> set = new HashSet<IntPair>();
		set.add(new IntPair(1, 3));
		set.add(new IntPair(2, 3));
		set.add(new IntPair(1, 3));
		System.out.println(set.toString());
		
		int[] a = Helper.bigList(1000, 100);
		Helper.printArray(a);
		pairsSum3(a, 30);
	}
	
	/*
	 * Time: O(n)
	 * Space: O(2n) = O(n)
	 * Tradeoff in extra space for faster runtime.
	 * Careful choice of data structure (i.e. Set) improves our runtime from O(nlogn).
	 */
	public static void pairsSum3(int[] a, int k) {
		if(a == null || a.length < 2) return;
		
		int compl;
		Set<Integer> set = new HashSet<Integer>();
		Set<IntPair> output = new HashSet<IntPair>();
		
		set.add(a[0]);
		for(int i=1; i < a.length; i++) {
			compl = k - a[i];
			IntPair pair = new IntPair(Math.min(a[i], compl), Math.max(a[i], compl));
			if(set.contains(compl))
				output.add(pair);
			set.add(a[i]);
		}
		
		for(IntPair p : output)
			System.out.println(p.toString());
	}
	
	/*
	 * Time: O(nlogn + n) = O(nlogn)
	 * Space: O(1)
	 */
	public static void pairsSum2(int[] a, int k) {
		// Second condition is important to check!!!
		if(a == null || a.length < 2) return;

		Arrays.sort(a);
		int left = 0;
		int right = a.length - 1;
		int sum;

		while(left < right) {
			sum = a[left] + a[right];
			if(sum == k) {
				System.out.printf("%d, %d%n", a[left], a[right]);
				left++;	// Make sure to left++ or right--!!!! Otherwise, program doesn't halt
			}
			else if(sum > k)
				right--;
			else
				left++;
		}
	}
	
	/*
	 * Time: O(nlogn + nlogn) = O(nlogn)
	 * Space: O(1)
	 */
	public static void pairsSum(int[] a, int k) {
		if(a == null) return;

		int compl;

		Arrays.sort(a);
		for(int i=0; i < a.length; i++) {
			compl = k - a[i];
			if(binarySearch(a, compl) > 0)
				System.out.printf("%d, %d%n", a[i], compl);
		}
	}

	public static int binarySearch(int[] a, int num) {
		if(a == null) return -1;

		int low = 0;
		int high = a.length-1;
		int mid;

		while(low <= high) {
			mid = (low + high) / 2;
			
			if(a[mid] == num)
				return mid;
			else if(num < a[mid])
				high = mid-1;
			else
				low = mid + 1;
		}

		return -1;
	}
}
