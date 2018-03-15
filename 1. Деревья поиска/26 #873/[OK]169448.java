import java.io.*;
import java.util.*;


public class Another_variant {
	static int max_difference;
	static int max_key;
	
	static class Node {
	    private int value;
	    private Node left;
	    private Node right;
	    private int left_height;
	    private int right_height;
	    private int difference;
	    
	    public int getDifference(){
	    	left_height = find_left_height(this);
	    	right_height = find_right_height(this);
	    	difference = Math.abs(right_height-left_height);
	    	return difference;
	    }
	    int counter_1;
	    
	    
		private int find_left_height(Node root){
			if(root.getLeft() == null){
			    return 0;
			}
			else{
				return Tree.NodeCount(root.getLeft());
			}
			    	
		}
		private int find_right_height(Node root){
			if(root.getRight()==null){
			    return 0;
			}
			else{
				return Tree.NodeCount(root.getRight());
			}
			    	
		}
		




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
	    int amount_of_nodes;
	    public Tree() {
	        this.root = null;
	    }  
	    public void insert(int x) {
	    	amount_of_nodes++;
	        root = doInsert(root, x);
	    }
	    
	    private static int NodeCount(Node root)
	    {

	        if (root.getLeft() == null && root.getRight() == null)
	            return 1;
	        int left = 0, right = 0;
	        if (root.getLeft() != null)
	            left = NodeCount(root.getLeft());
	        else
	            left = 0;
	        if (root.getRight() != null)
	            right = NodeCount(root.getRight());
	        else 
	            right = 0;

	        return left+right+1;
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
	    public void traverseTreeAndFindNodeWithMaxDifference(Node v) {
	    	if (v != null){
	    		findNodeWithMaxDifference(v);
	    		System.out.println("Node value: " + v.value +".Node difference: " + v.getDifference()+"\r\n");
	    		traverseTreeAndFindNodeWithMaxDifference(v.left);
	    		traverseTreeAndFindNodeWithMaxDifference(v.right);
	    	}
	    }
	 
	    	private void findNodeWithMaxDifference(Node node) {
	    		   if(node.getDifference()>=max_difference){
	    			   if(node.getDifference() == max_difference){
		    			   if(node.value>max_key){
		    				   max_key = node.value;
		    				   max_difference = node.getDifference();
		    			   }
	    			   }
	    			   else{
	    				   max_key = node.value;
	    				   max_difference = node.getDifference();
	    			   }
	    		   }
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
	         amount_of_nodes--;
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
		File file = new File("tst.in");
			
        Scanner in = new Scanner(file);
        PrintWriter output = new PrintWriter(new FileWriter("tst.out"));
        Tree tree=new Tree();
        while (in.hasNext()) {
            tree.insert(in.nextInt());
        }
        tree.traverseTreeAndFindNodeWithMaxDifference(tree.root);
        tree.delete(max_key);
        if(!tree.isEmpty()){
        	tree.traverseTree(tree.root, output);
        	}
        output.flush();
        
    }
		catch(Exception e){}

	}
	
}
