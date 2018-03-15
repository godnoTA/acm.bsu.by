import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



public class Test  implements Runnable{
	
	static class Node {
		
		public int path[];
		
		public int minHieght=0;
		
		public boolean isInMin=false;
		
		public boolean checkedAsMedian=false;
		
		public int key=0;
		public  Node parent=null;
		public  Node left=null;
		public  Node right=null;

		Node(int key)
		{
			this.key=key;			
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
		
		public boolean setRelation(Node n)
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
				
				return true;
				
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
				return true;
				
				}
			}
			
			return false;
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

	static class Tree {

		public int k;
		public Node root=null;
		
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
		
		public boolean addValue(int v)
		{
			Node n=new Node(v);
			
			if(root==null)
				setRoot(n);
			else
			{
				Node cur=root;
				return cur.setRelation(n);
			}	
			
			return true;
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
				
				Node next=cur.getLeft();
				
				if(next.getRight()==null)
				{
				
						cur.setKey(next.getKey());
						cur.setLeft(next.getLeft());
						if(next.getLeft()!=null)
						next.getLeft().setParent(cur);		
				}
			else
			{
				while(next.getRight()!=null)
				{
					next=next.getRight();
				}
				cur.setKey(next.getKey());
				myDelete(next);
				
			}					
			
		}
		
		}
	}

	public static Tree tree;
	
	public static int N=0;
	
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		//wrtitingFile();
		
		new Thread(null, new Test(), "", 64 * 1024 * 1024).start();
		
	//	getResult();

	}
	
	public static void mainActivity() 
	{

		try
		{
		wrtitingFile();
		
		findActivity();
		
		getResult();	
		
		}
		catch( IOException e)
		{
			
		}
		
	}
	
	public static void getResult()throws IOException 
	{
		 PrintWriter out = new PrintWriter(new FileWriter("tst.out"));		
		 
			printing(tree.root,out);
			
		    out.flush();
	}
	
	public static void printing(Node cur,PrintWriter out)
	{
		if(cur==null)
			return;
		
		out.println(cur.key);
		
		printing(cur.getLeft(),out);
		
		printing(cur.getRight(),out);
		
	}
	
	public static void wrtitingFile() throws IOException
	{		
		Scanner fr=new Scanner(new FileReader("tst.in"));
		
		tree=new Tree(fr.nextInt());
		N=1;
		
		while(fr.hasNextInt())
		{
			tree.addValue(fr.nextInt());	
				N++;
		}
	}

	
	public static void findActivity()
	{
		if(setMinHeightForAll())
		{
			checkMinForAll();
			
			createPath(tree.root);
			
			setPath(tree.root);
			
			counting=new int[current];
			delitable=new Node[current];
			
			running(tree.root);
			
			deleting();
		}
	}
	
	public static int len=0;
	
	public static boolean setMinHeightForAll()
	{
		setMinHeight(tree.root);
		
		len=tree.root.minHieght+1;
		
		if(len%2 == 0)
			return false;
		
		len/=2;
		len++;
		
		return true;
		
		
	}
	
	public static void setMinHeight(Node cur)
	{
		if(cur==null)
			return;
		
		setMinHeight(cur.getLeft());
		
		setMinHeight(cur.getRight());
		
		if(cur.getLeft()!=null)
			cur.minHieght=cur.getLeft().minHieght+1;
		
		if(cur.getRight()!=null )  
			{
				if(cur.getLeft()==null)
					cur.minHieght=cur.getRight().minHieght+1;
				else
					if(cur.getRight().minHieght<cur.getLeft().minHieght)
						cur.minHieght=cur.getRight().minHieght+1;
				
			}
		
	}
	
	public static void checkMinForAll()
	{
		checkMin(tree.root);
	}
	
	public static void checkMin(Node cur)
	{
		if(cur==null)
			return;
		
		cur.isInMin=true;
	
		if(cur.getLeft()==null)
		{
			if(cur.getRight()!=null)
				checkMin(cur.getRight());			
		}
		else
		{
			if(cur.getRight()==null)
				checkMin(cur.getLeft());
			else
			{
				if(cur.getLeft().minHieght < cur.getRight().minHieght)
					checkMin(cur.getLeft());
				else if (cur.getLeft().minHieght > cur.getRight().minHieght)
					checkMin(cur.getRight());
				else
				{			
					checkMin(cur.getLeft());
					
					checkMin(cur.getRight());			
				}
				
			}
		}
		
		
	}
	
	static int current=0;
	
	static int [] counting;
	
	static Node [] delitable;
	
	public static void setPath(Node cur)
	{
		
		if(cur.getLeft()!=null)
			if(cur.getLeft().isInMin)
				setPath(cur.getLeft());
		
		if(cur.getRight()!=null)
			if(cur.getRight().isInMin)
				setPath(cur.getRight());
		
		if(cur.getLeft()==null)
		{
			if(cur.getRight()==null)
			{
			//	System.out.println(N);
				
				cur.path[current]=1;
				
				current++;				
			}
			else
			{
				for(int i=0; i < N;i++)
					cur.path[i]+=cur.getRight().path[i];
				
			}
			
		}
		else
		{
			if(cur.getRight()==null)
			{
				for(int i=0; i < N;i++)
					cur.path[i]+=cur.getLeft().path[i];		
			}
			else
			{
				if(cur.getLeft().isInMin)
					for(int i=0; i < N;i++)
						cur.path[i]+=cur.getLeft().path[i];		
				
				if((cur.getRight().isInMin))
					for(int i=0; i < N;i++)
						cur.path[i]+=cur.getRight().path[i];
				
			}
			
		}
		
		
	}
	
	public static void createPath(Node cur)
	{
		if(cur==null)
			return;
		
		if(cur.isInMin)
			cur.path=new int[N];
		
		createPath(cur.getLeft());
		
		createPath(cur.getRight());
		
	}
	
	public static void running(Node cur)
	{

		if(cur.getLeft()!=null)
			if(cur.getLeft().isInMin)
				running(cur.getLeft());
		

	for(int i=0;i<current;i++)
		if(cur.path[i]!=0)
		{
			counting[i]++;
			
			if(counting[i]==len)
				if(!cur.checkedAsMedian)
				{
					delitable[i]=cur;
					cur.checkedAsMedian=true;
					
					//System.out.println("!!!!"+cur.key); //!!
				}
		}
		
		
		if(cur.getRight()!=null)
			if(cur.getRight().isInMin)
				running(cur.getRight());	
	}

	public static void deleting()
	{
	 for(int i=0;i<current;i++)
	 {
		 if(delitable[i]!=null)
		 {
			 if(isExist(delitable[i]))
				 tree.myDelete(delitable[i]);
			 else
			 {
				 Node del=linking(delitable[i].key);
				 tree.myDelete(del);
			 }
			 
		 }
		 
	 }
	}
	
	public static int existing=0;
	
	public static boolean isExist(Node node)
	{
		existing=0;
		
		tryExist(tree.root, node);
		
		if(existing==1)
			return true;
		
		return false;
	
	}
		
	public static void tryExist(Node cur,Node node)
	{
		if(cur==null)
			return;
		
		if(cur==node)
		{
			existing=1;
			return;
		}
		
		tryExist(cur.getLeft(),node);
		
		tryExist(cur.getRight(),node);
		
	}
	
public static Node link=null;
	
	public static Node linking(int key)
	{
		link=null;
		
		tryLink(tree.root,key);
	
		 return link;
	
	}
		
	public static void tryLink(Node cur,int key)
	{
		if(cur==null)
			return;
		
		if(cur.key==key)
		{
			link=cur;
			return;
		}
		
		tryLink(cur.getLeft(),key);
		
		tryLink(cur.getRight(),key);
		
	}

	@Override
	public  void run()  {
		// TODO Auto-generated method stub
		//findActivity();
		
		mainActivity();
	}
	
	
	
}
