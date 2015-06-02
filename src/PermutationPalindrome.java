/*
 * Write an efficient function that checks whether any permutation of an input string is a palindrome .

	Examples:
	"civic" should return true
	"ivicc" should return true
	"civil" should return false
	"livci" should return false
	
	https://www.interviewcake.com/question/permutation-palindrome?utm_source=weekly_email
 */

public class PermutationPalindrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "ivicc";
		System.out.printf("%s: %b, %b%n", s, hasPalindrome(s), hasPalindromeV3(s));
		
		s = "civic";
		System.out.printf("%s: %b, %b%n", s, hasPalindrome(s), hasPalindromeV3(s));
		
		s = "civil";
		System.out.printf("%s: %b, %b%n", s, hasPalindrome(s), hasPalindromeV3(s));
		
		s = "livci";
		System.out.printf("%s: %b, %b%n", s, hasPalindrome(s), hasPalindromeV3(s));
		
		s = "cc";
		System.out.printf("%s: %b, %b%n", s, hasPalindrome(s), hasPalindromeV3(s));
		
		s = "cca";
		System.out.printf("%s: %b, %b%n", s, hasPalindrome(s), hasPalindromeV3(s));

	}
	
	/*
	 * Method3:
	 * 	Same idea as Method2 except using parities to keep track of odd or even. Avoids int overflow
	 * 
	 * 	Time: O(n)
	 * 	Space: O(1)
	 */
	public static boolean hasPalindromeV3(String s) {
		if(s == null) return false;

		boolean[] parities = new boolean[26];
		for(int i=0; i < s.length(); i++) {
			int idx = s.charAt(i) - 'a';
			parities[idx] = !parities[idx];
		}

		boolean seen_odd = false;
		for(int i=0; i < parities.length; i++) {
			if(parities[i]) {
				if(seen_odd)
					return false;
				else
					seen_odd = true;
			}
		}

		return true;
	}
	
	
	/*
	 * Method 2
	 * 	Count the number of times a character appears in the string
	 * 	Check the array/map. If there is at most 1 odd count, return true. Else return false
	 * 	
	 * 	Disadvantage: Integer overflow if a character appears too many times.
	 * 
	 * 	Time: O(n)
	 * 	Space: O(1)	
	 */
	public static boolean hasPalindrome(String s) {
		if(s == null) return false;

		if(s.length() % 2 == 0)
			return checkEvenLetterCount(s);

		return checkOddLetterCount(s);
	}

	public static boolean checkEvenLetterCount(String s) {
		int[] alpha = new int[26];

		for(int i=0; i < s.length(); i++) {
			int val = s.charAt(i) - 'a';
			alpha[val]++;
		}

		for(int i=0; i < alpha.length; i++) {
			if(alpha[i] % 2 != 0)
				return false;
		}

		return true;
	}

	public static boolean checkOddLetterCount(String s) {
		int[] alpha = new int[26];

		for(int i=0; i < s.length(); i++) {
			int val = s.charAt(i) - 'a';
			alpha[val]++;
		}

		int oddCount = 0;
		for(int i=0; i < alpha.length; i++) {
			if(alpha[i] % 2 != 0)
				++oddCount;
		}

		return oddCount < 2;
	}

	/*
	 * Method1:
	 * 	Compute the different permutations of the string
	 * 	Check if each perm is a palindrome
	 * 
	 * 	Time: O(n! n) 
	 */
}
