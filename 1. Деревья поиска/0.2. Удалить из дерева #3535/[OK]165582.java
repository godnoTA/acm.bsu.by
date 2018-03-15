import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
        
	    public void deleteLoneRoot(TreeNode currentNode, TreeNode parentNode, int node) {
          //TreeNode parentNode = null, currentNode = root;
          if(currentNode == null) { return; }
          /*while(currentNode.returnValue() != node) {
        	  parentNode = currentNode;
        	  if(currentNode.returnValue() > node) {
        		  currentNode = currentNode.getLeft();
        	  }
        	  else {
        		  currentNode = currentNode.getRight();
        	  }  
          }*/
                if(parentNode == null) {
                	if(currentNode.getRight() != null) {
                		root = currentNode.getRight();
                	} else if(currentNode.getLeft() != null) {
                		root = currentNode.getLeft();
                	} else {
                		root = null;
                	}
                } else {
                	if(currentNode.getRight() != null) {
                		if(parentNode.getRight() == currentNode)
                		     parentNode.setRight(currentNode.getRight());
                		else 
                			 parentNode.setLeft(currentNode.getRight());
                	} else if(currentNode.getLeft() != null) {
                		if(parentNode.getRight() == currentNode)
               		         parentNode.setRight(currentNode.getLeft());
               		    else 
               			     parentNode.setLeft(currentNode.getLeft());
                	} else {
                		if(parentNode.getLeft() == currentNode) {
                			parentNode.setLeft(null);
                		} else { 
                		    parentNode.setRight(null); }
                	}
                }
	    } //deleteLoneRoot
	    
	    public void deleteRoot(TreeNode treeNode, int node) {
	    	 TreeNode parentNode = null, currentNode = root;
	   // 	 if(currentNode == null) { return; }
	    	 
	    	 while(currentNode.returnValue() != node) {
	               parentNode = currentNode;
	               if(currentNode.returnValue() > node) {
	            		  currentNode = currentNode.getLeft();
	               }
	               else {
	            		  currentNode = currentNode.getRight();
	               }  
	               if(currentNode == null) { return; }
	         }
	    	 
	    	 	    	 
	         if(currentNode.getLeft() == null || currentNode.getRight() == null) {
	       	    deleteLoneRoot(currentNode, parentNode, node);
	         }
	         else {	              	            
	            TreeNode rightThanLeftToEndNode = currentNode, rightThanLeftToEndNodeParent = parentNode;
	               rightThanLeftToEndNodeParent = rightThanLeftToEndNode;
	               rightThanLeftToEndNode = rightThanLeftToEndNode.getRight();
	               while (rightThanLeftToEndNode.getLeft() != null) {
	            	   rightThanLeftToEndNodeParent = rightThanLeftToEndNode;
	            	   rightThanLeftToEndNode = rightThanLeftToEndNode.getLeft();
	               }
	               
	               currentNode.setValue(rightThanLeftToEndNode.returnValue());
	               deleteLoneRoot(rightThanLeftToEndNode, rightThanLeftToEndNodeParent, rightThanLeftToEndNode.returnValue());
	        }
	         
	         
	    } //deleteRoot
	    
	    public void leftCLR(TreeNode treeNode) {
	    	if(treeNode != null) {
	    		st.add(treeNode.returnValue());
	    		leftCLR(treeNode.getLeft());
	    		leftCLR(treeNode.getRight());
	    	}
	    }
	    
	}
	

	
	public static void main(String Args[]) throws IOException
	{
		st = new ArrayList<>();
		Scanner SC = new Scanner(new File("input.txt"));
		int curVerInt = 0;
	    Tree fTree = new Tree();
	    if(!SC.hasNext()) { SC.close(); return; }
	    int ElForDelete = SC.nextInt();
	    //System.out.println(ElForDelete);
		   while(SC.hasNext()) {
			   curVerInt = SC.nextInt();
			   fTree.insert(curVerInt);
			   //System.out.println(curVerInt);
		   }
		   fTree.deleteRoot(fTree.retRoot(), ElForDelete);
	    try {
	    	PrintWriter pw = new PrintWriter(new File("output.txt"));
	    	fTree.leftCLR(fTree.retRoot());
	    	for(int i = 0; i < st.size(); i++) {
	    		pw.println(st.get(i));
	    	}	    	
	    	pw.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    SC.close();
	}

}