import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Tree implements Runnable {
	
    public static void main(String[] args) {
        new Thread(null, new Tree(), "", 64 * 1024 * 1024).start();
    }
    
    static class Node
	{
	   int key;
	   Node l_son;
	   Node r_son;
	   Node fath;
	   
	   Node(int k) {
			this.key = k;
			this.l_son=null;
			this.r_son=null;
			this.fath=null;
	    }
	}
    
    private static Node root;
	 static ArrayList<Integer> nodes = new ArrayList<Integer>();	
	 	 
	 public static boolean add(int V){
		Node a=root;
		Node b=new Node(V);
		if( root == null){
			  root=b;
			  return true;
		  }
		while(true)
		{
			if(a.key == V )
				return false;
			if(a.key > V)
			{
				if(a.l_son == null)
					break;
				a=a.l_son;
			}
			else
			{
				if(a.r_son == null)
					break;
				a=a.r_son;
			}	
	   }
		b.fath = a;
		if(a.key > b.key)
			a.l_son=b;
		else
			a.r_son=b;
		return true;
	 }
	 
	 public static boolean delete(int X){
		 if(root==null)
			 return false;
		Node a=root;
		while(true)
		{
			if(a.key==X)
				break;
			if(a.key > X){
				if(a.l_son==null)
					return false;
				a=a.l_son;
			}
			if(a.key <X){
				if(a.r_son==null)
					return false;
				a=a.r_son;
			}
		}
		Node b=null;
		if((a.r_son != null)&&(a.l_son != null)){
			b=a.r_son;
			while(b.l_son != null)
				b=b.l_son;
			a.key=b.key;
			if((b.fath).l_son==b){
				if(b.r_son!= null)
					(b.fath).l_son=b.r_son;
				else
					(b.fath).l_son=null;
			}
			if(a.r_son==b)
				a.r_son=b.r_son;
			b=null;
		}
		else{
			if(((a.l_son != null)&&(a.r_son==null))||((a.l_son==null)&&(a.r_son!= null))){
				if(a.l_son != null)
					b=a.l_son;
				if(a.r_son!=null)
					b=a.r_son;
				b.fath=a.fath;
				if(a.fath==null){
					root=b;
					a=null;
				}
				else
				{
					if((a.fath).r_son==a)
						(a.fath).r_son=b;
					if((a.fath).l_son==a)
						(a.fath).l_son=b;
				}
			}
			else
			{
				if(root==a)
					a=root=null;
				else{
					if((a.fath).r_son==a)
						(a.fath).r_son=null;
					if((a.fath).l_son==a)
						(a.fath).l_son=null;
					a=null;
				}
			}
		}
		return true;
	 }
	 	 
	 public static void Preoder(Node a, PrintWriter out){
		 if ( a!= null)
		    {

		        try {
		        	out.print( (int)a.key+"\r\n");
		        }
		        catch(Exception e) {   }
		    
		        Preoder(a.l_son, out);
		        Preoder(a.r_son,out);
		    }
	 }
		
	 public static Node getRoot(){
		 return root;
	 }
	 
	 public static int getHeight(Node c) {
		 
			if (c.l_son != null && c.r_son != null)
				return Math.max(getHeight(c.l_son), getHeight(c.r_son)) + 1;
			if (c.l_son != null)
				return getHeight(c.l_son) + 1;
			if (c.r_son != null)
				return getHeight(c.r_son) + 1;
			return -1;
			
		}

	 public static void Node_for_Delete(Node c) throws IOException {
			
		 if (c.l_son==null && c.r_son==null)
			 nodes.add(c.key);
			if (c.l_son==null && c.r_son!=null)
				Node_for_Delete(c.r_son);
			if (c.l_son!=null && c.r_son==null)
				Node_for_Delete(c.l_son);
			if (c.l_son != null && c.r_son != null) {
				if(getHeight(c.l_son)== getHeight(c.r_son)){
					nodes.add(c.key);
				}
				Node_for_Delete(c.l_son);
				Node_for_Delete(c.r_son);
			}
		}
	 
	 
	 public static  void average() throws IOException{
		 
		 Node_for_Delete(getRoot());
		 
		 if(nodes.size()%2!=0){
			 Collections.sort(nodes, new Comparator<Integer>() {
					public int compare(Integer a, Integer b) {
						return a.compareTo(b);
					}
				});
			 
			 delete((nodes.get(nodes.size()/2)));
		 }
			 
		 
	 }
 
    public void run() {

		
		Scanner in = null;
		try {
			in = new Scanner(new File("tst.in"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		while (in.hasNextInt()) 
				add(in.nextInt());
		in.close();
		
		try {
			average();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			File file = new File("tst.out");
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());
			Preoder(getRoot(),out);
		    out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		
	}
    
    }