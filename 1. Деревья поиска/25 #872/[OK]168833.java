import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
public class Tree {
	    static class Node {
	        int key;
	        Node left;
	        Node right;
	        int descendentCount;
	        public Node(int key) {
	            this.key = key;
	        }
	    }
	    private  int max = 0;
	    private Vector<Integer> keys;
	    private Vector<Integer> topNumber;
	 private Node root;
	 public Tree(){
		 keys = new Vector<Integer>();
		 topNumber = new Vector<Integer>();
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
		public void findTops(Node node){
			int rightNumber = 0;
			int leftNumber = 0;
			if(node.right != null){
				rightNumber = node.right.descendentCount + 1;
			}
			if(node.left != null){
				leftNumber = node.left.descendentCount + 1;
			}
			if(Math.abs(rightNumber - leftNumber) == max){
				keys.add(node.key);
			}
			if(Math.abs(rightNumber - leftNumber) > max){
				max = Math.abs(rightNumber - leftNumber);
				keys.clear();
				keys.add(node.key);
			}
			if(node.left != null){
				findTops(node.left);
			}
			if(node.right != null){
				findTops(node.right);
			}
		}
		public void delete(){
			Collections.sort(keys);
			if(keys.size() % 2 == 0){
				return;
			}
			if(!isKeyRoot(root, keys.get(keys.size()/2))){
				rightDeleting(this.root, keys.get(keys.size()/2));
				}
		}
		public int numberOfNodes(Node root, Integer descendentCount){
			if(root.left != null){
				descendentCount += numberOfNodes(root.left,0);
			}
			if(root.right != null){
				descendentCount += numberOfNodes(root.right,0);
			}
			if(root.right == null && root.left == null){
				root.descendentCount = 0;
				return 1;
			}
			root.descendentCount = descendentCount;
			return descendentCount+1;
		}
	public static void main(String[] args) throws IOException {
		String s1 = "tst.in";
		String s2 = "tst.out";
		BufferedReader buf1 = new BufferedReader(new FileReader(s1));
		BufferedWriter buf2 = new BufferedWriter(new FileWriter(s2));
		String str;
		Tree tree = new Tree();
		while((str = buf1.readLine()) != null){
			int key = Integer.parseInt(str);
			tree.insert(key);
		}
		Node root = tree.getRoot();
		tree.numberOfNodes(root, 0);
		//System.out.println(tree.numberOfNodes(root, (Integer)0));
		//System.out.println(root.right.descendentCount);
		tree.findTops(tree.getRoot());
		tree.delete();
		root = tree.getRoot();
		tree.leftPass(root, buf2);
		buf1.close();
		buf2.close();
	}
}
