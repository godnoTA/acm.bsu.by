import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Source_02 {
	
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static void main (String args[]) throws IOException{
		exists(filename);
		List<Long> buffer = new ArrayList<Long>();
		long del = 0;
		try {
	        //Объект для чтения файла в буфер
	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
	        try {
	            //В цикле построчно считываем файл
	            String s;
	            if((s = in.readLine()) != null)
	            	del = Long.parseLong(s);
	            s = in.readLine();
	            while ((s = in.readLine()) != null) {
	            	buffer.add(Long.parseLong(s));
	            }
	        } finally {
	            in.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
		
		Tree tree = new Tree();
		for(Long x : buffer){
			tree.insert(x);
		}
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	        	tree.SetRoot(tree.RightDelete(tree.GetRoot(), del));
	            tree.PreOrder(tree.GetRoot(), out);
	            for (Long i: buffer) System.out.print(i + "\n");
	        } finally {
	            out.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	private static void exists(String fileName) throws FileNotFoundException {
	    File file = new File(fileName);
	    if (!file.exists()){
	        throw new FileNotFoundException(fin.getName());
	    }
	}
}

class Tree {
    static class Node {
        long key;
        Node leftson;
        Node rightson;
        public Node(long key){
        	this.key = key;
        }
    }
    
    private Node root;
    
    public Tree(){
    	this.root = null;
    }
    
    public Node GetRoot(){
    	return this.root;
    }
    
    public void SetRoot(Node node){
    	this.root = node;
    }
    
    public void insert(long x) {
    	assert (x >= -Math.pow(2, 31) && x <= Math.pow(2, 31) - 1);
        root = doInsert(root, x);
    }
     
    private static Node doInsert(Node node, long x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.leftson = doInsert(node.leftson, x);
        } else if (x > node.key) {
            node.rightson = doInsert(node.rightson, x);
        }
        return node;
    }
    
    public long TravelSum (Node root){
    	if (root != null){    		
    		return TravelSum(root.leftson) + TravelSum(root.rightson) + root.key;
    	}
    	else return 0;
    }
    
    public void PreOrder (Node root, PrintWriter out){
    	if (root != null){
    		out.print(root.key);
    		out.println();
    		PreOrder(root.leftson, out);
    		PreOrder(root.rightson, out);
    	}
    	else return;
    }
    
    public Node RightDelete (Node root, long x){
    	if (root == null) return null;
    	if (x < root.key){
    		root.leftson = RightDelete (root.leftson, x);
    		
   			return root;
   		}
    		
    	else if (x > root.key){
    		root.rightson = RightDelete (root.rightson, x);
    		return root;
   		}
    	
    	else{
    		if (root.leftson == null){
    			return root.rightson;
    		}
    	
    		else if (root.rightson == null){
    			return root.leftson;
    		}
    	
    		else{
    			long min = findMin(root.rightson).key;
    			root.key = min;
    			root.rightson = RightDelete(root.rightson, min);
    			return root;
    		}
    	}
    }
    
    public Node findMin(Node root){
    	if(root.leftson != null){
    		return findMin(root.leftson);
    	}
    	else return root;
    }
}