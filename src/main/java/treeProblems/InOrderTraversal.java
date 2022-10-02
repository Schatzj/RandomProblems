package treeProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import BinarySearchTree.BSTree;

public class InOrderTraversal {
	
	public static void inOrderPrint(BSTree<Integer> node) {
		if(node == null) {
			return; 
		}
		
		inOrderPrint(node.getLeftNode());
		System.out.println(node.getData());
		inOrderPrint(node.getRightNode());
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
		
		inOrderPrint(tree);
	}


}
