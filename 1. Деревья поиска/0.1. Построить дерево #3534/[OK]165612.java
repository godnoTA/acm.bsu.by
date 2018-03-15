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
	public static void main(String[] args)throws FileNotFoundException 
	{
		try (Scanner in=new Scanner(new File("input.txt")))
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
