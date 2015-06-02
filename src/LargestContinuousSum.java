/*
 * Given an array of integers (positive and negative) find the largest continuous sum.
 * 
 * Variation: Return the indices for  the contiguous sum.
 * 
 * http://www.ardendertat.com/2011/09/24/programming-interview-questions-3-largest-continuous-sum/
 * 
 */
public class LargestContinuousSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] a = {1, 2, 3, 4, -6, 7, 7, 7};		//Output: 25 [0,7]
		//int[] a = {1, 3, 5, -8, -3, 4, 6, 8};		//Output: 18 [5,7]
		int[] a = {-40,1,40,-50,1,50,-20,1,20,0,0}; //Output: 52 [4,8]
		largestContigSumWithIndices(a);
		System.out.println(largestContinuousSum2(a));
	}
	
	// Variation with start and end indices
	public static void largestContigSumWithIndices(int[] a) {
		if(a == null || a.length == 0)
			return;
		
		int max = a[0];
		int sum = a[0];
		int tstart = 0;
		int start = 0;
		int end = 0;

		for(int i=1; i < a.length; i++) {
			if(sum + a[i] > a[i]) {
				sum += a[i];
			} else if(a[i] > sum + a[i]) {
				sum = a[i];
				tstart = i;
			}
			
			if(sum > max) {
				max = sum;
				start = tstart;
				end = i;
			}
		}

		System.out.printf("%d, [%d, %d]%n", max, start, end);
	}
	
	/*
	 * Time: O(n)
	 * Space: O(1)
	 */
	public static int largestContinuousSum2(int[] a) {
		if(a == null)
			return Integer.MIN_VALUE;

		int max = a[0];
		int sum = a[0];

		for(int i=1; i < a.length; i++) {
			sum = Math.max(sum + a[i], a[i]);
			max = Math.max(sum, max);
		}
		
		return max;
	}
	
	
	public static int largestContinuousSumV1a(int[] a) {
		if(a == null)
			return Integer.MIN_VALUE;

		int max = 0;
		int sum = 0;

		for(int i=0; i < a.length; i++) {
			sum += a[i];
			if(sum > max)
				max = sum;
			if(sum < 0)
				sum = 0;
		}
		return max;
	}

	// Wrong. Misunderstood problem
	public static int largestContinuousSum(int[] a) {
		if(a == null)
			return Integer.MIN_VALUE;

		int max = 0;
		int sum = 0;
		for(int i=0; i < a.length; i++) {
			if(a[i] < 0) {
				if(sum > max) {
					max = sum;
					sum = 0;
				} else if(sum <= 0)
					sum += a[i];
				else
					sum = 0;
			}
			else {
				if(sum < 0)
					sum = 0;
				sum += a[i];
			}
		}

		return (sum < 0) ? sum : Math.max(max, sum);
	}

}
