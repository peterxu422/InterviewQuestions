/*
 * Given a source word, target word and an English dictionary, transform the source word to 
 * target by changing/adding/removing 1 character at a time, while all intermediate words being 
 * valid English words. Return the transformation chain which has the smallest number of 
 * intermediate words.
 * 
 * http://www.ardendertat.com/2011/10/17/programming-interview-questions-8-transform-word/
 */

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TransformWord {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String, String> dict = new Hashtable<String, String>();
		dict.put("cat", "");
		dict.put("bat", "");
		dict.put("bet", "");
		dict.put("bed", "");
		dict.put("at", "");
		dict.put("ad", "");
		dict.put("ed", "");

		Hashtable<String, List<String>> graph = constructGraph(dict);
		System.out.println(graph);
		System.out.println(transformWord("cat", "bed", graph, dict));
		
		// Example graph where duplicates can appear in the lists.
//		dict.put("eeet", "");
//		dict.put("eet", "");
//		dict.put("eat", "");
//		dict.put("et", "");
	}
	
	/*
	 * Use BFS to find the shortest path from start to end.
	 * 
	 * Tricky part is storing the path. You can't just store one path, because multiple paths
	 * are spawned at each node. You can build up and store the path up to a node you're visiting.
	 * Need to store this for all nodes. Then when you get to the end, simply pick the path that
	 * was built up to end.
	 * http://stackoverflow.com/questions/5463505/returning-only-the-vertices-in-the-actual-shortest-path
	 * 
	 * Time: O(V)
	 * Space: O(V)
	 */
	public static String transformWord(String start, 
									  String end, 
									  Hashtable<String, List<String>> graph, 
									  Hashtable<String,String> dict) {
		if(!(dict.containsKey(start) || dict.containsKey(end)))
			return null;
		
		Hashtable<String, String> pathTo = new Hashtable<String, String>();
		Hashtable<String, Boolean> visited = new Hashtable<String, Boolean>();
		Queue<String> queue = new LinkedList<String>();
		queue.add(start);
		visited.put(start, true);
		pathTo.put(start, start);
		
		// BFS on start to find end.
		while(!queue.isEmpty()) {
			String top = queue.poll();
			List<String> nbrs = graph.get(top);
			for(String n : nbrs) {
				if(visited.get(n) == null || !visited.get(n)) {
					visited.put(n, true);
					String path = pathTo.get(top);
					path += "->" + n;
					pathTo.put(n, path);
					
					if(n.equals(end))
						return path;
					else
						queue.add(n);
				}
			}
		}
		
		return pathTo.get(end);
	}
	
	/*
	 * Construct the graph by taking each word in the dictionary and applying all possible
	 * operations on each word (add, remove, change letter at each index of the word).
	 * Check if the new resulting word is in the dictionary. If it is, add an edge.
	 */
	public static Hashtable<String, List<String>> constructGraph(Hashtable<String, String> dict) {
		Hashtable<String, List<String>> graph = new Hashtable<String, List<String>>();
		char[] letters = Helper.alphabet();
		
		Set<String> words = dict.keySet();
		Iterator<String> it = words.iterator();
		while(it.hasNext()) {
			String word = it.next();
			
			for(int i=0; i < word.length(); i++) {
				
				//Remove
				String remove = word.substring(0,i) + word.substring(i+1);
				addNeighbor(word, remove, dict, graph);
				
				//Change
				for(char c : letters) {
					String change = word.substring(0,i) + c + word.substring(i+1);
					addNeighbor(word, change, dict, graph);
				}
				
				//Add
				for(char c : letters) {
					String add = word.substring(0,i) + c + word.substring(i);
					addNeighbor(word, add, dict, graph);
				}
			}
		}
		
		return graph;
	}
	
	private static void addNeighbor(String word, 
									String nbrWord, 
									Hashtable<String,String> dict, 
									Hashtable<String, List<String>> graph) {
		
		if(dict.containsKey(nbrWord) && !word.equals(nbrWord)) {
			
			List<String> neighbors = graph.get(word);
			if(neighbors == null) {
				neighbors = new ArrayList<String>();
				graph.put(word, neighbors);
			}
			
			// Q: Needs one more check to see whether neighbors contains nbrWord already?
			if(!neighbors.contains(nbrWord))
				neighbors.add(nbrWord);
		}
	}
}
