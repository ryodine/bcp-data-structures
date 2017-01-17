import java.util.Scanner;

/**
 * Tester Class
 */
class YoArrayList_Johnson1 {
    
    /**
     * Makes default array and runs intercative tester program
     */
    public static void main(String[] args) {
        YoArrayList<String> list = new YoArrayList<String>();
        list.add("ayyy");
        list.add("lmao");
        list.add("!");
        
        
        String msg = "(v)iew list, (e)xit program, (ap)pend to list, (ad)d at index, (si)ze, (i)ndexOf, (rs)Remove String, (ri)Remove at index, (se)t at index";
        System.out.println(msg);
        
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.print(" >> ");
            String command = in.nextLine();
            
            switch (command) {
                case "v":
                    System.out.println("\t" + list);
                    break;
                case "e":
                    exit = true;
                    break;
                case "ap":
                    System.out.print("\tType what you want to add: ");
                    list.add(in.nextLine());
                    break;
                case "ad":
                    System.out.print("\tType what you want to add: ");
                    String what = in.nextLine();
                    System.out.print("\tType where you want to add it (between 0 and " + list.size() + ", inclusive): ");
                    list.add(what, Integer.parseInt(in.nextLine()));
                    break;
                case "si":
                    System.out.println("\t list is " + list.size() + " items long");
                    break;
                case "i":
                    System.out.print("\tWhat are you looking for?: ");
                    System.out.println("\tReturned: " + list.indexOf(in.nextLine()));
                    break;
                case "ri":
                    System.out.print("\tIndex to remove: ");
                    list.remove(Integer.parseInt(in.nextLine()));
                    break;
                case "rs":
                    System.out.print("\tWhat to remove: ");
                    list.remove(in.nextLine());
                    break;
                case "se":
                    System.out.print("\tIndex to set: ");
                    int where = Integer.parseInt(in.nextLine());
                    System.out.print("\tType what you want it to be (item is currently set to: \"" + list.get(where) + "\"): ");
                    list.set(where, in.nextLine());
                    break;
                default:
                    System.out.println("\t" + msg);
                    break;
            }
        }
    }   
}


/**
 * YoArrayList<T>
 * An implementation of an ArrayList of Objects of a generic type
 */
class YoArrayList<T> {
    
    //Store data as Object[]
    public Object[] array;
    
    //Store the last used index, start at 0
    int lastUsedIndex = 0;
    
    /**
     * Add an object at an index, with the ability to ensure capacity
     * @param o Object to be added
     * @param location where to add it
     */
    void add(Object o, int location) {
        checkCapacity();
        System.arraycopy(array, location, array, location + 1, lastUsedIndex - location);
        array[location] = o;
        lastUsedIndex++;
    }
    /**
     * Check Capacity: Based on last index, check to see if there is enough space to add another item. If there isn't, then make it!
     */
    private void checkCapacity() {
        if (lastUsedIndex >= array.length) {
            Object[] narray = new Object[array.length*2];
            for (int i = 0; i < array.length; i++) {
                narray[i] = array[i];
            }
            array = narray;
        }
    }
    
    /**
     * Append an object to the end
     * @param o Object to be appended
     */
    void add(Object o) {
        checkCapacity();
        array[lastUsedIndex++] = o;
    }
    
    /**
     * Size of arraylist
     * @return int Size of arraylist
     */
    int size(){
        return lastUsedIndex;
    }
    
    /**
     * Get the object at the index
     * @return <T> object at index
     */
    public T get( int location )  throws ArrayIndexOutOfBoundsException{
        if (location >= lastUsedIndex) {throw new ArrayIndexOutOfBoundsException(location);}
        return (T)array[location];
    }
    
    /**
     * Contructor
     * @param int size : Number of slots to allocate
     */
    public YoArrayList(int size) {
        size = (size > 0)? size : 2;
        array = new Object[size];
    }
    
    /**
     * Contructor
     * Constructs with 2 slots
     */
    public YoArrayList() {
        size = 2;
        array = new Object[size];
    }
    
    /**
     * toString
     * pretty-prints the array
     * @return String pretty-printed
     */
    public String toString() {
        String out = "[";
        for (int i = 0; i < size(); i++) {
            out += (get(i));
            if (i < size()-1) out +=", ";
        }
        out+="]";
        
        return out;
    }
    
    /**
     * indexOf
     * @param what Object of what to search for
     * @return int index where its found
     */
    public int indexOf(Object what) {
        int index = -1;
        for (int i = 0; i < size(); i++){
            if (array[i].equals(what)) {
                index = i;
                return index;
            }
        }
        return index;
    }
    
    /**
     * Remove an element
     * @param int location: Where to remove the element
     * @return <T> the element that was there
     */
    public T remove(int location) throws ArrayIndexOutOfBoundsException {
        
        if (location + 1 > size()) throw new ArrayIndexOutOfBoundsException(location);
        T obj = (T)array[location];
        for (int i = location; i < size()-1; i++) {
            array[i] = array[i+1];
        }
        lastUsedIndex--;
        return obj;
    }
    
    /**
     * Remove an element at first index
     * @param int location: What to remove
     * @return <T> the element that was there
     */
    public boolean remove(Object what) {
        if (indexOf(what) >= 0) remove(indexOf(what));
        return (indexOf(what) >= 0)? true : false;
    }
    
    /**
     * Set an element at an index
     * @param int where: the index
     * @param Object what; what to set it to
     * @return <T> what it was previously
     */
    public T set(int where, Object what) {
        if (where + 1 > size()) throw new ArrayIndexOutOfBoundsException(where);
        T previous = (T)array[where];
        array[where] = what;
        return previous;
    }
}