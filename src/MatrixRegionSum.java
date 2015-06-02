/*
 * Given a matrix of integers and coordinates of a rectangular region within the matrix, 
 * find the sum of numbers falling inside the rectangle. Our program will be called multiple 
 * times with different rectangular regions from the same matrix.
 * 
 * http://www.ardendertat.com/2011/09/20/programming-interview-questions-2-matrix-region-sum/
 */

public class MatrixRegionSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// total=1360
		int marks[][]= {{50,60,55,67,70},
						{62,65,70,70,81},
						{72,66,77,80,69},
						{22,36,57,40,39},
						{12,23,34,55,28}};
		
		int[][] rectSums = new int [marks.length][marks[0].length];
		precomputeSums(marks, rectSums);
		Helper.printMatrix(rectSums);
		int[] x = {1, 3};
		int[] y = {1, 3};
		System.out.printf("%d", matrixRegionSum2(rectSums, x, y));
	}
	
	/*
	 * Cache a subset of the rectangular area sums.
	 * Dynamic Programming solution to solving pre-computed sums.
	 * Run-time: O(MN)
	 */
	public static void precomputeSums(int[][] marks, int[][] rectSums) {
		if(marks.length == 0) return;	

		for(int r=0; r < marks.length; r++) {
			for(int c=0; c < marks[0].length; c++) {
				if(r==0 && c==0)
					rectSums[0][0] = marks[0][0];				
				else if(r == 0)
					rectSums[r][c] = rectSums[r][c-1] + marks[r][c];
				else if(c == 0)
					rectSums[r][c] = rectSums[r-1][c] + marks[r][c];
				else {
					rectSums[r][c] = rectSums[r-1][c] 	+ 
									 rectSums[r][c-1] 	- 
									 rectSums[r-1][c-1] + 
									 marks[r][c];	
				}
			}
		}
	}

	/*
	 * Time: O(1)
	 * Space: O(MN)
	 */
	public static int matrixRegionSum2(int[][] rectSums, int[] x, int[] y) {
		int od = rectSums[y[1]][x[1]];
		int oc = rectSums[y[1]][x[0] - 1];
		int ob = rectSums[y[0] - 1][x[1]];
		int oa = rectSums[y[0] - 1][x[0] - 1];

		return od - oc - ob + oa;
	}

	/*
	 * Time: O(MN)
	 * Space: O(1)
	 * Inefficient solution for repeated computes
	 */
	public static int matrixRegionSum(int[][] M, int[] x, int[] y) {
		if(M.length == 0)
			return Integer.MAX_VALUE;	

		int sum = 0;
		for(int i=y[0]; i <= y[1]; i++) {
			for(int j=x[0]; j <= x[1]; j++) {
				sum += M[i][j];
			}
		}

		return sum;
	}
}
