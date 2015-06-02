/*
 *  Given a linkedlist of integers and an integer value, delete every node of the linkedlist 
 *  containing that value.
 *  
 *  http://www.ardendertat.com/2011/09/29/programming-interview-questions-5-linkedlist-remove-nodes/
 */

public class LinkedListRemoveNodes {
	
	static class LinkedListNode {
		int data;
		LinkedListNode next;
		
		public LinkedListNode(int data, LinkedListNode next) {
			this.data = data;
			this.next = next;
		}
		
		@Override
		public String toString() {
			LinkedListNode cur = this;
			String s = "";
			while(cur != null) {
				s += cur.data + "->";
				cur = cur.next;
			}
			s += "x";
			
			return s;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListNode h1 = null;
		LinkedListNode h2 = new LinkedListNode(1, null);
		LinkedListNode h3 = new LinkedListNode(5, null);
		LinkedListNode h4 = new LinkedListNode(1, new LinkedListNode(2, null));
		
		LinkedListNode h5 = new LinkedListNode(5, 
							new LinkedListNode(5, 
							new LinkedListNode(5, null) ));
		
		LinkedListNode h6 = new LinkedListNode(1, 
							new LinkedListNode(2, 
							new LinkedListNode(5, 
							new LinkedListNode(5,null) )));
		
		LinkedListNode h7 = new LinkedListNode(1, 
							new LinkedListNode(2, 
							new LinkedListNode(5, 
							new LinkedListNode(5,
							new LinkedListNode(3,null) ))));
		/*
		System.out.println("Orig: " + h1);
		System.out.println("Del: " + deleteListVal(h1, 5));
		
		System.out.println("Orig: " + h2);
		System.out.println("Del: " + deleteListVal(h2, 5));
		
		System.out.println("Orig: " + h3);
		System.out.println("Del: " + deleteListVal(h3, 5));
		
		System.out.println("Orig: " + h4);
		System.out.println("Del: " + deleteListVal(h4, 5));
		
		System.out.println("Orig: " + h5);
		System.out.println("Del: " + deleteListVal(h5, 5));
		
		System.out.println("Orig: " + h6);
		System.out.println("Del: " + deleteListVal(h6, 5));
		
		System.out.println("Orig: " + h7);
		System.out.println("Del: " + deleteListVal(h7, 5));
		*/
		System.out.println("\n\n**** Recursive Approach");
		System.out.println("Orig: " + h1);
		System.out.println("Del: " + recDeleteListVal(h1, 5));
		
		System.out.println("Orig: " + h2);
		System.out.println("Del: " + recDeleteListVal(h2, 5));
		
		System.out.println("Orig: " + h3);
		System.out.println("Del: " + recDeleteListVal(h3, 5));
		
		System.out.println("Orig: " + h4);
		System.out.println("Del: " + recDeleteListVal(h4, 5));
		
		System.out.println("Orig: " + h5);
		System.out.println("Del: " + recDeleteListVal(h5, 5));
		
		System.out.println("Orig: " + h6);
		System.out.println("Del: " + recDeleteListVal(h6, 5));
		
		System.out.println("Orig: " + h7);
		System.out.println("Del: " + recDeleteListVal(h7, 5));
	}
	
	/*
	 * Method:
	 * 	Keep a prev and cur pointer. Iterate through the lists and set the next pointers accordingly
	 * 	if there is a matching value.
	 * 	Can be tricky to implement bug-free.
	 * 	Time: O(n)
	 * 	Space: O(1)
	 */
	public static LinkedListNode deleteListVal(LinkedListNode list, int val) {
		if(list == null)
			return null;

		LinkedListNode prev = null;
		LinkedListNode head = list;
		LinkedListNode cur = list;

		while(cur != null) {
			if(cur.data == val) {
				if(prev != null)
					prev.next = cur.next;
				else
					head = cur.next;
				cur = cur.next;
			} else {
				prev = cur;
				cur = cur.next;
			}
		}

		return head;
	}
	
	/*
	 * Recursive deletion approach
	 * Time: O(n)
	 * Space: O(n) stack space
	 */
	public static LinkedListNode recDeleteListVal(LinkedListNode list, int val) {
		if(list == null)
			return null;
		
		LinkedListNode cur = list;
		if(cur.data == val)
			cur = recDeleteListVal(cur.next, val);
		else
			cur.next = recDeleteListVal(cur.next, val);
		
		return cur;
	}
}
