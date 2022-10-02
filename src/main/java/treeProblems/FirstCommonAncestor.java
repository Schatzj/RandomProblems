package treeProblems;

import BinarySearchTree.BSTree;
import utilityObjects.CommonAncestorHelper;

/*
 * Finds the first common ancestor of two nodes. 
 * works on binary trees. Does not require it to be a binary search tree. 
 * 
 * If we can assume it is a binary search tree. Just modify the search operation, to return the 
 * node where the path diverges. 
 */
public class FirstCommonAncestor {

	public BSTree commonAncestor(BSTree root, BSTree nodeA, BSTree nodeB){
		CommonAncestorHelper helper = new CommonAncestorHelper();
		CommonAncestorHelper result = findCommonAncestor(root, nodeA, nodeB, helper);
		
		return result.getNode();
	}
	
	private CommonAncestorHelper findCommonAncestor(BSTree root, BSTree nodeA, BSTree nodeB, CommonAncestorHelper helper) {
 		if(root == null || helper.getNode() != null || helper.getNodesFound() == 2) {
			return helper;
		}
		
		boolean isNode = false;
		if(root == nodeA || root == nodeB) {
			helper.addNodeFound();
			isNode = true;
		}
		
		CommonAncestorHelper subTreeHelper = new CommonAncestorHelper();
		CommonAncestorHelper leftHelper = new CommonAncestorHelper();
		CommonAncestorHelper rightHelper = new CommonAncestorHelper();
		
		subTreeHelper = findCommonAncestor(root.getLeftNode(), nodeA, nodeB, subTreeHelper);
		leftHelper.setNodesFound(subTreeHelper.getNodesFound());
		leftHelper.setNode(subTreeHelper.getNode());
		if(leftHelper.getNodesFound() == 2 || leftHelper.getNode() != null) {
			if(leftHelper.getNode() == null) {
				helper.setNode(root);
			}else {
				helper.setNode(leftHelper.getNode()); 
			}
		}
		subTreeHelper.setNodesFound(0);
		rightHelper = findCommonAncestor(root.getRightNode(), nodeA, nodeB, subTreeHelper);
		if(rightHelper.getNodesFound() == 2 || rightHelper.getNode() != null) {
			if(rightHelper.getNode() == null) {
				helper.setNode(root);
			}else {
				helper.setNode(rightHelper.getNode()); 
			}
		}
		
		if(!isNode) {
			if(leftHelper.getNodesFound() == 1 && rightHelper.getNodesFound() == 1) {
				if(helper.getNode() == null) {
					helper.setNode(root);
				}
				return helper;
			}
		}
		helper.addToNodesFound(leftHelper.getNodesFound());
		helper.addToNodesFound(rightHelper.getNodesFound());
		
		
		return helper;
	}
	
	public static void main(String[] args) {
		BSTree<Integer>nodeA = new BSTree<Integer>(2);
		BSTree<Integer>nodeB = new BSTree<Integer>(6);
		
		BSTree<Integer> tree = new BSTree<Integer>(5);		
		tree.insert(new BSTree<Integer>(3));
		tree.insert(new BSTree<Integer>(7));
		tree.insert(nodeA);
		tree.insert(new BSTree<Integer>(4));
		tree.insert(new BSTree<Integer>(1));
		tree.insert(nodeB);
		tree.insert(new BSTree<Integer>(9));
		tree.insert(new BSTree<Integer>(8));
		tree.insert(new BSTree<Integer>(10));
		
		FirstCommonAncestor object = new FirstCommonAncestor();
		
		BSTree ca = object.commonAncestor(tree, nodeA, nodeB);
		if(ca != null) {
			System.out.println(ca.getData());	
		}else {
			System.out.println("null");
		}
		System.out.println(tree);
	}
}


