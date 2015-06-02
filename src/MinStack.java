import java.util.Stack;

/*
 * MinStack:
 * int pop()
 * void push()
 * int getMin() - don't pop off
 * 
 * all in O(1)
 * 
 * CareerBuilder Interview 2 - Candidate Data Processing Team.
 */

public class MinStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		stk = new Stack<Integer>();
		minStk = new Stack<Integer>();
		
		push(100);
		System.out.println(getMin() == 100);
		push(100);
		System.out.println(getMin() == 100);
		push(300);
		System.out.println(getMin() == 100);
		push(200);
		System.out.println(getMin() == 100);
		push(50);
		System.out.println(getMin() == 50);
		push(300);
		System.out.println(getMin() == 50);
		push(50);
		System.out.println(getMin() == 50);
		pop();
		System.out.println(getMin() == 50);
	}
	
	static Stack<Integer> stk;
	static Stack<Integer> minStk;
	
	public static int pop() {
		if(!minStk.isEmpty() || minStk.peek() == stk.peek())
			minStk.pop();
		return stk.pop();
	}
	
	public static void push(int i) {
		if(minStk.isEmpty() || i <= minStk.peek())
			minStk.push(i);
		stk.push(i);
	}
	
	public static int getMin() {
		if(minStk.isEmpty())
			return -1;
		return minStk.peek();
	}

}
