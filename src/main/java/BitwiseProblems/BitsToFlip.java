package BitwiseProblems;

/*
 * Given two integers. Determine how many bits in A will need to be flipped to equal B. 
 * 
 * 1011 
 * 1100
 * 
 * 3. Three bits will need to be flipped for A to equal b. 
 */
public class BitsToFlip {
	
	public static int numberOfBitsToFlip(int a, int b) {
		int bitsToFlip = a ^ b;
		int count = 0;
		
		while(bitsToFlip > 0) {
			if((bitsToFlip & 1) == 1) {
				count++;
			}
			bitsToFlip = bitsToFlip >> 1;
		}
		
		return count;
	}
	
	
	public static void main(String[] args) {
		System.out.println(numberOfBitsToFlip(11, 12));
		System.out.println(numberOfBitsToFlip(29, 15));
	}

}
