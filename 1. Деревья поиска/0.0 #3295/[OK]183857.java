
import java.io.*;
import java.util.LinkedList;
	
public class Tree {
	public static final String FILE_INPUT = "input.txt";
	public static final String FILE_OUTPUT = "output.txt";
	static class Node {
		double key;
		Node left;
		Node right;
		Node parent;
		int height;
		Integer lh;
		Integer b;
		Integer msl;
		Integer a;
		Integer c;
		
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
		
//		private static Node deleteElem(Node root, int value){
//			if(root == null){
//				return null;
//			}
//			else if(root.key > value){
//				root.left = deleteElem(root.left, value);
//			}
//			else if(root.key < value){
//				root.right = deleteElem(root.right, value);
//			}
//			else if(root.left != null && root.right != null){
//					//Node temp = root;
//					Node minNodeRight = minimumElement(root.right);
//					root.key = minNodeRight.key;
//					root.right = deleteElem(root.right, minNodeRight.key);
//				}
//				else if(root.left != null){
//					root = root.left;
//				}
//				else if(root.right != null){
//					root = root.right;
//				}
//				//right == null and left == null
//				else{
//					if (root.parent.left == root)
//						root.parent.left = null;
//					if(root.parent.right == root)
//						root.parent.right = null;
//					root = null;
//				}
//				
//			return root;
//		}
	}
	Integer Mmsl = 0;;
	Integer maxKey;
	private Node root;
	public Tree(){
		root = null;
	}
	public void insert(int x) {
		root = root.doInsert(this.root, x, null);
	}
	
//	public void deleteNode(int x){
//		this.root = this.root.deleteElem(this.root, x);
//	}
	
//	public void preOrderPrint(String filePath, Node node){
//		if(node == null){
//			return;
//		}
//		appendUsingFileWriter(filePath, node.key);
//		preOrderPrint(filePath, node.left);
//		preOrderPrint(filePath, node.right);
//	}
	public static int sumall=0;
	public void sum(Node node){
		if(node == null)
			return;
		sum(node.left);
		sumall+=node.key;
		sum(node.right);
	}
	public int setHeight(Node node){
		if(node.left == null && node.right == null){
			node.height = 0;
		}else if(node.left == null ){
			node.height = setHeight(node.right) + 1;
		}else if(node.right == null){
			node.height = setHeight(node.left) + 1;
		}else{
			node.height = Math.max(setHeight(node.left), setHeight(node.right)) +1;
		}	
		return node.height;
	}
	
	public void pastOrder(Node node){
		if(node != null){
			pastOrder(node.left);
			pastOrder(node.right);
			if(node.left == null && node.right == null){
				node.height = 0;
				node.lh = 1;
				node.msl = 0;
				return;
			}else if(node.left != null && node.right !=null){
				node.height = Math.max(node.left.height, node.right.height) + 1;
				if(node.left.height > node.right.height)
					node.lh = node.left.lh;
				else if(node.left.height < node.right.height)
					node.lh = node.right.lh;
				else // node.left.height == node.right.height
					node.lh = node.right.lh + node.left.lh;
				node.msl = node.left.height + node.right.height + 2;
				if(node.msl > Mmsl){
					Mmsl = node.msl;
				}
			}else if(node.right != null){
				node.height = node.right.height + 1;
				node.lh = node.right.lh;
				node.msl = node.right.height + 1;
				if(node.msl > Mmsl){
					Mmsl = node.msl;
				}
			}else if(node.left != null){
				node.height = node.left.height + 1;
				node.lh = node.left.lh;
				node.msl = node.left.height + 1;
				if(node.msl > Mmsl){
					Mmsl = node.msl;
				}
			}
		}
	}
	
//	public void postOrder(Node node){
//		if(node != null){
//			if(node.msl == Mmsl){
//				if(node.left != null && node.right != null){
//					node.b = node.left.lh * node.right.lh;
//				}else if(node.right != null){
//					node.b = node.right.lh;
//				}else{
//					node.b = node.left.lh;
//				}
//			}else{
//				node.b = 0;
//			}
//			
//			if(this.root == node){
//				node.a = 0;
//			}
//			if(node.left != null && node.right != null){
//				if(node.left.height > node.right.height){
//					node.left.a = node.a + node.b;
//					node.right.a = node.b;
//				}else if(node.left.height < node.right.height){
//					node.right.a = node.a + node.b;
//					node.left.a = node.b;
//				}else{
//					node.left.a = node.b + node.left.lh * node.a / node.lh;
//					node.right.a = node.b + node.right.lh * node.a / node.lh;
//				}
//			}else if(node.right != null){
//				node.right.a = node.a +node.b;
//			}else if(node.left != null){
//				node.left.a = node.a +node.b;
//			}
//			
//			node.c = node.a + node.b;
//			
//			if(node.c % 2 == 1){
//				if(maxKey == null){
//					maxKey = node.key;
//				}else{
//					if(node.key > maxKey){
//						maxKey = node.key;
//					}
//				}
//			}
//			
//			postOrder(node.left);
//			postOrder(node.right);
//		}		
//	}
	
	public int setLH(Node node){
		if(node.lh != null)
			return node.lh;
		if(node.left != null && node.right != null){
			if(node.left.height > node.right.height)
				node.lh = setLH(node.left);
			else if(node.left.height < node.right.height)
				node.lh = setLH(node.right);
			else // node.left.height == node.right.height
				node.lh = setLH(node.right) + setLH(node.left);
		} else if(node.left != null){
			node.lh = setLH(node.left);
		}
		else if (node.right != null){
			node.lh = setLH(node.right);
		}
		else if(node.left == null && node.right == null){
			node.lh  = 1;
		}
		return node.lh;
	}
	
	public void MSL(Node root){
		
	}
	
	private static void appendUsingFileWriter(String filePath, long text) {
		File file = new File(filePath);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file,true);
			fr.write(Long.toString(text));
			fr.write("\n");
			//System.out.println(text);
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
		
		Tree tree = new Tree();
		//int keyDelete = 0;
		try{

			FileInputStream fstream = new FileInputStream(FILE_INPUT);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			LinkedList<Long> arr = new LinkedList<Long>();
			long t=0,res=0;
			boolean flag = true;
			while ((strLine = br.readLine()) != null){
				t = Long.parseLong(strLine);
				if(arr.indexOf(t) != -1){
					flag = false;
				}
				if(flag){
					res+=t;
					arr.addLast(t);
				}
				flag=true;
				//System.out.println(key);
			}
			appendUsingFileWriter(FILE_OUTPUT,res);
		}catch (IOException e){
			//System.out.println("Ошибка");
		}
		//System.out.println(40);
		//tree.deleteNode(keyDelete);
		//tree.setHeight(tree.root);
		//tree.setLH(tree.root);
//		appendUsingFileWriter(FILE_OUTPUT, sumall);
		
		System.exit(0);
	}
}