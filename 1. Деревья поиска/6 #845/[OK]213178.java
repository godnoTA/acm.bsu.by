
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class BinaryTree implements Runnable{
	Node root;
	static StringBuffer result = new StringBuffer();
	Node maxRoot;

	static class Node {
		int key;
		int height;
		long sum;
		long rootSum;
		int msl;
		Node left;
		Node right;
		Node parent;

		public Node(int key, Node p) {
			this.key = key;
			this.parent = p;
		}
	}

	Node insert(Node t, Node p, int key) {
		if (t == null) {
			t = new Node(key, p);
		} else {
			if (key < t.key)
				t.left = insert(t.left, t, key);
			else if (key > t.key)
				t.right = insert(t.right, t, key);
		}
		return t;
	}

	public void insert(int key) {
		root = insert(root, null, key);
	}

	void replace(Node a, Node b) {
		if (a.parent == null)
			root = b;
		else if (a == a.parent.left)
			a.parent.left = b;
		else
			a.parent.right = b;
		if (b != null)
			b.parent = a.parent;
	}

	void remove(Node t, int key) {
		if (t == null)
			return;
		if (key < t.key)
			remove(t.left, key);
		else if (key > t.key)
			remove(t.right, key);
		else if (t.left != null && t.right != null) {
			Node m = t.right;
			while (m.left != null)
				m = m.left;
			t.key = m.key;
			replace(m, m.right);
		} else if (t.left != null) {
			replace(t, t.left);
		} else if (t.right != null) {
			replace(t, t.right);
		} else {
			replace(t, null);
		}
	}

	public void remove(int key) {
		remove(root, key);
	}

	void print(Node t) {
		if (t != null) {
			System.out.println(t.key);
			result.append(t.key + "\r\n");
			print(t.left);
			print(t.right);
		}
	}

	public void print() {
		print(root);
		System.out.println();
		result.append("\r\n");
	}

	public static void read(String fileName, BinaryTree tree) {
		File dir = new File(fileName);
		if (dir.getName().contains(".txt")) {
			workWithFile(dir, tree);
		}
	}

	public static void workWithFile(File file, BinaryTree tree) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					tree.insert(Integer.parseInt(s));
				}
			} finally {
				in.close();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void writeToFile(String fileName, String text) {
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	int maxHeight(Node a, Node b) {
		if (a.height > b.height)
			return a.height;
		else
			return b.height;
	}

	long maxSum(Node a, Node b) {
		if (a.height > b.height)
			return a.sum;
		else if (a.height < b.height)
			return b.sum;
		else if (a.sum > b.sum)
			return a.sum;
		else
			return b.sum;
	}

	void findData(Node t) {
		if (t == null)
			return;
		findData(t.left);
		findData(t.right);
		if (t.left != null && t.right != null) {
			t.height = maxHeight(t.right, t.left) + 1;
			t.msl = t.right.height + t.left.height + 2;
			t.sum = maxSum(t.right, t.left) + t.key;
			t.rootSum = t.right.sum + t.left.sum + t.key;
		} else if (t.left != null) {
			t.height = t.left.height + 1;
			t.msl = t.height;
			t.sum = t.left.sum + t.key;
			t.rootSum = t.sum;
		} else if (t.right != null) {
			t.height = t.right.height + 1;
			t.sum = t.right.sum + t.key;
			t.msl = t.height;
			t.rootSum = t.sum;
		} else {
			t.height = 0;
			t.msl = t.height;
			t.sum = t.key;
			t.rootSum = t.sum;
		}
	}

	void findMaxRoot(Node t) {
		if (t == null)
			return;
		findMaxRoot(t.left);
		findMaxRoot(t.right);
		if (maxRoot == null)
			maxRoot = t;
		else if (t.msl > maxRoot.msl) {
			maxRoot = t;
		} else if ((t.msl == maxRoot.msl) && (t.rootSum > maxRoot.rootSum)) {
			maxRoot = t;
		}
	}

	private void findAndDeleteCenter() {
		int i = 0;
		Node center = maxRoot;
		if (center.left != null && center.right != null) {
			if (center.right.height > center.left.height) {
				i = center.msl / 2 - center.left.height - 2;
				center = center.right;
				;
			} else {
				i = center.msl / 2 - center.right.height - 2;
				center = center.left;
			}
		} else if (center.left != null) {
			i = center.msl / 2 - 1;
			center = center.left;
		} else if (center.right != null) {
			i = center.msl / 2 - 1;
			center = center.right;
		}
		for (; i > 0; i--) {
			if (center.right != null && center.left != null) {
				if (center.right.height > center.left.height)
					center = center.right;
				else if (center.right.height < center.left.height)
					center = center.left;
				else if (center.right.sum > center.left.sum)
					center = center.right;
				else
					center = center.left;
			} else if (center.right != null) {
				center = center.right;
			} else if (center.left != null)
				center = center.left;
		}
		this.remove(center.key);
	}

	private static void doAlgorythm(BinaryTree tree) {
		tree.findData(tree.root);
		tree.findMaxRoot(tree.root);
		if (tree.maxRoot.msl % 2 == 0)
			if (((tree.maxRoot.left != null && tree.maxRoot.right != null)
					&& (tree.maxRoot.left.height != tree.maxRoot.right.height)) || tree.maxRoot.right == null
					|| tree.maxRoot.left == null)
				tree.findAndDeleteCenter();
		tree.remove(tree.maxRoot.key);
	}

	public static void main(String[] args) {
		new Thread(null, new BinaryTree(), "", 64 * 1024 * 1024).start();
	}

	@Override
	public void run() {
		BinaryTree tree = new BinaryTree();
		read("in.txt", tree);
		doAlgorythm(tree);
		tree.print();
		writeToFile("out.txt", result.toString());
	}

}
