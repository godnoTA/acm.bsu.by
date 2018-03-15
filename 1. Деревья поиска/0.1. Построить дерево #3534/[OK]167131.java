import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
public class Tree {
	    static class Node {
	        int key;
	        Node left;
	        Node right;
	        public Node(int key) {
	            this.key = key;
	        }
	    }
	 private Node root;
	 public Tree(){
		 
	 }
	 public Node getRoot(){
		 return root;
	 }
	 public void insert(int x) {
		    root = doInsert(root, x);
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
		public   void rightPass(Node root, BufferedWriter buf) throws IOException{
			System.out.println(root.key);
			String str = Integer.toString(root.key);
			buf.write(str);
			buf.newLine();
			if(root.left != null){
				rightPass(root.left, buf);
			}
			if(root.right != null){
				rightPass(root.right, buf);
			}
		}
	public static void main(String[] args) throws IOException {
		String s1 = "input.txt";
		String s2 = "output.txt";
		BufferedReader buf1 = new BufferedReader(new FileReader(s1));
		BufferedWriter buf2 = new BufferedWriter(new FileWriter(s2));
		String str;
		//int rootKey = Integer.parseInt(buf1.readLine());
		Tree tree = new Tree();
		while((str = buf1.readLine()) != null){
			int key = Integer.parseInt(str);
			tree.insert(key);
		}
		tree.rightPass(tree.getRoot(), buf2);
		buf1.close();
		buf2.close();
	}
}
