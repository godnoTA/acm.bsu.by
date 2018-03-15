import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class TreeZeroTest {
	
	static ArrayList <Integer> st;
	static Queue <TreeNode> stForWide, underStForWide, stVer;
	static int hght = 0, right = 0, left = 0, c = 0, versDel;
	static boolean whatIsBool = false;
	
	public static Comparator<TreeNode> MyComparator = new Comparator<TreeNode>() {
        @Override
        public int compare(TreeNode c1, TreeNode c2) {
            return 0;
        }
    };
	
	static public class TreeNode 
	{
		 private TreeNode left;
		 private TreeNode right;
		 private int value;
		 private int height;
		 
		 public TreeNode(int data)
		 {
			   value = data;
			   right = left = null;
		 }
		 
		 public int getHeight() {
			 return height;
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
          if(currentNode == null) { return; }
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
	    }
	    
	    public void deleteRoot(TreeNode treeNode, int node) {
	    	 TreeNode parentNode = null, currentNode = root;
	    	 
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
	         
	         
	    }
	    
	    public void leftCLR(TreeNode treeNode) {

	    	if(treeNode != null) {
	    		st.add(treeNode.returnValue());
	    		leftCLR(treeNode.getLeft());
	    		leftCLR(treeNode.getRight());
	    	}
	    }
	    
	    public int numOfNssryVers(TreeNode node) {
		TreeNode buf;
		stForWide = new PriorityQueue <TreeNode> (MyComparator);
		underStForWide = new PriorityQueue <TreeNode> (MyComparator);
		stForWide.add(node);
		while(stForWide.size() != 0) {
			while(stForWide.size() != 0) {
			  buf = stForWide.poll();
			  buf.height = hght;
			  if(buf.left != null)
				  underStForWide.add(buf.left);
			  if(buf.right != null)
			      underStForWide.add(buf.right); 
			}
			while(underStForWide.size() != 0) {
			  stForWide.add(underStForWide.poll()); 
			}
			++hght;
		}
		--hght;
		if(hght % 2 == 0) {
			//System.out.println(hght/2);
			return hght/2;
		}
		else {
			System.out.println( (hght-1)/2 + 1 );
			return ( (hght - 1)/2 + 1);
		}
	}

	    
	    public void checkAndPush(TreeNode treeNode, TreeNode rootOfVetka) {
	    	if(treeNode != null) {
	    		checkAndPush(treeNode.getLeft(), rootOfVetka);
	    		if(treeNode.returnValue() == rootOfVetka.returnValue()) {
	    			left = c;
	    			c = 0;
	    			whatIsBool = true;
	    		}
	    		else {
	    			
	    	        ++c;
	    		}
	    		checkAndPush(treeNode.getRight(), rootOfVetka);
	    	}	   
	    	right = c; 	
	    }
	    
	    public void boolLeftCLR(TreeNode treeNode, int h) {	    	
	    	if(treeNode != null) {
	    		if(treeNode.getHeight() == h) {
	    			checkAndPush(treeNode, treeNode);
	    			c = 0;
	    			if(left > right) {
	    				stVer.add(treeNode);
	    			}
	    		}
	    		boolLeftCLR(treeNode.getLeft(), h);
	    		boolLeftCLR(treeNode.getRight(), h);
	    	}
	    }
	    
	    public boolean findVerses(Tree tree) {
	    	TreeNode treeNode = tree.retRoot();
	    	stVer = new PriorityQueue <TreeNode> (MyComparator);
	    	int h = this.numOfNssryVers(this.retRoot());
	    	boolLeftCLR(treeNode, h);
	    	if(stVer.size() % 2 == 0) { 
                return false;
	    	}
	        	Integer[] intArr = new Integer[stVer.size()];
	        	int size = stVer.size();
                for(int i = 0; i < size; i++) {
                	intArr[i] = stVer.poll().returnValue();
                }
                Arrays.sort(intArr);
                versDel = intArr[(intArr.length -1) /2];
	        return true;
	    }
	}
	
	
	public static void main(String Args[]) throws IOException
	{
		st = new ArrayList<>();
		Scanner SC = new Scanner(new File("in.txt"));
		int curVerInt = 0;
	    Tree fTree = new Tree();
	    if(!SC.hasNext()) { SC.close(); return; }
		   while(SC.hasNext()) {
			   curVerInt = SC.nextInt();
			   fTree.insert(curVerInt);
		   }
		   if (fTree.findVerses(fTree)) {
		       fTree.deleteRoot(fTree.retRoot(), versDel);
		   }
	    try {
	    	PrintWriter pw = new PrintWriter(new File("out.txt"));
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