package treeProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import BinarySearchTree.BSTree;

/*
 * Determines if a BSTree is balanced. 
 * It is balanced if the height of each subtree differes by no more than one. 
 */
public class CheckBalanced {
	
	public static int INBALANCED = -1;
	
	public static int countChildren(BSTree<Integer> node) { 
		int children = 0;
		if(node == null) {
			return children;
		}
		
		int leftChildren = countChildren(node.getLeftNode());
		int rightChildren = countChildren(node.getRightNode());
//		int difference = leftChildren - rightChildren;
		
//		if(difference < 2 && difference > -2) {
//			return leftChildren + rightChildren + 1;
//		}else {
//			System.out.println("we are returning -1 the node is: " + node.getData());
//			//ok this isn't working because we are counting children, not depth. 
//			return -1;
//		}
		return leftChildren + rightChildren + 1;
	}
	
	public static int isBalanced(BSTree<Integer> node, int depth) {
		if(node == null) {
			return depth;
		}
		
		int leftDepth = isBalanced(node.getLeftNode(), depth);
		int rightDepth = isBalanced(node.getRightNode(), depth);
		int depthDifference = leftDepth - rightDepth;
		
//		System.out.println("left Depth: " + leftDepth + " right Depth: " + rightDepth);
		
		if(depthDifference < -1 || depthDifference > 1) {
//			System.out.println("node: " + node.getData() + " is returning INBALANCED");
			return INBALANCED;
		}
		
		int maxDepth = Math.max(leftDepth, rightDepth);
//		System.out.println("node: " + node.getData() + " returning: " + maxDepth);
		return maxDepth + 1;
	}
	
	public static void printDepth(BSTree<Integer> node, int depth) {
		if(node == null) {
			return; 
		}
		
		printDepth(node.getLeftNode(), depth + 1);
		printDepth(node.getRightNode(), depth + 1);
		System.out.println("node: " + node.getData() + " is at depth: " + depth);
		
	}
	
	public static void main(String[] args) {
		BSTree<Integer> tree = new BSTree<Integer>(5);
		tree.insert(new BSTree<Integer>(3));
		tree.insert(new BSTree<Integer>(7));
		tree.insert(new BSTree<Integer>(2));
		tree.insert(new BSTree<Integer>(4));
		tree.insert(new BSTree<Integer>(1));
//		tree.insert(new BSTree<Integer>(6));
//		tree.insert(new BSTree<Integer>(9));
//		tree.insert(new BSTree<Integer>(8));
//		tree.insert(new BSTree<Integer>(10));

		int isBalanced = CheckBalanced.isBalanced(tree, 0);
		if(isBalanced != -1) {
			System.out.println("the tree is balanced");
		}else {
			System.out.println("the tree is NOT balacned");
		}
//		System.out.println("the result is: " + CheckBalanced.countChildren(tree));
//		CheckBalanced.printDepth(tree, 0);
	}

}
