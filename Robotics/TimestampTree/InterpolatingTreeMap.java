import java.util.TreeMap;
import java.util.Map;
import java.lang.Exception;

public class InterpolatingTreeMap<K extends Number, V extends Averageable> extends TreeMap<K, V> {
    Integer max;
    
    public InterpolatingTreeMap(int maximumSize) {
        max = maximumSize;
    }
    
    public InterpolatingTreeMap() {}
    
    public V put(K key, V value) {
        if (max != null && max <= size()) {
            //"Prune" the tree if it is oversize
            K first = firstKey();
            remove(first);
        }
        
        super.put(key, value);
        
        return value;
    }
    
    public void putAll(Map<? extends K,? extends V> map) {
        System.out.println("Unimplemented Method");
    }
    
    public V getInterpolated(K key) {
        V gotval = get(key);
        if (gotval == null) {
            //Get surrounding keys for interpolation
            K topBound = ceilingKey(key);
            K bottomBound = floorKey(key);
            
            //If attempting interploation at ends of tree, return null
            if (topBound == null || bottomBound == null)
                return null;
                
            //Get surrounding values for interpolation
            V topElem = get(topBound);
            V bottomElem = get(bottomBound);
            
            //Number xValue, Number highXValue, Number highYValue, Number searchedForValue
            return (V) bottomElem.average((Number) bottomBound, (Number) topBound, topElem, (Number) key);
        } else {
            return gotval;
        }
    }
}