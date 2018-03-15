import java.io.*;
import java.util.Scanner;

public class BinTree {
	static BufferedWriter writer;
	static class Node{
		int key;
		Node right;
		Node left;
		Node (int key){
			this.key=key;
		}
		Node (){
		}
		private Node root;
		
		public void insert(int value){
			Node father=null;
			Node myNode = new Node (value);
			Node myRoot = root;
			while (myRoot != null){
				father = myRoot;
				if(value == myRoot.key){
					return;
				}
				else{
					if (value < myRoot.key){
						myRoot = myRoot.left;
					}
					else{
						if (value > myRoot.key){
							myRoot = myRoot.right;
						}
					}
				}
			}
			if (father == null){
				root = myNode;
			}
			else{
				if(value == father.key){
					return;
				}
				else{
					if (value > father.key){
						father.right=myNode;
					}
					else{
						if (value < father.key){
							father.left=myNode;
						}
					}
				}
			}
		}
		public void leftWay(Node myRoot) throws IOException{
			if (myRoot==null)
				return;
			if(myRoot!=null){
				writer.write(Integer.toString(myRoot.key)+"\r\n");
			}
			if(myRoot.left!=null){
				leftWay(myRoot.left);
			}
			if(myRoot.right!=null){
				leftWay(myRoot.right);
			}	
			writer.flush();
		}
	}

	public static void main(String[] args) throws IOException  {
        Node T = new Node();
        Scanner in=null;
        try{
        	in=new Scanner (new File("input.txt"));
       	 	String line;
            while(in.hasNextLine()){
            	line=in.nextLine();
            	T.insert(Integer.parseInt(line));
            }
            in.close();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
            T.leftWay(T.root);
        }
        catch (Exception e) {
		}
    } 
}
