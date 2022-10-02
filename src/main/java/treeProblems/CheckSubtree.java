package treeProblems;

import BinarySearchTree.BSTree;

/*
 * Given two binary search trees T1 and T2 determines if T2 is a sub tree of T1.
 * This algorithm should work for any binary tree. 
 * In otherwords if a node exists in T1, that if it was cut out, would form the tree T2. 
 */
public class CheckSubtree {
	
	public boolean isSubTree(BSTree t1, BSTree t2) {
		boolean result = false;
		
		BSTree node = t1.find(t2.getData());
		
		if(node == null) {
			return false;
		}else {
			return isSameTree(node, t2);
		}
		
//		return result;
	}
	
	public boolean isSameTree(BSTree t1, BSTree t2) {
		if(t1 == null && t2 == null) {
			return true;
		}
		
		if(t1 == null || t2 == null) {
			return false;
		}
		
		if(t1.getData().compareTo(t2.getData()) == 0) {
			boolean isSame = isSameTree(t1.getLeftNode(), t2.getLeftNode());
			if(isSame) {
				isSame = isSameTree(t1.getRightNode(), t2.getRightNode());
				return isSame;
			}else {
				return false;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		CheckSubtree cs = new CheckSubtree();
		
		BSTree<Integer> t1 = new BSTree<Integer>(50);
		t1.insert(new BSTree<Integer>(25));
		t1.insert(new BSTree<Integer>(60));
		t1.insert(new BSTree<Integer>(12));
		t1.insert(new BSTree<Integer>(70));
		BSTree<Integer> insertedNode = new BSTree<Integer>(6);
		t1.insert(insertedNode);
		t1.insert(new BSTree<Integer>(80));
		t1.insert(new BSTree<Integer>(3));
		t1.insert(new BSTree<Integer>(2));
		t1.insert(new BSTree<Integer>(1));
		t1.insert(new BSTree<Integer>(4));
		t1.insert(new BSTree<Integer>(55));
		t1.insert(new BSTree<Integer>(51));
		t1.insert(new BSTree<Integer>(56));
		t1.insert(new BSTree<Integer>(30));
		t1.insert(new BSTree<Integer>(40));
		t1.insert(new BSTree<Integer>(65));
		t1.insert(new BSTree<Integer>(63));
		t1.insert(new BSTree<Integer>(69));
		t1.insert(new BSTree<Integer>(72));
		t1.insert(new BSTree<Integer>(75));
		
		
		BSTree<Integer> t2 = new BSTree<Integer>(6);
		t2.insert(new BSTree<Integer>(3));
		t2.insert(new BSTree<Integer>(2));
		t2.insert(new BSTree<Integer>(1));
		t2.insert(new BSTree<Integer>(4));
		
		boolean isSubTree = cs.isSubTree(t1, t2);
		System.out.println(isSubTree);
	}

}
