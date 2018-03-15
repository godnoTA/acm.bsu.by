import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;


class Tree {
	class Node {
	    public long key;
	    public Node left;
	    public Node right;
	    public Node father;
	    public int leftheight=-1;
	    public int rightheight=-1;
		public int height = 0;
	    public Node(int key) {
	        this.key = key;
	    }
	}
  public Node root;
  public void insert(int x) {
      root = doInsert(root, x);
  }
   
  private Node doInsert(Node node, int x) {
      if (node == null) {
          return new Node(x);
      }
      if (x < node.key) {
          node.left = doInsert(node.left, x);
      } else if (x > node.key) {
          node.right = doInsert(node.right, x);
      }
      return node;
  }
  
  public static Node DeleteRecursively(Node v, long x) {
	  if(v==null)
	  return null;
	  else{
		  if (x < v.key) {
			  v.left=DeleteRecursively(v.left, x);
			  return v;
		  }
		  else
			  if( x > v.key) {
				  v.right=DeleteRecursively(v.right, x);
				  return v;
			  }
			  if( v.left==null) {
				  return v.right;
			  }
			  else
				  if (v.right==null) {
					  return v.left;
				  }
			  else {
				  if(v.right.height==v.left.height) {
					  long min_key = FindMin(v.right).key;
					  v.key = min_key;
					  v.right=DeleteRecursively(v.right, min_key);}
			  return v;
			  }
	  }
	 
  }
  public static Node FindMin(Node v) {
	  if (v.left!=null)
		  return FindMin(v.left);
	  else {
		  return v;
	  }
  }

public static void PreOrderTraversal(Node v, FileWriter fw) 
  { 
  if(v!=null) 
  { 
  try { 
  fw.write(Long.toString(v.key) + "\r\n"); 
  } catch (IOException e) { 
  e.printStackTrace(); 
  } 
  PreOrderTraversal(v.left,fw); 
  PreOrderTraversal(v.right,fw); 
  } 
  }
TreeSet<Long> myTreeSet=new TreeSet<>();
public void doSetHeight(Node p) 
{	
if (p != null) 
{ 
doSetHeight(p.left); 
doSetHeight(p.right); 
if (p.left == null && p.right == null) {
	myTreeSet.add(p.key);
	return; 
}
if (p.left == null || p.right == null) 
{ 
p.height = ((p.left == null) ? p.right.height : p.left.height) + 1; 
} 
else 
{ 
p.height = ((p.right.height > p.left.height) ? p.right.height : p.left.height) + 1;
if(p.left.height==p.right.height){
	myTreeSet.add(p.key);
} 
} 
}

}
public void mydelete () {
	long keydelete;
	if(myTreeSet.size()%2!=0) {
		
		long[]mas=new long[myTreeSet.size()];
		int i=0;
		for(long k:myTreeSet) {
			mas[i++]=k;
		}
		int j=(myTreeSet.size()-1)/2;
		keydelete=mas[j];
		
		DeleteRecursively(root, keydelete);
		}
}

}
public class IndTask implements Runnable {
	

	 public static void main(String args[]){
	    	
		 new Thread(null, new IndTask(), "", 64 * 1024 * 1024).start();
	    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		File in = new File("tst.in");
		   Tree mytree = new Tree();
		   try {
		        Scanner sc = new Scanner(in);
		        while (sc.hasNext()) {
		            mytree.insert(sc.nextInt());
		        }
		   } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    }
		  
		   try(FileWriter fos=new FileWriter("tst.out"))
	        {
			   Tree.Node node=mytree.root;
			   mytree.doSetHeight(node);
	           mytree.mydelete();
			   mytree.PreOrderTraversal(mytree.root, fos);
	        }
	        catch(IOException ex){
	        	System.out.println(ex.getMessage());
	        }
		  
	}
}

