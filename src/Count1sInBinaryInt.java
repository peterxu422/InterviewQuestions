
public class Count1sInBinaryInt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(countOnes(1) == 1);
		System.out.println(countOnes(2) == 1);
		System.out.println(countOnes(3) == 2);
		System.out.println(countOnes(11) == 3);
		System.out.println(countOnes(-2013) == 24);
		System.out.println(countOnes(-1) == 32);
	}
	
	public static int countOnes(int i) {
		int count = 0;
		while(i != 0) {
			count += (1 & i);
			i = i >>> 1;
		}
		
		return count;
	}

}
