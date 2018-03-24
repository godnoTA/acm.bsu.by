import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


class Tree {
	private class Node {
	    public int key;
	    public Node left;
	    public Node right;
	 
	    public Node(int key) {
	        this.key = key;
	    }
	}
    public Node root;
    public void insert(int x) {
        root = doInsert(root, x);
    }
     
    private Node doInsert(Node node, int x) {
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
    String str="";
    public static void PreOrderTraversal(Node v, FileWriter fw) 
    { 
    if(v!=null) 
    { 
    try { 
    fw.write(Integer.toString(v.key) + "\r\n"); 
    } catch (IOException e) { 
    e.printStackTrace(); 
    } 
    PreOrderTraversal(v.left,fw); 
    PreOrderTraversal(v.right,fw); 
    } 
    }
}
public class TreeDel implements Runnable {

	 public static void main(String args[]){

		 new Thread(null, new TreeDel(), "", 64 * 1024 * 1024).start();
		    
	    	
	    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
    	File in = new File("input.txt");
		   String strLine;
		   int k;
		   Tree mytree = new Tree();
		   try {
		        Scanner sc = new Scanner(in);
		        while (sc.hasNext()) {
		            mytree.insert(sc.nextInt());
		        }
		   } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		   try(FileWriter fos=new FileWriter("output.txt"))
	        {
	            
			   mytree.PreOrderTraversal(mytree.root, fos);
	        }
	        catch(IOException ex){
	        	System.out.println(ex.getMessage());
	        }
    	
	}
}