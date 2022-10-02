package MinHeap;

public class driver {
	public static void main(String[] args) {
		MinHeap<Integer> foo = new MinHeap<Integer>(Integer.class, 64);
		
		try {
			foo.insert(3);
			foo.insert(2);
//			foo.insert(1);
//			foo.insert(1);
			foo.insert(4);
			foo.insert(5);
			foo.insert(3);
			foo.insert(2);
			foo.insert(1);
			
			System.out.println(foo.peak());
			
			System.out.println(foo.toString());
			Integer value = foo.retrieve();
			System.out.println("Retreived value is: " + value);
			System.out.println(foo);
			
			value = foo.retrieve();
			System.out.println("Retreived value is: " + value);
			System.out.println(foo);
			
			value = foo.retrieve();
			System.out.println("Retreived value is: " + value);
			System.out.println(foo);
			
			foo.insert(7);
			System.out.println(foo);
			foo.insert(2);
			System.out.println(foo);
			
			foo.showState();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
