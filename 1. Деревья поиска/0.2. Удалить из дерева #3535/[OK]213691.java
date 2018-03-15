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
		private int depth;
		static private boolean flag = false;
		
		public Node(int key) {
			this.key = key;
			this.left = null;
			this.right = null;
			this.parent = null;
			this.depth = 0;
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
					node.setDepth(node.getDepth() + 1);
					return true;
				} else {
					node.setDepth(node.getDepth() + 1);
					this.left.appendChild(node);
				}
			}
			if(this.key < node.getKey()) {
				if(this.right == null) {
					this.right = node;
					node.setParent(this);
					node.setDepth(node.getDepth() + 1);
					return true;
				} else {
					node.setDepth(node.getDepth() + 1);
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
		
		public int getDepth() {
			return this.depth;
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
		
		public void setDepth(int depth) {
			this.depth = depth;
		}
		
		public boolean equals(Node node) {
			if(this.key == node.getKey()) //&& this.right == node.getRight() && this.left == node.getLeft()
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
		private LinkedList<Node> lists;
		private long sumKey;
		
		
		private int count = 0;
		private Node tmpAverageNode = null;
		private int tmpWayLength = 0;
		
		public BinarySearchTree() {
			this.root = null;
			this.nodes = new LinkedList<Node>();
			this.lists = new LinkedList<Node>();
			this.sumKey = 0;
		}
		
		public void push(int key) {
			Node node = new Node(key);
			if(this.root == null) {
				this.root = node;
				this.lists.add(node);
			} else {
				if(this.root.appendChild(node) == true) {
					this.lists.add(node);
					if(this.lists.contains(node.getParent())) {
						this.lists.remove(node.getParent());
					}
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
		
		public LinkedList<Node> getLists() {
			return this.lists;
		}
		
		public String toString() {
			return Long.toString(this.sumKey);
		}
		
		public List<Node> findMinimalWayBetweenRootAndList() {
			LinkedList<Node> res = new LinkedList<Node>();
			this.lists.sort((Node a, Node b) -> {
				if(b.getDepth() > a.getDepth()) 
					return -1;
				else if(b.getDepth() < a.getDepth())
					return 1;
				else
					return 0;				
			});
			int minDepth = this.lists.getFirst().getDepth();
			for(Node i: this.lists) {
				if(i.getDepth() == minDepth) 
					res.add(i);
			}
			return res;
		}
		
		private Node minimum(Node node) {
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
		
		public Node deleteRight(Node node, int key) {
			if(node == null)
				return node;
			if(node.getKey() > key) {
				node.setLeft(this.deleteRight(node.getLeft(), key));
			}
			if(node.getKey() < key) {
				node.setRight(this.deleteRight(node.getRight(), key));
			}
			if(node.getKey() == key) {
				if(node.getLeft() != null && node.getRight() != null) {
					node.setKey(this.minimum(node.getRight()).getKey());
					node.setRight(this.deleteRight(node.getRight(), node.getKey()));
				} else {
					if(node.getLeft() != null) {
						if(node == this.root)
							this.root =  node.getLeft();
						node = node.getLeft();
					} else {
						if(node.getRight() != null) {
							if(node == this.root)
								this.root = node.getRight();
							node = node.getRight();
						} else {
							if(node == this.root)
								this.root = null;
							node = null;
						}
					}
				}
			}
			return node;
		}
		
		private Node maximum(Node node) {
			if(node.getRight() == null) {
				return node;
			} else {
				Node res = node.getRight();
				while(res.getRight() != null) {
					res = res.getRight();
				}
				return res;
			}
		}
		
		public Node deleteLeft(Node node, int key) {
			if(node == null)
				return node;
			if(node.getKey() > key) {
				node.setLeft(this.deleteLeft(node.getLeft(), key));
			}
			if(node.getKey() < key) {
				node.setRight(this.deleteLeft(node.getRight(), key));
			}
			if(node.getKey() == key) {
				if(node.getLeft() != null && node.getRight() != null) {
					node.setKey(this.maximum(node.getLeft()).getKey());
					node.setLeft(this.deleteLeft(node.getLeft(), node.getKey()));
				} else {
					if(node.getLeft() != null) {
						if(node == this.root)
							this.root = node;
						node = node.getLeft();

					} else {
						if(node.getRight() != null) {							
							if(node == this.root)
								this.root = node;
							node = node.getRight();
						} else {
							node = null;
						}
					}
				}
			}
			return node;
		}
		
		public Node averageNode(Node node) {
			if(this.root == node) {
				return this.tmpAverageNode;
			} 
			this.count++;
			if(this.count == (this.tmpWayLength/2 + 1)) {
					this.tmpAverageNode = node;
			}
			this.averageNode(node.getParent());
			return this.tmpAverageNode;
		}
		
		public Node getAverageNode(Node node) {
			this.count = 0;
			Node res = null;
			if(node.getDepth()%2 == 0)
				this.tmpWayLength = node.getDepth() + 1;
				res = averageNode(node);
			return res;
		}
	}

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		File fis = new File("input.txt");
		File fos = new File("output.txt");
		Scanner in;
		FileWriter out;
		try {
			in = new Scanner(fis);
			int del = 0;
			if(in.hasNextLine()) {
				del = Integer.parseInt(in.nextLine());
			}
			if(in.hasNextLine()) {
				in.nextLine();
			}
			while(in.hasNextLine()) {
				tree.push(Integer.parseInt(in.nextLine()));
			}
	//		System.out.println("del:" + del);
			out = new FileWriter(fos);
		//	Node test = null;
		//	int t = test.getKey();
		//	tree.straightLeftTrip(tree.getRoot());
		//	System.out.println();
		//	System.out.println("It was delete" + tree.delete(tree.getRoot(), del).getKey());
			tree.straightLeftTrip(tree.getRoot());
			tree.deleteRight(tree.getRoot(), del);
			tree.straightLeftTrip(tree.getRoot());
			for(Node i: tree.getNodes()) {
				out.write(Integer.toString(i.getKey())/*+ " " + Integer.toString(i.getDepth())*/ +"\n");
			}
	/*		out.write("\n");
			for(Node i: tree.findMinimalWayBetweenRootAndList()) {
				out.write(Integer.toString(i.getKey())+ " " + Integer.toString(i.getDepth()) +"\n");
			}
			out.write("\n");
			for(Node i: tree.getLists()) {
				out.write(Integer.toString(tree.getAverageNode(i).getKey()) +"\n");
			}
			out.write("\n");
			tree.deleteLeft(tree.getAverageNode(tree.findMinimalWayBetweenRootAndList().get(0)), tree.getAverageNode(tree.findMinimalWayBetweenRootAndList().get(0)).getKey());
		//	System.out.println(tree.getAverageNode(tree.findMinimalWayBetweenRootAndList().get(0)).getKey());
			tree.straightLeftTrip(tree.getRoot());
			for(Node i: tree.getNodes()) {
				out.write(Integer.toString(i.getKey())+ " " + Integer.toString(i.getDepth()) +"\n");
			}*/
			out.close();
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}