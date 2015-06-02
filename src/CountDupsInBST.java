import java.util.Stack;


public class CountDupsInBST {

	private static class BTNode {
		int data;
		BTNode left;
		BTNode right;
		
		public BTNode(int data) {
			left = null;
			right = null;
			this.data = data;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BTNode n1 = new BTNode(5);
		BTNode n2 = new BTNode(3);
		BTNode n3 = new BTNode(10);
		BTNode n4 = new BTNode(2);
		BTNode n5 = new BTNode(4);
		BTNode n6 = new BTNode(3);
		BTNode n7 = new BTNode(8);
		BTNode n8 = new BTNode(10);
		BTNode n9 = new BTNode(5);
		BTNode n10 = new BTNode(8);
		BTNode n11 = new BTNode(8);
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n5.left = n6;
		
		n3.left = n7;
		n3.right = n8;
		n7.left = n9;
		n7.right = n10;
		n10.right = n11;
		
		System.out.println(countDups(n1));
		countDupsV2(n1);
		System.out.println(count);
	}
	
	static Stack<Integer> order = new Stack<Integer>();
	static int count = 0;

	public static void countDupsV2(BTNode root) {
		if(root == null)
			return;

		countDupsV2(root.left);
		if(!order.isEmpty() && order.peek() == root.data) {
			count++;
		}
		order.push(root.data);
		countDupsV2(root.right);
	}
	
	public static int countDups(BTNode root) {
		if(root == null)
			return 0;

		int count = countDups(root.left);
		if(root.left != null && root.left.data == root.data)
			count++;
		if(root.right != null && root.right.data == root.data)
			count++;

		return count + countDups(root.right);
	}

}
