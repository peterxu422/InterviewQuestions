import java.util.ArrayList;
import java.util.List;

/*
 * write an algorithm to determine is "happy" ... a number is happy if the sum of the square of 
 * the digits ever equals one. An example of a happy number is 19:
	1^2 + 9^2 = 82
	8^2 + 2^2 = 68
	6^2 + 8^2 = 100
	1^2 + 0^2 + 0^2 = 1
	
	If it is not happy then it will eventually hit repeat back to a number that it already saw
 * 
 */
public class HappyNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Happy Numbers");
		
		// Happy
		System.out.println("19 - " + isHappy(19));
		System.out.println("1 - " + isHappy(1));
		System.out.println("23 - " + isHappy(23));
		System.out.println("13 - " + isHappy(13));
		System.out.println("44 - " + isHappy(44));
		System.out.println("86 - " + isHappy(86));
		
		// Not happy
		System.out.println("14 - " + isHappy(14));
		System.out.println("20 - " + isHappy(20));
		System.out.println("93 - " + isHappy(93));
		System.out.println("75 - " + isHappy(75));
	}

	/* sum = num
	 * while sum not in Map
	 * 		For each digit d in sum
	 * 			sum += d^2
	 * 		
	 * 		if sum == 1
	 * 			return true
	 * 
	 * end while
	 * 
	 * return false
	 */
	public static boolean isHappy(int num) {
		if(num < 0)
			return false;
		
		int sum;
		List<Integer> appeared = new ArrayList<Integer>();
		while(!appeared.contains(num)) {
			appeared.add(num);
			sum = 0;
			while(num > 0) {
				int digit = num % 10;
				num /= 10;
				sum += digit*digit;
			}
			
			if(sum == 1)
				return true;
			num = sum;
		}
		
		return false;
	}
}
