package treeProblems;

import BinarySearchTree.BinaryTree;

/*
 * Given an array creates a BST tree with a minimum height. 
 */
public class MinimumTree {
	
	public static BinaryTree<Integer> convertArrayToTree(int[] array){
		BinaryTree<Integer> root = new BinaryTree<Integer>();
		return addArrayToTree(root, array, 0, array.length);
	}
	
	private static BinaryTree addArrayToTree(BinaryTree node, int[] array, int start, int stop) {
		int mid = (start + stop) / 2;
		if(start > stop || mid >= array.length) {
			return null;
		}
		node.setData(array[mid]);
		
		BinaryTree<Integer> leftNode = new BinaryTree<Integer>();
		BinaryTree<Integer> rightNode = new BinaryTree<Integer>();
		node.setLeftNode(leftNode);
		node.setRightNode(rightNode);
		
		addArrayToTree(leftNode, array, start, mid-1);
		addArrayToTree(rightNode, array, mid+1, stop);
		
		return node;
	}
	
	public static void main(String[] args) {
		int[] array = {1,2,3,4,5,6,7,8,9,10, 11, 12};
		BinaryTree<Integer> result = convertArrayToTree(array);
		System.out.println(result);
	}

}
