package BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * a binary tree but not a binary search tree. 
 * nodes are added in a somewhat random fashion. 
 * 
 * nodes are added to the left and then the right. 
 * will attempt to add 3 to the left, and then 3 to the right. 
 * this was done quickly to test valid Values, so not much care was taken. 
 */
public class BinaryTree <T extends Comparable<T>> {
	private BinaryTree<T> rightNode = null;
	private BinaryTree<T> leftNode = null;
	private T data = null;
	private int sideCount = -2;
	
	public BinaryTree(T data) {
		super();
		this.data = data;
	}
	
	public BinaryTree() {}
	
	public void insert(BinaryTree<T> value) {
		if(this == null || this.getData() == null) {
			this.setData(value.getData());
		}
		if(this.leftNode == null) {
			this.leftNode = value;
			sideCount += 1;
			return;
		}else if(this.rightNode == null) {
			this.rightNode = value;
			sideCount += 1;
			if(sideCount > 3) {
				sideCount = -2;
			}
			return;
		}
		
		if(sideCount < 1) {
			leftNode.insert(value);
			sideCount += 1;
		}else {
			rightNode.insert(value);
			sideCount += 1;
			if(sideCount > 3) {
				sideCount = -2;
			}
		}
	}
	
	public BinaryTree retrieve(T value) {
		//find the node
		BinaryTree node = null;
		BinaryTree currNode = this;
		BinaryTree parent = null;
		
		while(currNode != null) {
			if(currNode.getData().compareTo(value) == 0) {
				break;
			}else if(currNode.getData().compareTo(value) > 0) {
				parent = currNode;
				currNode = currNode.getLeftNode();
			}else {
				parent = currNode;
				currNode = currNode.getRightNode();
			}
		}
		
		//currNode is the node we want;
		//If it has no children remove it. 
		if(currNode.getLeftNode() == null && currNode.getRightNode() == null) {
			if(parent.getLeftNode().getData().compareTo(value) == 0) {
				node = parent.getLeftNode();
				parent.setLeftNode(null);
			}else {
				node = parent.getRightNode();
				parent.setRightNode(null);
			}
		}else if((currNode.getLeftNode() == null && currNode.getRightNode() != null) || 
				(currNode.getLeftNode() != null && currNode.getRightNode() == null)) { //if currNode has one child replace it with its child
			if(currNode.getLeftNode() != null) {
				node = currNode;
				parent.setLeftNode(currNode.getLeftNode());
			}else {
				node = currNode;
				parent.setRightNode(currNode.getLeftNode());
			}
		}else if(currNode.getLeftNode() != null && currNode.getRightNode() != null) {//if the currNode has two children. 
			BinaryTree target = null;
			//either the rightmost child of the left tree, or the leftmost child of the right tree. 
			//rightmost child of left tree
			target = currNode.getLeftNode();
			int count = 0;
			while(target.getRightNode() != null) {
				target = target.getRightNode();
				count++;
			}
			if(count < 1) {
				//There was no rightmost child on the left tree search for leftmost child of right Tree. 
				target = currNode.getRightNode();
				while(target.getLeftNode() != null) {
					target = target.getLeftNode();
				}
			}
			
			if(parent.getLeftNode().getData().compareTo(value) == 0) {
				node = parent.getLeftNode();
				parent.setLeftNode(target);
			}else {
				node = parent.getRightNode();
				parent.setRightNode(target);
			}
		}	
		
		return node;
	}
	
	public int getDepth() {
		return findDepth(this, 0);
	}
	
	private int findDepth(BinaryTree node, int currDepth) {
		if(node == null) {
			return currDepth;
		}
		
		int leftDepth = findDepth(node.getLeftNode(), currDepth + 1);
		int rightDepth = findDepth(node.getRightNode(), currDepth + 1);
		return Math.max(leftDepth, rightDepth);		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int depth = getDepth();
		int totalSpaces = depth * depth;
		int numbOfSpaces = totalSpaces;
		Queue<BinaryTree> queue = new LinkedList<>();
		queue.add(this);
		while(queue.size() > 0) {
			Queue<BinaryTree> altqueue = new LinkedList<>();
			int spacesToPrint = totalSpaces / (1 + queue.size()) - 2;
			spacesToPrint = Math.max(2, spacesToPrint);
			
			while(queue.size() > 0) {
				BinaryTree node = queue.remove();
				for(int i = 0; i<spacesToPrint; i++) {
					builder.append(" ");
				}
				String value = (node == null || node.getData() == null) ? "" : node.getData().toString();
				builder.append(value);
				if(node != null) {
					altqueue.add(node.getLeftNode());
					altqueue.add(node.getRightNode());
				}				
			}
			builder.append("\r\n");
			queue = altqueue;
		}
		
		return builder.toString();
	}
	
	public BinaryTree<T> getRightNode() {
		return rightNode;
	}
	public void setRightNode(BinaryTree<T> rightNode) {
		this.rightNode = rightNode;
	}
	public BinaryTree<T> getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(BinaryTree<T> leftNode) {
		this.leftNode = leftNode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}	
}
