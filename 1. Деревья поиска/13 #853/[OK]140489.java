import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class IntBSTree {

		private Node root;
		private int size;
		
		public IntBSTree() {
			root = null;
			size = 0;
		}
		
		public boolean isEmpty() {
	        return size == 0;
	    }
		
		public void insert(int key)
		{ 
			root = recursiveInsert(root, null, key); 
			size++;
		}
		
		public boolean contains(int key) {
	        return recursiveFind(root, key) != null;
	    }
		
		public void deleteElement(int key)
		{ 
			deleteItem(root, key); 
			size--; 
		}
		
		public void inorderShow() {
			recursiveInorderPrint(root);
		}
		
		public void preorderShow() {
			recursivePreorderPrint(root);
		}
		
		public void postorderShow() {
			recursivePostorderPrint(root);
		}
		
		private void PreorderFileprint(PrintWriter writer) throws FileNotFoundException {
			recursivePreorderFileprint(writer, root);
		}
		
		public void fillHeight() {
			recursiveFillHeight(root);
		}
		
		private class Node {
			private int key;
			private int height;
			private Node left, right, parent;
			
			Node(int key, Node parent) {
				this.key = key;
				this.parent = parent;
			}
		}
		
		private Node recursiveInsert(Node root, Node parent, int key) {
			if(root == null) {
				return new Node(key, parent);
			}
			if(key < root.key)
				root.left = recursiveInsert(root.left, root, key);
			else if(key > root.key)
				root.right = recursiveInsert(root.right, root, key);
			return root;
		}
		
		private Node recursiveFind(Node currentNode, int key) {
			if(currentNode == null || key == currentNode.key)
				return currentNode;
			if(key < currentNode.key)
				return recursiveFind(currentNode.left, key);
			else
				return recursiveFind(currentNode.right, key);
		}
		
		private Node findMin(Node startNode)
		{
			Node currentNode = startNode;
			while (currentNode.left != null)
				currentNode = currentNode.left;
			return currentNode;
		}
		
		private void replaceNodeInParent(Node node, Node source)
		{
			if(node != null)
				if(node == node.parent.left)
					node.parent.left = source;
				else
					node.parent.right = source;
			if(source != null)
				source.parent = node.parent;
		}
		
		private void deleteItem(Node startNode, int key)
		{
			if(startNode == null)
				throw new NoSuchElementException();
			if(startNode.key > key)
				deleteItem(startNode.left, key);
			else if(startNode.key < key)
				deleteItem(startNode.right, key);
			else {
				if(startNode.left != null && startNode.right != null) {
					Node successor = findMin(startNode.right);
					startNode.key = successor.key;
					deleteItem(successor, successor.key);
				}
				else if(startNode.left != null)   
					replaceNodeInParent(startNode, startNode.left);
				else if(startNode.right != null)   
					replaceNodeInParent(startNode, startNode.right);
				else
					replaceNodeInParent(startNode, null);
			}
		}
		
		private void recursiveInorderPrint(Node start) {
			if(start == null)
				return;
			else {
				recursiveInorderPrint(start.left);
				System.out.println(start.key);
				recursiveInorderPrint(start.right);
			}
		}
		
		private void recursivePreorderPrint(Node start) {
			if(start == null)
				return;
			else {
				System.out.println(start.key);
				recursivePreorderPrint(start.left);
				recursivePreorderPrint(start.right);
			}
		}
		
		private void recursivePreorderFileprint(PrintWriter writer, Node start) throws FileNotFoundException {
			if(start == null)
				return;
			else {
				writer.println(start.key);
				recursivePreorderFileprint(writer, start.left);
				recursivePreorderFileprint(writer, start.right);
			}
		}
		
		private void recursivePostorderPrint(Node start) {
			if(start == null)
				return;
			else {
				recursivePostorderPrint(start.left);
				recursivePostorderPrint(start.right);
				System.out.println(start.key);
			}
		}
		
		private static void calculateHeight(Node a) {
			if(a == null)
				return;
			else {
				if(a.left == null && a.right == null)
					a.height = 0;
				else if(a.left != null && a.right == null) 
					a.height = a.left.height + 1;
				else if(a.left == null && a.right != null) 
					a.height = a.right.height + 1;
				else
					a.height = Math.max(a.left.height, a.right.height) + 1;
			}
		}
		
		private void recursiveFillHeight(Node start) {
			if(start == null)
				return;
			else {
				recursiveFillHeight(start.left);
				recursiveFillHeight(start.right);
				calculateHeight(start);
			}
		}
		
		private static int getPathLength(Node a) {
			if(a == null)
				return -1;
			else {
				if(a.left == null && a.right == null)
					return 0;
				else if(a.left != null && a.right == null) 
					return a.left.height + 1;
				else if(a.left == null && a.right != null) 
					return a.right.height + 1;
				else
					return a.left.height + a.right.height + 2;
			}
		}
		
		private static Node getPathRoot(Node start, Node max) {
			if(start == null)
				return max;
			else {
				int maxPathLen = getPathLength(max);
				if((2 * start.height + 1) < maxPathLen)
					return max;
				int PathLen = getPathLength(start);
				if(PathLen > maxPathLen) {
					max = start;
				}
				else if(PathLen == maxPathLen && start.key < max.key) {
					max = start;
				}
				max = getPathRoot(start.left, max);
				max = getPathRoot(start.right, max);
			}
			return max;
		}
		
		private static boolean isLeaf(Node a) {
			return a.left == null && a.right == null;
		}
		
		public static LinkedList<Node> getLongestPath(Node a) {
			LinkedList<Node> path = new LinkedList<Node>();
			path.add(a);
			Node currentright = a.right;
			Node currentleft = a.left;
			if(currentright != null) {
				while(!isLeaf(currentright)) {
					path.addLast(currentright);
					if(currentright.left != null && currentright.right == null) {
						currentright = currentright.left;
					} 
					else if(currentright.left == null && currentright.right != null) {
						currentright = currentright.right;
					} else {
						if(currentright.right.height > currentright.left.height) {
							currentright = currentright.right;
						} else {
							currentright = currentright.left;
						}
					}
				}
				path.addLast(currentright);
			}
			if(currentleft != null) {
				while(!isLeaf(currentleft)) {
					path.addFirst(currentleft);
					if(currentleft.left != null && currentleft.right == null) {
						currentleft = currentleft.left;
					} 
					else if(currentleft.left == null && currentleft.right != null) {
						currentleft = currentleft.right;
					} else {
						if(currentleft.right.height > currentleft.left.height) {
							currentleft = currentleft.right;
						} else {
							currentleft = currentleft.left;
						}
					}
				}
				path.addFirst(currentleft);
			}
			return path;
		}
		
		private static Node getMeanNode(LinkedList<Node> a) {
			Node min = null;
			a.sort(new Comparator<Node>() {

				public int compare(Node o1, Node o2) {
					return Integer.compare(o1.key, o2.key);
				}
				
			});
			if(a.size()%2 == 1)
				min = a.get((a.size()-1) / 2);
			return min;
		}
		
		public static IntBSTree solve(IntBSTree a) {
			a.fillHeight();
			Node longestPathRoot = getPathRoot(a.root, null);
			LinkedList<Node> path = getLongestPath(longestPathRoot);
			LinkedList<Node> rootpath = null;
			int size1 = path.size() - 1;
			int size2 = 0;
			int sum1 = path.get(0).key + path.get(size1 - 1).key;
			int sum2 = path.get(1).key + path.get(size1).key;
			int sum3 = Integer.MAX_VALUE;
			if(a.root.height == getPathLength(a.root)) {
				if (longestPathRoot == a.root) {
					if(path.size()%2 == 1) {
						Node ptr = getMeanNode(path);
						a.deleteItem(ptr, ptr.key);
					}
					return a;
				}
				else if((getPathLength(longestPathRoot)) == getPathLength(a.root)) {
					rootpath = getLongestPath(a.root);
					if(rootpath.size()%2 == 1) {
						Node ptr = getMeanNode(rootpath);
						a.deleteItem(ptr, ptr.key);
					}
					return a;
				}
				else if((getPathLength(longestPathRoot) - 1) == getPathLength(a.root)) {
					rootpath = getLongestPath(a.root);
					size2 = rootpath.size();
					sum3 = rootpath.get(0).key + rootpath.get(size2 - 1).key;
				}
			}
			if(sum1 < sum2 && sum1 < sum3) {
				if(size1%2 == 1) {
					path.pollLast();
					Node ptr = getMeanNode(path);
					a.deleteItem(ptr, ptr.key);
				}
			}
			else if(sum2 < sum1 && sum2 < sum3) {
				if(size1%2 == 1) {
					path.pollFirst();
					Node ptr = getMeanNode(path);
					a.deleteItem(ptr, ptr.key);
				}
			}
			else if(sum3 < sum1 && sum3 < sum2) {
				if(size2%2 == 1) {
					Node ptr = getMeanNode(rootpath);
					a.deleteItem(ptr, ptr.key);
				}
			}
			else if(sum3 == sum1 && sum3 < sum2) {
				if(a.root.key < longestPathRoot.key) {
					if(size2%2 == 1) {
						Node ptr = getMeanNode(rootpath);
						a.deleteItem(ptr, ptr.key);
					}
				}
				else {
					if(size1%2 == 1) {
						path.pollLast();
						Node ptr = getMeanNode(path);
						a.deleteItem(ptr, ptr.key);
					}
				}
			}
			else if(sum3 == sum2 && sum3 < sum1) {
				if(a.root.key < longestPathRoot.key) {
					if(size2%2 == 1) {
						Node ptr = getMeanNode(rootpath);
						a.deleteItem(ptr, ptr.key);
					}
				}
				else {
					if(size1%2 == 1) {
						path.pollFirst();
						Node ptr = getMeanNode(path);
						a.deleteItem(ptr, ptr.key);
					}
				}
			}
			else if(sum1 == sum2 && sum1 < sum3 && size1%2 == 1) {
				Node n1, n2;
				LinkedList<Node> secondpath = new LinkedList<Node>(path);
				path.pollLast();
				n1 = getMeanNode(path);
				secondpath.pollFirst();
				n2 = getMeanNode(secondpath);
				if(n1.key == n2.key)
					a.deleteItem(n1, n1.key);
			}
			return a;
		}
		
		public static void main(String[] args) throws NumberFormatException, IOException {
			IntBSTree a = new IntBSTree();
			BufferedReader reader = new BufferedReader(new FileReader("tst.in"));
			String line = "";
			while((line = reader.readLine()) != null && line != "") {
				a.insert(Integer.parseInt(line));
			}
			reader.close();
			solve(a);
			PrintWriter writer = new PrintWriter("tst.out");
			a.PreorderFileprint(writer);
			writer.close();
		}
}