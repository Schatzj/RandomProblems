package ArrayQueue;

/*
 * This class is simply to test my implementation. 
 * I would think a proper implementation would:
 * A: use generics
 * B: increase the size of the array instead of throwing an error when the array is full. 
 */
public class ArrayQueue {

	int[] data;
	int head = 0; 
	int tail = 0;
	boolean full = false;
	boolean empty = true;
	
	public ArrayQueue(int size) {
		this.data = new int[size];
	}
	
	public void enqueue(int value) throws Exception {
		if (full) {
			throw new Exception("Queue is full");
		}
		data[tail++] = value;
		if(tail >= data.length) {
			tail = 0;
		}
		
		if(tail == head) {
			full = true;
		}
		
		empty = false;
	}
	
	public int dequeue() throws Exception{
		//reset the. This is not actually required, but may be helpful. 
		if (empty) {
			head = 0;
			tail = 0;
			throw new Exception("Queue is Empty");
		}

		int value = data[head++];
		if(head >= data.length) {
			head = 0;
		}
		
		if(head == tail) {
			empty = true;
		}
		
		full = false;
		
		return value;
	}
	
	public int peek() {
		return data[head];
	}
	
	@Override
	public String toString() {
		String s = "Head: " + head + " tail: " + tail + " full: " + full + " empty: " + empty + " \r\n";
		for(int i : data) {
			s = s + data[i] + ", ";
		}
		
		s = s.substring(0, s.length() -2);
		
		return s;
	}
}
