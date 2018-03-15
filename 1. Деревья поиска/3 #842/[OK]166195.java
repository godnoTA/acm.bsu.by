import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class test 
{
	static class TVertex 
	{
	    public int value;
	    public int cL;
	    public int cR;
	    public TVertex right;
	    public TVertex left;
	    public TVertex (int value,int cl,int cr)
	    {
	    	this.value=value;
	    	this.cL=0;
	    	this.cR=0;
	    }
	}
	public static TVertex root;
	public static ArrayList<Integer> values = new ArrayList<Integer>(); 
	public static void insert(int value)
	{
		root=preinsert(root,value);
	}
	public static TVertex preinsert(TVertex root,int value)
	{
		if(root==null)
			return new TVertex(value,0,0);
		if(value>root.value)
		{
				root.right=preinsert(root.right,value);
				root.cR++;
		}
		else
		{
			if(value<root.value)
			{
				root.left=preinsert(root.left,value);
				root.cL++;
			}
		}
		return root;
	}
	public static void preorder1(TVertex root)
	{
		if(root!=null)
		{
			try
			{
				if(Math.abs(root.cL-root.cR)==1)
					values.add(root.value);
			}
			catch(Exception e){}
			preorder1(root.left);
			preorder1(root.right);
		}
	}
	public static void preorder(TVertex root,PrintWriter out)
	{
		if(root!=null)
		{
			try
			{
				out.write(Integer.toString(root.value));
				out.println();
			}
			catch(Exception e){}
			preorder(root.left,out);
			preorder(root.right,out);
		}
	}
	public static void delete(int value)
	{
		root=predelete(root,value);
	}
	public static TVertex predelete(TVertex root,int value)
	{
		if(root==null)
			return null;
		else
		{
			if(value>root.value)
				root.right=predelete(root.right,value);
			else
			{
				if(value<root.value)
					root.left=predelete(root.left,value);
				else 
				{
					if(root.right!=null && root.left!=null)
					{
						root.value=minKey(root.right).value;
						root.right=predelete(root.right,root.value);
					}
					else
					{
						if(root.right==null)
							return root.left;
						if(root.left==null)
							return root.right;
					}
				}
			}
			return root;
		}
	}
	public static TVertex minKey(TVertex vertex)
	{
		if(vertex.left!=null)
			return minKey(vertex.left);
		else
			return vertex;
	}
	public static void main(String[] args)throws FileNotFoundException 
	{
		try (Scanner in=new Scanner(new File("in.txt")))
		{
			while(in.hasNextInt())
			{
				insert(in.nextInt());
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			PrintWriter out = new PrintWriter(new File("out.txt"));
			preorder1(root);
			Collections.sort(values);
			if(values.size()%2==0)
				preorder(root,out);
			else
			{
				delete(values.get(values.size()/2));
				preorder(root,out);
			}
			out.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}
