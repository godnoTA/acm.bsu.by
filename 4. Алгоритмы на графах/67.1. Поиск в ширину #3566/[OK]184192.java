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
	
	int N;
	public static LinkedList<Integer> wer[] = null;
	public static boolean ed[] = null;
	public static LinkedList<Integer> p = null;
	public static int ted[] = null;
	public static int dex=0;
	
	public static void BFS(int we) 
	{ 
	    if (ed[we])
		{ 
	        return;
	    }
	    p.push(we); 
	    ed[we] = true; 
		ted[we]=++dex;
	    while (!p.isEmpty())
		{
	        we = p.get(0);
	        p.removeFirst();
	        for (int i=0; i<wer[we].size(); ++i)
			{ 
	            int ve = wer[we].get(i);
	            if (ed[ve]) 
				{ 
	                continue;
	            }
	            p.addLast(ve); 
	            ed[ve] = true;
				ted[ve]=++dex;
	        }
	    }
	}
	
	public static void main(String args[]){
		
		Integer[][] Matr;
//		Integer N;
		Tree root = new Tree();
		int keyDelete = 0;
		try{
			FileInputStream fstream = new FileInputStream("input.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			strLine = br.readLine();
			int N = Integer.parseInt(strLine);
			wer = new LinkedList[N];
			for(int i=0; i < N; i++){
				wer[i] = new LinkedList<Integer>();
			}
			ed = new boolean[N];
			for(int i=0; i < N; i++){
				ed[i] = false;
			}
			p = new LinkedList<Integer>();
			ted = new int[N];
			
			
			int count=0;
			while((strLine=br.readLine()) != null){
				StringTokenizer strtok = new StringTokenizer(strLine," ");
				for(int i=0;i < N; i++){
					int t = Integer.parseInt(strtok.nextToken());
					if(t!=0)
						wer[count].addLast(i);
				}
				count++;
			}
			
			for(int i=0; i < N; i++){
				BFS(i);
			}
		
		
		
		
		
		
		for(int i=0; i < N; i++){
			appendUsingFileWriter("output.txt", ted[i]);
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