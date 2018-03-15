import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
}

public class Source {
	
	final static String filename = "input.txt";
	static File fin = new File(filename);
	static File fout = new File("output.txt");
	public static void main (String args[]) throws IOException{
		exists(filename);
		List<Long> buffer = new ArrayList<Long>();
		try {
	        //Объект для чтения файла в буфер
	        BufferedReader in = new BufferedReader(new FileReader( fin.getAbsoluteFile()));
	        try {
	            //В цикле построчно считываем файл
	            String s;
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
		for(long x : buffer){
			tree.insert(x);
		}
		
		try {
	        if(!fout.exists()){
	            fout.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(fout.getAbsoluteFile());
	 
	        try {
	            out.print(tree.TravelSum(tree.GetRoot()));
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