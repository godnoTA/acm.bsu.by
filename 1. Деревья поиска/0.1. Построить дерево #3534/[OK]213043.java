import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class BinarySearchTree {
    Node root;
    static class Node {
        int key;
        Node l;
        Node r;
        Node p;
        public Node(int key, Node p) {
            this.key = key;
            this.p = p;
        }
    }
    
    Node search(Node t, int key) {
        if (t == null || t.key == key)
            return t;
        if (key < t.key)
            return search(t.l, key);
        else
            return search(t.r, key);
    }
    
    public Node search(int key) {
        return search(root, key);
    }
    
    Node insert(Node t, Node p, int key) {
        if (t == null) {
            t = new Node(key, p);
        } else {
            if (key < t.key)
                t.l = insert(t.l, t, key);
            if (key > t.key)
                t.r = insert(t.r, t, key);
        }
        return t;
    }
    
    public void insert(int key) {
        root = insert(root, null, key);
    }
    
    void replace(Node a, Node b) {
        if (a.p == null)
            root = b;
        else if (a == a.p.l)
            a.p.l = b;
        else
            a.p.r = b;
        if (b != null)
            b.p = a.p;
    }
    
    void remove(Node t, int key) {
        if (t == null)
            return;
        if (key < t.key)
            remove(t.l, key);
        else if (key > t.key)
            remove(t.r, key);
        else if (t.l != null && t.r != null) {
            Node m = t.r;
            while (m.l != null)
                m = m.l;
            t.key = m.key;
            replace(m, m.r);
        } else if (t.l != null) {
            replace(t, t.l);
        } else if (t.r != null) {
            replace(t, t.r);
        } else {
            replace(t, null);
        }
    }
    
    public void remove(int key) {
        remove(root, key);
    }
    
    void print(Node t) {
        if (t != null) {
            print(t.l);
            System.out.print(t.key + " ");
            print(t.r);
        }
    }
    
    public void print() {
        print(root);
        System.out.println();
    }
    
    static void contPreOrder(Node top) throws IOException {
    	
    		FileWriter out = new FileWriter("output.txt");
        Stack<Node> stack = new Stack<> (); 
        while (top!=null || !stack.empty()){
            if (!stack.empty()){
                top=stack.pop();
            } 
            while (top!=null){
            		out.write(top.key + "\n");
            		System.out.println(top.key);    	
                if (top.r!=null) stack.push(top.r);
                top=top.l;
            }
        }
        out.flush();
		out.close();
   }
    
    public static void main(String[] args) {
    		try
    		{
	        BinarySearchTree tree = new BinarySearchTree();
	        File input = new File ("input.txt");
			Scanner sc = new Scanner(input);
				
			while (sc.hasNextInt())
			{
				tree.insert(sc.nextInt());
			}
	
			sc.close();

			contPreOrder(tree.root);	

	        System.exit(0);
    		}
    		catch(Exception e)
    		{
    			System.err.println (e);
    			System.exit(-1);
    		}
    }
}