

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BinaryST implements Runnable {
	private Node root;   
	private HashSet<Integer> set = new HashSet<>();
	private ArrayList<Integer> values = new ArrayList<>();
	private Scanner in;
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
    	int cmp = value - x.value;//value.compareTo(x.value);
    	if      (cmp < 0) x.left = put(x.left, value);
    	else if (cmp > 0) x.right = put(x.right, value);
    	x.size = 1 + size(x.left) + size(x.right);
    	return x;
    }
    
    public boolean contains(int value) {
    	return set.contains(value);

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
	
	public void print(FileWriter out) throws IOException {
		print(root, out);
	}
	
	private void print(Node x, FileWriter out) throws IOException{
		if (x != null) {
			System.out.println(x.value);
			out.write(x.value + "\r\n");
			if (x.left != null)  print(x.left, out);
			if (x.right != null) print(x.right, out);
		}
	}
	public void getValues(ArrayList<Integer> arr) {
		getValues(root, arr);
	}
	
	private void getValues(Node x, ArrayList<Integer> arr) {
		if (x != null) {
			arr.add(x.value);
			if (x.left  != null) getValues(x.left, arr);
			if (x.right != null) getValues(x.right, arr);
		}
	}
	@Override
	public void run() {
		File fis = new File("input.txt");
		File fos = new File("output.txt");
		int tmp;
		try {
			in = new Scanner(fis);
			while (in.hasNextLine()) {
				tmp = Integer.parseInt(in.nextLine());
				if (!contains(tmp)) {
					set.add(tmp);
					put(tmp);
				}
			}
			in.close();
			getValues(values);
			out = new FileWriter(fos);
			for (int i: values) out.write(i + "\r\n");
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(null, new BinaryST(), "", 32 * 1024 * 1024).start();
	}

}