import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Tree {
    class Node 
    {
        int key;
        Node left;
        Node right;
        public Node(int x)
        {
        	this.key = x;
        }
    }
    Node root;
    
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
 
    public void Print(Node v, FileWriter fw)
    {
    	if(v!=null)
    	{
    		try {
				fw.write(Integer.toString(v.key));
				fw.write('\n');
			} catch (IOException e) {}
    		Print(v.left,fw);
        	Print(v.right,fw);
    	}
    }
    
    public Node DeleteElement(Node v, int x)
    {
    	if(v == null)
    		return null;
    	if(x<v.key)
    	{
    		v.left = DeleteElement(v.left,x);
    		return v;
    	}
    	else if(x>v.key)
    	{
    		v.right = DeleteElement(v.right,x);
    		return v;
    	}
    	if(v.left == null)
    		return v.right;
    	else if(v.right == null)
    		return v.left;
    	else
    	{
    		int minkey = FindMinEl(v.right).key;
    		v.key = minkey;
    		v.right = DeleteElement(v.right, minkey);
    		return v;
    	}
    }
    public Node FindMinEl(Node v)
    {
    	if(v.left!=null)
    		return FindMinEl(v.left);
    	else
    		return v;
    }
}

public class Main implements Runnable{
	
	public static void main(String[] args) throws IOException {
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}
	public void run() {
		try{
			Tree tree = new Tree();
			Scanner in = new Scanner(new FileReader("input.txt"));
			int del = Integer.parseInt(in.nextLine());
			in.nextLine();
			while (in.hasNextLine()) {
				tree.insert(Integer.parseInt(in.nextLine()));
	        }
			tree.root = tree.DeleteElement(tree.root, del);
			FileWriter out = new FileWriter("output.txt");
			tree.Print(tree.root, out);
			in.close();
			out.close();
		}catch (IOException e) {}
	}
}