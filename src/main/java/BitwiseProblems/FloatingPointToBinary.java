package BitwiseProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * converts decimal floating point numbers into binary. 
 * For simplicity the binary is returned as a string. 
 */
public class FloatingPointToBinary {

	public String convertFloatToBinary(double floatValue) {
		String wholeValue = convertWholeNumberToBinary((int)Math.floor(floatValue));
		String decimalValue = convertDecimalsToBinary(floatValue - Math.floor(floatValue));
		
		return wholeValue + "." + decimalValue;
	}

	private String convertDecimalsToBinary(double value) {
		if(value >= 1) {
			value = value -1;
		}
		if(value == 0) {
			return "";
		}
				
		return (int)Math.floor(value * 2) + convertDecimalsToBinary(value * 2) + "";
	}

	private String convertWholeNumberToBinary(int value) {
		if(value < 1) {
			return "";
		}
		
		return convertWholeNumberToBinary(value / 2) + value % 2 + "";
	}

	
	public static void main(String[] args) {
		FloatingPointToBinary f2b = new FloatingPointToBinary();
		String result = f2b.convertFloatToBinary(32.40625);
		System.out.println(result);
	}
	
}
