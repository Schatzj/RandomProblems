package CCIProblems;

/*
 * Given a two dimensional array of ints, a starting location, and a new value. 
 * replace the old value, and all adjacent touching old values with the new value, 
 * much like the paint fill function in ms paint. 
 */
public class PaintFill {
	
	static int[][] directions = {
			{-1, 0}, //up
			{0, 1}, //right
			{1, 0}, //down
			{0, -1}, //left
			{-1, -1}, //diag-left up
			{-1, 1}, //diag-right up
			{1, -1}, //diag-left down
			{1, 1} //diag-right down
	};
	
	
	public static int[][] paintfill(int[][] data, int row, int col, int value){
		if(validIndex(data, row, col)) {
			int oldValue = data[row][col];
			return paintfill(data, row, col, oldValue, value);
		}
		return data;
	}
	
	/*
	 * If the location is valid, and the value is the original targeted value, update the value. 
	 * Then repeat the same logic in every direction. 
	 */
	public static int[][] paintfill(int[][] data, int row, int col, int oldValue, int newValue){
		if(validIndex(data, row, col) == false) {
			return data;
		}
		
		if(data[row][col] == oldValue) {
			data[row][col] = newValue;
		}else {
			return data;
		}
		
		for(int i = 0; i<directions.length; i++) {
			int newRow = row + directions[i][0];
			int newCol = col + directions[i][1];
			paintfill(data, newRow, newCol, oldValue, newValue);
		}
		
		return data;
	}
	
	public static boolean validIndex(int[][] data, int row, int col) {
		if(row < data.length && row > -1) {
			if(col < data[row].length && col > -1) {
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		int[][] data1 = {
			{1,1,1,1,1},
			{1,0,0,0,1},
			{1,0,0,0,1},
			{1,0,0,0,1},
			{1,1,1,1,1}
		};
		
		int[][] data2 = {
				{1,1,1,1,1},
				{1,0,0,0,1},
				{1,0,0,0,1},
				{1,0,0,0,1},
				{1,1,0,1,1}
			};
		
		int[][] data3 = {
				{0,1,0,0,0},
				{0,1,0,0,0},
				{0,1,0,0,0},
				{0,1,1,1,1},
				{0,0,0,0,0}
			};
		
		int[][] result = paintfill(data1, 2, 2, 4);
		
		for(int[] row : result) {
			for(int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println("");
		}
		
		System.out.println();
		result = paintfill(data2, 1, 2, 4);
		
		for(int[] row : result) {
			for(int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println("");
		}
		
		System.out.println();
		result = paintfill(data3, 0, 0, 4);
		
		for(int[] row : result) {
			for(int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println("");
		}
		
	}

}
