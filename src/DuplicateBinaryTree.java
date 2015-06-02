
public class DuplicateBinaryTree {

	private static class BTNode {
		int key;
		String val;
		BTNode left;
		BTNode right;
		
		public BTNode(int key, String val) {
			this.key = key;
			this.val = val;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTNode n1 = new BTNode(1, "a");
		BTNode n2 = new BTNode(2, "b");
		BTNode n3 = new BTNode(3, "c");
		BTNode n4 = new BTNode(4, "d");
		BTNode n5 = new BTNode(5, "e");
		BTNode n6 = new BTNode(6, "f");
		
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		
		BTNode m1 = duplicate(n1);
		System.out.println(m1 + " " + m1.key + ", " + m1.val);
	}
	
	public static BTNode duplicate(BTNode root) {
		if(root == null)
			return null;
		
		BTNode dupRoot = new BTNode(root.key, root.val);
		dupRoot.left = duplicate(root.left);
		dupRoot.right = duplicate(root.right);
		
		return dupRoot;
	}

}
