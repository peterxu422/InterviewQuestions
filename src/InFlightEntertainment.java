/*
 * https://www.interviewcake.com/question/inflight-entertainment
 * 
 * Users on longer flights like to start a second movie right when their first one ends, 
 * but they complain that the plane usually lands before they can see the ending. So you're 
 * building a feature for choosing two movies whose total runtimes will equal the exact 
 * flight length.

Write a function that takes an integer flight_length (in minutes) and an array of integers
 movie_lengths (in minutes) and returns a boolean indicating whether there are two numbers 
 in movie_lengths whose sum equals flight_length.

When building your function:

Assume your users will watch exactly two movies
Don't make your users watch the same movie twice
Optimize for runtime over memory
 */


import java.util.HashMap;
import java.util.Map;

public class InFlightEntertainment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {60, 70, 80};
		System.out.println(getTwoMoviesV3(150, arr)); // true
		System.out.println(getTwoMoviesV3(140, arr)); // true
		System.out.println(getTwoMoviesV3(120, arr)); // false
		
		int[] arr2 = {60, 60, 70, 80};
		System.out.println(getTwoMoviesV3(120, arr2)); // true
		System.out.println(getTwoMoviesV3(140, arr2)); // true
		System.out.println(getTwoMoviesV3(160, arr2)); // false
		System.out.println(getTwoMoviesV3(110, arr2)); // false
		
		System.out.println();
		System.out.println(getTwoMoviesV3a(150, arr)); // true
		System.out.println(getTwoMoviesV3a(140, arr)); // true
		System.out.println(getTwoMoviesV3a(120, arr)); // false
		System.out.println(getTwoMoviesV3a(120, arr2)); // true
		System.out.println(getTwoMoviesV3a(140, arr2)); // true
		System.out.println(getTwoMoviesV3a(160, arr2)); // false
		System.out.println(getTwoMoviesV3a(110, arr2)); // false
	}
	
	/*
	 * Method1: 
	 * 	Double for loop
	 * 		check each number with every other number and compare sum
	 * 
	 * Time: O(n^2)
	 * Space: O(1)
	 */
	
	/*
	 * Method2:
	 * 	If given range of movie lengths
	 * 	Use movie lengths as indices in a count array. Tally up number of movies in each index
	 * 	Loop through count array and find your pair
	 * 
	 * 	Time: O(n)
	 * 	Space: O(1). Range should be a constant
	 */
	
	/*
	 * Method3:
	 * 	Same as Method2, but use a HashMap
	 * 
	 * Time: O(n)
	 * Space: O(1)
	 */
	public static boolean getTwoMoviesV3(int flightLength, int[] movieLengths) {
		if(movieLengths == null)
			return false;
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i < movieLengths.length; i++) {
			int m1 = movieLengths[i];
			if(map.containsKey(m1))	
				map.put(m1, map.get(m1) + 1);
			else
				map.put(m1, 1);
		}
		
		for(int i=0; i < movieLengths.length; i++) {
			int m1 = movieLengths[i];
			int m2 = flightLength - m1;
			
			if(map.containsKey(m2) 
					&& ((m1 == m2 && map.get(m2) > 1)
					|| (m1 != m2 && map.get(m2) > 0)))
				return true;
		}
		
		return false;
	}
	
	/*
	 * Method3a:
	 * 	Compact version of Method3
	 * 	Single loop
	 * 
	 */
	public static boolean getTwoMoviesV3a(int flightLength, int[] movieLengths) {
		if(movieLengths == null)
			return false;
		
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		for(int i=0; i < movieLengths.length; i++) {
			int m1 = movieLengths[i];
			int m2 = flightLength - m1;
			
			if(map.containsKey(m2))
				return true;
			
			map.put(m1, true);
		}
		
		return false;
	}

}
