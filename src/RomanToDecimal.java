import java.util.HashMap;
import java.util.Map;


public class RomanToDecimal {

	static Map<Character, Integer> symbols;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		symbols = new HashMap<Character, Integer>();
		symbols.put('I', 1);
		symbols.put('V', 5);
		symbols.put('X', 10);
		symbols.put('L', 50);
		symbols.put('C', 100);
		symbols.put('D', 500);
		symbols.put('N', 1000);
		
		System.out.println(roman2Decimal("I") == 1);
		System.out.println(roman2Decimal("II") == 2);
		System.out.println(roman2Decimal("IV") == 4);
		System.out.println(roman2Decimal("VII") == 7);
		System.out.println(roman2Decimal("XIV") == 14);
		System.out.println(roman2Decimal("XXIV") == 24);
		System.out.println(roman2Decimal("XLVII") == 47);
		System.out.println(roman2Decimal("LXIV") == 64);
		System.out.println(roman2Decimal("LXIX") == 69);
		System.out.println(roman2Decimal("LXXXIX") == 89);
		System.out.println(roman2Decimal("XCV") == 95);
		System.out.println(roman2Decimal("XCVIII") == 98);
		System.out.println(roman2Decimal("XCIX") == 99);
		System.out.println(roman2Decimal("D") == 500);
		System.out.println(roman2Decimal("DCCLIX") == 759);
		System.out.println(roman2Decimal("CDLXVII") == 467);
		
	}
	
	public static int roman2Decimal(String roman) {
		if(roman == null)
			return -1;
		
		int sum = 0;
		for(int i=0; i < roman.length(); i++) {
			char curChar = roman.charAt(i);
			if(!symbols.containsKey(curChar))
				return -1;
			int curVal = symbols.get(curChar);
			
			if(i < roman.length()-1) {
				char nextChar = roman.charAt(i+1);
				if(!symbols.containsKey(nextChar))
					return -1;
				int nextVal = symbols.get(nextChar);
				
				if(nextVal > curVal)
					curVal *= -1;
			}
			
			sum += curVal;
		}
		
		return sum;
	}

}
