import java.io.*;
import java.util.*;

public class MyTree{
	
	static int amount_of_nodes;
    static int counter_for_print;
	public static void main(String[] args){
		
		Scanner sc;
		Tree tree = new Tree();
		try {
		sc = new Scanner(new File("input.txt"));
		while (sc.hasNextLine()) {
			tree.insert(sc.nextInt());
		}
		sc.close();
		} catch (Exception e1) {}
		
		try {
		FileWriter writeFile = new FileWriter(new File("output.txt"));
		tree.PreOrderTraversalAndPrint(tree.root, writeFile);
		writeFile.close();
		} catch (Exception e1) {}
	}
	
	static class Node {
	    public int key;
	    public Node left;
	    public Node right;
	 
	    public Node(int key) {
	        this.key = key;
	    }
	}
	 
	private static Node doInsert(Node node, int x) {
	    if (node == null) {
	        return new Node(x);
	    }
	    if (x < node.key) {
	        node.left = doInsert(node.left, x);
	    } else if (x > node.key) {
	        node.right = doInsert(node.right, x);
	    }
	    return node;
	}
	
	private static void print(Node node, FileWriter writeFile) {
	    try {
	    	counter_for_print++;
	    	if(counter_for_print!=amount_of_nodes){
	    		writeFile.write(""+node.key+"\r\n");
	    		}
	    	else{
	    		writeFile.write(""+node.key);
	    	}
		} catch (IOException e) {}
	}
	
	public static class Tree {
	    private Node root;
	    public void insert(int x) {
		    root = doInsert(root, x);
		    amount_of_nodes++;
		}
	    public void PreOrderTraversalAndPrint(Node v, FileWriter out){
	    	if(v!=null){
		    	print(v, out);
		    	PreOrderTraversalAndPrint(v.left,out);
		    	PreOrderTraversalAndPrint(v.right, out);
	    	}
	    }
	}
}



