package BitwiseProblems;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * Given an input 1775 (11011101111) find the longest possible sequence of 1's by flipping a single bit. 
 * return the longest sequence you can create. 
 * i.e. if the input 1775 the result is 8. 
 * 
 * works by converting the value to a string and manipulating the string. 
 * This does not seem like the best solution.
 * 
 *  Further research suggests that instead of using strings a better approach would be to use logical right shift. 
 *  Shift the value 32 times (assuming 4 byte integer), or until the value in decimal == 0 (no more 1's are present). 
 *  By comparing value & 1 we can determine if the least significant bit is a 1 or a 0. 
 *  something like:
 *  while(value != 0){
 *  	if((value & 1) == 1){
 *  		LSB is a 1. 
 */
public class FlipToWin {
	
	
	public static void main(String[] args) {
		int result = flipBitToCreateLongestSequence(1775);
		System.out.println(result);
		result = flipBitToCreateLongestSequence(1979);
		System.out.println(result);
		result = flipBitToCreateLongestSequence(1947);
		System.out.println(result);
		result = flipBitToCreateLongestSequence(1);
		System.out.println(result);
		result = flipBitToCreateLongestSequence(0);
		System.out.println(result);
		result = flipBitToCreateLongestSequence(-1);
		System.out.println(result);
	}
	
	private static int flipBitToCreateLongestSequence(int value) {
		String input = Integer.toBinaryString(value);
		System.out.println(input);
		
		//split our total into the 1's counted before and the 1's counted after encounting a zero. 
		//this allows use to restart the count with the 1's after the first zero encountered. 
		int firstHalf = 0;
		int secondHalf = 0;
		//the largest series found so far. 
		int max = 0; 
		boolean bitFlipped = false;
		
		char[] characters = input.toCharArray();
		for(char c : characters) {
			if(c == '1') {
				 if(bitFlipped) {
					 secondHalf++;
				 }else {
					 firstHalf++;
				 }
			}else {
				 if(!bitFlipped) {
					 firstHalf++;
					 bitFlipped = true;
				 }else {
					 max = Math.max(max, (firstHalf + secondHalf));
					 //we encountered a second 0. Reset the count. 
					 firstHalf = secondHalf + 1; //the bit we are about to flip
					 secondHalf = 0; 
					 //we are counting this bit as a 1 when it is a zero, so we have flipped a bit for this sequence. 
					 bitFlipped = true;
				 }
			}
		}
		
		max = Math.max((firstHalf + secondHalf), max);
		return max;
	}

}
