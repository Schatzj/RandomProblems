package MinHeap;
import java.lang.reflect.Array;

//cheat code https://www.softwaretestinghelp.com/heap-data-structure-in-java/
//I feel like it is worth noting, I did not really use the cheat code to develop this. 
//I just wanted the to keep the link for future reference in case I wanted to see another implementation.
//index cheat 2i + 1 and 2i + 2, and its parent is at index ⌊(i-1)/2⌋.

public class MinHeap<T extends Comparable<T>> {
	private T[] data;
	private int size = 0;
	
	public MinHeap(Class<T> clazz, int capacity) {
		data = (T[]) Array.newInstance(clazz, capacity);
	}
	
	public void insert(T clazz) throws Exception {
		if(data[size] != null) {
			throw new Exception("insert error: cell already contains a value");
		}else {
			data[size] = clazz;
		}
		size++; 
		
		//sift;
		siftUp(size-1);
		
		parentsAreSmallerThanChilder();
	}
	
	public void showState() {
		for(int i = 0; i<data.length; i++) {
			System.out.print(data[i] + " ");
		}
	}
	
	public void siftUp(int index) {
		try {
			int parentIndex = (index - 1)/2;
			int leftChildIndex = parentIndex*2 +1;
			int smallestChildIndex; 
			T parent = data[parentIndex];
			T leftChild = data[leftChildIndex];
			T rightChild;
			if(leftChildIndex + 1 < data.length) {
				rightChild = data[leftChildIndex + 1];
			}else {
				rightChild = null;
			}
			T smallestChild;
			
			if(leftChild == null && rightChild == null) {
				smallestChild = parent;
				smallestChildIndex = parentIndex;
			}else if(rightChild == null) {
				smallestChild = leftChild;
				smallestChildIndex = leftChildIndex;
			}else {
				if(leftChild.compareTo(rightChild) > 0) { //possible null pointers. 
					smallestChild = rightChild;
					smallestChildIndex = leftChildIndex + 1;
				}else {
					smallestChild = leftChild;
					smallestChildIndex = leftChildIndex;
				}
			}
			
			
			while(parent.compareTo(smallestChild) > 0) {
				//the parent is larger. Swap parent and child
				data[parentIndex] = smallestChild;
				data[smallestChildIndex] = parent;
				
				//update values so the loop can continue. 
				parentIndex = (parentIndex -1)/2;
				leftChildIndex = parentIndex*2 +1;
				parent = data[parentIndex];
				leftChild = data[leftChildIndex];
				rightChild = data[leftChildIndex + 1];
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void siftDown(int startIndex) {
		int parentIndex = startIndex;
		int leftChildIndex = parentIndex*2 + 1;
		int smallestChildIndex;
		T parent = data[parentIndex];
		T leftChild = data[leftChildIndex];
		T rightChild = data[leftChildIndex+1];
		T smallestChild; 
		
		while(parentIndex < data.length) {
			if(parent == null) {
				break;
			}
			if(leftChild != null && rightChild != null) {
				if(leftChild.compareTo(rightChild) > 0) {
					smallestChild = rightChild;
					smallestChildIndex = leftChildIndex +1;
				}else {
					smallestChild = leftChild;
					smallestChildIndex = leftChildIndex;
				}
			}else {
				if(leftChild != null) {
					smallestChild = leftChild;
					smallestChildIndex = leftChildIndex;
				}else if(rightChild != null) {
					smallestChild = rightChild;
					smallestChildIndex = leftChildIndex +1;
				}else {
					break;
				}
			}
			
			if(parent.compareTo(smallestChild)> 0) {
				//swap parent and smallest child
				data[parentIndex] = smallestChild;
				data[smallestChildIndex] = parent;
			}
			
			//setup to continue in the smallest child direction. 
			parentIndex = smallestChildIndex;
			leftChildIndex = parentIndex*2 + 1;
			smallestChildIndex = leftChildIndex;
			parent = data[parentIndex];
			if(leftChildIndex < data.length) {
				leftChild = data[leftChildIndex];
				if(leftChildIndex + 1 < data.length) {
					rightChild = data[leftChildIndex+1];
				}else {
					rightChild = null;
				}				
			}else {
				leftChild = null;
				rightChild = null;
			}		
			smallestChild = null;
			
		}
	}

	public T peak() {
		return data[0];
	}
	
	public String toString() {
		String result = "";
		if(size > 0) {
			result = "" + data[0];
		}
		
		for(int i = 1; i<size; i++) {
			result = result + " " + data[i];
		}
		
		return result;
	}
	
	public T retrieve(){
		T temp = data[0];
		
		//replace data[0] with bottom right most value. 
		if(size > 0) {
			data[0] = data[size-1];
			data[size -1] = null;
			size--;
			siftDown(0);
		}		
		
		//sift to ensure heap property. 
		
		
		parentsAreSmallerThanChilder();
		return temp;
	}
	
	public T delete() {
		return retrieve();
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty () {
		return size == 0;
	}	
	
	public T[] asArray() {
		return data;
	}
	
	public boolean parentsAreSmallerThanChilder() {
		int parentIndex = 0;
		int lci = 0;
		T parent = data[parentIndex]; 
		T leftChild = data[lci];
		T rightChild = data[lci + 1];
		
		while(parentIndex < data.length) {
			if(parent == null) {
				break;
			}
			if(leftChild != null) {
				if(parent.compareTo(leftChild) > 0) {
					System.out.println("Parent is greater than child; parent: " + parent + " leftChild " + leftChild + " rightChild: " +rightChild + " parentIndex " + parentIndex);
					return false;
				}
			}
			if(rightChild != null) {
				if(parent.compareTo(rightChild) > 0) {
					System.out.println("Parent is greater than child; parent: " + parent + " leftChild " + leftChild + " rightChild: " +rightChild + " parentIndex " + parentIndex);
					return false;
				}
			}
			
			parentIndex = parentIndex + 1;
			lci = parentIndex*2 + 1;
			if(parentIndex < data.length) {
				parent = data[parentIndex];
			}else {
				break;
			}
			
			if(lci < data.length) {
				leftChild = data[lci];
			}else {
				leftChild =null;
			}
			
			if(lci+1 < data.length) {
				rightChild = data[lci + 1];
			}else {
				rightChild =null;
			}
			
		}
		return true;
	}
}
