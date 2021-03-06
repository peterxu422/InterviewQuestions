import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Helper {

    /* Helpers */
    
    public static void printArray(int[] a) {
        for(int i : a)
           System.out.print(i + " ");
       System.out.println();
    }
    
    public static int[] bigList(int n, int range) {
        int[] array = new int[n];
        for(int i=0; i < array.length; i++)
            array[i] = (int) (Math.random() * range) + 1;
        
        return array;
    }
    
    public static void printMatrix(int[][] matrix) {
    	for(int r=0; r < matrix.length; r++) {
    		for(int c=0; c < matrix[0].length; c++) {
    			System.out.printf("%7d", matrix[r][c]);
    		}
    		System.out.println();
    	}
    }
    
    public static char[] alphabet() {
    	return "abcdefghijklmnopqrstuvwxyz".toCharArray();
    }
    
    /* Binary Tree helpers */
    
    protected static class BinaryTreeNode<T extends Comparable<?>> {
		BinaryTreeNode<T> left;
		BinaryTreeNode<T> right;
		T data;
		
		protected BinaryTreeNode(BinaryTreeNode<T> left, BinaryTreeNode<T> right, T data) {
			this.left = left;
			this.right = right;
			this.data = data;
		}
	}
	
	protected static class BTreePrinter {

	    public static <T extends Comparable<?>> void printNode(BinaryTreeNode<T> root) {
	        int maxLevel = BTreePrinter.maxLevel(root);

	        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
	    }

	    private static <T extends Comparable<?>> void printNodeInternal(List<BinaryTreeNode<T>> nodes, int level, int maxLevel) {
	        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
	            return;

	        int floor = maxLevel - level;
	        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
	        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
	        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

	        BTreePrinter.printWhitespaces(firstSpaces);

	        List<BinaryTreeNode<T>> newNodes = new ArrayList<BinaryTreeNode<T>>();
	        for (BinaryTreeNode<T> node : nodes) {
	            if (node != null) {
	                System.out.print(node.data);
	                newNodes.add(node.left);
	                newNodes.add(node.right);
	            } else {
	                newNodes.add(null);
	                newNodes.add(null);
	                System.out.print(" ");
	            }

	            BTreePrinter.printWhitespaces(betweenSpaces);
	        }
	        System.out.println("");

	        for (int i = 1; i <= endgeLines; i++) {
	            for (int j = 0; j < nodes.size(); j++) {
	                BTreePrinter.printWhitespaces(firstSpaces - i);
	                if (nodes.get(j) == null) {
	                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
	                    continue;
	                }

	                if (nodes.get(j).left != null)
	                    System.out.print("/");
	                else
	                    BTreePrinter.printWhitespaces(1);

	                BTreePrinter.printWhitespaces(i + i - 1);

	                if (nodes.get(j).right != null)
	                    System.out.print("\\");
	                else
	                    BTreePrinter.printWhitespaces(1);

	                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
	            }

	            System.out.println("");
	        }

	        printNodeInternal(newNodes, level + 1, maxLevel);
	    }

	    private static void printWhitespaces(int count) {
	        for (int i = 0; i < count; i++)
	            System.out.print(" ");
	    }

	    private static <T extends Comparable<?>> int maxLevel(BinaryTreeNode<T> node) {
	        if (node == null)
	            return 0;

	        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
	    }

	    private static <T> boolean isAllElementsNull(List<T> list) {
	        for (Object object : list) {
	            if (object != null)
	                return false;
	        }

	        return true;
	    }

	}
}
