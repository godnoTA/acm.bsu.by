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
	   
	   Node(double k) {
			this.key = k;
			this.l_son=null;
			this.r_son=null;
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
		if(a.key > b.key)
			a.l_son=b;
		else
			a.r_son=b;
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
				
		while (in.hasNextLine()) {
			try{
				double c=Double.parseDouble(in.nextLine());
				add(c);
			}
			catch(Exception e){}
		}
		in.close();
		
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