
public class FizzBuzz {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int test = 30;
		System.out.println("Solution1");
		FizzBuzz1(test);
		
		System.out.println("\nSolution2");
		FizzBuzz2(test);
		
		System.out.println("\nSolution3");
		FizzBuzz3(test);
		//testGetFizzBuzz();
	}
	
	// Solution1 - Spaghetti
	public static void FizzBuzz1(int n) {
		for(int i=1; i <= n; i++) {
			if( (i%3 == 0) && (i%5 == 0) )
				System.out.println("FizzBuzz");
			else if(i%3 == 0)
				System.out.println("Fizz");
			else if(i%5 == 0)
				System.out.println("Buzz");
			else
				System.out.println(i);
		}
	}
	
	// Solution2 - Conceptually Separated
	public static void FizzBuzz2(int n) {
		String fb;
		for(int i=1; i <= n; i++) {
			fb = "";
			if(i%3 == 0)
				fb += "Fizz";
			if(i%5 == 0)
				fb += "Buzz";
			if(fb.isEmpty())
				fb += i;
			
			System.out.println(fb);
		}
	}

	// Solution3 - Syntactically Separated
	public static void FizzBuzz3(int n) {
		for(int i=1; i <= n; i++)
			System.out.println(getFizzBuzz(i));
	}
	
	public static String getFizzBuzz(int n) {
		String fb = "";
		if(n%3 == 0)
			fb += "Fizz";
		if(n%5 == 0)
			fb += "Buzz";
		if(fb.isEmpty())
			fb += n;
		
		return fb;
	}
	
	public static void testGetFizzBuzz() {
		System.out.println(getFizzBuzz(1).equals("1"));
		System.out.println(getFizzBuzz(3).equals("Fizz"));
		System.out.println(getFizzBuzz(5).equals("Buzz"));
		System.out.println(getFizzBuzz(7).equals("7"));
		System.out.println(getFizzBuzz(15).equals("FizzBuzz"));
	}
}
