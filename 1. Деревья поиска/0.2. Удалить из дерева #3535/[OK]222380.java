
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class BinaryST implements Runnable {
	private Node root;   
	private HashSet<Integer> set = new HashSet<>();
	private FileWriter out;

    private class Node {
        private int value;        
        private Node left, right;  
        private int size;

        public Node(int val, int size) {
            this.value = val;
            this.size = size;
        }
    }

    public BinaryST() {}

    public boolean isEmpty() {
    	return size() == 0;
    }
    
    private int size(Node x) {
    	if (x == null) return 0;
    	return x.size;
    }
    
    public int size() {
    	return size(root);
    }
    
    public void put(int value) {
    	root = put(root, value);
    }
    
    private Node put(Node x, int value) {
    	if (x == null)
    		return new Node(value, 1); 
    	int cmp = value - x.value;
    	if      (cmp < 0) x.left = put(x.left, value);
    	else if (cmp > 0) x.right = put(x.right, value);
    	x.size = 1 + size(x.left) + size(x.right);
    	return x;
    }
    
    public boolean contains(int value) {
    	return set.contains(value) == true;

    }
    
    public int get(int value) {
    	return get(root, value);
    }

	private int get(Node x, int value) {
		if (x == null) return -1;
		int cmp = value - x.value;//value.compareTo(x.value);
		if      (cmp < 0) return get(x.left, value);
		else if (cmp > 0) return get(x.right, value);
		else              return value;
	}
	  public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException();
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.size = 1 + size(x.right) + size(x.left);
		return x;
	}
	
	public void delete(int value) {
	    root = delete(root, value);
	}
	
	private Node delete(Node x, int value) {
	    if (x == null) return null;
	
	    int cmp = value - x.value;//.compareTo(x.value);
	    if      (cmp < 0) x.left  = delete(x.left,  value);
	    else if (cmp > 0) x.right = delete(x.right, value);
	    else { 
	        if (x.right == null) return x.left;
	        if (x.left  == null) return x.right;
	        Node t = x;
	        x = min(t.right);
	        x.right = deleteMin(t.right);
	        x.left = t.left;
	    } 
	    x.size = size(x.left) + size(x.right) + 1;
	    return x;
	} 
	
	public int min() {
	    if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
	    return min(root).value;
	} 
	
	private Node min(Node x) { 
	    if (x.left == null) return x; 
	    else                return min(x.left); 
	} 
	
	  public void print(FileWriter out) {
		print(root, out);
	}
	
	private void print(Node x, FileWriter out) {
		if (x != null) {
			try {
				String test = Integer.toString((Integer) x.value);
				//System.out.print(test + "|");
				out.write(test + "\r\n");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (x.left != null) print(x.left, out);
			if (x.right != null) print(x.right, out);
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		File fis = new File("input.txt");
 		File fos = new File("output.txt");
 		Scanner in;
 		FileWriter out;
 		int del = 0;
 		try {
 			in = new Scanner(fis);
 			if (in.hasNextLine())
 				del = Integer.parseInt(in.nextLine());
 			while (in.hasNextLine()) {
 				String str = in.nextLine();
 				if ("".equals(str)) continue;
 				int tmp = Integer.parseInt(str);
 				if (!contains(tmp))
 					put(tmp);
 			}
 			in.close();
 			delete(del);
 			out = new FileWriter(fos);
 			print(out);
 			out.close();
 			
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		}		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(null, new BinaryST(), "", 64 * 1024 * 1024).start();
	}

}