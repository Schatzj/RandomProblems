/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package linkedLists;

public class LLAddition {
	
	
	/*
	 * Given two linked lists, where each linked list represents a integer number (where the root of the node
	 * is the highest order value of the number i.e. 123 would be represented as a list of 1 -> 2 ->3)
	 * add the numbers, and return the result as a linked list. 
	 */
    public static LinkedList inOrderAdd(LinkedList nodeOne, LinkedList nodeTwo) {
    	//ensure the linked lists have the same length. so that we are adding 100 to 100 and 10s to 10s, 1s to 1s and so on. 
    	while(nodeOne.length() > nodeTwo.length()) {
    		LinkedList node = new LinkedList(0);
    		node.next = nodeTwo;
    		nodeTwo = node;
    	}
    	while(nodeOne.length() < nodeTwo.length()) {
    		LinkedList node = new LinkedList(0);
    		node.next = nodeOne;
    		nodeOne = node;
    	}
    	
    	if(nodeOne.next == null) {
    		return new LinkedList(nodeOne.data + nodeTwo.data);
    	}else {
    		int sum = nodeOne.data + nodeTwo.data;
    		LinkedList result = inOrderAdd(nodeOne.next, nodeTwo.next);
    		boolean carry = result.data / 10 > 0;
    		if(carry) {
    			sum++;
    			result.setData(result.data % 10);
    		}
    		LinkedList summation = new LinkedList(sum, result);
    		return summation;
    	}
    }
    
    /*
     * Does the same as above, but the number is represented in reverse order. 
     * That is the root of the list is the 1's followed by 10s and so on. 
     */
    public static LinkedList reverseOrderAdd(LinkedList nodeOne, LinkedList nodeTwo) {
    	if(nodeOne == null) {
    		nodeOne = new LinkedList(0);
    	}
    	if(nodeTwo == null) {
    		nodeTwo = new LinkedList(0);
    	}
    	
    	LinkedList longerList = (nodeOne.length() >= nodeTwo.length()) ? nodeOne : nodeTwo;
    	if(longerList.next != null) {
    		int sum = nodeOne.data + nodeTwo.data;
    		if(sum / 10 > 0) {
    			sum = sum %10;
    			longerList.next.setData(longerList.next.data + 1);
    		}
    		return new LinkedList(sum, reverseOrderAdd(nodeOne.next, nodeTwo.next));
    	}else {
    		int sum = nodeOne.data + nodeTwo.data;
    		if(sum / 10 > 0) {
    			sum = sum %10;
    			return new LinkedList(sum, new LinkedList(1));
    		}
    		return new LinkedList(sum);
    	}
    	
    }

	public static void main (String[] args) {
    	LinkedList one = new LinkedList(3);
    	one.addToList(new LinkedList(5));    	
    	one.addToList(new LinkedList(8));
    	
    	LinkedList two = new LinkedList(3);
    	two.addToList(new LinkedList(7));
    	two.addToList(new LinkedList(9));
    	two.addToList(new LinkedList(3));
    	
    	LinkedList result = inOrderAdd(one, two);
    	result.printList();
    	System.out.println("------------------------------------");
    	result = reverseOrderAdd(one, two);
    	result.printList(); // should be 4826
    }
}
