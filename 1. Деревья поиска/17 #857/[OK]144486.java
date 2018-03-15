import java.util.*;
import java.awt.PageAttributes.PrintQualityType;
import java.io.*;
import java.io.File;
import java.io.IOException;

public class NullNullTask {


	public static void main(String[] args) throws IOException {
		
		Tree tree= new Tree();
		int x;
		 BufferedReader br = new BufferedReader(new FileReader("in.txt"));
		 
	        String line;
	        while ((line = br.readLine()) != null) {
	             x = Integer.parseInt(line);
	             
	            tree.addValue(x);
	        }

		
		
	
		 PrintWriter out = new PrintWriter(new FileWriter("out.txt"));		 
	
		 goHigh(tree.root);		
		// for(Node i: list)
		//	 System.out.println(i.getKey()+" ");
		addNode(tree.root); 
		Node founded=choiseNode();
	//	System.out.println(founded.getKey());
	
		if(founded!=null)
		tree.myDelete(founded);
	
		pp(tree.root);
		if(tree.root!=null)
		{
		for(Integer i : vec)
		{
			out.println(i);
		}
		}
	        
	        out.flush();


	}
	
	
	static int gearCount=0;
	static int gear=-1;
	static int max=0;
	
	public static Vector<Integer> vec=new Vector<Integer>();
	
	public static void pp(Node cur)
	{
		 if (cur == null)
		        return;
		 
		 vec.add(cur.getKey());
		 pp(cur.getLeft());
		 pp(cur.getRight());
				
	}
	

	
	public static int countHigh(Node cur)
	{
		gearCount=0;
		gear=-1;
		max=0;
		myGearCount(cur);
		return gearCount;
	}
	public static void  myGearCount(Node cur)
	{		
		gear++;
		
		 if (cur == null)
		 {
			 gear--;
			 return ; 
		 }
		       
		 
		 if(gearCount<gear)
		 {
			 gearCount=gear;
			max=cur.getKey(); 
		 }
		 
		 myGearCount(cur.getLeft());
		 myGearCount(cur.getRight());
		 gear--;
		return ;		
	}

	public static int diffScore(Node cur)
	{
		int difference=0;
		int leftScore=-1;
		int rightScore=-1;
		
		Node leftSide=cur.getLeft();
		Node rightSide=cur.getRight();
		leftScore=countHigh(leftSide);
		rightScore=countHigh(rightSide);
		
		difference=Math.abs(leftScore-rightScore);
		
		return difference;
	}
	
	public static int maxLeRi;
	public static int  lHigh;
	public static  int rHigh;
	public static ArrayList<Node> list=new ArrayList<Node>();
	public static void goHigh(Node cur)
	{		
		 if (cur == null)
		 return ; 
		 
		 goHigh(cur.getLeft());
		 goHigh(cur.getRight());
		 
		 lHigh=-1;
		 rHigh=-1;
		 if(cur.getLeft()==null)
			 lHigh=-1;
		 else
			 lHigh=cur.getLeft().high;
		 
		 if(cur.getRight()==null)
			 rHigh=-1;
		 else
			 rHigh=cur.getRight().high;
		 
		 cur.high=Math.abs(Math.max(lHigh, rHigh)+1);
		 cur.leRi=Math.abs(lHigh-rHigh);
		 
		// if(cur.leRi==maxLeRi)	
		//	 list.add(cur);
		 if(cur.leRi>maxLeRi)
		 {
			 maxLeRi=cur.leRi;
		//	 list.clear();
			// list.add(cur);
		 }
		 
		 
		return ;
	}
/*
	public static Node choiseNode()
	{
		if(list.size()%2==0)
			return null;
		else
		{
			//Collections.sort((List<Node>) list);
			list.sort(new MyComp());
		return	list.get(list.size()/2);
		}
		
	}
*/
	public static Node choiseNode()
	{
		if(list.size()%2==0)
			return null;
		else
		return list.get(list.size()/2);
		
	}
	
 public static void addNode(Node cur)
 {
	 if (cur == null)
	        return;
	 
	 addNode(cur.getLeft());
	 
	 if(cur.leRi==maxLeRi)
	 list.add(cur);
	
	 addNode(cur.getRight());	
 
 }
	
	
}

class MyComp implements Comparator<Node>
{

	@Override
	public int compare(Node o1, Node o2) {
		if(o1.getKey()>o2.getKey())
			return 1;
		else if(o1.getKey()<o2.getKey())
			return -1;
		return 0;
		
	}
	
}



class Node {

	private int key;
	private Node parent;
	private Node left;
	private Node right;
	public int high;
	public int leRi;
	
	Node()
	{
		left=null;
		right=null;
		parent=null;
	}
	Node(int key)
	{
		this.key=key;
		left=null;
		right=null;
		parent=null;
	}
	public void setRight(Node r)
	{
		right=r;
	}
	public void setLeft(Node l)
	{
		left=l;
	}
	public void setParent(Node p)
	{
		parent=p;
	}
	public Node getLeft()
	{
		return left;
	}
	public Node getRight()
	{
		return right;
	}
	public Node getParent()
	{
		return parent;
	}
	public int getKey()
	{
		return key;
	}
	
	public void setKey(int k)
	{
		key=k;
	}
	public void setRelation(Node n)
	{
		if(this.getKey()<n.getKey())
		{
			if(this.getRight()!=null)
			{
				this.getRight().setRelation(n);
			}
			else
			{
			setRight(n);
			n.setParent(this);
			
			}
		}
		else
		if(this.getKey()>n.getKey())
		{
			if(this.getLeft()!=null)
			{
				this.getLeft().setRelation(n);
				
			}
			else
			{
			setLeft(n);
			n.setParent(this);
			
			}
		}
	}
   	    
	public int myCompare(Node n)
    {
		if(this==null)
			return 0;
		
   	 if(this.getKey()<n.getKey())
   	 {
   		if(this.getRight()!=null)
   return -1;
   	 }
   	 if(this.getKey()>n.getKey())
   	 {
   		if(this.getLeft()!=null)
   			return 1;
   	 }
   	
			
   	 return 0;
    }
     
}

class Tree {

	public int k;
	public Node root;
	
	Tree()
	{	
		root=null;	
	//	root.setParent(null);
	}
	Tree(int r)
	{
		
		root=new Node(r);
		root.setParent(null);
		root.setLeft(null);
		root.setRight(null); 
		
		
	}
	public Node getRoot()
	{
		
		return root;
	}
	public void setRoot(Node a)
	{
		root=a;
		root.setParent(null);
	}
	public void addValue(int v)
	{
		Node n=new Node(v);
		
		if(root==null)
			setRoot(n);
		else
		{
			Node cur=root;
			cur.setRelation(n);
		}	
	}
	public Node findByValue(int v)
	{
		Node n=new Node (v);
		Node cur=root;
		int k=0;
		do
		{
			k=cur.myCompare(n);
			if(k==-1)
				cur=cur.getRight();
			else
				if(k==1)
					cur=cur.getLeft();
			
		}
		while(k!=0);
		if(cur.getKey()==n.getKey())
		{
			Node copy=new Node(cur.getKey());
			System.out.println(" \nнашел:"+copy.getKey());
			return copy;
			
		}
		System.out.println(" не нашел");
		return null;
	}
	
	
	private Node findBySecretValue(int v)
	{
		Node n=new Node(v);
		Node cur=root;
		int k=0;
		do
		{
			k=cur.myCompare(n);
			if(k==-1)
				cur=cur.getRight();
			else
				if(k==1)
					cur=cur.getLeft();
			
		}
		while(k!=0);
		if(cur.getKey()==n.getKey())
		{
			
			return cur;
			
		}
		System.out.println(" не нашел,что удалять.");
		return null;
	}
	public void myPreorder()
	{
		preorder(root);
	}
	public void myPostorder()
	{
		postorder(root);
	}
	public void myIn()
	{
		inorder(root);
	}
	public void  preorder(Node cur)
	{		
		//System.out.print(n.getKey()+"  ");
		 if (cur == null)
		        return;
		 System.out.print(cur.getKey()+"  ");
		 preorder(cur.getLeft());
		 preorder(cur.getRight());
				
	}
	
	public void  postorder(Node cur)
	{		
		//System.out.print(n.getKey()+"  ");
		 if (cur == null)
		        return;
		 
		 postorder(cur.getLeft());
		
		 postorder(cur.getRight());
		 System.out.print(cur.getKey()+"  ");
				
	}
	public void  inorder(Node cur)
	{		
		
		 if (cur == null)
		        return;
		 
		 inorder(cur.getLeft());
		 System.out.print(cur.getKey()+"  ");
		
		 inorder(cur.getRight());		
	}

	
	
	public  void myDelete(Node cur)
	{
		if(cur.getRight()==null && cur.getLeft()!=null)
		{
			if(cur.getParent()!=null)
			{
				if(cur.getParent().getLeft()==cur)
					cur.getParent().setLeft(cur.getLeft());
				else
					cur.getParent().setRight(cur.getLeft());
				
				cur.getLeft().setParent(cur.getParent());
			}
			else
			{
				root=cur.getLeft();
				cur.getLeft().setParent(null);
				cur.setLeft(null);
			}
			return;
		}
		if(cur.getLeft()==null && cur.getRight()!=null)
		{
			if(cur.getParent()!=null)
			{
		if(cur.getParent().getLeft()==cur)
			cur.getParent().setLeft(cur.getRight());
		else
			cur.getParent().setRight(cur.getRight());
		
		cur.getRight().setParent(cur.getParent());
		
			}
			else
			{
			root=cur.getRight();
			cur.getRight().setParent(null);
			cur.setRight(null);
				
			}
			return;
		}
		if(cur.getLeft()==null && cur.getRight()==null)
		{
			if(cur.getParent()!=null)
			{
			if(cur.getParent().getLeft()==cur)
				cur.getParent().setLeft(null);
			else
				cur.getParent().setRight(null);
			}
			else
			{
				root=null;
			}
			
			return;
		}
		if(cur.getLeft()!=null && cur.getRight()!=null)
		{
			
			Node next=cur.getRight();
			if(next.getLeft()==null)
			{
			
					cur.setKey(next.getKey());
					cur.setRight(next.getRight());
					if(next.getRight()!=null)
					next.getRight().setParent(cur);		
			}
		else
		{
			while(next.getLeft()!=null)
			{
				next=next.getLeft();
			}
			cur.setKey(next.getKey());
			myDelete(next);
			
		}					
		
	}
	
	}
}

