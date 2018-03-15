import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class BinaryTree {
    static Node root;
    static StringBuffer result = new StringBuffer();
    static class Node {
        int key;
        Node left;
        Node right;
        Node parent;
        public Node(int key, Node p) {
            this.key = key;
            this.parent = p;
        }
    }
    
/*    Node search(Node t, int key) {
        if (t == null || t.key == key)
            return t;
        if (key < t.key)
            return search(t.left, key);
        else
            return search(t.right, key);
    }*/
    
   /* public Node search(int key) {
        return search(root, key);
    }*/
    
    Node insert(Node t, Node p, int key) {
        if (t == null) {
            t = new Node(key, p);
        } else {
            if (key < t.key)
                t.left = insert(t.left, t, key);
            else if(key > t.key)
                t.right = insert(t.right, t, key);
        }
        return t;
    }
    
    public void insert(int key) {
        root = insert(root, null, key);
    }
    
/*    void replace(Node a, Node b) {
        if (a.parent == null)
            root = b;
        else if (a == a.parent.left)
            a.parent.left = b;
        else
            a.parent.right = b;
        if (b != null)
            b.parent = a.parent;
    }*/
    
/*    void remove(Node t, int key) {
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
      */  /*}
    }*/
    
/*    public void remove(int key) {
        remove(root, key);
    }
    */
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
					/*if(!contains(treeKeys, Integer.parseInt(s))){*/
						tree.insert(Integer.parseInt(s));
					/*}*/
				}
			} finally {
				in.close();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static void writeToFile(String fileName, String text){
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
	
	/*public static boolean contains(LinkedList<Integer> li, int k){
		for(int tmp: li){
			if(k == tmp)
				return true;
		}
		return false;
	}*/
    
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        read("input.txt", tree);
        tree.print();
        writeToFile("output.txt", result.toString());
    }
}