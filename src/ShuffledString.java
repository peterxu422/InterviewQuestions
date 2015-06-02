/*
 * We are given 3 strings: str1, str2, and str3. Str3 is said to be a shuffle of str1 and str2 if 
 * it can be formed by interleaving the characters of str1 and str2 in a way that maintains the 
 * left to right ordering of the characters from each string. For example, given str1=”abc” 
 * and str2=”def”, str3=”dabecf” is a valid shuffle since it preserves the character ordering of 
 * the two strings. So, given these 3 strings write a function that detects whether str3 is a valid 
 * shuffle of str1 and str2.
 * 
 * http://www.ardendertat.com/2011/10/10/programming-interview-questions-6-combine-two-strings/
 */

import java.util.Set;
import java.util.HashSet;

public class ShuffledString {

	private static class StringTuple {
		
		String s1;
		String s2;
		
		public StringTuple(String s1, String s2) {
			this.s1 = s1;
			this.s2 = s2;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof StringTuple) {
				StringTuple other = (StringTuple) obj;
				return other.s1.equals(this.s1) &&
						other.s2.equals(this.s2);
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return s1.hashCode()*31 + s2.hashCode();
		}
		
		@Override
		public String toString() {
			return "[" + s1 + "," + s2 + "]";
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1, s2, s3;
		/*
		s1 = "abc";
		s2 = "def";
		s3 = "dabecf";
		System.out.printf("detectShuffle1| %s, %s, %s: %b%n", s1, s2, s3, detectShuffle1(s1,s2,s3,0,0,0));
		System.out.printf("detectShuffle2| %s, %s, %s: %b%n", s1, s2, s3, detectShuffle2(s1,s2,s3));
		
		s1 = "ef";
		s2 = "b";
		s3 = "efb";
		System.out.printf("detectShuffle1| %s, %s, %s: %b%n", s1, s2, s3, detectShuffle1(s1,s2,s3,0,0,0));
		System.out.printf("detectShuffle2| %s, %s, %s: %b%n", s1, s2, s3, detectShuffle2(s1,s2,s3));
		
		// Edge cases
		s1 = "ebaf";
		s2 = "cedg";
		s3 = "cedebagf";
		System.out.printf("detectShuffle1| %s, %s, %s: %b%n", s1, s2, s3, detectShuffle1(s1,s2,s3,0,0,0));
		System.out.printf("detectShuffle2| %s, %s, %s: %b%n", s1, s2, s3, detectShuffle2(s1,s2,s3));
		
		s1 = "eef";
		s2 = "eeg";
		s3 = "eeegef";
		System.out.printf("detectShuffle1| %s, %s, %s: %b%n", s1, s2, s3, detectShuffle1(s1,s2,s3,0,0,0));
		System.out.printf("detectShuffle2| %s, %s, %s: %b%n", s1, s2, s3, detectShuffle2(s1,s2,s3));
		*/
		
		// Test cache benefits
		counter = 0;
		s1 = "eef";
		s2 = "eeg";
		s3 = "eeegef";
		System.out.printf("detectShuffle1| %s, %s, %s: %b%n", s1, s2, s3, detectShuffle1(s1,s2,s3,0,0,0));
		System.out.printf("detectShuffle2| %s, %s, %s: %b%n", s1, s2, s3, detectShuffle2(s1,s2,s3));
		System.out.println(counter);
		
	}
	
	static int counter = 0;
	
	public static boolean detectShuffle2(String s1, String s2, String s3) {
		Set<StringTuple> cache = new HashSet<StringTuple>();
		if(s1.length() + s2.length() != s3.length())
			return false;
		return detectShuffle2_rec(s1, s2, s3, 0, 0, 0, cache);
	}
	
	private static boolean detectShuffle2_rec(String s1, String s2, String s3, 
												int idx1, int idx2, int idx3,
												Set<StringTuple> cache) {
		
		// Toggle: Print this output when ignoring the cache 
//		System.out.printf("1)%s 2)%s 3)%s %s%n",s1.substring(idx1),
//												s2.substring(idx2),
//												s3,
//												cache.toString());		
		
		
		// Toggle: Print this when using the cache
		if(cache.contains(new StringTuple(s1.substring(idx1), 
										  s2.substring(idx2) ))) {
			return false;
		}
		
		System.out.printf("1)%s 2)%s 3)%s %s%n",s1.substring(idx1),
												s2.substring(idx2),
												s3,
												cache.toString());
		
		counter++;
		if(idx3 == s3.length())
			return idx1 == s1.length() && idx2 == s2.length();
		
		char c1 = (idx1 < s1.length()) ? s1.charAt(idx1) : '\0';
		char c2 = (idx2 < s2.length()) ? s2.charAt(idx2) : '\0';
		char c3 = s3.charAt(idx3);
		
		if(idx1 < s1.length() && c3 != c1 &&
			idx2 < s2.length() && c3 != c2) {
			StringTuple tmp = new StringTuple(s1.substring(idx1), s2.substring(idx2));
			cache.add(tmp);
			return false;
		}
		
		/*
		 *  Bad idea to do post-increment in a recursive parameter
		 *  E.g. leftTry = detectShuffle2(s1, s2, s 3, idx1++, idx2, idx3++);
		 *  Would cause stack overflow b/c increment is done after usage of the variable.
		 *  In fact, don't do increment at all!!! It messes up the idx's for the second if condition
		 *  if it needs to be eval'd
		 */
//		boolean leftTry = false; 
//		boolean rightTry = false;
//		if(idx1 < s1.length() && c3 == c1)
//			leftTry = detectShuffle2_rec(s1, s2, s3, idx1 + 1, idx2, idx3 + 1, cache);
//		if(idx2 < s2.length() && c3 == c2)
//			rightTry = detectShuffle2_rec(s1, s2, s3, idx1, idx2 + 1, idx3 + 1, cache);
		
		/*
		 * Commenting out above and using this code instead saves so much time!!!!
		 * Once the solution is found, return true. Don't bother computing the second 'if'
		 * s1="eef"
		 * s2="eeg"
		 * s3="eeegef"
		 * Above code: 20 steps
		 * Below code: 10 steps
		 */
		if(idx1 < s1.length() 
				&& c3 == c1		// Must check this condition to advance the correct idx 
				&& detectShuffle2_rec(s1, s2, s3, idx1+1, idx2, idx3+1, cache))
			return true;
		if(idx2 < s2.length() 	// Backtracks, if the previous one did not find a valid execution path
				&& c3 == c2		// Must check this condition to advance the correct idx
				&& detectShuffle2_rec(s1, s2, s3, idx1, idx2+1, idx3+1, cache))
			return true;

		return false;
	}
	
	/*
	 * Does not work for duplicate characters in both strings placed at specific locations that 
	 * can be evaluated incorrectly.
	 */
	public static boolean detectShuffle1(String s1, String s2, String s3, int idx1, int idx2, int idx3) {
		if(idx3 == s3.length())
			return idx1 == s1.length() && idx2 == s2.length();
		
		if((idx1 < s1.length()) && s3.charAt(idx3) == s1.charAt(idx1))
			idx1++;
		else if((idx2 < s2.length()) && s3.charAt(idx3) == s2.charAt(idx2))
			idx2++;
		else
			return false;
		idx3++;

		return detectShuffle1(s1, s2, s3, idx1, idx2, idx3);
	}
	
}
