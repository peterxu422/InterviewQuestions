/*
 * Find out if binomial coefficient C(n,k) is divisible by the given positive integer 
 * number d.

Input (n) integer :
1<=n<=1000

Input (k) integer :
1<=k<=n

Input (d) integer :
2<=d<=1000000

Output integer :
1 if C(n,k) is divisible by d, 0 otherwise
 */

public class BinCoeffDivisible {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * Test: C(200,100, 38022) should be 0
	 */
	public static int combinatoricFactor2(int n, int k, int d) {
		int t = 1;
	    int b = 1;
	  	int t2 = n;
	  	int b1 = 1;
	  	int t1,b2;
	  
	  if( k < (n-k) ) {
	   	t1 = n - k + 1;
	    b2 = k;
	  } else {
	    t1 = k + 1;
	    b2 = n - k;
	  }
	  
	  
	  for(int i=0; i < (t2- t1 + 1); i++) {
	   	t *= (t1 + i);
	    b *= (b1 + i);
	    if(t % b == 0) {
	      t /= b;
	      b = 1;
	    }
	  }
	  
	  return ( t % (b*d) == 0) ? 1 : 0;
	}


}
