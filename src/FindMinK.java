import java.util.PriorityQueue;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

/* http://www.ardendertat.com/2011/05/30/my-favorite-interview-question/
 * In an integer array with N elements (N is large), find the minimum k elements (k << N).
 */
public class FindMinK {

     public static void main(String []args){
         
         int[] a = Helper.bigList(100, 100);
         PriorityQueue<Integer> pq = findMinK(a, 20);
         
         int[] b = a.clone();
         Arrays.sort(b);
         Helper.printArray(b);
         ArrayList<Integer> result = new ArrayList<Integer>(pq);
         Collections.sort(result);
         System.out.println(result.toString());
     }
     
     /*
        Time: O(n log k)
        Space: O(k)
     */
     public static PriorityQueue<Integer> findMinK(int[] a, int k) {
         if(a == null) return null;
        
        // Make a max-heap by setting Collections.reverseOrder() which returns a comparator
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, Collections.reverseOrder()); 
        int val;
        
        for(int i=0; i < a.length; i++) {
            val = a[i];
            
            if(pq.peek() == null)
                pq.add(val);
            else if(val < pq.peek()) {
                if(pq.size() < k)
                    pq.add(val);
                else {
                    pq.poll();
                    pq.add(val);
                }
            }
        }
        
        return pq;
     }
     
     public static void findMinK2(int[] a, int k) {
    	 if(a == null) return;
    	 
    	 for(int i=0; i < k; i++)
    		 System.out.println(a[i] + " ");
    	 System.out.println();
     }
     
     /*
      * Time: O(n + k log n)
      * Space: O(1)
      */
     public static void heapify(int[] a) {
 		if(a == null) return;
 		
 		int size = a.length;
 	
 		for(int i=size/4; i >= 0; i=(i-1)/2) {
 			int nextLvlSt = 2*i + 1;
 			for(int j=i; j < nextLvlSt; j++){
 				int tmp = a[j];
 				int child;
 				int k = j;
 	
 				// percolate down
 				for(; (2*k + 1) < size; k=child) {
 					child = 2*k + 1;
 					if(child != size && a[child] > a[child+1])
 						child++;
 					
 					if(tmp > a[child])
 						a[k] = a[child];
 					else
 						break;
 				}
 				a[k] = tmp;
 			}
 		
 			if(i == 0) break;
 		}
 	}
     
     

}
