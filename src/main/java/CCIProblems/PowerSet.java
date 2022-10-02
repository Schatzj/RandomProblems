package CCIProblems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
 * Given a set, return all possible subsets. 
 * The current implementation currently return the original set as well. 
 * However, the function could be wrapped in another function which removes the first (original) set from the result. 
 */
public class PowerSet {
	
	public static int callCount = 0;
	
	/*
	 * Remove an element from the set. 
	 * Then recursively call the function. 
	 * append the removed item as a set
	 * append all sets received from the recursive call. 
	 * append the removed item to all sets received from the recursive call.  
	 */
	public static List<List<Integer>> powerSet(Set<Integer> set){
		callCount++;
		if(set.isEmpty()) {
			return null;
		}
		
		if(set.size() == 1) {
			Iterator<Integer> iterator = set.iterator();
			Integer value = iterator.next();
			List<List<Integer>> result = new ArrayList<>();
			result.add(new ArrayList<Integer>());
			result.get(0).add(value);
			return result;
		}
		
		Iterator<Integer> iterator = set.iterator();
		Integer value = iterator.next();
		Set<Integer> subset = new HashSet<Integer>();
		while(iterator.hasNext()) {
			subset.add(iterator.next());
		}
		
		List<List<Integer>> listOfSets = powerSet(subset);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for(List<Integer> list : listOfSets) {
			List<Integer> activeSet = new ArrayList<Integer>();
			for(Integer v : list) {
				activeSet.add(v);
			}
			activeSet.add(value);
			result.add(activeSet);
		}
		
		List<Integer>currentSingleSet = new ArrayList<Integer>();
		currentSingleSet.add(value);
		result.add(currentSingleSet);
		result.addAll(listOfSets);
		
		return result;
	}
	
	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
//		set.add(4);
		
		List<List<Integer>> result = powerSet(set);
		for(List<Integer> list : result) {
			for(Integer value : list) {
				System.out.print(value + ", ");
			}
			System.out.println("");
		}
		
		System.out.println("callCount: " + callCount);
	}

}
