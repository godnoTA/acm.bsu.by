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
		public void leftPass(Node root, BufferedWriter buf) throws IOException{
			String str = Integer.toString(root.key);
			buf.write(str);
			buf.newLine();
			if(root.left != null){
				leftPass(root.left, buf);
			}
			if(root.right != null){
				leftPass(root.right, buf);
			}
		}
		public boolean isKeyRoot(Node root, int number){
			if(root.key == number){
				if(root.left == null && root.right == null){
					return true;
				}
				if(root.left == null && root.right != null){
					this.root = root.right;
					return true;
				}
				if(root.left != null && root.right == null){
					this.root = root.left;
					return true;
				}
			}
			return false;
		}
		public Node rightDeleting(Node root,int number){
			if(root == null){
				return null;
			}
			if(number < root.key){
				root.left = rightDeleting(root.left, number);
				return root;
			}
			else if(number > root.key){
				root.right = rightDeleting(root.right, number);
				return root;
			}
			if(root.left == null){
				return root.right;
			}
			else if(root.right == null){
				return root.left;
			}
			else{
				int min_key = findMin(root.right).key;
				root.key = min_key;
				root.right = rightDeleting(root.right, min_key);
				return root;
			}
		}
		public Node findMin(Node root){
			if(root.left != null){
				return findMin(root.left);
			}
			else{
				return root;
			}
		}
	public static void main(String[] args) throws IOException {
		String s1 = "input.txt";
		String s2 = "output.txt";
		BufferedReader buf1 = new BufferedReader(new FileReader(s1));
		BufferedWriter buf2 = new BufferedWriter(new FileWriter(s2));
		String str;
		int number = Integer.parseInt(buf1.readLine());
		buf1.readLine();
		Tree tree = new Tree();
		while((str = buf1.readLine()) != null){
			int key = Integer.parseInt(str);
			tree.insert(key);
		}
		Node root = tree.getRoot();
		if(!tree.isKeyRoot(root, number)){
		tree.rightDeleting(root, number);
		}
		root = tree.getRoot();
		tree.leftPass(root, buf2);
		buf1.close();
		buf2.close();
	}
}
