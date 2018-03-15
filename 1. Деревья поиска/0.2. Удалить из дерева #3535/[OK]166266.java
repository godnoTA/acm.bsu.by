

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


class Tree {
    static class Node {
        long key;
        Node left;
        Node right;
        public Node(long key) {
            this.key = key;
        }
    }
    private Node root;
    public void Insert(long x) {
    	if(Find(root, x) != null)
    		return;
        root = Paste(root, x);
    }
     
    private static Node Paste(Node node, long x) {
        if (node == null) {
            return new Node(x);
        }
        if (x < node.key) {
            node.left = Paste(node.left, x);
        } else if (x > node.key) {
            node.right = Paste(node.right, x);
        }
        return node;
    }
    public boolean Search(long x){
    	return (Find(root, x) == null)?false:true;
    }
    private static Node Find(Node root, long x)
    {
        if(root == null)
            return null;
        if(root.key == x)
            return root;
        if(root.key > x){

            return Find(root.left, x);
        }
        else{
            return Find(root.right, x);
        }
    }
    public void Print(FileWriter file)throws Exception
    {
    	preorderPrint(root, file);
    }
    private static void preorderPrint(Node root, FileWriter file)throws Exception
    {
        if (root == null)   
        {
           return;
        }
        String str = Long.toString(root.key) + "\n";
        file.write(str);
        preorderPrint(root.left, file);   
        preorderPrint(root.right, file);  
    }
    public void Erase(long x)
    {
    	root = Remove(root, x);
    }
    private static Node Remove(Node root, long x)
    {
    	if(root == null)
    		return null;
    	if( x < root.key)
    	{ 
    		root.left = Remove(root.left, x);
    		return root;
    	}
    	if( x > root.key)
    	{
    		root.right = Remove(root.right, x);
    		return root;
    	}
    		if (root.left == null)
    			return root.right;
    		if(root.right == null)
    			return root.left;
    		else{
    			long min_key = FindMin(root.right).key;
    			root.key = min_key;
    			root.right = Remove(root.right, min_key);
    			return root;
    		}
    }
    private static Node FindMin(Node v)
    {
    	if(v.left != null)
    		return FindMin(v.left);
    	else
    		return v;
    }
}
public class Main_02 {

	public static void main(String [] args){
		try
		{
			FileReader file = new FileReader("input.txt");
			BufferedReader br = new BufferedReader (file);
			String str;
			Tree tree = new Tree();
			str = br.readLine();
			long del = Long.parseLong(str);
			br.readLine();
			while((str = br.readLine()) != null)
			{
				long num = Long.parseLong(str);
				tree.Insert(num);
			}
			file.close();
			br.close();
			tree.Erase(del);
			FileWriter out = new FileWriter("output.txt");
			tree.Print(out);
			out.close();
		}
		 catch(IOException ex)
		{
			 System.out.println(ex.getMessage());
		}
		catch(IllegalArgumentException ex)
		{
			 System.out.println(ex.getMessage());
		}
		catch(Exception ex)
		{
			 System.out.println(ex.getMessage());
		}
	}
}
