import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

class Node {
	public long key; 
	public Node left;
	public Node right;
	public int height = 0;  
	
	Node(long a)
	{
		key = a;
	}
}

class Tree {
	public Node root;
	
	Tree(){}
	
	Tree(Node a)
	{
		root = a;
	}

	public void insert(long val)
	{
		Node pv = root;
		Node prev = null;
		while (pv != null)
		{
			prev = pv;
			if (val < pv.key)
				pv = pv.left;
			else 
			{
				if (val > pv.key)
					pv = pv.right;
				else
					return;
			}
		}
		
		Node pnew = new Node(val);
		if (prev == null)
			root = pnew;
		else
		{
			pnew.height = prev.height + 1;
			if (val < prev.key)
				prev.left = pnew;
			else
				prev.right = pnew;
		}
	}
	public void print(PrintStream out)
	{
		Node p = root;
		doPrint(p, out);
	}
	public void doPrint(Node p, PrintStream out)
	{
		if (p != null)
		{
			out.println(p.key);
			doPrint(p.left, out);
			doPrint(p.right, out);
		}
	}
	public void delete(long val)
	{
		Node pv = root;
		Node prev = null;
		while (pv != null)
		{
			if (val < pv.key)
			{
				prev = pv;
				pv = pv.left;
			}
			else 
			{
				if (val > pv.key)
				{
					prev = pv;
					pv = pv.right;
				}
				else
					break;
			}
		}
		if (pv == null)
			return;
		
		Node res;
		if (pv.left == null)
			res = pv.right;
		else
		{
			if (pv.right == null)
				res = pv.left;
			else
			{
				Node min_node_prev = pv;
				Node min_node = pv.right;
				while (min_node.left != null)
				{
					min_node_prev = min_node;
					min_node = min_node.left;
				}
					 
				res = pv;
				pv.key = min_node.key;
				replace_child(min_node_prev, min_node, min_node.right);
			}
		}
		replace_child(prev, pv, res);
	}
	public void replace_child(Node prev, Node oldp, Node newp)
	{
	    if (prev == null)
	        root = newp;
	    else
	    {
	    	if (prev.left == oldp)
	    		prev.left = newp;
	    	else
	    	{
	    		if (prev.right == oldp)
	    			prev.right = newp;
	    	}
	    }
	}
}


public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
			Scanner in = new Scanner(new FileInputStream("input.txt"));
			PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
			long val = Long.parseLong(in.nextLine());
			in.nextLine();
			
			Tree T = new Tree();
			
			while (in.hasNextLine())
			{
					T.insert(Long.parseLong(in.nextLine()));
			}
			T.delete(val);
			T.print(out);
			
			in.close();
			out.close();
			System.exit(0);
		}
}
