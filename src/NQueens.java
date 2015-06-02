import java.util.ArrayList;
import java.util.List;


public class NQueens {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getSolutions().size());
	}
	
	public static final int EMPTY_SPOT = -1;
	
	public static List<int[]> getSolutions() {
		int[] emptyBoard = new int[8];
		for(int i=0; i < emptyBoard.length; i++)
			emptyBoard[i] = EMPTY_SPOT;
		return getAllSolutions(8, emptyBoard);
	}
	
	public static List<int[]> getAllSolutions(int nQueensLeft, int[] currentPlacements) {
		List<int[]> solns = new ArrayList<int[]>();
		if(nQueensLeft == 0) {
			solns.add(currentPlacements);
			return solns;
		}
		
		int n = currentPlacements.length;
		for(int row=0; row < n; row++) {
			int col = n - nQueensLeft;
			if(isValid(currentPlacements, row, col)) {
				int[] placementsToTry = currentPlacements.clone();
				placementsToTry[col] = row;
				solns.addAll(getAllSolutions(nQueensLeft-1, placementsToTry));
			}
		}
		
		return solns;
	}
	
	public static boolean isValid(int[] currentPlacements, int row, int col) {
		
		if(currentPlacements[col] != EMPTY_SPOT)
			return false;
		
		for(int i=0; i < col; i++) {
			if(currentPlacements[i] == row)
				return false;
			
			if(Math.abs(row - currentPlacements[i]) == Math.abs(i - col))
				return false;
		}
		
		return true;
	}
}
