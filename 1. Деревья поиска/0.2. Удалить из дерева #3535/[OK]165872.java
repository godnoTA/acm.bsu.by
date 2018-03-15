import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class test 
{
	static class TVertex 
	{
	    public int value;
	    public TVertex right;
	    public TVertex left;
	    public TVertex (int value)
	    {
	    	this.value=value;
	    }
	}
	public static TVertex root;
	public static void insert(int value)
	{
		root=preinsert(root,value);
	}
	public static TVertex preinsert(TVertex root,int value)
	{
		if(root==null)
			return new TVertex(value);
		if(value>root.value)
				root.right=preinsert(root.right,value);
		else
			if(value<root.value)
				root.left=preinsert(root.left,value);
		return root;
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
		int key;
		try (Scanner in=new Scanner(new File("input.txt")))
		{
			key=in.nextInt();
			String s=in.nextLine();
			while(in.hasNextInt())
			{
				insert(in.nextInt());
			}
			delete(key);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			PrintWriter out = new PrintWriter(new File("output.txt"));
			preorder(root,out);
			out.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}
