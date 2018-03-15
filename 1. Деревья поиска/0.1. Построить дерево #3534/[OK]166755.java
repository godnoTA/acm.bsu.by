import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class Tree {
    static class Node {
        int key;
        Node leftson;
        Node rightson;
        public Node(int key){
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
    
    public void insert(int x) {
    	assert (x >= -Math.pow(2, 31) && x <= Math.pow(2, 31) - 1);
        root = doInsert(root, x);
    }
     
    private static Node doInsert(Node node, int x) {
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
    
    public int TravelSum (Node root){
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
}

public class Source_01 {
	
	final static String filename = "Input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static void main (String args[]) throws IOException{
		exists(filename);
		List<Integer> buffer = new ArrayList<Integer>();
		try {
	        //Объект для чтения файла в буфер
	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
	        try {
	            //В цикле построчно считываем файл
	            String s;
	            while ((s = in.readLine()) != null) {
	            	buffer.add(Integer.parseInt(s));
	            }
	        } finally {
	            in.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
		
		Tree tree = new Tree();
		for(int x : buffer){
			tree.insert(x);
		}
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	            tree.PreOrder(tree.GetRoot(), out);
	            for (int i: buffer) System.out.print(i + "\n");
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