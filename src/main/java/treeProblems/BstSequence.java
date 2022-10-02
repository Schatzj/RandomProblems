package treeProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import BinarySearchTree.BSTree;

/*
 * Given a BSTree, print all possible arrays which if traveresed left to right, 
 * and each cell added to the tree could have created the tree. 
 * if the tree is 2
 * 				/   \
 * 		       1     3
 * the output would be {2, 1, 3}, {2, 3, 1}
 */
public class BstSequence {

	public List<LinkedList<Integer>> generateArrays(BSTree<Integer> root) {
		return createAllPossibleArrays(root);
	}

	private List<LinkedList<Integer>> createAllPossibleArrays(BSTree<Integer> root) {
		if (root == null) {
			return null;
		}
		if (root.getLeftNode() == null && root.getRightNode() == null) {
			LinkedList<Integer> possibleArrays = new LinkedList<Integer>();
			possibleArrays.add(root.getData());
			ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
			result.add(possibleArrays);
			return result;
		}

		List<LinkedList<Integer>> leftArrays = createAllPossibleArrays(root.getLeftNode());
		List<LinkedList<Integer>> rightArrays = createAllPossibleArrays(root.getRightNode());

		List<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
		if(leftArrays != null) {
			List<LinkedList<Integer>> leftResult = createPermutations(leftArrays, rightArrays, root);
			result.addAll(leftResult);
		}
		
		if(rightArrays != null) {
			List<LinkedList<Integer>> rightResult = createPermutations(rightArrays, leftArrays, root);
			result.addAll(rightResult);
		}
		return result;
		
	}
	
	private List<LinkedList<Integer>> createPermutations(List<LinkedList<Integer>> left, List<LinkedList<Integer>> right, BSTree<Integer> root) {
		List<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
		LinkedList<Integer> prefix;
		LinkedList<Integer> postfix;
		
		for(int i = 0; i<left.size(); i++) {
			LinkedList<Integer> list = left.get(i);
			for(int index = 0; index < list.size(); index++) {
				prefix = new LinkedList<Integer>();
				postfix = new LinkedList<Integer>();
				for(int j = 0; j<index; j++) {
					prefix.add(list.get(j));
				}
				for(int j = index; j<list.size(); j++) {
					postfix.add(list.get(j));
				}
				if(right != null) {
					for(LinkedList<Integer> rightList : right) {
						for(int j = 0; j<rightList.size(); j++) {
							LinkedList<Integer> currentList = new LinkedList<Integer>();
							currentList.add(root.getData());
							for(int k = 0; k<j; k++) {
								currentList.add(rightList.get(k));
							}
							currentList.addAll(prefix);
							for(int k = j; k<rightList.size(); k++) {
								currentList.add(rightList.get(k));
							}
							currentList.addAll(postfix);
							result.add(currentList);
							if(prefix.size() < 1) {
								break;
							}
						}
					}
				}else {
					LinkedList<Integer> currentList = new LinkedList<Integer>();
					currentList.add(root.getData());
					currentList.addAll(prefix);
					currentList.addAll(postfix);
					result.add(currentList);
					break;
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		BstSequence bstS = new BstSequence();

		BSTree<Integer> tree = new BSTree<Integer>(5);
		tree.insert(new BSTree<Integer>(3));
		tree.insert(new BSTree<Integer>(4));
		tree.insert(new BSTree<Integer>(6));		
		tree.insert(new BSTree<Integer>(1));
		tree.insert(new BSTree<Integer>(2));
		tree.insert(new BSTree<Integer>(7));
		
		System.out.println(tree);
		List<LinkedList<Integer>> result = bstS.generateArrays(tree);
		for(LinkedList<Integer> list : result) {
			System.out.print(list.get(0));
			for(int i = 1; i<list.size(); i++) {
				System.out.print(", " + list.get(i));
			}
			System.out.println(" ");
		}
	}
}
