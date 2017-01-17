public class TreeTester_JohnsonR {
    
    /**
     * Ryan Johnson - TreeAsg3b
     * 
     * WHY THIS WORKS
     * - INSERT METHOD: Traverses the tree by comparing the would be inserted value
     *   to what is already there, finally finding its nook and inserting it. This
     *   is pretty efficient and also is recursive
     * 
     * - REMOVE METHOD: This method does many things, and is also recursive. If it
     *   encounters a leaf it kills it, if it encounters a single child node it shifts
     *   it appropriately, and if it encouters a two child node (EX: root node),
     *   it picks the lowest value on the nodes's right tree and sets that to the node's
     *   value then destroys the node where it took the value from.
     * 
     * - PRINT METHOD: Its just recursive inorder traversal and printing. pretty simple stuff
     *   It also checks if the tree is null
     * 
     * EFFICIENCY
     * - Basically is recursive
     * - Pretty efficient
     * 
     */
    
    
    /**
     * BSTree Tester
     */
    public static void main(String[] args) {
        BSTree tree = new BSTree();
        
        //Insert Nonsequential values to test sort
        System.out.println(Colors.green + "Entering Tree values" + Colors.reset);
        
        tree.insert(4);
        tree.insert(1);
        tree.insert(0);
        tree.insert(3);
        tree.insert(2);
        tree.insert(9);
        tree.insert(10);
        tree.insert(11);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        

        System.out.println(Colors.green + "Tree has been created." + Colors.reset + " Now lets " + Colors.red + "remove" + Colors.reset + " some!");
        //Print the tree to see if sorted.
        
        tree.remove(4);
        tree.remove(1);
        tree.remove(0);
        tree.remove(3);
        tree.remove(2);
        tree.remove(9);
        
        System.out.print("Output: " + Colors.blue);
        tree.print();
        System.out.println(Colors.reset);
        
        System.out.println(Colors.red + "Removing the rest" + Colors.reset);
        
        tree.remove(10);
        tree.remove(11);
        tree.remove(5);
        tree.remove(6);
        tree.remove(7);
        tree.remove(8);
        
        System.out.print("Output: " + Colors.blue);
        tree.print();
        System.out.println(Colors.reset);
    }
}

/**
 * Binary search tree
 */
class BSTree {
    
    //Store the root node
    private TreeNode treeroot = null;
    
    //Wrapper function for helper method insertIntoSubTree, but starts at the tree's root
    public void insert( Comparable comp ) {
        System.out.println("-" + Colors.green + " Adding" + Colors.reset + " " + comp);
        treeroot = insertIntoSubTree(treeroot, comp);
    }
    
    //Reucrsive function to insert into a treenode that can be a tree, a subtree, or a leaf. Inserts left or right depending on compareTo
    @SuppressWarnings("unchecked")
    private TreeNode insertIntoSubTree(TreeNode root, Comparable insert) {
        if (root == null) {
            root = new TreeNode(insert);
        } else {
            if (((Comparable)root.getValue()).compareTo(insert) > 0) {
                System.out.println("  \u2514  Traversing Left (" + insert + " < " + root.getValue() + ")");
                root.setLeft(insertIntoSubTree(root.getLeft(), insert));
            } else if (((Comparable)root.getValue()).compareTo(insert) < 0) {
                System.out.println("  \u2514  Traversing Right (" + insert + " > " + root.getValue() + ")");
                root.setRight(insertIntoSubTree(root.getRight(), insert));
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
    public void remove(Comparable x) {
        treeroot = remove( x, treeroot );
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
            t.setValue(findMin( t.getRight() ).value);
            t.setRight(remove((Comparable)t.value, t.getRight()));
        } else {
            t = (t.getLeft() != null) ? t.getLeft() : t.getRight();
        }
        
        return t;
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

/**
 * Color Class to make terminal pretty
 */
final class Colors {
    public static final String reset = "\u001B[0m";
	public static final String black = "\u001B[30m";
	public static final String red = "\u001B[31m";
	public static final String green = "\u001B[32m";
	public static final String yellow = "\u001B[33m";
	public static final String blue = "\u001B[34m";
	public static final String purple = "\u001B[35m";
	public static final String cyan = "\u001B[36m";
	public static final String white = "\u001B[37m";
}
