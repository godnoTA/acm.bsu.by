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
		
		public void setKey(int key) {
			this.key = key;
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
		
		public boolean equals(Node node) {
			if(this.key == node.getKey() && this.right == node.getRight() && this.left == node.getLeft())
				return true;
			return false;
		}
		
		public void copy(Node node) {
			this.key = node.getKey();
			this.right = node.getRight();
			this.left = node.getLeft();
			this.parent = node.getParent();
		}
	}
	
	public static class BinarySearchTree {
		
		private Node root;
		private LinkedList<Node> nodes;
		private long sumKey;
		
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
			} else {
				if(this.root.appendChild(node) == true) {
//				this.root.appendChild(node);
	//				nodes.add(node);
				}
			}
		}
		
		private void prepare() {
			this.sumKey = 0;
			this.nodes = null;
			this.nodes = new LinkedList<Node>();
		}
		
		private void trip(Node node) {
			if(node != null) {
				System.out.println(node.getKey());
				nodes.add(node);
				this.sumKey = this.sumKey + node.getKey();
				trip(node.getLeft());
				trip(node.getRight());
			}
		}
		
		public void straightLeftTrip(Node node) {
			prepare();
			trip(node);
		}
		
		public Node getRoot() {
			return this.root;
		}
		
		public List<Node> getNodes() {
			return this.nodes;
		}
		
		public String toString() {
			return Long.toString(this.sumKey);
		}
		
		public Node minimum(Node node) {
			if(node.getLeft() == null) {
				return node;
			} else {
				Node res = node.getLeft();
				while(res.getLeft() != null) {
					res = res.getLeft();
				}
				return res;
			}
		}
		
		public Node delete(Node node, int key) {
			if(node == null)
				return node;
			if(node.getKey() > key) {
				node.setLeft(this.delete(node.getRight(), key));
			}
			if(node.getKey() < key) {
				node.setRight(this.delete(node.getLeft(), key));
			}
			if(node.getKey() == key) {
				if(node.getLeft() != null && node.getRight() != null) {
					node.setKey(this.minimum(node.getRight()).getKey());
					node.setRight(this.delete(node.getRight(), node.getKey()));
				} else {
					if(node.getLeft() != null) {
						Node parent = node.getParent();
						node = node.getLeft();
		//				node.setLeft(null);
						node.setParent(parent);
					} else {
						Node parent = node.getParent();
						node = node.getRight();
		//				node.setRight(null);
						node.setParent(parent);
					}
				}
			}
			return node;
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
	//		int del = Integer.parseInt(in.nextLine());
	//		in.nextLine();
			while(in.hasNextLine()) {
				tree.push(Integer.parseInt(in.nextLine()));
	//			sum = sum + Integer.parseInt(in.nextLine());
			}
	//		System.out.println("del:" + del);
		//	try {
				out = new FileWriter(fos);
				tree.straightLeftTrip(tree.getRoot());
	/*			System.out.println();
				System.out.println("It was delete" + tree.delete(tree.getRoot(), del).getKey());
				tree.straightLeftTrip(tree.getRoot());
				for(Node i: tree.getNodes()) {
					out.write(Integer.toString(i.getKey())+"\n");
				}*/
				out.write(tree.toString());
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
