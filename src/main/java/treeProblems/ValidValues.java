package treeProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import BinarySearchTree.BSTree;
import BinarySearchTree.BinaryTree;

/*
 * checks the BSTree to ensure the values are legal. 
 * That is ensures that evey node in the left subtree is smaller or equal to the current node, 
 * and that every node in the right subtree is larger than the current node.  
 */
public class ValidValues {
	
	public static boolean isValidTree(BinaryTree<Integer> node, int min, int max) {
		if(node == null) {
			return true;
		}
		
		boolean isLeftValid = isValidTree(node.getLeftNode(), Integer.MIN_VALUE, node.getData());
		boolean isRightValid = isValidTree(node.getRightNode(), node.getData(), Integer.MAX_VALUE);
		
		boolean selfIsValid = (min<=node.getData() && node.getData() < max);
		
		return isLeftValid && isRightValid && selfIsValid;
	}
	
	public static boolean isValidTree(BSTree<Integer> node, int min, int max) {
		if(node == null) {
			return true;
		}
		
		boolean isLeftValid = isValidTree(node.getLeftNode(), Integer.MIN_VALUE, node.getData());
		boolean isRightValid = isValidTree(node.getRightNode(), node.getData(), Integer.MAX_VALUE);
		
		boolean selfIsValid = (min<=node.getData() && node.getData() < max);
		
		return isLeftValid && isRightValid && selfIsValid;
	}
	
	
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(5);
		tree.insert(new BinaryTree<Integer>(3));
		tree.insert(new BinaryTree<Integer>(7));
		tree.insert(new BinaryTree<Integer>(2));
		tree.insert(new BinaryTree<Integer>(4));
		tree.insert(new BinaryTree<Integer>(1));
		tree.insert(new BinaryTree<Integer>(6));
		tree.insert(new BinaryTree<Integer>(9));
		tree.insert(new BinaryTree<Integer>(8));
		tree.insert(new BinaryTree<Integer>(10));

		System.out.println(tree);
		System.out.println(isValidTree(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
		
		BSTree<Integer> tree2 = new BSTree<Integer>(5);
		tree2.insert(new BSTree<Integer>(3));
		tree2.insert(new BSTree<Integer>(7));
		tree2.insert(new BSTree<Integer>(2));
		tree2.insert(new BSTree<Integer>(4));
		tree2.insert(new BSTree<Integer>(1));
		tree2.insert(new BSTree<Integer>(6));
		tree2.insert(new BSTree<Integer>(9));
		tree2.insert(new BSTree<Integer>(8));
		tree2.insert(new BSTree<Integer>(10));
		
		System.out.println(isValidTree(tree2, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}
}
