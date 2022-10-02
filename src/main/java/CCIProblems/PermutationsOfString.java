package CCIProblems;

import java.util.LinkedList;
import java.util.List;

/*
 * Given a string creates all permutations of the string where all permutations contain all the original letters in the string. 
 * i.e. given abc the output will be: 
 * abc
 * bac
 * bca	
 * acb
 * cab
 * cba
 */
public class PermutationsOfString {

	//I misunderstood the problem
//	public static List<String> stringPermutations(String s){
//		List<String> result = new LinkedList<String>();
//		if(s.isEmpty() || s.length() < 2) {
//			result.add(s);
//			return result;
//		}
//		
//		String character = s.substring(0, 1);
//		List<String> permutations = stringPermutations(s.substring(1, s.length()));
//		List<String> activeSet = copy(permutations);
//		result.add(character);
//		for(String string : activeSet) {
//			char[] characters = string.toCharArray();
//			for(int i = 0; i<characters.length; i++) {
//				result.add((string.substring(i, string.length()) + character));
//			}
//			result.add(character + string);
//		}	
//		
//		result.addAll(permutations);
//		
//		return result;
//	}
	
//	private static List<String> copy(List<String> permutations) {
//		List<String> result = new LinkedList<String>();
//		for(String s : permutations) {
//			result.add(String.copyValueOf(s.toCharArray()));
//		}
//		return result;
//	}
	
	public static List<String> stringPermutations(String s){
		List<String> result = new LinkedList<String>();
		if(s.isEmpty() || s.length() < 2) {
			result.add(s);
			return result;
		}
		
		String character = s.substring(0, 1);
		List<String> permutations = stringPermutations(s.substring(1, s.length()));
		for(String string : permutations) {
			char[] characters = string.toCharArray();
			for(int i = 0; i<characters.length; i++) {
				String prefix = string.substring(0, i);
				String suffix = string.substring(i, string.length());
				result.add(prefix + character + suffix); 
			}
			result.add(string + character);
		}	
		
		return result;
	}

	public static void main(String[] args) {
		List<String> result = stringPermutations("aabc");
		for(String s : result) {
			System.out.println(s);
		}
		
		System.out.println();
		System.out.println();
	}
}
