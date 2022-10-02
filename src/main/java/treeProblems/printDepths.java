package treeProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import BinarySearchTree.BSTree;

/*
 * The goals is to create link lists which contain all the nodes of a given depth for a tree. 
 * if the tree has a depth of D, we should have D LL where each LL maps to one depth, and contains all nodes in the tree. 
 * 
 * We may use an List instead of an LL
 */
public class printDepths {
	
	public static void addNodeToList(BSTree<Integer> node, List<LinkedList<Integer>> list, int level) {
		while(list.size() <= level) {
			list.add(new LinkedList<Integer>());
		}
		
		list.get(level).add(node.getData());
	}
	
	public static List<LinkedList<Integer>> inOrderSearch(BSTree<Integer> root, List<LinkedList<Integer>> list, int level) {
		if(root == null) {
			return list;
		}
		
		inOrderSearch(root.getLeftNode(), list, level + 1);
		addNodeToList(root, list, level);
		inOrderSearch(root.getRightNode(), list, level + 1);
		return list;		
	}
	
	public static void main(String[] args) {
		BSTree<Integer> tree = new BSTree<Integer>(5);
		tree.insert(new BSTree<Integer>(3));
		tree.insert(new BSTree<Integer>(7));
		tree.insert(new BSTree<Integer>(2));
		tree.insert(new BSTree<Integer>(4));
		tree.insert(new BSTree<Integer>(1));
		tree.insert(new BSTree<Integer>(6));
		tree.insert(new BSTree<Integer>(9));
		tree.insert(new BSTree<Integer>(8));
		tree.insert(new BSTree<Integer>(10));

		List<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>();
		printDepths.inOrderSearch(tree, list, 0);

		for (LinkedList<Integer> values : list) {
			values.forEach(v -> System.out.print(v + " "));
			System.out.println();
		}
	}

}
