import java.util.NoSuchElementException;
import java.util.ListIterator; 

public class SingleLink_Johnson {
    public static void main(String[] args){
        SingleLinkedList<Integer> l = new SingleLinkedList<Integer>();
        l.addFirst(1);
        l.addFirst(2);
        l.addFirst(3);
        l.add(1,100);
        l.remove(0);
        l.removeFirst();
        l.addLast(123);
        l.removeLast();
        l.printList();
        System.out.println("List Size: " + l.size());
        System.out.println("First Item: " + l.getFirst());
        System.out.println("Second Item: " + l.get(1));
    }
    
    
}

class SingleLinkedList<E> {

	public Node first;
	
	public int size() {
		int size = 0;
		boolean isgood = true;
		Node last = first;
    	while (isgood) {
    		try {
    			last = last.next;
    			size++;
    		} catch (NullPointerException e) {
    			isgood = false;
    		}
    	}
    	return size;
	}
	
	public E removeLast() {
		return remove(size()-1);
	}
	
	public void addLast(E element) {
		Node newN = new Node();
		newN.data = element;
		newN.next = null;
		nodeGet(size()-1).next = newN;
	}
   
   	public void printList() {
    	Node last = first;
    	boolean isgood = true;
    	while (isgood) {
    		try {
    			System.out.print(last.hashCode() + ": ");
    			System.out.println(last.data);
    			last = last.next;
    		} catch (NullPointerException e) {
    			isgood = false;
    		}
    	}
    }
   
	private class Node 	{ 
		public E data;
		public Node next;
	}
	
	public E get(int index){
		return nodeGet(index).data;
	}
	
	private Node nodeGet(int index) {
		Node last = first;
		for (int i = 0; i < index; i++) {
			last = last.next;
		}
		return last;
	}
	
	public E remove(int index) {
		E removal = get(index);
		if (index == 0) {
			removeFirst();
		}
		Node last = first;
		for (int i = 0; i < index; i++) {
			if (i == index - 1) {
				last.next = last.next.next;
			}
			last = last.next;
		}
		return removal;
	}
	
	public void add(int index, E element) throws NoSuchElementException {
		Node newN = new Node();
		newN.data = element;
		if (index == 0) {
			addFirst(element);
		}
	    int i = 0;
	    Node last = first;
	    while (i < index) {
	    	if (i == index - 1) {
		    	if (last.next != null)
		    		newN.next = last.next;
		    	if (last != null) {
		    		last.next = newN;
		    	} else {
		    		first = newN;
		    	}
	    	}
	    	try {
	        	last = last.next;
	    	} catch (NullPointerException e) {
	    		throw new NoSuchElementException();
	    	}
	        i++;
	    }
	}
	
	public SingleLinkedList() {
		first = null;
	}
	
	/**
	* @return the first element in the linked list
	*/	
	public E getFirst() {
		if (first == null)
			throw new NoSuchElementException();
		return first.data;
	}
	
	/**
	* Removes the first element in the linked list.
	* @return the removed element
	*/
	public E removeFirst() {
		if (first == null)
			throw new NoSuchElementException();
		E element = first.data;
		first = first.next;
		return element;
	}

	/**
	* Adds an element to the front of the linked list.
	* @param element the data to store in the linked list
	*/
	public void addFirst(E element)  {
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = first;
		first = newNode;
	}
	
   

}