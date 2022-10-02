package CCIProblems;

import java.util.ArrayList;
import java.util.List;

/*
 * Place 8 queens on a chess board so that no queen can capture another queen. 
 */
public class EightQueens {

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
	
	static int totalSolutions = 0;
	
	public static List<Integer> findConfigurations(){
		List<Integer> result = new ArrayList<Integer>();
		int[][] board = new int[8][8];
		int workingRow = 0;
		result = solve(result, board, workingRow);
		return result;
	}

	private static List<Integer> solve(List<Integer> result, int[][] board, int workingRow) {
//		boolean foundValidPlacement = false;
		for(int i = 0; i<board[workingRow].length; i++) {
			int[][] tempBoard = createNewArray(board); 
			if(tempBoard[workingRow][i] < 1) {
				tempBoard[workingRow][i] = 1;
				if(workingRow == tempBoard.length -1) {
					//we are on the final row, this is a valid result, add it to the list. 
					//print it instead of dealing with returning a result. 
					if(isValidSolution(board)) {
						printBoard(tempBoard);
						totalSolutions++;
					}
						
				}else {
					//update the board (mark now illegal cells), and recurse.
					updateBoard(tempBoard, workingRow, i);
					solve(result, tempBoard, workingRow + 1);
				}
			}
			
			if(workingRow >= board.length) {
				break;
			}
		}
		return result;
	}

	private static boolean isValidSolution(int[][] board) {
		int valid = 0; 
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				if(board[i][j] == 1) {
					valid +=1;
				}
			}
		}
		
		return valid == 7;
	}

	private static int[][] createNewArray(int[][] board) {
		int[][] result = new int[board.length] [board[0].length];
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				result[i][j] = board[i][j];
			}
		}
		
		return result;
	}

	private static void updateBoard(int[][] board, int startRow, int startCol) {
		for(int dir = 0; dir<directions.length; dir++) {
			int row = startRow;
			int col = startCol;
			row += directions[dir][0];
			col += directions[dir][1];
			while(row < board.length && row > -1 && col < board[row].length && col > -1) {
				if(board[row][col] == 1) {
					System.out.println("Overwritting a 1");
				}
				board[row][col] = 2;
				row += directions[dir][0];
				col += directions[dir][1];
			}
		}
	}

	private static void printBoard(int[][] board) {
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");
		}
		
		System.out.println("");
		System.out.println("");
	}

	public static void main(String[] args) {
		findConfigurations();
		System.out.println("Total Solutions: " + totalSolutions);
		//https://www.cs.toronto.edu/~fbacchus/Presentations/CSP-BasicIntro.pdf
		//solution appears to be correct. 
	}
}
