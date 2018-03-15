import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class my_tree {
	
	 static class Node
	{
	   double key;
	   Node l_son;
	   Node r_son;
	   Node fath;
	   
	   Node(double k) {
			this.key = k;
			this.l_son=null;
			this.r_son=null;
			this.fath=null;
	    }
	}
	 
	 private static Node root;
	 
	 public static Node getRoot(){
		 return root;
	 }
	 
	 public static boolean add(double V){
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
	 
	 public static boolean delete(double X){
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
		
	 
	

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in=new Scanner(new File("input.txt"));
		
		double del_node=Double.parseDouble(in.nextLine());
		
		while (in.hasNextLine()) {
			try{
				double c=Double.parseDouble(in.nextLine());
				add(c);
			}
			catch(Exception e){}
		}
		in.close();
		
		delete(del_node);
		
		File file = new File("output.txt");
		 
	    try {
	        if(!file.exists())
	            file.createNewFile();
	        
	        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
	        
	        Preoder(getRoot(),out);
	        out.close();
	    } 
	    catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	 
		
		
	}

}