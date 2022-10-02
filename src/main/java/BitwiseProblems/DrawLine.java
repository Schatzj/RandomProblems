package BitwiseProblems;

/*
 * Cracking the coding interview problem 5.8
 */
public class DrawLine {
	
	public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		if(y > (screen.length-1)/width) {
			System.out.println("ERROR height is greater then the screen height");
		}else if(y < 0) {
			System.out.println("ERROR height is less than 0");
		}
		
		int row = (width * y);
		
		int startingIndex = row;
		int startingBit = x1;
		while(startingBit > 7) {
			startingIndex++;
			startingBit = startingBit - 8;
		}
		
		System.out.println("startingIndex: " + row + " starting bit: " + startingBit);
		
		int bitsToDraw = x2 - x1;
		//handle starting byte
		int bitsRemainingInByte = 7 - startingBit;
		if(bitsRemainingInByte < bitsToDraw) {
			byte mask = (byte)(-1 >> bitsRemainingInByte);
			screen[startingIndex] = mask;
			startingIndex++;
		}else {
			System.out.println("SHIFTING: " + (x2 - x1) + " AND : " + (x1 % 8));
//			byte mask = (byte)(~0x7f);
			int intMask = (256);
			System.out.println(Integer.toBinaryString(intMask));
			intMask = (intMask >>> (x2 - x1));
			System.out.println(Integer.toBinaryString(intMask));
			
			System.out.println("INT MASK: " + Integer.toBinaryString(intMask));
			intMask = (intMask >> (x1 % 8));
			System.out.println(Integer.toBinaryString(intMask));
			String resultWithPadZero = String.format("%8s", Integer.toBinaryString(intMask)).replace(" ", "0");
			System.out.println(resultWithPadZero);
			System.out.println();
			System.out.println();
			screen[startingIndex] = (byte)intMask;
		}
		
		int count = 0; 
		for(int i = 0; i<screen.length; i++) {
			if(count == width) {
				System.out.println();
				count = 0;
			}
			int result = screen[i] & 0xff;
			String resultWithPadZero = String.format("%8s", Integer.toBinaryString(result)).replace(" ", "0");
			System.out.print(resultWithPadZero);
			System.out.print(" | ");
			count++;
		}
		
//		while(bitsToDraw > 0) {
//			int bitsRemainingInByte = 7 - startingBit;
//			if(bitsRemainingInByte == 7) {
//				if(bitsRemainingInByte < bitsToDraw) {
//					byte mask = -1;
//					screen[startingIndex] = mask;		
//				}else {
//					//can't set the whole byte. 
//					
//				}
//			}
//			
//			break;
//		}
	}
	
	public static void main(String[] args) {
		byte[] screen = new byte[16];
		for(int i = 0; i<screen.length; i++) {
			byte b = 0;
			screen[i] = b;
		}
		
		drawLine(screen, 4, 12, 13, 1);
	}

}
