package CCIProblems;

/*
 * Create a recursive function to multiply two positive integers without using the * operator. 
 */
public class RecursiveMultiply {
	
	public static int recursiveMultiply(int a, int b) {
		System.out.println("called for: " + a + " * " + b);
		if(b < 2) {
			return 0;
		}
		
		int remainder = b%2;
		
		int result = a << 1;
		if(remainder > 0) {
			result += a;
			b--;
		}
		result += recursiveMultiply(a, b-2);
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(recursiveMultiply(5, 5));
		System.out.println(recursiveMultiply(5, 6));
		System.out.println(recursiveMultiply(7, 8));
	}
}
