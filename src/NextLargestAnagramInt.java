import java.util.PriorityQueue;


public class NextLargestAnagramInt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printTest(nextSameDigits(115), 151);
		printTest(nextSameDigits(1500), 5001);
		printTest(nextSameDigits(10), -1);
		printTest(nextSameDigits(1156), 1165);
		printTest(nextSameDigits(265), 526);
		printTest(nextSameDigits(1526543), 1532456);
	}
	
	public static void printTest(int result, int output) {
		System.out.println(result + " " + (result == output));
	}

	public static int nextSameDigits(int I) {
		int min = -1;
		char[] s = (Integer.toString(I)).toCharArray();
		int n = s.length-1;

		for(int i=n; i >= 0; i--) {
			int r = s[i] - '0';
			for(int j=i-1; j >= 0; j--) {
				int l = s[j] - '0';
				if(r > l) {
					swap(s,i,j);
					String tmp = new String(s);
					int result = Integer.parseInt(tmp);
					int remain = Integer.parseInt(tmp.substring(j+1));
					result = result - remain + smallest(remain);
					min = (min <0) ? result : Math.min(min, result);
					swap(s,i,j); // Need to re-swap them otherwise other iterations will operate on incorrect input
				}
			}
		}	

		return min;
	}

	public static void swap(char[] s, int i, int j) {
		char c = s[i];
		s[i] = s[j];
		s[j] = c;
	}
	
	public static int smallest(int I) {
		int[] digits = new int[10];
		while(I > 0) {
			digits[I%10]++;
			I /= 10;
		}
		
		int result = 0;
		for(int i=0; i < 10; i++) {
			while(digits[i] > 0) {
				result *= 10;
				result += i;
				digits[i]--;
			}
		}
		
		return result;
	}
}
