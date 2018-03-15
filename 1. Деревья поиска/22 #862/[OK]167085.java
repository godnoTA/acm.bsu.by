
import java.io.*;
import java.util.*;

public class BinTree {
	static BufferedWriter writer;
	static ArrayList <Integer> array = new ArrayList <Integer>();
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
		public Node delete(int value, Node myRoot){
			if(myRoot==null)
				return null;
			if (myRoot.key>value){
				myRoot.left=delete(value,myRoot.left);
				return myRoot;
			}
			if(myRoot.key<value){
				myRoot.right=delete(value,myRoot.right);
				return myRoot;
			}

			if(myRoot.left == null)
				return myRoot.right;
			if(myRoot.right == null)
				return myRoot.left;

			Node minRoot=find(myRoot.right);
			int minKey=minRoot.key;
			myRoot.key=minKey;
			myRoot.right=delete(minKey, myRoot.right);
			return myRoot;
		}

		public int getSon(Node root) {
			if (root.left != null && root.right != null)
				return getSon(root.left)+ getSon(root.right) + 2;
			if (root.left != null)
				return getSon(root.left)+1;
			if (root.right != null)
				return getSon(root.right)+1;
			return 0;
		}
		public int getHeight(Node root) {
			if (root.left != null && root.right != null)
				return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
			if (root.left != null)
				return getHeight(root.left) + 1;
			if (root.right != null)
				return getHeight(root.right) + 1;
			return 1;
		}

		public Node find (Node myRoot) {
			if(myRoot.left!=null){
				Node minRoot=find(myRoot.left);
				return minRoot;
			}
			return myRoot;
		}

		public void findAndDelete(Node myRoot) throws IOException {
			
			
			if (myRoot.left==null && myRoot.right!=null){
				findAndDelete(myRoot.right);
			}
			if (myRoot.left!=null && myRoot.right==null){
				findAndDelete(myRoot.left);
			}
			if (myRoot.left != null && myRoot.right != null) {
				if(myRoot.getHeight(myRoot.left)!=myRoot.getHeight(myRoot.right) && myRoot.getSon(myRoot.left)==myRoot.getSon(myRoot.right)){
					array.add(myRoot.key);
				}
				findAndDelete(myRoot.left);
				findAndDelete(myRoot.right);
			}
		}

		public void leftWayAll(Node myRoot) throws IOException{
			if (myRoot==null)
				return;
			if(myRoot!=null){
				writer.write(Integer.toString(myRoot.key)+"\r\n");
			}
			if(myRoot.left!=null){
				leftWayAll(myRoot.left);
			}
			if(myRoot.right!=null){
				leftWayAll(myRoot.right);
			}	
			writer.flush();
		}
	}


	public static void main(String[] args) throws IOException  {
		Node T = new Node();
		Scanner in=new Scanner (new File("in.txt"));
		String line;
		while(in.hasNextLine()){
			line=in.nextLine();
			T.insert(Integer.parseInt(line));
		}
		T.findAndDelete(T.root);

		Collections.sort(array, new Comparator<Integer>() {
			public int compare(Integer arg0, Integer arg1) {
				if(arg0<arg1)
					return -1;
				else 
					return 1; 
			}
		});
		if(array.size()%2!=0){
			T.delete(array.get((int)array.size()/2),T.root);
		}
		in.close();
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out.txt")));
		T.leftWayAll(T.root);    
	} 
}
