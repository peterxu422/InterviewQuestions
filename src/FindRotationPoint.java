/*
 * https://www.interviewcake.com/question/find-rotation-point
 * 
 * I want to learn some big words so people think I'm smart.
I opened up a dictionary to a page in the middle and started flipping through, looking for words 
I didn't know. I put each word I didn't know at increasing indices in a huge array I created in 
memory. When I reached the end of the dictionary, I started from the beginning and did the same 
thing until I reached the page I started at.

Now I have an array of words that are mostly alphabetical, except they start somewhere in the 
middle of the alphabet, reach the end, and then start from the beginning of the alphabet. In other 
words, this is an alphabetically ordered array that has been "rotated." For example:

words = [
    'ptolemaic',
    'retrograde',
    'supplant',
    'undulate',
    'xenoepist',
    'asymptote', # <-- rotates here!
    'babka',
    'banoffee',
    'engender',
    'karpatka',
    'othellolagkage',
    ]
Write a function for finding the index of the "rotation point," which is where I started 
working from the beginning of the dictionary. This array is huge (there are lots of words I 
don't know) so we want to be efficient here.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FindRotationPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = {"ptolemaic",
				"pzieoelectirc",
			    "retrograde",
			    "supplant",
			    "undulate",
			    "xenoepist",
			    "asymptote",
			    "babka",
			    "banoffee",
			    "engender",
			    "karpatka",
			    "othellolagkage",
			    "peterxu"};
		System.out.println(findRotationPt(words)); // 5
		System.out.println(findRotationPtV2(words)); // 5
		
		words = readWords();
		System.out.println(findRotationPt(words)); // 152116
		System.out.println(findRotationPtV2(words));
	}
	
	/*
	 * Method1:
	 * 	1. Clone array. Sort big array
	 * 	2. Get first element
	 * 	3. Loop original array and find matching first element
	 * 	4. Return index.
	 * 
	 * Time: O(n log n)
	 */

	/*
	 * Method2:
	 * 	1. While(next word > current word)
	 * 		current word = next word
	 * 		next word = next.next word
	 * 	2. return index
	 * 
	 * 	Time: O(n)
	 */
	public static int findRotationPtV2(String[] arr) {
		if(arr == null)
			return -1;
		System.out.println("Entering findRotationPtV2()...");
		long st = System.nanoTime();
		long end;
		for(int i=1; i < arr.length-1; i++) {
			if(arr[i].compareTo(arr[i-1]) < 0 && arr[i].compareTo(arr[i+1]) < 0) {
				end = System.nanoTime();
				System.out.printf("Exiting findRotationPtV2().");
				printTS(st, end);
				return i;
			}
		}
		
		end = System.nanoTime();
		System.out.printf("Exiting findRotationPtV2().");
		printTS(st, end);
		return -1;
	}
	
	/*
	 * Method3:
	 * 	Binary Search Variation
	 * 	
	 * 	if(a[mid] > a[left]) // Normal
	 * 		binSearch(right), mid inclusive
	 *  else if(a[mid] < a[right])
	 *  	binSearch(left), mid inclusive
	 *  
	 *  if(a[mid] < a[left]) // rotation pt somewhere between
	 *  	binSearch(left, mid inclusive)
	 *  else if(a[mid] > a[right])
	 *  	binSearch(right, mid inclusive)
	 *  
	 *  Time: O(log n)
	 *  Space: O(1)
	 *  
	 *  This is a great example of the difference between "knowing" something and knowing 
	 *  something. You might have heard of binary search before, but that doesn't help you 
	 *  much unless you've learned the lessons binary search teaches us. 

Binary search teaches us that when an array is sorted or mostly sorted:
The value at a given index tells us a lot about what's to the left and what's to the right.
We don't have to look at every item in the array. By inspecting the middle item, we can 
"rule out" half the array.
We can use this approach over and over, cutting the problem in half until we have the answer. This is sometimes called "divide and conquer."
	 */
	public static int findRotationPt(String[] arr) {
		if(arr == null)
			return -1;
		
		System.out.println("Entering findRotationPt()...");
		long st = System.nanoTime();
		long end;
		
		int low = 0;
		int high = arr.length-1;
		int mid;
		
		while(low <= high) {
			mid = (low + high) / 2;
			//System.out.println(mid);
			if(mid > 0 && mid < arr.length-1 && 
					arr[mid].compareTo(arr[mid-1]) < 0 &&
					arr[mid].compareTo(arr[mid+1]) < 0) {
				end = System.nanoTime();
				System.out.println("Exiting findRotationPt()");
				printTS(st, end);
				return mid;
			}
			else if(arr[mid].compareTo(arr[low]) > 0 ||
					arr[mid].compareTo(arr[high]) > 0) {
				low = mid;
			}
			else if(arr[mid].compareTo(arr[high]) < 0 ||
					arr[mid].compareTo(arr[low]) < 0) {
				high = mid;
			}
		}
		
		end = System.nanoTime();
		System.out.println("Exiting findRotationPt()");
		printTS(st, end);
		return -1;
	}
	
	// Helpers
	public static String[] readWords() {
		System.out.println("Reading words...");
		List<String> words = new ArrayList<String>();
		File file = new File("./data/wordsRotated.txt");
		LineIterator it = null;
		
		try {
			it = FileUtils.lineIterator(file);
			while(it.hasNext()) {
				String line = it.nextLine().toLowerCase();
				words.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(it != null)
				LineIterator.closeQuietly(it);
		}
		
		System.out.println("Finished Reading.");
		String[] wordsArr = new String[words.size()];
		return words.toArray(wordsArr);
	}
	
	public static void printTS(long st, long end) {
		System.out.printf("Time elapsed: %d%n", end - st);
	}
	
}
