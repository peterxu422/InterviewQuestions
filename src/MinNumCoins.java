/*
 * Given a list of coins and the a total amount. Calculate the minimum number of coins 
 * can be used to reach the total amount.
 */

public class MinNumCoins {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getMinNumCoins(25) == 1);
		System.out.println(getMinNumCoins(1) == 1);
		System.out.println(getMinNumCoins(125) == 5);
		System.out.println(getMinNumCoins(130) == 6);
	}

	public static final int QTR = 25;
	public static final int DIME = 10;
	public static final int NICK = 5;
	public static final int PEN = 1;

	public static int getMinNumCoins(int amt) {
		return getMinNumCoinsRec(amt, -1);
	}

	public static int getMinNumCoinsRec(int amt, int prevCoin) {
		int coinVal;
		switch(prevCoin) {
			case -1:
				coinVal = QTR;
				break;
			case QTR:
				coinVal = DIME;
				break;
			case DIME:
				coinVal = NICK;
				break;
			case NICK:
				coinVal = PEN;
				break;
			default:
				return 0;
		}

		if(amt == 0)
			return 0;

		int numCoins = amt / coinVal;
		int remainAmt = amt % coinVal;
		return numCoins + getMinNumCoinsRec(remainAmt, coinVal);
	}
}
