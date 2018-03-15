
import java.io.*;
import java.util.Scanner;

public class _0_2 {
	
	class Node{
		
		int k;
		Node left, right;
		
		Node(int k){
			this.k = k;
		}
	}
	
	public Node add(Node v, int x){
		if(v == null)
			return new Node(x);
		if(x < v.k)
			v.left = add(v.left, x);
		else if(x > v.k)
			v.right = add(v.right, x);
		return v;
	}
	
	public void Left(Node v){
		
	    if (v != null) {
	        System.out.print((Integer)v.k + " \r\n");
	        Left(v.left);
	        Left(v.right);
	    }
	}
	
	
	public Node remove(Node v,int x){
		
		if(v == null) 
			return null; 
		if(x < v.k){ 
			v.left = remove(v.left,x); 
			return v; 
		} 
		else if(x > v.k){ 
			v.right = remove(v.right,x); 
			return v; 
		} 
		if(v.left == null) 
			return v.right; 
		else if(v.right == null) 
			return v.left; 
		else { 
			int min = FindMin(v.right).k; 
			v.k = min; 
			v.right = remove(v.right, min); 
			return v; 
		} 
	} 
	
		public static Node FindMin(Node v){ 
			if(v.left != null) 
				return FindMin(v.left); 
			else 
				return v;
			}
	
	
	
	public void fwrite(Node v, FileWriter f) throws IOException{
		
	    if (v != null) {
	        f.write((Integer)v.k + " \r\n");
	        fwrite(v.left,f);
	        fwrite(v.right,f);
	    }
	}
	
	
	public static void main(String[] args) throws IOException{
		
		try(FileInputStream fin = new FileInputStream("input.txt");
                FileWriter out = new FileWriter("output.txt",false))
        {
			Scanner scanner = new Scanner(new File("input.txt"));
			
			_0_2 t = new _0_2();
			int key = (int)scanner.nextInt();
			_0_2.Node n = t.new Node(scanner.nextInt());
	        while(scanner.hasNextInt()){
	            t.add(n,scanner.nextInt());
	        }
	        	t.Left(n);
	        	_0_2.Node m = t.remove(n,key);
	        	t.fwrite(m,out);
	            scanner.close();
        
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        }   
		
		
		
		
	}

	
}


