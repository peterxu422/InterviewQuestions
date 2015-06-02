
public class RemoveRepeatedSpaces {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(removeRepeatedSpacesV2("one two  three    four"));
	}

	// Wrong
	public static String removeRepeatedSpacesV2(String s) {
		if(s == null)
			return null;
		
		char[] sArr = s.toCharArray();
		int i=0;
		for(int j=0; j < sArr.length; j++) {
			if(sArr[j] == ' ') {
				sArr[i++] = sArr[j++];
				while(j < sArr.length && sArr[j] == ' ')
					j++;
			}
			
			sArr[i++] = sArr[j];
		}
		
		if(i < sArr.length)
			sArr[i] = '\0';
		
		return new String(sArr);
	}
	
	// Wrong
	public static String removeRepeatedSpacesV1(String s) {
		if(s == null)
			return null;
		
		char[] sArr = s.toCharArray();
		for(int i=0; i < sArr.length; i++) {
			if(sArr[i] == ' ') {
				i++;
				while(i < sArr.length && sArr[i] == ' ') {
					sArr[i] = '\0';
					i++;
				}
			}
		}
		
		return new String(sArr);
	}
}
