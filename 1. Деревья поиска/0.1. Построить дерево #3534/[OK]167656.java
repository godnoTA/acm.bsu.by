import java.io.*;
	
public class Tree {
	static class Node {
		int key;
		Node left;
		Node right;
		
		public Node(int key) {
			this.key = key;
		}
		
		private static Node doInsert(Node node, int x) {
			if (node == null) {
				return new Node(x);
			}
			if (x < node.key) {
				node.left = doInsert(node.left, x);
			} else if (x > node.key) {
				node.right = doInsert(node.right, x);
			}
			return node;
		}
	}
	
	private Node root;
	public Tree(){
		root = null;
	}
	public void insert(int x) {
		root = root.doInsert(root, x);
	}
	
	public void preOrderPrint(String filePath, Node node){
		if(node == null){
			return;
		}
		appendUsingFileWriter(filePath, node.key);
		preOrderPrint(filePath, node.left);
		preOrderPrint(filePath, node.right);
	}
	
	private static void appendUsingFileWriter(String filePath, int text) {
		File file = new File(filePath);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file,true);
			fr.write(Integer.toString(text));
			fr.write("\n");
			//System.out.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]){
		
		Tree root = new Tree();
		try{
			FileInputStream fstream = new FileInputStream("input.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			while ((strLine = br.readLine()) != null){
				if(strLine.isEmpty() == true)
					continue;
				root.insert(Integer.parseInt(strLine));
				//System.out.println(key);
			}
		}catch (IOException e){
			//System.out.println("Ошибка");
		}
		
		//String data = "10";
		//int x= 10;
		//System.out.println(root.root.key);
		//Node node = root.root.left;
		//System.out.println(node.key);
		root.preOrderPrint("output.txt", root.root);
		System.exit(0);
	}
}