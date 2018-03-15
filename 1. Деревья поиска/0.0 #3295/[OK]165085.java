import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TreeZeroTest {
	
	static public class TreeNode 
	{
		 private TreeNode left;
		 private TreeNode right;
		 private int value;
		 
		 public TreeNode(int data)
		 {
			   value = data;
			   right = left = null;
		 }
		 public int returnValue()
	     {
	   	   return this.value;
	     }     
		 //----------------------------------------------------------------------
		 public void setValue(int data)
	     {
	   	   this.value=data;
	     }
		 //----------------------------------------------------------------------
		 public TreeNode getLeft() 
	     {
	          return this.left;
	     }    
		 //----------------------------------------------------------------------
		 public void setLeft(TreeNode left) 
	     {
	          this.left = left;
	     } 
		 //----------------------------------------------------------------------
		 public TreeNode getRight() 
	     {
	          return this.right;
	     }  
		 //----------------------------------------------------------------------
		 public void setRight(TreeNode right) 
	     {
	          this.right = right;
	     }
		 //----------------------------------------------------------------------
	}
	
	static public class Tree
	{
		private TreeNode root;
	    
		public Tree() { this.root=null; }
	//----------------------------------------------------------------------
		public TreeNode retRoot() {
			return root;
		}
	    public void insert(int data)
	     {
		  TreeNode buffer = root;
			 if(root==null)
			 {
	            root = new TreeNode(data);
	     	 }
			 else 
			    while(true)
			    {
			      if(buffer.returnValue() > data) 
			      {
			    	if(buffer.getLeft() != null )
			    	{
					   buffer=buffer.getLeft();
			        }
	  	            else
	  	            {
		               TreeNode Mynode = new TreeNode(data);
		               buffer.setLeft(Mynode);
	                   break;
	                }
		           }
			       else if(buffer.returnValue() < data)
			       {
			           if(buffer.getRight() != null )
			    	   {	    				 
						 buffer=buffer.getRight();
					   }
				       else
				       {
				    	 TreeNode Mynode = new TreeNode(data);
		                 buffer.setRight(Mynode);
			             break;
				       } 
			       }
			       else if(buffer.returnValue() == data) {
			    	   break;
			       }
			    }	     
	     }
	    public Long retSum(TreeNode treeNode) {
	    	Long sum = (long) 0;
	    	if(treeNode != null) {
	    		sum += treeNode.returnValue();
	    		sum += (Long)retSum(treeNode.getLeft());
	    		sum += (Long)retSum(treeNode.getRight());
	    	}
	    	return sum;
	    }
	}
	
	public static void main(String Args[]) throws IOException
	{
		Scanner SC = new Scanner(new File("input.txt"));
		int sum = 0, curVerInt = 0;
		Long sumTest = (long) 0;
	    Tree fTree = new Tree();   
		   while(SC.hasNext()) {
			   curVerInt = SC.nextInt();
			   fTree.insert(curVerInt);
		   }
		   sumTest = fTree.retSum(fTree.retRoot());
	    try {
	    	PrintWriter pw = new PrintWriter(new File("output.txt"));
	    	pw.print(sumTest);
	    	pw.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    SC.close();
	}

}