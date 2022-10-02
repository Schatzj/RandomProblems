package treeProblems;

import BinarySearchTree.BSTWithParent;
import BinarySearchTree.BSTree;

/*
 * given a node in a Binary Search tree. 
 * Returns the node of the next "in-order" or successor node. 
 * This would be the node with the next largest value. 
 * 
 * 
 * not good. The book says I need (or can assume I have) a reference to each nodes parent. 
 * May need to recreate the binary search tree. May be good practice in general. 
 */
public class FindSuccessor {
	
	public static BSTWithParent getSuccessor(BSTWithParent node){
		BSTWithParent nextValue = null;
		BSTWithParent activeNode = node.getRightNode();
		while(activeNode != null) {
			nextValue = activeNode;
			activeNode = activeNode.getLeftNode();
			if(activeNode != null) {
				nextValue = activeNode;
			}
		}
		
		if(nextValue == null) { 
			//does not contain a right subtree. 
			//We need to go up the tree until we are the left child of a parent.
			//then print the parent
			BSTWithParent currNode = node;
			BSTWithParent parent = node.getParent();
			while(parent != null && parent.getLeftNode() != currNode) {
				currNode = parent; 
				parent = parent.getParent();
			}
			return parent; 
		}else {
			return nextValue;
		}
	}

	public static void main(String[] args) {
		BSTWithParent<Integer> tree = new BSTWithParent<Integer>(5);
		tree.insert(new BSTWithParent<Integer>(3));
		tree.insert(new BSTWithParent<Integer>(7));
		tree.insert(new BSTWithParent<Integer>(2));
		tree.insert(new BSTWithParent<Integer>(4));
		tree.insert(new BSTWithParent<Integer>(1));
		tree.insert(new BSTWithParent<Integer>(6));
		tree.insert(new BSTWithParent<Integer>(9));
		tree.insert(new BSTWithParent<Integer>(8));
		tree.insert(new BSTWithParent<Integer>(10));
		tree.insert(new BSTWithParent<Integer>(11));
		tree.insert(new BSTWithParent<Integer>(12));
		tree.insert(new BSTWithParent<Integer>(13));
		tree.insert(new BSTWithParent<Integer>(14));
		tree.insert(new BSTWithParent<Integer>(15));
		tree.insert(new BSTWithParent<Integer>(16));
		tree.insert(new BSTWithParent<Integer>(17));
		tree.insert(new BSTWithParent<Integer>(18));
		tree.insert(new BSTWithParent<Integer>(19));
		tree.insert(new BSTWithParent<Integer>(20));
		
		System.out.println(getSuccessor(tree).getData());
		BSTWithParent<Integer> node = tree.retrieve(7);
		System.out.println(getSuccessor(node).getData());
		node = tree.retrieve(3);
		System.out.println(getSuccessor(node).getData());
		node = tree.retrieve(1);
		System.out.println(getSuccessor(node).getData());
		node = tree.retrieve(4);
		System.out.println(getSuccessor(node).getData());
		node = tree.retrieve(15);
		System.out.println(getSuccessor(node).getData());
		
		
//		BSTWithParent<Integer> tree = new BSTWithParent<Integer>(5);
//		tree.insert(new BSTWithParent<Integer>(3));
//		tree.insert(new BSTWithParent<Integer>(7));
//		tree.insert(new BSTWithParent<Integer>(9));
//		tree.insert(new BSTWithParent<Integer>(11));
//		tree.insert(new BSTWithParent<Integer>(15));
//		tree.insert(new BSTWithParent<Integer>(29));
//		
//		System.out.println(getSuccessor(tree).getData());
//		BSTWithParent<Integer> node = tree.retrieve(15);
//		System.out.println(getSuccessor(node).getData());
//		node = tree.retrieve(29);
//		BSTWithParent result = getSuccessor(node);
//		if(result == null) {
//			System.out.println("null");
//		}else {
//			System.out.println(result.getData());
//		}
	}
	
}
