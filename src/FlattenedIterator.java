/*
 * Google phone screen 4/1
 * 
 * FlattenedIterator class implements the Iterator interface
	The class contains private List<Iterator<T>> iteratorList.

	Implement 2 methods in the class: boolean hasNext() and T next()

	iteratorList = [ [1, 2, 3, 4, 9, 10], [5, 6, 7, 8] ] -> 
	next() will return the following order: 1, 5, 2, 6, 3, 7, 4, 8, 9
 */

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class FlattenedIterator<T> implements Iterator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Iterator<Integer>> list = new ArrayList<Iterator<Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 9, 10));
		ArrayList<Integer> b = new ArrayList<Integer>(Arrays.asList(5, 6, 7, 8));
		ArrayList<Integer> c = new ArrayList<Integer>(Arrays.asList(3, 9, 15, 23));
		list.add(a.iterator());
		list.add(b.iterator());
		list.add(c.iterator());
		FlattenedIterator fit = new FlattenedIterator(list);
		
		while(fit.hasNext())
			System.out.print(fit.next() + " ");
	}
	
	private List<Iterator<T>> iteratorList;
	private int itIdx;
	private Iterator it;
	
	public FlattenedIterator(List<Iterator<T>> list) {
		iteratorList = list;
		itIdx = 0;
		it = iteratorList.get(itIdx);
	}
	
	@Override
	public boolean hasNext() {
		for(Iterator<T> tmp : iteratorList) {
			if(tmp.hasNext())
				return true;
		}
		return false;
	}
	
	@Override
	public T next() {
		if(!hasNext())
			return null;
		
		T object = null;; 
		try {
			object = (T) it.next();
		} catch (NoSuchElementException nsee) {
			
		}
		
		if(object == null) {
			int firstItIdx = itIdx;
			boolean allNull = true;
			itIdx = (itIdx + 1) % iteratorList.size();
			
			while(itIdx != firstItIdx) {
				it = iteratorList.get(itIdx);
				if(it.hasNext()) {
					allNull = false;
					object = (T) it.next();
					return object;
				}
				itIdx = (itIdx + 1) % iteratorList.size();	
			}
			
			if(allNull)
				return null;
		}
		
		itIdx = (itIdx + 1) % iteratorList.size();
		it = iteratorList.get(itIdx);
		
		return object;
	}
	
	@Override
	public void remove() {
		if(!hasNext())
			return;
		it.remove();
		itIdx = (itIdx + 1) % iteratorList.size();
	}
}
