package linkedLists;

public class LinkedList {
	
	int data; 
	LinkedList next;
	
	public LinkedList() {}
	
	public LinkedList(int data) {
		this.data = data;
	}
	
	public LinkedList(int data, LinkedList next) {
		super();
		this.data = data;
		this.next = next;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public LinkedList getNext() {
		return next;
	}
	public void setNext(LinkedList next) {
		this.next = next;
	}
	
	public void addToList(LinkedList node) {
		//get the last node; 
		LinkedList currNode = this;
		while(currNode.next != null) {
			currNode = currNode.next;
		}
		
		currNode.next = node;
	}
	
	public int length() {
		int size = 1;
		LinkedList currNode = this;
		while(currNode.next != null) {
			currNode = currNode.next;
			size++;
		}
		
		return size;
	}
	
	public void printList() {
		LinkedList currNode = this;
		while(currNode.next != null) {
			System.out.println(currNode.data);
			currNode = currNode.next;
		}
		
		System.out.println(currNode.data);
	}

}
