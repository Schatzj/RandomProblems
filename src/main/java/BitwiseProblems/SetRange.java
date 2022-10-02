package BitwiseProblems;

/*
 * Given a value M and a range of values (another value) N and a start index i and a stop index e. 
 * replace the bits in M from i to e with the values in N. 
 * Example 
 * M = 1001 1101
 * N = 0110
 * i = 2
 * e = 5
 * result = 1001 1001
 * 
 * It is safe to assume M will always have enough room to accomade N between i and e. 
 */
public class SetRange {
	
	public static int setBits(int m, int n, int i, int e) {
		//all zeros, expect keeps the bits in m from 0 to i
		int startingBits = ((-1 << i) -1) & m;
		
		//makes all bits in m 0 except bits e thu end of m. 
		int endingBits = (-1 << e-1)&m;
		
		//shift n over to i, and combine with end. 
		int result = endingBits | (n << i);
		//combine with the starting bits. 
		result = result | startingBits;
		return result;
	}
	
	public static void main (String[] args) {
		int m = 1024;
		int n = 19;
		int i = 2;
		int e = 6;
		
		Integer result = setBits(m, n, i, e);
		System.out.println(Integer.toString(result,2));
		
		int result2 = setBits(157, 6, 2, 5);
		System.out.println(Integer.toString(result2,2));
//		System.out.println(Integer.toBinaryString(-1) + " and : " + Integer.toBinaryString(17));
	}

}
