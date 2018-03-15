import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BST {
	
	public static class Node {
		
		private int key;
		private Node left;
		private Node right;
		private Node parent;
		static private boolean flag = false;
		
		public Node(int key) {
			this.key = key;
			this.left = null;
			this.right = null;
			this.parent = null;
		}
		
		public boolean appendChild(Node node) {
			if(this.key == node.getKey()) {
				this.flag = true;
				return false;
			}
			if(this.key > node.getKey()) {
				if(this.left == null) {
					this.left = node;
					node.setParent(this);
					return true;
				} else {
					this.left.appendChild(node);
				}
			}
			if(this.key < node.getKey()) {
				if(this.right == null) {
					this.right = node;
					node.setParent(this);
					return true;
				} else {
					this.right.appendChild(node);
				}
			}
			if( this.flag == true) {
				this.flag = false;
				return false;
			} else {
				return true;
			}
		}
		
		public int getKey() {
			return this.key;
		}
		
		public Node getLeft() {
			return this.left;
		}

		public Node  getRight() {
			return this.right;
		}

		public Node getParent() {
			return this.parent;
		}
		
		public void setLeft(Node node) {
			this.left = node;
		}

		public void setRight(Node node) {
			this.right = node;
		}

		public void setParent(Node node) {
			this.parent = node;
		}
		
	}
	
	public static class BinarySearchTree {
		
		private Node root;
		private LinkedList<Node> nodes;
		private int sumKey;
		
		public BinarySearchTree() {
			this.root = null;
			this.nodes = new LinkedList<Node>();
			this.sumKey = 0;
		}
		
		public void push(int key) {
			Node node = new Node(key);
			if(this.root == null) {
				this.root = node;
//				nodes.add(root);
				sumKey = sumKey + key;
			} else {
				if(this.root.appendChild(node) == true) {
//				this.root.appendChild(node);
					sumKey = sumKey + key;
	//				nodes.add(node);
				}
			}
		}
		
		public void straightLeftTrip(Node node) {
			if(node != null) {
				System.out.println(node.getKey());
				nodes.add(node);
				straightLeftTrip(node.getLeft());
				straightLeftTrip(node.getRight());
			}
		}
		
		public Node getRoot() {
			return this.root;
		}
		
		public List<Node> getNodes() {
			return this.nodes;
		}
		
		public String toString() {
			return Integer.toString(this.sumKey);
		}
	}

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		File fis = new File("input.txt");
		File fos = new File("output.txt");
		Scanner in;
		FileWriter out;
//		int sum = 0;
		try {
			in = new Scanner(fis);
			while(in.hasNextLine()) {
				tree.push(Integer.parseInt(in.nextLine()));
	//			sum = sum + Integer.parseInt(in.nextLine());
			}
		//	try {
				tree.straightLeftTrip(tree.getRoot());
				out = new FileWriter(fos);
				for(Node i: tree.getNodes()) {
					out.write(Integer.toString(i.getKey())+"\n");
				}
		//		out.write(tree.toString());
		//		out.write(Integer.toString(sum));
				out.close();
	/*		} catch (IOException e) {
				e.printStackTrace();
			}*/
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(tree);
	}
}
