package treeProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import BinarySearchTree.BSTree;

/*
 * Given a binary tree of integers (positive and negatives), and a target value. 
 * Find the number of paths which sum to the target value. 
 * The paths do not need to start at the root, or end at a leaf. 
 * However, they must move down the tree from the starting node. 
 */
public class PathOfSums {
	
	private static int findNumberOfSums(BSTree<Integer> tree, int target) {
		List<List<Integer>> sums = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		list.add(0); //the first list will contain the answer. The number of sums. Plus it starts the recursion. 
		sums.add(list);
		sums = getSums(tree, target, sums, 0);
		
		int result = 0;
		for(int i = 0; i<sums.size(); i++) {
			for(int j = 0; j<sums.get(i).size(); j++) {
				System.out.print(sums.get(i).get(j) + ", ");
			}
			System.out.println();
		}
		return sums.get(0).get(0);
	}
	
	private static List<List<Integer>> getSums(BSTree<Integer> tree, int target, List<List<Integer>> currentSums, int index){
		if(tree == null) {
			return currentSums;
		}
		
		//get the previous running sum. 
		List<Integer> previousSums = currentSums.get(index);
		//add a new list to the list of  lists, with nodes total, and running totals. 
		int runningTotal = tree.getData() + previousSums.get(previousSums.size() -1);
		List<Integer> totals = new ArrayList<Integer>();
		totals.add(tree.getData());
		for(int i = 0; i<previousSums.size(); i++) {
			if(previousSums.get(i) == 0) {
				break;
			}
			int value = previousSums.get(i) + tree.getData();
			//update the result. 
			if(value == target) {
				List<Integer> results = currentSums.get(0);
				results.set(0, results.get(0) + 1);
				currentSums.set(0, results);
			}
			totals.add(previousSums.get(i) + tree.getData());
		}
		currentSums.add(totals);
		
		int currentTotalIndex = currentSums.size() - 1;
		List<List<Integer>> leftSums = getSums(tree.getLeftNode(), target, currentSums, currentTotalIndex);
		List<List<Integer>> rightsums = getSums(tree.getRightNode(), target, currentSums, currentTotalIndex);
		
		for(int i = currentSums.size(); i<rightsums.size(); i++) {
			leftSums.add(rightsums.get(i));
		}
		
		return leftSums;
	}
	
	
	public static void main(String[] args) {
		BstSequence bstS = new BstSequence();

		BSTree<Integer> tree = new BSTree<Integer>(5);
		tree.insert(new BSTree<Integer>(3));
		tree.insert(new BSTree<Integer>(4));
		tree.insert(new BSTree<Integer>(6));		
		tree.insert(new BSTree<Integer>(1));
		tree.insert(new BSTree<Integer>(2));
		tree.insert(new BSTree<Integer>(4));
		tree.insert(new BSTree<Integer>(7));
		
		int result = findNumberOfSums(tree, 11);
		System.out.println();
		System.out.println(tree);
		System.out.println("result: " + result);
	}

}

