package BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

public class BSTree <T extends Comparable<T>>{
	private BSTree<T> rightNode = null;
	private BSTree<T> leftNode = null;
	private T data = null;
	
	public BSTree(T data) {
		super();
		this.data = data;
	}
	
	public BSTree() {}
	
	public void insert(BSTree<T> value) {
		if(this == null || this.getData() == null) {
			this.setData(value.getData());
		}
		if(value.getData().compareTo(this.data) <= 0) {
			if(this.leftNode != null) {
				leftNode.insert(value);
			}else {
				this.setLeftNode(value);
			}
		}else {
			if(this.rightNode != null) {
				this.rightNode.insert(value);
			}else {
				this.rightNode = value;
			}
		}
	}
	
	public BSTree find(T value) {
		BSTree result = search(this, value);
		return result;
	}
	
	private BSTree search(BSTree<T> bsTree, T value) {
		if(bsTree == null) {
			return null; 
		}
		
		int comparison = bsTree.getData().compareTo(value);
		if( comparison == 0) {
			return bsTree;
		}
		
		BSTree result;
		if(comparison < 0) {
			result = search(bsTree.getRightNode(), value);
		}else {
			result = search(bsTree.getLeftNode(), value);
		}
		
		return result;
	}

	public BSTree retrieve(T value) {
		//find the node
		BSTree node = null;
		BSTree currNode = this;
		BSTree parent = null;
		
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
			BSTree target = null;
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
	
	private int findDepth(BSTree node, int currDepth) {
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
		Queue<BSTree> queue = new LinkedList<>();
		queue.add(this);
		while(queue.size() > 0) {
			Queue<BSTree> altqueue = new LinkedList<>();
			int spacesToPrint = totalSpaces / (1 + queue.size()) - 2;
			spacesToPrint = Math.max(2, spacesToPrint);
			
			while(queue.size() > 0) {
				BSTree node = queue.remove();
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
	
	public BSTree<T> getRightNode() {
		return rightNode;
	}
	public void setRightNode(BSTree<T> rightNode) {
		this.rightNode = rightNode;
	}
	public BSTree<T> getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(BSTree<T> leftNode) {
		this.leftNode = leftNode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
