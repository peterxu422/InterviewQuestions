/*
 * Given a binary tree, check whether it’s a binary search tree or not. 
 * 
 * A special note about these types of problems when interviewing at Microsoft: while 
 * algorithmically your solution should be recursive, your implementation should be prefixed 
 * by: "the problem with the recursive implementation is that it's only good when you know the 
 * depth, otherwise you can easily blow the stack". This shows you understand the practical 
 * difference between writing code in a classroom and in a production environment.
 * 
 * If the interviewer then says: "let's say you don't need to worry about that", you proceed with 
 * your solution, but twice in my own experience the problem was precisely about whether I would 
 * just rush to implement the obvious and well known book solution, or stop to consider the real 
 * world implications.
 * 
 * http://www.ardendertat.com/2011/10/10/programming-interview-questions-7-binary-search-tree-check/
 */
public class BinarySearchTreeCheck {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Helper.BinaryTreeNode<Integer> root;
		
		root = test1();
		Helper.BTreePrinter.printNode(root);
		System.out.printf("Method1:%b%n" +
						  "Method2:%b%n%n", isBST(root), isBST2(root));

		root = test2();
		Helper.BTreePrinter.printNode(root);
		System.out.printf("Method1:%b%n" +
				  		  "Method2:%b%n%n", isBST(root), isBST2(root));
		
		root = test3();
		Helper.BTreePrinter.printNode(root);
		System.out.printf("Method1:%b%n" +
				  		  "Method2:%b%n%n", isBST(root), isBST2(root));
		
		root = test4();
		Helper.BTreePrinter.printNode(root);
		System.out.printf("Method1:%b%n" +
				  		  "Method2:%b%n%n", isBST(root), isBST2(root));
		
		root = test5();
		Helper.BTreePrinter.printNode(root);
		System.out.printf("Method1:%b%n" +
				  		  "Method2:%b%n%n", isBST(root), isBST2(root));
	}
	
	/*
	 * Method1
	 * Recursively check each sub-tree. Pass in min and max value. 
	 * Check each node is within min and max range
	 * 
	 * if left, max = cur.data
	 * if right, min = cur.data
	 * 
	 * Time: O(n)
	 * Space: O(log n) stack space
	 */
	public static boolean isBST(Helper.BinaryTreeNode<Integer> root) {
		if(root == null)	
			return false;

		return isBST_rec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public static boolean isBST_rec(Helper.BinaryTreeNode<Integer> root, Integer min, Integer max) {
		if(root == null)
			return true;

		if(root.data < min || root.data > max)
			return false;

		return isBST_rec(root.left, min, root.data) && 
				isBST_rec(root.right, root.data, max);
	}
	
	/*
	 * Revisting basics.
	 * 	- Immutability
	 * 	- Pass by value, pass by reference
	 * 	- object lifetime
	 * Integers are immutable.
	 * 
	 * http://stackoverflow.com/questions/16061970/why-this-method-does-not-change-integer-value
	 */
	public static void m1() {
		Integer a = new Integer(2);
		m2(a);
		System.out.println(a);
	}
	
	/*
	 * Re-assigning value here creates a new instantiated Integer object with value 3.
	 * i, which is a copy reference, will point to this new instantiated object.
	 * When m2() is over, i disappears.
	 */
	public static void m2(Integer i) {
		System.out.println("m2: " + i.intValue());
		i = 3;
		System.out.println("m2: " + i.intValue());
	}
	
	/*
	 * Method2
	 */
	static int last;
	
	public static boolean isBST2(Helper.BinaryTreeNode<Integer> root) {
		if(root == null)
			return false;
		last = Integer.MIN_VALUE;
		return isBST2_rec(root);
	}
	
	/*
	 * Can't pass in Integer last as an object param. Integer is immutable, so setting on each
	 * recursive call doesn't work. Need to use static int.
	 * 
	 * Advantage: Don't need to pass in max and min values in range to update.
	 * 
	 * Time: O(n)
	 * Space: O(log n) stack space
	 */
	public static boolean isBST2_rec(Helper.BinaryTreeNode<Integer> root) {
		if(root == null)
			return true;
		
		if(!isBST2_rec(root.left))
			return false;
		
		if(last > root.data)
			return false;
		
		last = root.data.intValue();
		return isBST2_rec(root.right);
	}

	public static Helper.BinaryTreeNode<Integer> test1() {
		Helper.BinaryTreeNode<Integer> n1 = new Helper.BinaryTreeNode<Integer>(null, null, 1);
		Helper.BinaryTreeNode<Integer> n2 = new Helper.BinaryTreeNode<Integer>(null, null, 4);
		Helper.BinaryTreeNode<Integer> n3 = new Helper.BinaryTreeNode<Integer>(n1, n2, 2);
		Helper.BinaryTreeNode<Integer> n4 = new Helper.BinaryTreeNode<Integer>(null, null, 5);
		Helper.BinaryTreeNode<Integer> root = new Helper.BinaryTreeNode<Integer>(n3, n4, 3);
		
		return root;
	}
	
	public static Helper.BinaryTreeNode<Integer> test5() {
		Helper.BinaryTreeNode<Integer> n1 = new Helper.BinaryTreeNode<Integer>(null, null, 1);
		Helper.BinaryTreeNode<Integer> n2 = new Helper.BinaryTreeNode<Integer>(null, null, 6);
		Helper.BinaryTreeNode<Integer> n3 = new Helper.BinaryTreeNode<Integer>(n1, n2, 5);
		Helper.BinaryTreeNode<Integer> n4 = new Helper.BinaryTreeNode<Integer>(null, null, 3);
		Helper.BinaryTreeNode<Integer> n5 = new Helper.BinaryTreeNode<Integer>(n4, null, 8);
		Helper.BinaryTreeNode<Integer> root = new Helper.BinaryTreeNode<Integer>(n3, n5, 7);
		
		return root;
	}
	
	public static Helper.BinaryTreeNode<Integer> test2() {
		Helper.BinaryTreeNode<Integer> n1 = new Helper.BinaryTreeNode<Integer>(null, null, 4);
		Helper.BinaryTreeNode<Integer> n2 = new Helper.BinaryTreeNode<Integer>(null, null, 8);
		Helper.BinaryTreeNode<Integer> n3 = new Helper.BinaryTreeNode<Integer>(n1, n2, 6);
		Helper.BinaryTreeNode<Integer> n4 = new Helper.BinaryTreeNode<Integer>(null, null, 12);
		Helper.BinaryTreeNode<Integer> n5 = new Helper.BinaryTreeNode<Integer>(n4, null, 13);
		Helper.BinaryTreeNode<Integer> n6 = new Helper.BinaryTreeNode<Integer>(n3, n5, 10);
		
		Helper.BinaryTreeNode<Integer> n7 = new Helper.BinaryTreeNode<Integer>(null, null, 19);
		Helper.BinaryTreeNode<Integer> n8 = new Helper.BinaryTreeNode<Integer>(null, n7, 17);
		Helper.BinaryTreeNode<Integer> n9 = new Helper.BinaryTreeNode<Integer>(null, null, 21);
		Helper.BinaryTreeNode<Integer> n10 = new Helper.BinaryTreeNode<Integer>(null, null, 24);
		Helper.BinaryTreeNode<Integer> n11 = new Helper.BinaryTreeNode<Integer>(n9, n10, 23);
		Helper.BinaryTreeNode<Integer> n12 = new Helper.BinaryTreeNode<Integer>(n8, n11, 20);
		
		Helper.BinaryTreeNode<Integer> root = new Helper.BinaryTreeNode<Integer>(n6, n12, 15);
		
		return root;
	}
	
	public static Helper.BinaryTreeNode<Integer> test3() {
		Helper.BinaryTreeNode<Integer> n1 = new Helper.BinaryTreeNode<Integer>(null, null, 4);
		Helper.BinaryTreeNode<Integer> n2 = new Helper.BinaryTreeNode<Integer>(null, null, 8);
		Helper.BinaryTreeNode<Integer> root = new Helper.BinaryTreeNode<Integer>(n1, n2, 6);
		
		return root;
	}
	
	public static Helper.BinaryTreeNode<Integer> test4() {
		Helper.BinaryTreeNode<Integer> n1 = new Helper.BinaryTreeNode<Integer>(null, null, 1);
		Helper.BinaryTreeNode<Integer> n2 = new Helper.BinaryTreeNode<Integer>(null, null, 4);
		Helper.BinaryTreeNode<Integer> n3 = new Helper.BinaryTreeNode<Integer>(n1, n2, 5);
		Helper.BinaryTreeNode<Integer> n4 = new Helper.BinaryTreeNode<Integer>(null, null, 8);
		Helper.BinaryTreeNode<Integer> root = new Helper.BinaryTreeNode<Integer>(n3, n4, 7);
		
		return root;
	}
}
