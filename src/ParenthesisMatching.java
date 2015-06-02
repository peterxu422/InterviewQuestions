/*
 * I like parentheticals (a lot).
"Sometimes (when I nest them (my parentheticals) too much (like this (and this))) 
they get confusing."

Write a function that, given a sentence like the one above, along with the position of an 
opening parenthesis, finds the corresponding closing parenthesis.

Example: if the example string above is input with the number 10 (position of the first parenthesis), 
the output should be 79 (position of the last parenthesis).

https://www.interviewcake.com/question/matching-parens?utm_source=weekly_email
 */

import java.util.Stack;

public class ParenthesisMatching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get confusing.";
		System.out.printf("method1) %d, method2) %d%n", 
							parenthesisIdx(s, 10), 
							parenthesisIdx2(s,10));
	}
	
	
	/*
	 * Time: O(n)
	 * Space: O(1)
	 */
	public static int parenthesisIdx2(String s, int open) {
		if(s == null || open >= s.length())
			return -1;

		int openParenCnt = 0;
		int i = open;

		if(s.charAt(i) == '(')
			openParenCnt++;
		else
			return -1;

		
		while(openParenCnt > 0 && i < s.length()-1) {
			i++;	// Where i++ goes matters. Can't go after conditions.
			if(s.charAt(i) == '(')
				openParenCnt++;
			else if(s.charAt(i) == ')')
				openParenCnt--;
		}

		return (openParenCnt > 0) ? -1 : i;
	}
	
	/*
	 * Time: O(n)
	 * Space: O(n)
	 */
	public static int parenthesisIdx(String s, int open) {
		if(s == null || open >= s.length())
			return -1;

		Stack<Character> stk = new Stack<Character>();
		int i = open;
		
		if(s.charAt(i) == '(')
			stk.push(s.charAt(i));
		else
			return -1;
		
		
		while(!stk.isEmpty() && i < s.length()-1) {
			i++;
			if(s.charAt(i) == '(')
				stk.push(s.charAt(i));
			else if(s.charAt(i) == ')')
				stk.pop();
		}
		
		return (stk.isEmpty()) ? i : -1;
	}

}
