package BitwiseProblems;

/*
 * Code fails
 * CCI problem 5.4
 * 
 * Notes:
 * Next find earlist 0. swap with closest previous 1
 * prev: find earliest 0. swap with closest next 1. 
 * 
 * However, previous fails as it never changes anything to negative in my implementation (Integer.Max) should return a negative number. 
 * 		so should 7
 * 
 * Additionally I do not rearrange the bits enough. 
 * Next is the correct first step, but I should then rearrange the ones to the right of the earlist 0 to be in the smallest places possible. 
 * 
 * TakeTwo is better. It is my own approach. The only thing I took from the book was the realization I need to rearrange the ones. 
 * Still need to do previous though.
 */
public class NextNumber {
	public static void closish(int value) {
		int originalValue = value;
		int index = 0;
		int prevOne = -1;
		int firstZero = -1;
		int nextOne = -1;
		
		while(index < 33 && (((prevOne > -1) && (firstZero > -1) && (nextOne > -1)) == false)) {
			if(((value & 1) == 1)) {
				//1 is found
				if(firstZero > -1) {
					nextOne = index;
				}else {
					prevOne = index;
				}
			}else {
				//0 is found
				if(firstZero < 0) {
					firstZero = index;
				}
			}
			
			value = value >>> 1;
			index++;
		}
		
		//we have our indexes. 
		//now apply rules:
		//next: swap firstZero and Prev one. 
			//if PrevOne < 0: in the next greatest left place. 
			//if firstZero < 0 //this should be impossible.
		//prev: swap firstZero and nextOne
			//if nextOne < 0. Error no smaller value. 
		//ONE CASE IS FAILING. 
		
		//generate next smaller value
		if(nextOne < 0) {
			System.out.println("ERROR no smaller value");
		}else {
			//toggle the zero. 
			int mask = (1 << firstZero);
			int smaller = originalValue ^ mask;
			mask = (1 << nextOne);
			smaller = smaller ^ mask;
			System.out.println("smaller: " + smaller + " " + Integer.toBinaryString(smaller));
		}
		
		
		System.out.println(Integer.toBinaryString(originalValue));
		System.out.println("PrevOne: " + prevOne + " firstZero: " + firstZero + " nextOne: " + nextOne);
		System.out.println("---------------------------------------------------");
	}
	
	public static void findEarliestZero(int value) {
		int originalValue = value;
//		Integer next = null;
//		Integer prev = null;
		int index = 0;
		int oneBeforeZero = -1;
		int zeroAfterOne = -1;
		
		int zeroBeforeOne = -1;
		int oneAfterZero = -1;
		
		while(index < 33) {
			if(oneBeforeZero > -1 && zeroAfterOne > -1 && zeroBeforeOne > -1 && oneAfterZero >-1) {
				break;
			}
			if(((value & 1) == 1)) {
				//1 is found				
				if(oneBeforeZero > -1 && zeroAfterOne > -1) {
					oneAfterZero = index;
				}else {
					oneBeforeZero = index;
				}
				
				if(zeroBeforeOne > -1) {
					oneAfterZero = index;
				}
			}else {
				//0 is found
				if(oneBeforeZero > -1 && zeroAfterOne > -1) {
					//do not change zeroAfterOne.
				}else if(oneBeforeZero > -1) {
					zeroAfterOne = index;
				}
				if(oneAfterZero < 0) {
					zeroBeforeOne = index;
				}
			}
			
			value = value >>> 1;
			index++;
		}
		
		//toggle the zero. 
		int mask = (1 << zeroAfterOne);
		int next = originalValue ^ mask;
		mask = (1 << oneBeforeZero);
		next = next ^ mask;
		if(next < originalValue) {
			System.out.println("ERROR no next value");
		}else {
			System.out.println("next: " + next + " " + Integer.toBinaryString(next));
		}
		
		mask = (1 << zeroBeforeOne);
		int prev = originalValue ^ mask;
		mask = (1 << oneAfterZero);
		prev = prev ^ mask;
		if(oneAfterZero < 0) {
			System.out.println("ERROR no smaller value");
		}else {
			System.out.println("prev: " + prev + " " + Integer.toBinaryString(prev));
		}
		
		
		
		System.out.println(String.format("oneBeforeZero %s zeroAfterOne %s zeroBeforeOne %s oneAfterZero %s", oneBeforeZero, zeroAfterOne,
				zeroBeforeOne, oneAfterZero));
		System.out.println("Original: " + Integer.toBinaryString(originalValue));
		System.out.println("---------------------------------------------------");
	}
	
	public static void main(String[] args) {
//		System.out.println(findnext(7));
//		System.out.println(findnext(167));
//		System.out.println(findnext(Integer.MAX_VALUE));
//		System.out.println(findnext(4));
		
		findEarliestZero(7);
		findEarliestZero(167);
		findEarliestZero(Integer.MAX_VALUE);
		findEarliestZero(4);
		findEarliestZero(13948);
		
		System.out.println("__________________________________________________");
		takeTwo(46);		
		takeTwo(7);
		takeTwo(167);
		takeTwo(Integer.MAX_VALUE);
		takeTwo(4);
		takeTwo(13948);
		System.out.println("PREVIOUS");
		takeTwoPrev(46);		
		takeTwoPrev(7);
		takeTwoPrev(167);
		takeTwoPrev(Integer.MAX_VALUE);
		takeTwoPrev(4);
		takeTwoPrev(13948);
	}
	
	public static void takeTwo(int value) {
		int original = value;
		int index = -1;
		int ones = 0;
		
		while(index < 33) {
			index++;
			if(((value & 1) == 1)) {
				ones++;
			}else {
				if(ones > 0) {
					break;
				}
			}
			
			value = value >>> 1;
		}
		
		//to get next value. we flip index and index -1. 
		//we recreate the number from index - 1 to the right. 
		//we add ones -1 in the rightmost slots, and fill the rest with zero. 
		
		//flip the bits. 
		value = original;
		int mask = 1;
		mask = mask << index;
		value = value ^ mask;
		mask = 1; 
		mask = mask << index -1;
		value = value ^ mask;
		
		//clear the number from 0 - index -1; 
		mask = -1; 
		mask = (mask << index);
		value = value & mask;
		
		//set the bits 0 - index - 1 to One. 
		mask = 0;
		for(int i = 1; i<ones; i++) {
			mask = mask << 1;
			mask++;
		}
		value = value | mask;
		System.out.println("RESULT: " + Integer.toBinaryString(value) + " in Decimal " + value);
		
		
//		System.out.println(String.format("ones %s sumOfOnes %s index %s", ones, sumOfOnes, index));
	}
	
	public static void takeTwoPrev(int value) {
		int original = value;
		int index = -1;
		int ones = 0;
		int zeroIndex = -1;
		
		while(index < 32) {
			index++;
			if(((value & 1) == 1)) {
				if(zeroIndex > -1) {
					break;
				}
				ones++;
			}else {
				zeroIndex = index;
			}
			
			value = value >>> 1;
		}
		
//		System.out.println("value: " + Integer.toBinaryString(original) + " index: " + index + " ones: " + ones + " zeroIndex: " + zeroIndex);
		
		//flip zeroIndex and index;
		value = original;
		int mask = 1;
		mask = mask << index;
		value = value ^ mask;
		mask = 1; 
		mask = mask << zeroIndex;
		value = value ^ mask;
//		System.out.println("value: " + Integer.toBinaryString(original) + " updated: " + Integer.toBinaryString(value));
		
		//clear the number from 0 - index -1; 
		mask = -1; 
		mask = (mask << zeroIndex);
		value = value & mask;
//		System.out.println("value: " + Integer.toBinaryString(value) + " mask: " + Integer.toBinaryString(mask));
		
		//set the bits closest to zeroIndex to 1 (as many as we have in ones count -1).  
		if(index < 32) {
			mask = 0;
			for(int i = 0; i<ones; i++) {
				mask = mask << 1;
				mask++;
			}
			mask = mask << (zeroIndex-ones);
//			System.out.println("applying " + Integer.toBinaryString(mask));
			value = value | mask;
		}else {
			mask = 1;
			mask = mask << (31);
//			System.out.println("applying " + Integer.toBinaryString(mask));
			value = value | mask;
			value = value ^ 1;
		}
		
		System.out.println("RESULT: " + Integer.toBinaryString(value) + " in Decimal " + value);
	}
}
