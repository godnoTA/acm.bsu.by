import java.io.*;
import java.util.*;

public class Tree {
	static class Node {
		int key;
		Node left;
		Node right;
		Node parent;
		boolean root;
		
		public Node(int key) {
			this.key = key;
		}
		public Node(int key, Node parent){
			this.key = key;
			this.parent = parent;
		}
		private static Node doInsert(Node node, int x, Node parent) {
			if (node == null) {
				return new Node(x, parent);
			}
			if (x < node.key) {
				node.left = doInsert(node.left, x, node);
			} else if (x > node.key) {
				node.right = doInsert(node.right, x, node);
			}
			return node;
		}
		
		public static Node minimumElement(Node root){
			if (root.left == null)
				return root;
			else{
				return minimumElement(root.left);
			}
		}
		
		private static Node deleteElem(Node root, int value){
			if(root == null){
				return null;
			}
			else if(root.key > value){
				root.left = deleteElem(root.left, value);
			}
			else if(root.key < value){
				root.right = deleteElem(root.right, value);
			}
			else if(root.left != null && root.right != null){
					Node temp = root;
					Node minNodeRight = minimumElement(temp.right);
					root.key = minNodeRight.key;
					deleteElem(root.right, minNodeRight.key);
				}
				else if(root.left != null){
					root = root.left;
				}
				else if(root.right != null){
					root = root.right;
				}
				//right == null and left == null
				else{
					if (root.parent.left == root)
						root.parent.left = null;
					if(root.parent.right == root)
						root.parent.right = null;
					root = null;
				}
				
			return root;
		}
	}	
	private Node root;
	public Tree(){
		root = null;
	}
	public void insert(int x) {
		root = root.doInsert(this.root, x, null);
	}
	
	public void deleteNode(int x){
		//*/
		if(this.root.key == x){
			this.root = null;
		}
		//*/
		this.root = this.root.deleteElem(this.root, x);
	}
	
	public void preOrderPrint(String filePath, Node node){
		if(node == null){
			return;
		}
		appendUsingFileWriter(filePath, node.key);
		preOrderPrint(filePath, node.left);
		preOrderPrint(filePath, node.right);
	}
	
	public void postOrder(Node node){
		postOrder(node.left);
		postOrder(node.right);
		
	}
	
	public void MSL(Node root){
		
	}
	
	private static void appendUsingFileWriter(String filePath, int text) {
		File file = new File(filePath);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file,true);
			fr.write(Integer.toString(text));
			fr.write(" ");
			System.out.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}//*/
		finally{
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//*/
	}
	
	public static void main(String args[]){
		
		int[] Matr;
		int N;
		//Tree root = new Tree();
		//int keyDelete = 0;
		try{
			
			FileInputStream fstream = new FileInputStream("input.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			//keyDelete = Integer.parseInt( br.readLine() );
			N = Integer.parseInt(br.readLine());
			Matr = new int[N];
			for(int i=0; i < N; i++){
				Matr[i]=0;
			}
			File file = new File("output.txt");
			FileWriter fr = null;
			fr = new FileWriter(file,true);
			int t=1,ii=0,jj=0;
			while ((strLine = br.readLine()) != null){
				StringTokenizer strMatr= new StringTokenizer(strLine, " ");
				 ii = Integer.parseInt(strMatr.nextToken());
				 jj = Integer.parseInt(strMatr.nextToken());
				Matr[jj-1]=ii;
			}
			
			
			for(int i=0; i < N; i++){
				fr.write(Integer.toString(Matr[i])+" ");
			}
			fr.close();
		}catch (IOException e){
			//System.out.println("РћС€РёР±РєР°");
		}
		
		
		
		//System.out.println(40);
		//root.deleteNode(keyDelete);
		//root.preOrderPrint("output.txt", root.root);
		System.exit(0);
	}
}