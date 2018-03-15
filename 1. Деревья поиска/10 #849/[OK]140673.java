// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// void removeMin( )      --> Remove minimum item
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Exceptions are thrown by insert, remove, and removeMin if warranted


import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree {
    long max_sum = 0;
    long max_number = 0;
    static ArrayList<Integer> outlist = new ArrayList<>();
    static ArrayList<Integer> fin = new ArrayList<>();
    static Set<Integer> roots = new TreeSet<>();
    static Set<Integer> to_delete = new TreeSet<>();
    /**
     * Construct the tree.
     */

    public long height(BinaryNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }

    public static long leaves(BinaryNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.leaves;
        }
    }

    public void calculate(BinaryNode node) {
        if (node.left == null && node.right == null) {
            node.height = 0;
        } else {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }

        if (node.left == null && node.right == null) {
            node.leaves = 1;
        }
        else if ((node.height == height(node.left) + 1) && (node.height == height(node.right) + 1)) {
            node.leaves = leaves(node.left) + leaves(node.right);
        }
        else if ((node.height == height(node.left) + 1) && (node.height > height(node.right) + 1)) {
            node.leaves = leaves(node.left);
        }
        else if ((node.height > height(node.left) + 1) && (node.height == height(node.right) + 1)) {
            node.leaves = leaves(node.right);
        }
        else {
            int z = 3;
//            System.out.println("ERROR");
        }

        long sum = 0;

        if (node.left == null) {
            if (node.right == null) {
                sum = height(node);
            } else {
                sum = height(node.right) + 1;
            }
        }
        else if (node.right == null) {
            sum = height(node.left) + 1;
        }
        else {
            sum = height(node.left) + height(node.right) + 2;
        }
        // System.out.println(node.element + " " + sum);
        if (sum > max_sum) {
            roots.clear();
            roots.add(node.element);
            max_sum = sum;
        }
        else if (sum == max_sum) {
            roots.add(node.element);
        }
    }

    public void preorder_add(BinaryNode node) {
        if (node == null) return;

        outlist.add(node.element);
        // first recur on both subtrees
        preorder_add(node.left);
        preorder_add(node.right);

        // then deal with the nod
    }

    public void postorder(BinaryNode node) {
        if (node == null) return;

        // first recur on both subtrees
        postorder(node.left);
        postorder(node.right);

        // then deal with the node
        calculate(node);
    }

    public void postorder_del(BinaryNode node) {
        if (node == null) return;

        // first recur on both subtrees
        postorder_del(node.left);
        postorder_del(node.right);

        // then deal with the node
        if (to_delete.contains(node.element)) {
            fin.add(node.element);
        }
    }

    public void calculateb(BinaryNode node) {
        if (node == null) {
            return;
        }
        if ((node.left == null) && (node.right == null)) {
            return;
        }
        if (node.left == null) {
            node.right.a = node.a;
        }
        else if (node.right == null) {
            node.left.a = node.a;
        }
        else if (height(node.left) == height(node.right)) {
            long flv = (leaves(node.left) * node.a) / (leaves(node.right) + leaves(node.left));
            long frv = node.a - flv;
            node.left.a = node.b + flv;
            node.right.a = node.b + frv;
        }
        else if (height(node.left) > height(node.right)) {
            node.left.a = node.b + node.a;
            node.right.a = node.b;
        }
        else if (height(node.left) < height(node.right)) {
            node.right.a = node.b + node.a;
            node.left.a = node.b;
        }
        else {
//            System.out.println("ERROR");
            int z = 3;
        }
    }
    public void preorder(BinaryNode node) {
        if (node == null) return;

        // first recur on both subtrees
        calculateb(node);
        preorder(node.left);
        preorder(node.right);

        // then deal with the node

    }
    public void calculateans(BinaryNode node) {
        // System.out.println(node.element + ": " + node.a + '-' + node.b);
        if (node.a + node.b > max_number) {
            to_delete.clear();
            to_delete.add(node.element);
            max_number = node.a + node.b;
        }
        else if (node.a + node.b == max_number) {
            to_delete.add(node.element);
        }
    }
    public void preorder2(BinaryNode node) {
        if (node == null) return;

        // first recur on both subtrees
        calculateans(node);
        preorder2(node.left);
        preorder2(node.right);

        // then deal with the node

    }

    public void printTree() {
        printTree(root);
//        System.out.println();
    }
    private void printTree(BinaryNode node) {
        if (node == null) return;

        // left, node itself, right
        printTree(node.left);
//        System.out.print(node.element + "-" + node.b + '-' + node.a + ", ");
        printTree(node.right);
    }


    public BinarySearchTree( ) {
        root = null;
    }

    /**
     * Insert into the tree.
     * @param x the item to insert.
     * @throws DuplicateItemException if x is already present.
     */
    public void insert( Comparable x ) {
        root = insert( x, root );
    }

    /**
     * Remove from the tree..
     * @param x the item to remove.
     * @throws ItemNotFoundException if x is not found.
     */
    public void remove( Comparable x ) {
        root = remove( x, root );
    }

    /**
     * Remove minimum item from the tree.
     * @throws ItemNotFoundException if tree is empty.
     */
    public void removeMin( ) {
        root = removeMin( root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public Comparable findMin( ) {
        return elementAt( findMin( root ) );
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item or null if empty.
     */
    public Comparable findMax( ) {
        return elementAt( findMax( root ) );
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public Comparable find( Comparable x ) {
        return elementAt( find( x, root ) );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( ) {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( ) {
        return root == null;
    }

    /**
     * Internal method to get element field.
     * @param t the node.
     * @return the element field or null if t is null.
     */
    private Comparable elementAt( BinaryNode t ) {
        return t == null ? null : t.element;
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws DuplicateItemException if x is already present.
     */
    protected BinaryNode insert( Comparable x, BinaryNode t ) {
        if( t == null )
            t = new BinaryNode( x );
        else if( x.compareTo( t.element ) < 0 )
            t.left = insert( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate -> do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode remove( Comparable x, BinaryNode t ) {
        if( t == null )
            throw new ItemNotFoundException( x.toString( ) );
        if( x.compareTo( t.element ) < 0 )
            t.left = remove( x, t.left );
        else if( x.compareTo( t.element ) > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = removeMin( t.right );
        } else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to remove minimum item from a subtree.
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws ItemNotFoundException if x is not found.
     */
    protected BinaryNode removeMin( BinaryNode t ) {
        if( t == null )
            throw new ItemNotFoundException( );
        else if( t.left != null ) {
            t.left = removeMin( t.left );
            return t;
        } else
            return t.right;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    static BinaryNode findMin( BinaryNode t ) {
        if( t != null )
            while( t.left != null )
                t = t.left;

        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private BinaryNode findMax( BinaryNode t ) {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private BinaryNode find( Comparable x, BinaryNode t ) {
        while( t != null ) {
            if( x.compareTo( t.element ) < 0 )
                t = t.left;
            else if( x.compareTo( t.element ) > 0 )
                t = t.right;
            else
                return t;    // Match
        }

        return null;         // Not found
    }

    /** The tree root. */
    protected BinaryNode root;
    static class FastScanner extends BufferedReader {
        StringTokenizer st;
        boolean eof;
        String buf;
        String curLine;
        boolean createST;

        public FastScanner(String fileName) throws FileNotFoundException {
            this(fileName, true);
        }

        public FastScanner(String fileName, boolean createST)
                throws FileNotFoundException {
            super(new FileReader(fileName));
            this.createST = createST;
            nextToken();
        }

        public FastScanner(InputStream stream) {
            this(stream, true);
        }

        public FastScanner(InputStream stream, boolean createST) {
            super(new InputStreamReader(stream));
            this.createST = createST;
            nextToken();
        }

        String nextLine() {
            String ret = curLine;
            if (createST) {
                st = null;
            }
            nextToken();
            return ret;
        }

        String nextToken() {
            if (!createST) {
                try {
                    curLine = readLine();
                } catch (Exception e) {
                    eof = true;
                }
                return null;
            }
            while (st == null || !st.hasMoreTokens()) {
                try {
                    curLine = readLine();
                    st = new StringTokenizer(curLine);
                } catch (Exception e) {
                    eof = true;
                    break;
                }
            }
            String ret = buf;
            buf = eof ? "-1" : st.nextToken();
            return ret;
        }

        int nextInt() {
            return Integer.parseInt(nextToken());
        }

        long nextLong() {
            return Long.parseLong(nextToken());
        }

        double nextDouble() {
            return Double.parseDouble(nextToken());
        }

        BigInteger nextBigInteger() {
            return new BigInteger(nextToken());
        }

        public void close() {
            try {
                buf = null;
                st = null;
                curLine = null;
                super.close();
            } catch (Exception e) {

            }
        }

        boolean isEOF() {
            return eof;
        }
    }
    // Test program
    public static void main(String[] args) throws IOException {
        BinarySearchTree t = new BinarySearchTree( );
        FastScanner scan = new FastScanner("in.txt");
        int v;
        while(!scan.isEOF()) {
            v = scan.nextInt();
            t.insert(v);
        }
//        System.out.println("INPUT:");
//        t.printTree();
        t.postorder(t.root);
//        System.out.println("ROOTS:");
        for(Integer item : roots) {
            // System.out.println(item + " ");
            BinaryNode node = t.find(item, t.root);
            node.b = leaves(node.left) * leaves(node.right);
            // System.out.println(node.b);
        }
        t.preorder(t.root);
//        t.printTree();
        t.root.a = 0;
        t.preorder2(t.root);
//        System.out.println("TO_DELETE:");
//        for (Integer item : to_delete) {
//            System.out.println(item);
//        }
        t.postorder_del(t.root);
//        System.out.println("TO_DELETE (SORTED):");
//        for (Integer item : fin) {
//            System.out.println(item);
//        }
        for (Integer item : fin) {
            t.remove(item);
        }
        t.preorder_add(t.root);
//        System.out.println("ANS");


//        for (Integer item : outlist) {
//            System.out.println(item);
//        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("out.txt"), "utf-8"))) {
            for (Integer item : outlist) {
                writer.write(item.toString() + '\n');
            }
        }
    }
}


// Basic node stored in unbalanced binary search trees
// Note that this class is not accessible outside
// of this package.

class BinaryNode {
    // Constructors
    BinaryNode( Comparable theElement ) {
        element = (Integer) theElement;
        left = right = null;
    }

    // Friendly data; accessible by other package routines
    Integer element;      // The data in the node
    BinaryNode left;         // Left child
    BinaryNode right;        // Right child
    long height = 0;
    long leaves = 0;
    long a = 0;
    long b = 0;
}


/**
 * Exception class for duplicate item errors
 * in search tree insertions.
 * @author Mark Allen Weiss
 */
class DuplicateItemException extends RuntimeException {
    /**
     * Construct this exception object.
     */
    public DuplicateItemException( ) {
        super( );
    }
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public DuplicateItemException( String message ) {
        super( message );
    }
}


/**
 * Exception class for failed finds/removes in search
 * trees, hash tables, and list and tree iterators.
 * @author Mark Allen Weiss
 */
class ItemNotFoundException extends RuntimeException {
    /**
     * Construct this exception object.
     */
    public ItemNotFoundException( ) {
        super( );
    }

    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public ItemNotFoundException( String message ) {
        super( message );
    }
}