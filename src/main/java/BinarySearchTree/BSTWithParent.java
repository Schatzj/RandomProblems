package BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

public class BSTWithParent <T extends Comparable<T>> {
	private BSTWithParent<T> rightNode = null;
	private BSTWithParent<T> leftNode = null;
	private BSTWithParent<T> parent = null;
	private T data = null;
	
	public BSTWithParent(T data) {
		super();
		this.data = data;
	}
	
	public BSTWithParent() {}
	
	public void insert(BSTWithParent<T> value) {
		insertNode(value, null);
	}
	
	private void insertNode(BSTWithParent<T> value, BSTWithParent<T> parent) {
		if(data == null) {
			this.data = value.getData();
			this.parent = parent;
		}
		
		if(data.compareTo(value.getData()) > 0) {
			if(leftNode != null) {
				leftNode.insertNode(value, this);
			}else {
				leftNode = value;
				leftNode.parent = this;
			}
		}
		
		if(data.compareTo(value.getData()) < 0) {
			if(rightNode != null) {
				rightNode.insertNode(value, this);
			}else {
				rightNode = value;
				rightNode.parent = this;
			}
		}
	}
	
	public BSTWithParent retrieve (T value) {
		if(this.data == value) {
			return this;
		}
		
		if(this.data.compareTo(value) < 0) {
			if(this.rightNode != null) {
				return rightNode.retrieve(value);
			}else {
				return null;
			}
		}
		
		if(this.data.compareTo(value) > 0) {
			if(this.leftNode != null) {
				return leftNode.retrieve(value);
			}else {
				return null;
			}
		}
		return null;
	}
	
	public BSTWithParent delete (T value) {
		if(this.data == value) {
			//this is the desired node. remove and return it. 
			
			if(hasChildren()) {
				//the node has children. 
				//replace the node with the smallest node in the right tree. 
				//if the right tree is null, replace the node with the largest value in the left tree. 
				BSTWithParent<T> replacmentNode = this.rightNode.smallestNodeInRightTree();
				boolean replacementFromRight = true;
				if(replacmentNode == null) {
					replacmentNode = this.leftNode.largestNodeInLeftTree();
					replacementFromRight = false;
				}
				
				//remove the replacement node from its parents sub tree. 
				BSTWithParent<T> oldParent = replacmentNode.getParent();
				if(oldParent.rightNode == replacmentNode) {
					oldParent.rightNode = null;
				}else {
					oldParent.leftNode = null;
				}
				
				//remove the current node from its parents subtree, and replace it with the replacement node. 
				if(parent.rightNode == this) {
					parent.rightNode = replacmentNode;
				}else {
					parent.leftNode = replacmentNode;
				}
				
				if(replacementFromRight) {
					//update the replacements left subTree. 
					//update the replacement nodes subtrees. 
					replacmentNode.leftNode = this.leftNode;
					if(replacmentNode.leftNode != null) {
						replacmentNode.leftNode.parent = replacmentNode;
					}
				}else {
					replacmentNode.rightNode = this.rightNode;
					if(replacmentNode.rightNode != null) {
						replacmentNode.rightNode.parent = replacmentNode;
					}
				}
				return this;
			}
		}
		
		if(this.data.compareTo(value) < 0) {
			if(this.rightNode != null) {
				rightNode.delete(value);
			}else {
				return null;
			}
		}
		
		if(this.data.compareTo(value) > 0) {
			if(this.leftNode != null) {
				leftNode.delete(value);
			}else {
				return null;
			}
		}
		return null;
	}
	
	private BSTWithParent<T> smallestNodeInRightTree() {
		BSTWithParent<T> currSmallest = this;
		BSTWithParent<T> activeNode = rightNode;
		while(activeNode != null) {
			activeNode = activeNode.leftNode;
			if(activeNode != null) {
				currSmallest = activeNode;
			}
		}
		
		return currSmallest;
	}
	
	private BSTWithParent<T> largestNodeInLeftTree(){
		BSTWithParent<T> currLargest = this;
		BSTWithParent<T> activeNode = rightNode;
		while(activeNode != null) {
			activeNode = activeNode.rightNode;
			if(activeNode != null) {
				currLargest = activeNode;
			}
		}
		
		return currLargest;
	}
	
	public boolean hasChildren() {
		if(this.leftNode != null) {
			return true;
		}
		if(this.rightNode != null) {
			return true;
		}
		return false;
	}
	
	public int getDepth() {
		return findDepth(this, 0);
	}
	
	private int findDepth(BSTWithParent node, int currDepth) {
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
		Queue<BSTWithParent> queue = new LinkedList<>();
		queue.add(this);
		while(queue.size() > 0) {
			Queue<BSTWithParent> altqueue = new LinkedList<>();
			int spacesToPrint = totalSpaces / (1 + queue.size()) - 2;
			spacesToPrint = Math.max(2, spacesToPrint);
			
			while(queue.size() > 0) {
				BSTWithParent node = queue.remove();
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
	
	public BSTWithParent<T> getRightNode() {
		return rightNode;
	}
	public void setRightNode(BSTWithParent<T> rightNode) {
		this.rightNode = rightNode;
	}
	public BSTWithParent<T> getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(BSTWithParent<T> leftNode) {
		this.leftNode = leftNode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BSTWithParent<T> getParent(){
		return this.parent;
	}
}
