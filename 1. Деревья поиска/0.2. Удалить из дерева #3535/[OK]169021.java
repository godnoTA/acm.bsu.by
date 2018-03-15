import java.io.*;
import java.util.*;

public class Main {
	
	static class Node {
	    private int value;
	    private Node left;
	    private Node right;
	    
	    public Node(int key) {
	        this.value = key;
	    }
	 
	    public int getData() {
	        return this.value;
	    }
	 
	    public void setData(int value) {
	        this.value = value;
	    }
	 
	    public Node getLeft() {
	        return this.left;
	    }
	 
	    public void setLeft(Node left) {
	        this.left = left;
	    }
	 
	    public Node getRight() {
	        return this.right;
	    }
	 
	    public void setRight(Node right) {
	        this.right = right;
	    }
	 
	   
	}
	
	static class Tree {
	     Node root;
	     
	    public Tree() {
	        this.root = null;
	    }  
	    public void insert(int x) {
	        root = doInsert(root, x);
	    }
	     
	    private static Node doInsert(Node node, int x) {
	        if (node == null) {
	            return new Node(x);
	        }
	        if (x < node.value) {
	            node.left = doInsert(node.left, x);
	        } else if (x > node.value) {
	            node.right = doInsert(node.right, x);
	        }
	        return node;
	    }
	    
	    private void traverseTree(Node current,PrintWriter out ) {
	    		
	    	if (current == null)
	            return;
	        	out.println(current.getData());
	            traverseTree(current.getLeft(), out);
	            traverseTree(current.getRight(), out);
	    }
	 
	   
	    public boolean isEmpty()
	     {
	         return root == null;
	     }
	    
	    public boolean search(int val)
	     {
	         return search(root, val);
	     }
	     
	     private boolean search(Node r, int val)
	     {
	         boolean found = false;
	         while ((r != null) && !found)
	         {
	             int rval = r.getData();
	             if (val < rval)
	                 r = r.getLeft();
	             else if (val > rval)
	                 r = r.getRight();
	             else
	             {
	                 found = true;
	                 break;
	             }
	             found = search(r, val);
	         }
	         return found;
	     }
	    
	     public void delete(int k)
	     {
	         if (isEmpty()) return;
	         if(search(k) == false) return;
	         root = delete(root, k);
	     }
	     
	    
	    
	     private Node delete(Node root, int k)
	     {
	         if (root == null)
	        	 return null;
	        
	         if (k < root.getData())
	         {
	            root.left = delete(root.getLeft(), k);
	            return root;
	         }
	         else if (k > root.getData())
	         {
	        	 root.right = delete(root.getRight(), k);
		         return root;
	         }
	         if(root.getLeft()==null){
	        	 return root.getRight();
	         }
	         else{ 
	        	 if(root.getRight()==null){
	        		 return root.getLeft();
	        	 }
	        	 else{
	        		 int minValue = findMin(root.right).getData();
	        		 root.setData(minValue);
	        		 root.right=delete(root.getRight(), minValue);
	        		 return root;
	        	 }
	         }
	     }
	     
	     public Node findMin(Node node){
	    		    if (node.getLeft()!=null)
	    		        return findMin(node.getLeft());
	    		    else
	    		        return node;
	     }

	}
	
	public static void main(String[] args) throws IOException {
		try{
		File file = new File("input.txt");
			
        Scanner in = new Scanner(file);
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        Tree tree=new Tree();
        int for_delete =in.nextInt();
        while (in.hasNext()) {
            tree.insert(in.nextInt());
        }
        tree.delete(for_delete);
        if(!tree.isEmpty()){
        	tree.traverseTree(tree.root, out);
        	}
        out.flush();
    }
		catch(Exception e){}

	}
	
}
