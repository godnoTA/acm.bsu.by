import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Main implements Runnable{
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			Tree tree = new Tree();
			FileReader fr = new FileReader("input.txt");
			Scanner scan = new Scanner(fr);
			int del = Integer.parseInt(scan.nextLine());
			scan.nextLine();
			while (scan.hasNextLine()) {
				tree.insert(Integer.parseInt(scan.nextLine()));
	        }
			fr.close();
			tree.root = tree.DeleteRecursively(tree.root, del);
			FileWriter writer = new FileWriter("output.txt");
			tree.PreOrderTraversal(tree.root, writer);
			writer.close();
			}
			catch (NumberFormatException | IOException e) {
		        System.err.println("Неверный формат строки!");
		    } 
	}

}

class Tree {
    static class Node 
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
     
    private static Node doInsert(Node node, int x) {
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
 
    public static void PreOrderTraversal(Node v, FileWriter fw)
    {
    	if(v!=null)
    	{
    		//System.out.println(v.key);
    		try {
				fw.write(Integer.toString(v.key));
				fw.write('\n');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		PreOrderTraversal(v.left,fw);
        	PreOrderTraversal(v.right,fw);
    	}
    }
    
    public static Node DeleteRecursively(Node v, int x)
    {
    	if(v == null)
    		return null;
    	if(x<v.key)
    	{
    		v.left = DeleteRecursively(v.left,x);
    		return v;
    	}
    	else if(x>v.key)
    	{
    		v.right = DeleteRecursively(v.right,x);
    		return v;
    	}
    	if(v.left == null)
    		return v.right;
    	else if(v.right == null)
    		return v.left;
    	else
    	{
    		int minkey = FindMin(v.right).key;
    		v.key = minkey;
    		v.right = DeleteRecursively(v.right, minkey);
    		return v;
    	}
    }
    public static Node FindMin(Node v)
    {
    	if(v.left!=null)
    		return FindMin(v.left);
    	else
    		return v;
    }
}