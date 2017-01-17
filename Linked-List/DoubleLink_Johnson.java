import java.util.NoSuchElementException;

public class DoubleLink_Johnson {
    public static void main(String[] args){
        DoubleLinkedList<Integer> l = new DoubleLinkedList<Integer>();
        l.addFirst(1);
        l.addFirst(2);
        l.addFirst(3);
        l.add(3,100);
        l.add(1,1001);
        l.remove(2);
        l.removeFirst();
        //System.out.println(l.last);
        l.addLast(123);
        l.removeLast();
        l.printList();
        System.out.println("List Size: " + l.size());
        System.out.println("First Item: " + l.getFirst());
        System.out.println("Second Item: " + l.get(1));

        
        l.reverse();
        l.printList();
      
    }
    
    
}

class DoubleLinkedList<E> {

	private Node first;
	
	private Node last;
	
	public void reverse() {
		Node n = first;
		Node next = n.next;
		while(next != null){
			Node prevN = new Node();
			Node nextN = new Node();
			next = n.next;
			n.next = n.previous;
			n.previous = next;
			if (next != null)
				n = next;
		}
		Node f = first;
		first = last;
		last = f;
	}
	
	public int size() {
		int size = 0;
		boolean isgood = true;
		Node lastt = first;
    	while (isgood) {
    		try {
    			lastt = lastt.next;
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
		newN.previous = last;
		newN.previous.next = newN;
		last = newN;
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
		public String print(int indentationLvl) {
			String tabs = "";
			for (int i = 0; i < indentationLvl; i++) {
				tabs = tabs + "\t";
			}
			return "NODE " + hashCode() + "\n" + tabs + "\tContent:" + data + "\n" +tabs +"\tPrev: " + ((previous != null)? previous.hashCode() : "None") + "\n" +tabs + "\tNext: " + ((next != null)? next.print(indentationLvl+1) : "None") + ((last.hashCode() == hashCode())? "\n" + tabs + "\t*Last Element*" : "");
		}
		public E data;
		public Node next;
		public Node previous;
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
		Node lastt = first;
		for (int i = 0; i < index; i++) {
			if (i == index - 1) {
				lastt.next = lastt.next.next;
				if (lastt.next != null) {
					lastt.next.previous = lastt;
				} else { 
					last = lastt;
				}
			}
			lastt = lastt.next;
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
	    Node lastt = first;
	    while (i < index) {
	    	if (i == index - 1) {
		    	if (lastt.next != null) {
		    		newN.next = lastt.next;
		    		newN.next.previous = newN;
		    	} else {
		    		last = newN;
		    	}
		    	if (lastt != null) {
		    		lastt.next = newN;
		    		newN.previous = lastt;
		    	} else {
		    		first = newN;
		    	}
	    	}
	    	try {
	        	lastt = lastt.next;
	    	} catch (NullPointerException e) {
	    		last = newN;
	    		//throw new NoSuchElementException();
	    		
	    	}
	        i++;
	    }
	}
	
	public DoubleLinkedList() {
		first = null;
		last = null;
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
		first.previous = null;
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
		if (newNode.next != null) {
			newNode.next.previous = newNode;
		} else {
			last = newNode;
		}
		first = newNode;
	}
	
   

}