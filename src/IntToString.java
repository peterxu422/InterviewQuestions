
public class IntToString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(IntToStr(12345).equals("12345"));
		System.out.println(IntToStr(-12345).equals("-12345"));
		System.out.println(IntToStr(0).equals("0"));
	}
	
	public static String IntToStr(int i) {
		if(i == 0)
			return "0";
		
		boolean neg = i < 0;
		int num = Math.abs(i);
		StringBuilder sb = new StringBuilder();
		
		while(num > 0) {
			int digit = num % 10;
			sb.append((char) ('0' + digit));
			num /= 10;
		}
		
		if(neg)
			sb.append('-');
		
		return sb.reverse().toString();
	}

}
