/*
 * Givn an array: [a1, a2,..., aN, b1, b2,..., bN, c1, c2,..., cN]
 * convert it to: [a1, b1, c1, a2, b2, c2,..., aN, bN, cN]
 * in-place using constant extra space.
 * 
 * http://www.ardendertat.com/2011/10/18/programming-interview-questions-9-convert-array/
 */

public class ConvertArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Test1
		int[] arr = new int[12];
		int[] arr2 = new int[12];
		
		arr[0] = 1;
		arr[1] = 4;
		arr[2] = 7;
		arr[3] = 10;
		arr[4] = 2;
		arr[5] = 5;
		arr[6] = 8;
		arr[7] = 11;
		arr[8] = 3;
		arr[9] = 6;
		arr[10] = 9;
		arr[11] = 12;
		
		Helper.printArray(arr);
//		convertArray(arr, 4);
//		Helper.printArray(arr);
//		convertArrayv2(arr, arr2, 4);
//		Helper.printArray(arr2);
		convertArrayv3(arr, 4);
		Helper.printArray(arr);
		
		// Test2
		arr = new int[18];
		arr2 = new int[18];
		
		arr[0] = 1;
		arr[1] = 4;
		arr[2] = 7;
		arr[3] = 10;
		arr[4] = 13;
		arr[5] = 16;
		arr[6] = 2; 	//
		arr[7] = 5;
		arr[8] = 8;
		arr[9] = 11;
		arr[10] = 14;
		arr[11] = 17; 	//
		arr[12] = 3;
		arr[13] = 6;
		arr[14] = 9;
		arr[15] = 12;
		arr[16] = 15;
		arr[17] = 18;
		
		System.out.println();
		Helper.printArray(arr);
//		convertArray(arr, 6);
//		Helper.printArray(arr);
//		convertArrayv2(arr, arr2, 6);
//		Helper.printArray(arr2);
		convertArrayv3(arr, 6);
		Helper.printArray(arr);
		
		// Test3
		arr = new int[6];
		arr2 = new int[6];
		arr[0] = 1;
		arr[1] = 4;
		arr[2] = 7;
		arr[3] = 10;
		arr[4] = 2;
		arr[5] = 5;
		System.out.println();
		Helper.printArray(arr);
//		convertArray(arr, 2);
//		Helper.printArray(arr);
//		convertArrayv2(arr, arr2, 2);
//		Helper.printArray(arr2);
		convertArrayv3(arr, 2);
		Helper.printArray(arr);
	}

	/*
	 * Method3:
	 * 	In-place sorting
	 * 	
	 * This algorithm is NOT linear since some elements will have getIdx called multiple times
	 * Time: O(n^1.3) or superlinear
	 * Space: O(1)
	 * 
	 * Algorithm:
	 * 	for curIdx=0 to arr.length
	 * 		swapIdx = getIdx(curIdx)
	 * 		if(swapIdx >= curIdx)
	 * 			swap
	 * 		else
	 * 			while(swapIdx < curIdx)
	 * 				swapIdx = getIdx(swapIdx)
	 * 			swap
	 * 
	 * Why does this Algorithm work?
	 * 		if swapIdx < curIdx, then this means that the element at swapIdx was already replaced 
	 * 		earlier. What element replaced it? It was the one from which getIdx() was called on 
	 * 		swapIdx.

			Thinking about this inductively, assume an element at swapIdx where swapIdx < curIdx 
			must be in the "correct" position. Therefore, only elements at swapIdx where 
			swapIdx > curIdx can only be possible candidates.
	 * 
	 */
	public static void convertArrayv3(int[] arr, int n) {
		if(arr == null)
			return;
		
		int curIdx, swapIdx, tmp;

		for(curIdx=0; curIdx < arr.length; curIdx++) {
			swapIdx = getIdx(curIdx, n);
			if(swapIdx < curIdx) {
				do {
					swapIdx = getIdx(swapIdx,n);
				} while(swapIdx < curIdx);
			}
			
			tmp = arr[swapIdx];
			arr[swapIdx] = arr[curIdx];
			arr[curIdx] = tmp;
		}
	}
	
	/*
	 * Method2:
	 * 	Not in-place sorting
	 * Time: O(n)
	 * Space: O(n)
	 */
	public static void convertArrayv2(int[] arr, int[] newArr, int n) {
		for(int i=0; i < newArr.length; i++)
			newArr[i] = arr[getIdx(i, n)];
	}
	
	/*
	 * 	(i%3) - tells you if its a , b or c that is chosen
		(i%3 )*N - moves the pointer to the start of that elements series in original array.
		i/3 -will give index of that element in an array having only that type.

		Analogous to Page table lookup in OS

		phys addr = page number + page offset
		i%3 *N - Tells you the Page no in physical memory
		adding i/3 - tells you the offset in the page
		So the 3 is the page size (in bytes)
		i is the virtual address
		http://www.cs.columbia.edu/~junfeng/10sp-w4118/lectures/l21-page.pdf
	 */
	public static int getIdx(int i, int n) {
		return n*(i % 3) + i/3;
	}
	
	/*
	 * Method1
	 * 	Get the next B and C elements and store in tmp 
	 * 	Shift stuff over
	 * 	Put the tmps into the proper places in front
	 * Time: O(n^2)?
	 * Space: O(1)
	 */
	public static void convertArray(int[] arr, int n) {
		if(arr == null)
			return;

		int i = 0;
		int j = n;
		int k = 2*n;

		int tmpB, tmpC;

		while(j - i > 1) {
			tmpB = arr[j];
			tmpC = arr[k];
	
			for(int y=k-1; y > j; y--)
				arr[y+1] = arr[y];
			
			for(int z=j-1; z > i; z--)
				arr[z+2] = arr[z];
	
			arr[i+1] = tmpB;
			arr[i+2] = tmpC;
			i += 3;
			j += 2;
			k++;
		}
	}
}