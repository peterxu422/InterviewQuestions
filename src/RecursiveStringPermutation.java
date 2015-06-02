import java.util.ArrayList;
import java.util.List;

/*
 * https://www.interviewcake.com/question/recursive-string-permutations?utm_source=weekly_email
 * 
 * Write a recursive function for generating all permutations of an input string. Return them as 
 * an array.
	
	Don't worry about duplicates—assume every character is unique.

	Don't worry about time or space complexity—if we wanted efficiency we'd write an iterative 
	version.

	Your function can have loops—it just needs to also be recursive
 */

public class RecursiveStringPermutation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Test1
		String s = "abcd";
		String[] perms = getPermutations(s);
		System.out.printf("Method1. " +
						  "Test String: %s. " + 
						  "Number of permutations: %d%n", s, perms.length);
		for(String str : perms)
			System.out.println(str);
		
		System.out.println();
		perms = getPermutations2(s);
		System.out.printf("Method2. " +
						  "Test String: %s. " + 
						  "Number of permutations: %d%n", s, perms.length);
		for(String str : perms)
			System.out.println(str);
		
		// Test2
		System.out.println();
		s = "abcde";
		perms = getPermutations(s);
		System.out.printf("Method1. " +
						  "Test String: %s. " + 
						  "Number of permutations: %d%n", s, perms.length);
		for(String str : perms)
			System.out.println(str);
		
		System.out.println();
		perms = getPermutations2(s);
		System.out.printf("Method2. " +
						  "Test String: %s. " + 
						  "Number of permutations: %d%n", s, perms.length);
		for(String str : perms)
			System.out.println(str);
	}
	
	
	public static String[] getPermutations(String s) {
		if(s == null)
			return null;
		
		List<String> perms = new ArrayList<String>();
		recPermutations(perms, "", s);
		String[] permsArray = new String[perms.size()];
		return perms.toArray(permsArray);
	}
	
	/*
	 * Method1
	 * Factorial approach.
	 * Do all 4 possible letters in first position, then remaining in next and so on.
	 * 
	 * Time: O(n!)
	 * Space: O(n!)
	 */
	public static void recPermutations(List<String> perms, String s, String remainChar) {
		if(s == null)
			return;
		
		if(remainChar.isEmpty()) {
			perms.add(s);
			return;
		}
		
		char[] nextChars = remainChar.toCharArray();
		String tmp = "";
		String tmpRemainChar = "";
		for(char c : nextChars) {
			tmp = s + c;
			tmpRemainChar = remainChar.replaceAll("" + c, "");
			recPermutations(perms, tmp, tmpRemainChar);
		}
	}
	
	/*
	 * Method2
	 * Build up from base case.
	 * Insert the next character to consider into each possible space
	 */
	public static String[] getPermutations2(String s) {
		if(s == null)
			return null;
		
		List<String> perms = recPermutations2(s);
		String[] permsArray = new String[perms.size()];
		return perms.toArray(permsArray);
	}
	
	public static List<String> recPermutations2(String s) {
		if(s == null)
			return null;
		
		List<String> perms = new ArrayList<String>();
		if(s.length() == 1) {
			perms.add(s);
			return perms;
		}
		
		String subStr = s.substring(1);
		char c = s.charAt(0);
		List<String> subPerms = recPermutations2(subStr);
		for(String str : subPerms) {
			String tmp = "";
			for(int i=0; i < str.length()+1; i++) { 	// Must add +1 because otherwise you don't get all the possible placements
				tmp = str.substring(0,i) + c + str.substring(i);
				perms.add(tmp);
			}
			//tmp = str + c;
			//perms.add(tmp);
		}
		
		return perms;
	}
}
