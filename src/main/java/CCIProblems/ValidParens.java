package CCIProblems;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * given an integer value N create all possible valid parenthesis combinations that have N parenthesis
 */
public class ValidParens {
	
	/*
	 * base case and build. 
	 * For each value place a set of parenthesis () before all others, and then after each closing ). 
	 * 
	 * This is my own solution. 
	 */
	public static Set<String> validParenPermutations(int target){
		Set<String> result = new LinkedHashSet<String>();
		if(target < 2) {
			result.add("()");
			return result;
		}
		
		Set<String> temp = validParenPermutations(target -1);
		Iterator<String> iterator = temp.iterator();
		while(iterator.hasNext()) {
			int count = 0;
			char[] chars = iterator.next().toCharArray();
			
			while(count > -1) {
				int index = indexOfNthOccurance(')', count, chars);
				if(index < 0) {
					break;
				}
				StringBuilder builder = new StringBuilder();
				for(int i = 0; i<index; i++) {
					builder.append(chars[i]);
				}
				builder.append("()");
				for(int i = index; i<chars.length; i++) {
					builder.append(chars[i]);
				}
				
				result.add(builder.toString());
				count++;
			}
		}
		return result;
	}
	
	private static int indexOfNthOccurance(char target, int occurrence, char[] data) {
		if(occurrence < 1) {
			return 0;
		}
		
		int found = 0; 
		for(int i = 0; i<data.length; i++) {
			if(data[i] == target) {
				found++;
			}
			if(found == occurrence) {
				return i;
			}
		}
		
		return -1;
	}
	
	/*
	 * Simplified solution
	 * created using hints from the book. 
	 */
	public static Set<String> simplified(int target){
		Set<String> result = new LinkedHashSet<String>();
		if(target < 2) {
			result.add("()");
			return result;
		}
		
		Set<String> temp = validParenPermutations(target -1);
		Iterator<String> iterator = temp.iterator();
		while(iterator.hasNext()) {
			String value = iterator.next();
			result.add("()" + value);
			result.add("(" + value + ")");
			result.add(value + "()");
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Set<String> result = validParenPermutations(3);
		result.forEach(s -> System.out.println(s));
		System.out.println();
		System.out.println();
		result = simplified(3);
		result.forEach(s -> System.out.println(s));
	}

}
