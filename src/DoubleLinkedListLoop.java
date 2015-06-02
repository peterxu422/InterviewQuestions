/*
 * Write a method to verify whether a doubly linked list has a loop
 * 
 * CareerBuilder On-site Interview with Recruitment Edge Team
 */

public class DoubleLinkedListLoop {

	private class Node {
		Node next;
		Node prev;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean isLoop(Node head) {
		if(head == null)
			return false;
		
		Node prev = head;
		Node cur = head.next;
		
		while(cur != null) {
			if(cur.prev != prev || cur == prev)
				return true;
			prev = cur;
			cur = cur.next;
		}
		
		return false;
	}
}
