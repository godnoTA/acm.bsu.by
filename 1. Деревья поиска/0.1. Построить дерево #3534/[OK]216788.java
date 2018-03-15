//01

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	
	static class Tree {
		 
		 static class Node {
		        int key;
		        Node left;
		        Node right;
		        
		        public Node(int key) 
		        {
		            this.key = key;
		        }
		    }
		 
		public Node root;
		 
			public void insert(int x) {
			    root = doInsert(root, x);
			}
			 
			private static Node doInsert(Node node, int x) {
			    if (node == null) 
			    {
			        return new Node(x);
			    }
			    			    
			    if (x < node.key) {
			        node.left = doInsert(node.left, x);
			    } else if (x > node.key) {
			        node.right = doInsert(node.right, x);
			    }
			    
			    return node;
			}
			
			void printLeft(Node t, PrintStream out) {
		        if (t != null) {
		            
		        	out.println(t.key);
		            printLeft(t.left, out);
		            printLeft(t.right, out);
		        }
		    }
		    public void printLeft(PrintStream out) {
		        printLeft(root, out);
		    }
		    
	}
	
public static void main (String args[]) {
	
		Tree our_tree = new Tree ();
		try{
			Scanner in = new Scanner(new File("input.txt"));
			while (in.hasNextInt())
			{
					our_tree.insert(in.nextInt());
			}
			in.close();
			
			PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
			our_tree.printLeft(out);
			out.close();
			
		}
		
		catch(IOException exc){
            System.out.println(exc.getMessage());
        } 
		


	}


}
