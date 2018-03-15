
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
		
		Integer[][] Matr;
		Integer N;
		Tree root = new Tree();
		int keyDelete = 0;
		try{
			FileInputStream fstream = new FileInputStream("input.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			//keyDelete = Integer.parseInt( br.readLine() );
			N = Integer.parseInt(br.readLine());
			Matr = new Integer[N][N];
			System.out.println(N);
			int i = 0;
			while ((strLine = br.readLine()) != null){
				StringTokenizer strMatr= new StringTokenizer(strLine, " ");
				for(int j = 0; j < N; j++){
					Matr[i][j] = Integer.parseInt(strMatr.nextToken());
				}
				i++;
				//if(strLine.isEmpty() == true)
					//continue;
				//root.insert(Integer.parseInt(strLine));
				
			}
			for(int s = 0; s< N; s++){
				for(int j=0; j < N; j++)
					System.out.print( Matr[s][j]);
				System.out.println();
			}
			
			for(int ii=0; ii < N; ii++){
				boolean flag = false;
				for(int jj=0; jj < N; jj++){
					//appendUsingFileWriter(String filePath, int text)
					if(Matr[jj][ii] == 1){
						flag = true;
						appendUsingFileWriter("output.txt", jj + 1);
					}
				}
				if(flag == false)
					appendUsingFileWriter("output.txt", 0);
			}
		}catch (IOException e){
			//System.out.println("Ошибка");
		}
		
		
		
		//System.out.println(40);
		//root.deleteNode(keyDelete);
		//root.preOrderPrint("output.txt", root.root);
		System.exit(0);
	}
}