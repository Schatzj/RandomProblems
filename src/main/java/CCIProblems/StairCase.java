package CCIProblems;

import java.util.HashMap;

/*
 * Assuming you have a staircase of size X, and I child which skip up 1 - y steps. 
 * Determine how many different ways the child could climb the stairs. 
 */
public class StairCase {
	
	/*
	 * If the child can skip up to y steps, then the number of of solutions for 0 - y equals the sum of the previous solutions + 1; 
	 * 
	 * for steps greater then y, it is the sum of the previous y steps. 
	 * 
	 * for example if a y = 3; 
	 * step 1: 1 solution (1)
	 * step 2: 1 + 1 solutions (11, 2)
	 * step 3: 2 + 1 + 1 solutions  (111, 12, 21, 3)
	 * step 4 = 4 + 2 + 1 = 7 solutions
	 * step 5 = 7 + 4 + 2 = 13 solutions. 
	 */
	
	public static int poorSolution(int staircaseSize, HashMap<Integer, Integer> solutions) {
		if(solutions.containsKey(staircaseSize)) {
			return solutions.get(staircaseSize);
		}else if(staircaseSize == 1){
			solutions.put(staircaseSize, 1);
		}else if(staircaseSize == 2){
			solutions.put(staircaseSize, 2);
		}else if(staircaseSize == 3){
			solutions.put(staircaseSize, 4);
		}else {
			solutions.put(staircaseSize, (poorSolution(staircaseSize-1, solutions) + poorSolution(staircaseSize-2, solutions) + poorSolution(staircaseSize-3, solutions)));
		}
		
		return solutions.get(staircaseSize);
	}
	
	public static int goodSolution(int staircaseSize, int maxStepLength, HashMap<Integer, Integer> solutions) {
		if(solutions.containsKey(staircaseSize)) {
			return solutions.get(staircaseSize);
		}else if(staircaseSize == 1){
			solutions.put(staircaseSize, 1);
		}else {
			int result = 0; 
			for(int i = maxStepLength; i>0; i--) {
				if(staircaseSize - i > 0) {
					result = result + goodSolution(staircaseSize - i, maxStepLength, solutions);
				}				
			}
			if(staircaseSize <= maxStepLength) {
				result++;
			}
			solutions.put(staircaseSize, result);
		}
		
		return solutions.get(staircaseSize);
		
	}
	
	
	public static void main(String[] args) {
		int result = poorSolution(5, new HashMap<Integer, Integer>());
		System.out.println(result);
		
		result = goodSolution(5, 3, new HashMap<Integer, Integer>());
		System.out.println(result);
	}

}
