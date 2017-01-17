import java.util.NoSuchElementException;

public class QueueTester {
    public static void main(String[] args) {
        Queue<String> q = new Queue<String>();
        
        System.out.println("Q is empty: "+ q.isEmpty());
        System.out.println("Now adding H, E, L, L, O, B, C, P");
        q.add("H");
        q.add("E");
        q.add("L");
        q.add("L");
        q.add("O");
        q.add("B");
        q.add("C");
        q.add("P");
        
        System.out.println("The top element is: "+ q.peek());
        
        while (!q.isEmpty()){
          System.out.println("removing: "+q.remove());
        }
        System.out.println("Is it empty: "+ q.isEmpty());
        System.out.println("Now adding 1");
        q.add("1");
        System.out.println("Is it empty: "+ q.isEmpty());
        System.out.println("Now adding 2, 3, 4, 5");
        q.add("2");
        q.add("3");
        q.add("4");
        q.add("5");
        System.out.println("The top element is: "+ q.peek());
        System.out.println("Removing " + q.remove() );
        System.out.println("Removing "+ q.remove() );
        System.out.println("Now adding 7, 8, 9, 10, 11, 12");
        q.add("7");
        q.add("8");
        q.add("9");
        q.add("10");
        q.add("11");
        q.add("12"); 
        System.out.println("Now adding Last");
        q.add("Last");
        System.out.println("The top element is: "+ q.peek());
        System.out.println("The size is: "+ q.size());
        
        
        while (!q.isEmpty()){
          System.out.println("removing:" +q.remove());
        }
        for(int i = 0; i < 10000; i++ )
            q.add( "" + i );
    }
}

class Queue<T> {
    private static final int size = 10;
    private Object[] data;
    private int last = 0;
    public Queue() {
        data = new Object[size];
    }
    
    public boolean isEmpty() {
        return (last == 0);
    }
    
    public boolean add(T x) {
        if (last != size) {
            data[last++] = x;
        } else {
            throw new ArrayIndexOutOfBoundsException("Cannot add anything else to the stack");
        }
        return true;
    }
    
    public T remove() {
        if (!isEmpty()) {
            Object dat = data[0];
            for (int i = 1; i < data.length; i++) {
                data[i-1] = data[i];
            }
            last--;
            return (T) dat;
        } else {
            throw new NoSuchElementException();
        }
    }
    
    public int size() {
        return last;
    }
    
    public T peek() {
        if (!isEmpty()) {
            return (T) data[0];
        } else {
            return null;
        }
    }
    
    public String toString() {
        String out = "";
        
        out += "[";
        for (int i = 0; i < data.length; i++) {
            out += data[i] + ", ";
        }
        out += "]";
        return out;
    }
}