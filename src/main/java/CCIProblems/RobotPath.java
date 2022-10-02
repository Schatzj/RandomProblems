package CCIProblems;

/*
 * Assume you have a robot that starts in the top left corner of a grid. 
 * Assume the robot can only move down and to the right. 
 * Assume the robot wants to get to the bottom right corner of the grid. 
 * Assume some cells in the grid are off limits. 
 * Determine if there is a valid path for the robot to reach the target cell. 
 * 
 * Point of interest. 
 * This was suppose to be a dynamic programming problem. 
 * The current solution, does not use dynamic programming, but does not appear to do any duplicate work. 
 */
public class RobotPath {
	
	public static final int[][] directions = new int[][] {
		{0, 1}, //right
		{1, 0} //down
	};
	
	//0 = OK
	//1 = Off Limits. 
	
	/*
	 * Go in a direction (the start direction is right). 
	 * If you cannot proceed in the direction. Change to the other direction
	 * If you still cannot proceed, backtrack, and attempt the direction. 
	 */
	public static int findPath(int[][] grid, int currentRow, int currentColumn, int direction) {
		System.out.println("checking cells: " + currentRow + "X" + currentColumn);
		if(currentRow >= grid.length || currentColumn >= grid[0].length) {
			return 1;
		}else if(currentRow < 0 || currentColumn < 0) {
			return 1;
		}else if(grid[currentRow][currentColumn] == 1) {
			return 1;
		}
		if(currentRow == grid.length-1 && currentColumn == grid[0].length-1) {
			//we are in the target cell. Success; 
			System.out.println(currentRow + ":" + currentColumn + " Success!");
			return 0;
		}
		int newRow = currentRow + directions[direction][0];
		int newColumn = currentColumn + directions[direction][1];
		int result = findPath(grid, newRow, newColumn, direction);
		if(result == 0) {
			System.out.println(currentRow + ":" + currentColumn);
			return 0;
		}else {
			direction = 1 - direction;
			newRow = currentRow + directions[direction][0];
			newColumn = currentColumn + directions[direction][1];
			result = findPath(grid, newRow, newColumn, direction);
			if(result == 0) {
				System.out.println(currentRow + ":" + currentColumn);
				return 0;
			}else {
				return 1;
			}
		}
		
	}
	
	public static void main(String[] args) {
		int[][] grid = new int[][] {
			{0,0,0,1,0},
			{0,0,0,0,0},
			{0,0,0,1,0},
			{0,0,0,1,0},
			{0,0,0,1,0}
		};
		findPath(grid, 0, 0, 0);
		
	}

}
