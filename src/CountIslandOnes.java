
public class CountIslandOnes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = {{0,1,0,0,0},
						{0,0,0,0,1},
						{0,0,0,0,0},
						{0,0,1,1,0},
						{1,0,0,0,1}};
		System.out.println(countIslands(grid) == 5);
	}
	
	/*
	 * Time: O(m*n) or O(m^2 * n^2) b/c of dfs traversal?
	 * Space: O(m*n)
	 */
	public static int countIslands(int[][] grid) {
		if(grid == null)
			return -1;
		
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		int count = 0;
		for(int i=0; i < grid.length; i++) {
			for(int j=0; j < grid[0].length; j++) {
				if(grid[i][j] == 1 && visited[i][j] == false) {
					count++;
					dfs(grid, visited, i, j);
				}
			}
		}
		
		return count;
	}
	
	public static void dfs(int[][] grid, boolean[][] visited, int i, int j) {
		if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length)
			return;
		
		if(grid[i][j] == 1 && visited[i][j] == false) {
			visited[i][j] = true;
			dfs(grid, visited, i-1, j);
			dfs(grid, visited, i, j+1);
			dfs(grid, visited, i+1, j);
			dfs(grid, visited, i, j-1);
		}
	}

}
