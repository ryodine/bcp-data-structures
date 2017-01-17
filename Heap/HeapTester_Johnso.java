/**
 * Ignatian Self-review paragraph
 * 
 * While writing this code, I began to contemplate to myself what the meaning of data is.
 * I mean, what is data anyway? People in computer science classes tell us that it
 * 1 and 0 in a computer, but how does the computer experience it? And at what level?
 * Are we just 1s and 0s in a larger scheme? Is there someone typing on our society's
 * proverbial keyboard, controlling how we move? I mean, we could all be completely controlled
 * by someone at a keyboard, just as the bits that are being used to display this message have
 * been controlled by me and others in transit as they made it to you. What if the bits feel?
 * What if we really don't feel? If this were a true CS reflection, I should probably encode it in binary,
 * but then I would just be manipulating bits. P.S. I have no clue what to actually write for this.
 */


/**
 * HeapTester_Johnso
 * 
 * The Tester class for YoHeap, adds 10 random doubles with 1 decimal of precision from -10 to 10
 * and prints the heap at every step, and then the array of the heap at the end
 */
public class HeapTester_Johnso {
    public static void main(String[] args) {
        YoHeap theheap = new YoHeap<Double>();
        for (int i = 0; i < 10; i++) {
            Double theAdd = Math.round((Math.random() * 10) * 10.0) / 10.0;
            theheap.add(theAdd);
            theheap.print();
            System.out.println("======[Added: " + theAdd + "]======");
        }
        for (int i = 0; i < 10; i++) {
            Double removed = (Double) theheap.removeRoot();
            theheap.print();
            System.out.println("======[Removed: " + removed + "]======");
        }
        System.out.println(theheap);
    }
}

/**
 * YoHeap
 * 
 * A generic implementation of a Max-heap, where T extends Comparable
 */
class YoHeap<T extends Comparable> {
    @SuppressWarnings("unchecked")
    private T[] data = (T[]) new Comparable[2];
    private int lastindex = 0;
    
    /**
     * Adds an element ot the bottom of the heap, and then bubbles it up to where its gonna live.
     */
    @SuppressWarnings("unchecked")
    public void add(T obj) {
        if (lastindex >= data.length) {
            //needs to grow
            T[] narray = (T[]) new Comparable[data.length*2];
            for (int i = 0; i < data.length; i++) {
                narray[i] = data[i];
            }
            data = narray;
        }
        
        data[lastindex++] = obj;
        bubbleUp(lastindex - 1);
    }
    
    @SuppressWarnings("unchecked")
    //Helper method for add
    private void bubbleUp(int nodeIndex) {
        if (nodeIndex != 0) {
            //Node is not root
            int parentIndex = indexOfParent(nodeIndex);
            if (data[nodeIndex].compareTo(data[parentIndex]) > 0) {
                T oldParent = data[parentIndex];
                data[parentIndex] = data[nodeIndex];
                data[nodeIndex] = oldParent;
                bubbleUp(parentIndex);
            }
            
        }
    }
    
    //Helper method for add
    private int indexOfParent(int nodeIndex) {
        return (nodeIndex - 1) / 2;
    }
    
    //Helper method for remove
    private int indexOfLeft(int nodeIndex) {
        return (nodeIndex*2)+1;
    }
    
    //Helper method for remove
    private int indexOfRight(int nodeIndex) {
        return (nodeIndex*2)+2;
    }
    
    
    public T removeRoot() {
        if (data[0] == null) {
            System.out.println("Empty Heap");
            return null;
        } else {
            T old = data[0];
            data[0] = data[--lastindex];
            if (lastindex > 0)
                siftDown(0);
            return old;
        }
    }
    
    private void siftDown(int nodeIndex) {
        int leftChildIndex = indexOfLeft(nodeIndex);
        int rightChildIndex = indexOfRight(nodeIndex);
        int maxIndex;
        if (rightChildIndex >= lastindex) {
              if (leftChildIndex >= lastindex)
                    return;
              else
                    maxIndex = rightChildIndex;
        } else {
              if (data[leftChildIndex].compareTo(data[rightChildIndex]) <= 0)
                    maxIndex = rightChildIndex;
              else
                    maxIndex = leftChildIndex;
        }
        if (data[nodeIndex].compareTo(data[maxIndex]) < 0) {
              T tmp = data[maxIndex];
              data[maxIndex] = data[nodeIndex];
              data[nodeIndex] = tmp;
              siftDown(maxIndex);
        }
    }
    
    //Helper method for print
    private int maxdepth() {
		return (int) Math.ceil(Math.sqrt(lastindex));
	}
	
	/**
	 * Prints each row of the heap on a line in the console
	 */
	public void print() {
	    int depth = maxdepth();
	    for (int i = 0; i < depth; i++) {
	        int offset = (int)Math.pow(2,i);
	        for (int j = 0; j < offset; j++) {
	            if (j + (i*2) < lastindex) {
	                System.out.print(data[j + (i*2)] + " ");
	            }
	        }
	        System.out.println("");
	    }
	}
    
    /**
	 * Prints the heap as an array
	 */
    public String toString() {
        String str = "{";
        for (int i = 0; i < lastindex; i++) {
            str += ((i == (lastindex-1))? data[i] : data[i] + ", ");
        }
        
        return str + "}";
    }
}