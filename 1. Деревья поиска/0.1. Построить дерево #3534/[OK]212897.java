

import java.io.*;
import java.util.Scanner;

public class Tree {
	
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
	
	public void Left(Node v, FileWriter f) throws IOException{
		
	    if (v != null) {
	        f.write((Integer)v.k + " \r\n");
	        Left(v.left,f);
	        Left(v.right,f);
	    }
	}
	
	
	public static void main(String[] args) throws IOException{
		
		try(FileInputStream fin = new FileInputStream("input.txt");
                FileWriter out = new FileWriter("output.txt",false))
        {
			Scanner scanner = new Scanner(new File("input.txt"));
			
			Tree t = new Tree();
			Tree.Node n = t.new Node(scanner.nextInt());
	        while(scanner.hasNextInt()){
	            t.add(n,scanner.nextInt());
	        }
	        	t.Left(n,out);
	            scanner.close();
        
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        }   
		
		
		
		
	}

	
}


