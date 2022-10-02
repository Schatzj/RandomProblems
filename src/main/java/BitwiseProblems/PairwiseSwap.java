package BitwiseProblems;

/*
 * swap even and odd pair of bits on a given integer. 
 * 
 */
public class PairwiseSwap {

	public static int swapBits(int value) {
		int mask = 3;
		int result = 0;
		
		int count = 0;
		while(count < 16) {
			mask = 3;
			int bits = value & mask;
			if(bits == 1) {
				//swap the bits. 
				mask = (2 << (count * 2));
				result = result | mask;
			}else if(bits == 2) {
				//swap the bits. 
				mask = (1 << (count * 2));
				result = result | mask;
			}else {
				//bits are the same, no need to swap. 
				mask = (bits << (count * 2));
				result = result | mask;
			}
			count++;
			value = value >> 2;
		}
		
		return result;
	}
	
	public static void main(String [] args) {
		System.out.println(Integer.toBinaryString(7));
		int result = swapBits(7);
		System.out.println(Integer.toBinaryString(result));
		
		System.out.println(Integer.toBinaryString(11));
		result = swapBits(11);
		System.out.println(Integer.toBinaryString(result));
		
		System.out.println(" " + Integer.toBinaryString(84));
		int test = swapBits(84);
		System.out.println(Integer.toBinaryString(test));
	}
}
