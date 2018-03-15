

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class BSTree<T1 extends Comparable<T1>, T2> {
	static class Node<T1, T2> {
		T1 key;
		T2 value;
		Node<T1, T2> left, right;
		
		Node(T1 key, T2 value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private Node<T1, T2> root = null;
	
	public boolean containsKey(T1 key) {
		Node<T1, T2> x = root;
		
		while (x != null) {
			int cmp = key.compareTo(x.key);
			
			if (cmp == 0) {
				return true;
			}
			if (cmp < 0) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		
		return false;
	}
	
	public T2 getByKey(T1 key) {
		Node<T1, T2> x = root;
		
		while (x != null) {
			int cmp = key.compareTo(x.key);
			
			if (cmp == 0) {
				return x.value;
			}
			if (cmp < 0) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		
		return null;
	}
	
	public void add(T1 key, T2 value) {
		Node<T1, T2> x = root, y = null;
		
		while (x != null) {
			int cmp = key.compareTo(x.key);
			
			if (cmp == 0) {
				return;
			} else {
				y = x;
			
				if (cmp < 0) {
					x = x.left;
				} else {
					x = x.right;
				}
			}
		}
		
		Node<T1, T2> newNode = new Node<T1, T2>(key, value);
			
		if (y == null) {
			root = newNode;
		} else {
			if (key.compareTo(y.key) < 0) {
				y.left = newNode;
			} else {
				y.right = newNode;
			}
		}
	}
	
	public void remove(T1 key) {
		Node<T1, T2> x = root, y = null;
		
		while (x != null) {
			int cmp = key.compareTo(x.key);
			
			if (cmp == 0) {
				break;
			} else {
				y = x;
			
				if (cmp < 0) {
					x = x.left;
				} else {
					x = x.right;
				}
			}
		}
		
		if (x == null) {
			return;
		}
		
		if (x.right == null) {
			if (y == null) {
				root = x.left;
			} else {
				if (x == y.left) {
					y.left = x.left;
				} else {
					y.right = x.left;
				}
			}
			
		} else {
			Node<T1, T2> leftMost = x.right;
			y = null;
			
			while (leftMost.left != null) {
				y = leftMost;
				leftMost = leftMost.left;
			}
			
			if (y != null) {
				y.left = leftMost.right;
			} else {
				x.right = leftMost.right;
			}
			
			x.key = leftMost.key;
			x.value = leftMost.value;
		}
	}
	
	public void preOrderWalk(PrintStream writer) {
		printPreOrderWalk(root, writer);
	}
	
	private void printPreOrderWalk(Node<T1, T2> root, PrintStream writer) {
		if (root != null) {
			writer.println(root.value);
			printPreOrderWalk(root.left, writer);
			printPreOrderWalk(root.right, writer);
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
			PrintStream writer = new PrintStream("output.txt");
			 
			String line;
			int x;
			BSTree<Integer, Integer> tree = new BSTree<>();
			
			while ((line = reader.readLine()) != null) {
				x = Integer.parseInt(line);
				tree.add(x, x);
			}
			
			tree.preOrderWalk(writer);
			
			reader.close();
			writer.close();
		}
		catch(Exception e) {
			
		}
	}
}




