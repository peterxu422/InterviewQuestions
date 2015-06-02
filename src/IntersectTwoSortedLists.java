import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class IntersectTwoSortedLists {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> l1 = new ArrayList<Integer>();
		List<Integer> l2 = new ArrayList<Integer>();
		
		l1.add(1);
		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(6);
		
		Iterator<Integer> it = l1.iterator();
		while(it.hasNext()) {
			System.out.println(it.next() + ":" + it.hasNext());
		}
		
		l2.add(2);
		l2.add(4);
		l2.add(6);
		l2.add(8);
		
		List<Integer> l3 = intersect1(l1, l2);
		for(int i : l3)
			System.out.print(i + "->");
		System.out.println();
	}
	
	// Wrong result using Iterators
	public static List<Integer> intersect1(List<Integer> l1, List<Integer> l2) {
		if(l1 == null || l2 == null)
			return null;
		
		List<Integer> l3 = new ArrayList<Integer>();
		Iterator<Integer> it1 = l1.iterator();
		Iterator<Integer> it2 = l2.iterator();
		int cur1 = it1.next();
		int cur2 = it2.next();
		
		while(it1.hasNext() && it2.hasNext()) {
			while(it1.hasNext() && cur1 < cur2)
				cur1 = it1.next();
			while(it2.hasNext() && cur2 < cur1)
				cur2 = it2.next();
			
			if(cur1 == cur2) {
				l3.add(cur1);
				if(it1.hasNext())
					cur1 = it1.next();
			}
		}
		
		return l3;
	}

}
