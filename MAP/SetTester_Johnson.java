/**
 * Self-Check
7.1.1
Makes a new hashset s of strings
adds “hello” to hashset s
adds “bye” to hashset s
s becomes the union of hashset s and s which means it pretty much does nothing
Makes a new treeset t of strings
adds “123” to treeset t
s becomes the union of hashset s and treeset t which means “123” is added to s
prints whether s has all the things in t: True
prints whether t has all the things in s: False
prints whether s has the string “ace”: False
prints whether s has the string “123”: True
only keeps elements that are in treemap in setmap s
True Checks if s contains the element “123”
Only keeps elements in s in t
True Checks if t contains “123”
 
7.1.4
	 setACopy is needed because if you were to call the methods using the setACopy with a reference to setA would edit setA rather than the intended setACopy. If you used setACopy = setA; then you would be making changes to setA since setACopy would reference the setA object. In this case, it would first do the addAll then do retainAll both on setA.


 * 
 * 
 * 
 */

public class SetTester_Johnson {
    public static void main(String[] args) {
        YoMap<String,String> m = new YoMap<String,String>();
        m.put("ayy", "lmao");
        System.out.println(m.get("ayy"));
        m.put("sup", "dog");
        m.put("123", "456");
        m.put("palindrome", "emordnilap");
        System.out.println(m.put("palindrome", "Not anymore!"));
        System.out.println(m.get("palindrome"));
        System.out.println(m.remove("palindrome"));
        System.out.println(m.size());
    }
}

class YoMap<K extends Comparable, V> {
    private BSTree tree;
    private int size = 0;
    public YoMap() {
        tree = new BSTree();
    }
    
    public int size() {
        return size;
    }
    
    public V put(K key, V val) {
        MapNode<K,V> old = (MapNode<K,V>)tree.insert(new MapNode<K,V>(key, val));
        if (old != null) {
            return old.value;
        } else {
            size++;
            return null;
        }
    }
    
    public V remove(K key) {
        MapNode<K,V> removed = (MapNode<K,V>)tree.remove(new MapNode<K,V>(key, null));
        if (removed != null) {
            size--;
            return removed.value;
        } else {
            return null;
        }
    }
    
    public boolean isEmpty() { return tree.isEmpty(); }
    
    public V get(K key) {
        MapNode<K,V> found = (MapNode<K,V>)tree.search(new MapNode<K,V>(key, null));
        return (found != null)? found.value : null;
    }
    
    private class MapNode<k extends Comparable, v> implements Comparable {
        public k key;
        public v value;
        public MapNode(k key, v value) {
            this.key = key; this.value = value;
        }
        
        public int compareTo(Object other) {
            return ((k)other).compareTo(key);
        }
    }
}


//Binary Search Tree
class BSTree {
    
    //Store the root node
    private TreeNode treeroot = null;
    
    //Wrapper function for helper method insertIntoSubTree, but starts at the tree's root
    private Comparable old = null;
    public Comparable insert( Comparable comp ) {
        treeroot = insertIntoSubTree(treeroot, comp);
        Comparable _old = old;
        old = null;
        return _old;
    }
    
    public boolean isEmpty() {
        return treeroot == null;
    }
    
    //Reucrsive function to insert into a treenode that can be a tree, a subtree, or a leaf. Inserts left or right depending on compareTo
    @SuppressWarnings("unchecked")
    private TreeNode insertIntoSubTree(TreeNode root, Comparable insert) {
        if (root == null) {
            root = new TreeNode(insert);
        } else {
            if (((Comparable)root.getValue()).compareTo(insert) > 0) {
                root.setLeft(insertIntoSubTree(root.getLeft(), insert));
            } else if (((Comparable)root.getValue()).compareTo(insert) < 0) {
                root.setRight(insertIntoSubTree(root.getRight(), insert));
            } else {
                //Same compare, same node, return old version
                old = (Comparable) root.getValue();
                root.setValue(insert);
            }
        }
        return root;
    }
    
    //Wrapper function for recursive printAtRoot
    public void print() {
        if (treeroot == null) {
            System.out.println("Empty Tree");
        } else {
            printAtRoot(treeroot);
        }
    }
    
    //Prints the left, then the value of the node, then the right.
    private void printAtRoot(TreeNode root) {
        if (root.getLeft() != null) {
            printAtRoot(root.getLeft());
        }
        System.out.print(root.getValue() + ", ");
        if (root.getRight() != null) {
            printAtRoot(root.getRight());
        }
    }
    
    /**
     * Remove a tree node
     */
    public Comparable remove(Comparable x) {
        treeroot = remove( x, treeroot );
        Comparable _old = old;
        old = null;
        return _old;
    }
    
    public static abstract class Traversable {
        public abstract void traverseLogic(BSTree tree, Comparable value);
    }
    
    public void traverse(Traversable t) {
        inOrder(treeroot, t);
    }
    
    private void inOrder(TreeNode tr, Traversable t)
	{
		if (tr != null) {
         inOrder(tr.getLeft(), t);
         t.traverseLogic(this, (Comparable) tr.getValue());
         inOrder(tr.getRight(), t);
      }
	}
    
    /**
     * Internal remove method, recursive
     */
    private TreeNode remove( Comparable x, TreeNode t) {
        if (t == null) {
            return t; //item not found
        }
        
        if (x.compareTo(t.value) < 0) {
            t.setLeft(remove( x, t.getLeft() ));
        } else if (x.compareTo(t.value) > 0) {
            t.setRight(remove(x, t.getRight()));
        } else if (t.getLeft() != null && t.getRight() != null) {
            //Two Children
            old = (Comparable) t.getValue();
            t.setValue(findMin( t.getRight() ).value);
            t.setRight(remove((Comparable)t.value, t.getRight()));
        } else {
            old = (Comparable) t.getValue();
            t = (t.getLeft() != null) ? t.getLeft() : t.getRight();
        }
        
        return t;
    }
    
    public Comparable search(Comparable what) {
        TreeNode found = _search(treeroot, what);
        if (found != null) {
            return (Comparable)(found.getValue());
        } else {
            return null;
        }
        
    }
    
    private TreeNode _search(TreeNode where, Comparable what) {
        if (where == null) {
            return null;
        } else if (where.getLeft() != null && ((Comparable)where.getValue()).compareTo(what) > 0) {
            return _search(where.getLeft(), what);
        } else if (where.getRight() != null && ((Comparable)where.getValue()).compareTo(what) < 0) {
            return _search(where.getRight(), what);
        } else if (((Comparable)where.getValue()).compareTo(what) == 0) {
            return where;
        } else {
            return null;
        }
    }
    
    /**
     * Finds the min value in a tree
     */
    private TreeNode findMin(TreeNode t) {
        if (t == null) {
            return null;
        } else if (t.getLeft() == null) {
            return t;
        } 
        
        return findMin(t.getLeft());
    }
    
    //copied from the assignment
    public class TreeNode {
        private Object value;
        private TreeNode left;
        private TreeNode right;
        
        public TreeNode(Object initValue)
          { value = initValue; left = null; right = null; }
    
        public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
          { value = initValue; left = initLeft; right = initRight; }
    
        public Object getValue() { return value; }
        public TreeNode getLeft() { return left; }
        public TreeNode getRight() { return right; }
    
        public void setValue(Object theNewValue) { value = theNewValue; }
        public void setLeft(TreeNode theNewLeft) { left = theNewLeft; }
        public void setRight(TreeNode theNewRight) { right = theNewRight; }
    }
}
