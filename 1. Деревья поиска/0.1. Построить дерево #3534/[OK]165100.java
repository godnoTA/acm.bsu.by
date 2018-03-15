import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class TreeZeroTest {
	
	static ArrayList <Integer> st;
	
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
	    public void retSum(TreeNode treeNode) {
	    	if(treeNode != null) {
	    		st.add(treeNode.returnValue());
	    		System.out.println(treeNode.returnValue());
	    		retSum(treeNode.getLeft());
	    		retSum(treeNode.getRight());
	    	}
	    }
	}
	
	public static void main(String Args[]) throws IOException
	{
		st = new ArrayList<>();
		Scanner SC = new Scanner(new File("input.txt"));
		int curVerInt = 0;
	    Tree fTree = new Tree();   
		   while(SC.hasNext()) {
			   curVerInt = SC.nextInt();
			   fTree.insert(curVerInt);
		   }
	    try {
	    	PrintWriter pw = new PrintWriter(new File("output.txt"));
	    	fTree.retSum(fTree.retRoot());
	    	int size = st.size();
	    	for(int i = 0; i<size; i++) {
	    		//System.out.println(st.size());
	    		pw.print(st.get(i) + "\n");
	    	}
	    	
	    	pw.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    SC.close();
	}

}