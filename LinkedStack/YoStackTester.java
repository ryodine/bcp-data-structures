import java.util.EmptyStackException;
/**
 * YoStackTester
 * Test Class
 */
public class YoStackTester {
	/**
	 * Supplied tester code
	 */
    public static void main(String[] args) {
        YoStack<String> dishes = new YoStack<String>();
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing H, E, L, L, O");
		dishes.push("H");
		dishes.push("E");
		dishes.push("L");
		dishes.push("L");
		dishes.push("O");

		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()){
			System.out.println("Popping: "+dishes.pop());
		}
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 1");
		dishes.push("1");
		System.out.println("Is it empty: " + dishes.isEmpty());
		System.out.println("Now pushing 2, 3, 4, 5");
		dishes.push("2");
		dishes.push("3");
		dishes.push("4");
		dishes.push("5");
		System.out.println("The top element is: " + dishes.peek());
		System.out.println("Removing " + dishes.pop() );
		System.out.println("Removing "+ dishes.pop() );
		System.out.println("Now pushing Last");
		dishes.push("Last");
		System.out.println("The top element is: " + dishes.peek());

		while (!dishes.isEmpty()){
			System.out.println("Popping: " +dishes.pop());
		}
    }
}

class YoStack<T> {
    
    private Node head;
    
    /**
     * returns if stack is empty as boolean
     */
    public boolean isEmpty() {
        return (head == null);
    }
    
    /**
     * pushes a node to the top and returns what you just pushed
     */
    public T push(T data) {
        head = new Node(data, head);
        return data;
    }
    
    /**
     * returns and removes top node
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            T oldVal = head.data;
            head = head.next;
            return oldVal;
        }
    }
    
    /**
     * returns top node
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return head.data;
        }
    }
    
    private class Node 	{ 
		public T data;
		public Node next;
		public Node(T dat, Node nex) {
		    data = dat;
		    next = nex;
		}
	}
}