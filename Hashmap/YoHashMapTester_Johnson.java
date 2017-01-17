import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
/**
 * YoHashMapTester_Johnson
 * Tester Class
 * 
 * Why does this work
 * Well, after reading the directions and the book, I used chaining and I also
 * used known good data structures like ArrayList, LinkedList, Set, and HashSet
 * although the last two are *****ONLY USED FOR .keySet()*****.
 * The main inefficiency is the iterative searching of linked list.
 * Collisions were tested.
 */
public class YoHashMapTester_Johnson {
    
    //Tester
    public static void main(String[] args) {
        CrapStringHashMap map = new CrapStringHashMap();
        map.put("Ryan", 123);
        map.put("Vidur", 456);
        map.put("Manu", 789);
        System.out.println("Is Ryan in the map?: " + map.containsKey("Ryan"));
        System.out.println("Is Mihir in the map?: " + map.containsKey("Mihir"));
        
        System.out.println("What is Manu valued at?: " + map.get("Manu"));
        
        System.out.println("What is Ryan valued at?: " + map.get("Ryan"));
        
        System.out.println("What is the size?: " + map.size());
        
        System.out.println("What other keys are in the map?: " + map.keySet());
        
        System.out.println("After removing \"Vidur\", which equalled " + map.remove("Vidur") + ", the map now contains: " + map.keySet());
    
        System.out.println("The size is finally: " + map.size());
    }
}

/**
 * The Hash map class
 * Uses strings with an awful hashing method
 * Uses chaining not open adressing
 */
 
class CrapStringHashMap {
    
    private int size = 0;
    
    //Internal key-value pair
    private class HashPair {
        public String key;
        public Object value;
        public HashPair(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
    
    //Length 10 table
    private final static int TABLE_SIZE = 10;
    ArrayList<LinkedList<HashPair>> table = new ArrayList<LinkedList<HashPair>>(TABLE_SIZE);
    
    //Constructor makes array list of nulls
    public CrapStringHashMap() {
        for (int i = 0; i < 10; i++) {
            table.add(null);
        }
    }
    
    //Rippy hasher
    private int hash(String toHash) {
        return toHash.length();
    }
    
    //Puts the object in array and chains
    public Object put(String key, Object value) {
        HashPair toPut = new HashPair(key, value);
        int loc = hash(key) % 10;
        if (table.get(loc) == null) {
            table.set(loc, new LinkedList<HashPair>());
        }
        for (HashPair h : table.get(loc)) {
            if (h.key == key) {
                Object old = h.value;
                h.value = value;
                return old;
            }
        }
        size++;
        table.get(loc).add(toPut);
        return null;
    }
    
    //Gets object from array/linkedlist
    public Object get(String key) {
        if (table.get(hash(key) % TABLE_SIZE) != null) {
            for (HashPair h : table.get(hash(key) % TABLE_SIZE)) {
                if (h.key == key) {
                    return h.value;
                }
            }
            return null;
        } else {
            return null;
        }
    }
    
    //Size. Is modified in get and put
    public int size() {
        return size;
    }
    
    //Searches for key
    public boolean containsKey(String key) {
        if (table.get(hash(key) % TABLE_SIZE) != null) {
            for (HashPair h : table.get(hash(key) % TABLE_SIZE)) {
                if (h.key == key) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
    
    //Returns set of keys (as hashset because why not)
    public Set keySet() {
        Set<String> keys = new HashSet<String>();
        for (LinkedList<HashPair> ll : table) {
            if (ll != null) {
                for (HashPair pair : ll) {
                    keys.add(pair.key);
                }
            }
        }
        return keys;
    }
    
    //Removes and return value of key
    public Object remove(String key) {
        int loc = hash(key) % 10;
        if (table.get(hash(key) % TABLE_SIZE) != null) {
            int i = 0;
            for (HashPair h : table.get(hash(key) % TABLE_SIZE)) {
                if (h.key == key) {
                    Object old = h.value;
                    table.get(hash(key) % TABLE_SIZE).remove(i);
                    size--;
                    return old;
                }
                i++;
            }
            return null;
        } else {
            return null;
        }
    }
}