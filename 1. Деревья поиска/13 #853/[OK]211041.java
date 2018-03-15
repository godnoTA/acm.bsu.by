 import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

class Node implements Comparable<Node>{
	public long key; 
	public Node left;
	public Node right;
	public Node father;
	public int height = 0;  
	public int msl = 0;
	
	Node(long a)
	{
		key = a;
	}
	public int compareTo(Node a)
	{
		return new Long(key).compareTo(new Long(a.key));
    }
}

class Tree {
	public Node root;
	public static int max_msl = 0;
	public static Stack<Node> st;
	
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
			if (val < prev.key)
				prev.left = pnew;
			else
				prev.right = pnew;
			pnew.father = prev;
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
	public void setHeight()
	{
		Node p = root;
		doSetHeight(p);
	}
	public void doSetHeight(Node p)
	{
		if (p != null)
		{
			doSetHeight(p.left);
			doSetHeight(p.right);
			if (p.left == null && p.right == null)
				return;
			if (p.left == null || p.right == null)
			{
				p.height = ((p.left == null) ? p.right.height : p.left.height) + 1;
				p.msl = p.height; ///?
			}
			else
			{
				p.height = ((p.right.height > p.left.height) ? p.right.height : p.left.height) + 1;	
				p.msl = p.left.height + p.right.height + 2 - 1; //т к нужно вершины разной высоты
			}
			if (p.msl >= max_msl)
			{
					if (p.msl > max_msl)
					{
						max_msl = p.msl;
						if (st != null)
							st = null;
					}
					if (st == null)
						st = new Stack<Node>();
					st.push(p);
			}
		}
	}
	
	public void delete(Node pv)
	{
		if (pv == null)
			return;
		Node prev = pv.father;
		
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
	
	public Node pSearch()
	{
		Node n[] = new Node[5];
		Node path[] = new Node[5];
		Node p; // beg, end;
		long sum;
		
		if (st.empty())
			return null;
		p = (Node) st.pop();
		path[0] = p;
		if (p.left == null || p.right == null)
		{
			path[1] = p;
			path[2] = endSearch(p);
		}
		else
		{
			Node beg2, end2;
			path[1] = endSearch(p.left);  //beg
			path[2] = almostEndSearch(p.right);  //end

			beg2 = almostEndSearch(p.left);
			end2 = endSearch(p.right);
			
			if (path[1].key + path[2].key >= beg2.key + end2.key)
			{
				if (path[1].key + path[2].key == beg2.key + end2.key)
				{
					path[3] = beg2;
					path[4] = end2;
				}
				else
				{
					path[1] = beg2;
					path[2] = end2;
				}
			}
		}
		n = Arrays.copyOf(path, 5);
		sum = n[1].key + n[2].key;
		
		while (!st.empty())
		{
			p = (Node) st.pop();
			path = null;
			path = new Node[5];
			path[0] = p;
			if (p.left == null || p.right == null)
			{
				path[1] = p;
				path[2] = endSearch(p);
			}
			else
			{
				Node beg2, end2;
				path[1] = endSearch(p.left);
				path[2] = almostEndSearch(p.right);

				beg2 = almostEndSearch(p.left);
				end2 = endSearch(p.right);
				
				if (path[1].key + path[2].key > beg2.key + end2.key)
				{
					path[1] = beg2;
					path[2] = end2;
				}
				else
				{
					if (path[1].key + path[2].key == beg2.key + end2.key)
					{
						path[3] = beg2;
						path[4] = end2;
					}
				}
			}
			if (path[1].key + path[2].key < sum)
			{
				n = null;
				n = new Node[5];
				n = Arrays.copyOf(path, 5);
				sum = n[1].key + n[2].key;
			}
			else
			{
				if (path[1].key + path[2].key == sum)
				{
					if (path[0].key < n[0].key)
					{
						n = null;
						n = new Node[5];
						n = Arrays.copyOf(path, 5);
						sum = n[1].key + n[2].key;
					}
					if (path[0].key == n[0].key)
					{
						n = null;
						n = new Node[5];
						n = Arrays.copyOf(path, 5);
						sum = n[1].key + n[2].key;
					}
				}
			}
		}
		Node pMiddle = null;
		if (n[3] == n[4]) // null
		{
			pMiddle = middleSearch(n);
		}
		else
		{
			Node p1 = middleSearch(Arrays.copyOfRange(path, 0, 3));
			Node temp[] = new Node[3];
			temp[0] = path[0];
			temp[1] = path[3];
			temp[2] = path[4];
			Node p2 = middleSearch(temp);
			if (p1 == p2)
				pMiddle = p1;
		}
		return pMiddle;
	}
	public Node endSearch(Node p)
	{
		Node pv;
		pv = p;
		while (p != null)
		{
			pv = p;
			if (p.left == null || p.right == null)
				p = (p.left == null)? p.right : p.left;
			else 
			{
				if(p.right.height != p.left.height)
					p = (p.right.height < p.left.height) ? p.left : p.right;
				else
				{
					Node p1 = endSearch(p.left);
					Node p2 = endSearch(p.right);
					return pv = (p1.key > p2.key) ? p2 : p1;
				}
			}
		}
		return pv;
	}
	public Node almostEndSearch(Node p)
	{
		Node pv;
		pv = p.father;
		while (p != null)
		{
			pv = p.father;
			if (p.left == null || p.right == null)
				p = (p.left == null)? p.right : p.left;
			else 
			{
				if(p.right.height != p.left.height)
					p = (p.right.height < p.left.height) ? p.left : p.right;
				else
				{
					Node p1 = almostEndSearch(p.left);
					Node p2 = almostEndSearch(p.right);
					return pv = (p1.key > p2.key) ? p2 : p1;
				}
			}
		}
		return pv;
	}
	
	public static Node middleSearch(Node[] n)
	{
		if (max_msl == 0 || (max_msl % 2 != 0))
			return null;
		Node[] peaks = new Node[max_msl + 1];
		int N = 0;
		Node p = n[1];
		while (p != n[0])
		{
			peaks[N++] = p;
			p = p.father;
		}
		p = n[2];
		while (p != n[0])
		{
			peaks[N++] = p;
			p = p.father;
		}
		if (peaks[N - 1].key != p.key)
			peaks[N] = p;
	//	for (Node peak: peaks)
		//System.out.println(peak.key);
		Arrays.sort(peaks);
		
		return peaks[max_msl / 2];
	}
}


public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
			Scanner in = new Scanner(new FileInputStream("tst.in"));
			PrintStream out = new PrintStream(new FileOutputStream("tst.out"));
			
			Tree T = new Tree();
			
			while (in.hasNextLine())
			{
					T.insert(Long.parseLong(in.nextLine()));
			}
			
			T.setHeight();
			T.delete(T.pSearch());
			T.print(out);
			
			in.close();
			out.close();
			System.exit(0);
		}
}
