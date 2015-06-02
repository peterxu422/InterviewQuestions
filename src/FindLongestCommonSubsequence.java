/*
 * Dynamic Programming
 * 
 */

public class FindLongestCommonSubsequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "ABAZDC";
		String t = "BACBAD";
		System.out.println(getLCS(s,t).equals("ABAD"));
	}
	
	public static String getLCS(String s, String t) {
		if(s == null || t == null)
			return null;
		
		int[][] LCS = new int[s.length()][t.length()];
		initLCS(LCS, s, t);
		printLCS(LCS);
		return findLCS(LCS, s, t);
	}
	
	public static void initLCS(int[][] LCS, String s, String t) {
		for(int i=0; i < s.length(); i++) {
			for(int j=0; j < t.length(); j++) {
				char sChar = s.charAt(i);
				char tChar = t.charAt(j);
				int val;
				if(sChar != tChar) {
					int prevSLCS = (i == 0) ? 0 : LCS[i-1][j];
					int prevTLCS = (j == 0) ? 0 : LCS[i][j-1];
					val = Math.max(prevSLCS, prevTLCS);
				} else {
					val = ((i-1) < 0 || (j-1) < 0) ? 0 : LCS[i-1][j-1];
					val++;
				}
				LCS[i][j] = val;
			}
		}
	}
	
	public static String findLCS(int[][] LCS, String s, String t) {
		int i = s.length() - 1;
		int j = t.length() - 1;
		StringBuilder sb = new StringBuilder();
		
		while(LCS[i][j] != 0) {
			char sChar = s.charAt(i);
			char tChar = t.charAt(j);
			if(sChar == tChar) {
				sb.append(sChar);
				i = (i == 0) ? 0 : --i;		// Can't use i--!!!! Stores i in the variable, not i--
				j = (j == 0) ? 0 : --j;
			} else {
				if(i > 0 && j <= 0)					i--;
				else if(i <= 0 && j > 0)			j--;
				else if(LCS[i-1][j] > LCS[i][j-1])	i--;
				else 								j--;
			}
			
		}
		
		return sb.reverse().toString();
	}
	
	// Helper
	public static void printLCS(int[][] LCS) {
		for(int i=0; i < LCS.length; i++) {
			for(int j=0; j < LCS[0].length; j++) {
				System.out.printf("%4d", LCS[i][j]);
			}
			System.out.println();
		}
	}
}
