import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Tree {
	private static Node root = null;
	static class Node {
		long key;
        Node left;
        Node right;
        boolean isActive = true;
        public Node(long key) {
            this.key = key;
        }
    }
	public static long codeLength = 0;
	public static void buildHuffmanTree(PriorityQueue<Long> frequencies){
		Node node;
		long sum;
		Node min1;
		Node min2;
		Long arr[] = new Long[frequencies.size()];
		frequencies.toArray(arr);
		Comparator <Node> cmp = new Comparator <Node>(){
			@Override
			public int compare(Node node1, Node node2){
				return(node1.key > node2.key)? 1:(node1.key < node2.key)?-1:0;
			}
		};
		PriorityQueue<Node> roots = new PriorityQueue<Node>(cmp);
		for(int i = 0;i < frequencies.size();i++){
			roots.add(new Node(arr[i]));
		}
		while(roots.size() != 1){
			min1 = roots.peek();
			roots.remove();
			min2 = roots.peek();
			roots.remove();
			sum = min1.key + min2.key;
			node = new Node(sum);
			node.left = min1;
			node.right = min2;
			root = node;
			roots.add(node);
		}
		countCodeLength(root, 0);
	}
	public static void countCodeLength(Node node, int height){
		if(node.left != null){
			height++;
			countCodeLength(node.left, height);
			height--;
		}
		if(node.right != null){
			height++;
			countCodeLength(node.right, height);
			height--;
		}
		if(node.left == null && node.right == null){
		codeLength += node.key*height;
		}
	}
	public static void main(String[] args) throws IOException {
		String s1 = "huffman.in";
		String s2 = "huffman.out";
		BufferedReader buf1 = new BufferedReader(new FileReader(s1));
		BufferedWriter buf2 = new BufferedWriter(new FileWriter(s2));
		long N = Long.parseLong(buf1.readLine());
		StringTokenizer st = new StringTokenizer(buf1.readLine());
	  	PriorityQueue <Long>  frequencies = new PriorityQueue <Long> ((int) N);
		while(st.hasMoreTokens()){
			frequencies.add(Long.parseLong(st.nextToken()));
		}
		buildHuffmanTree(frequencies);
		buf2.write(Long.toString(codeLength));
		buf1.close();
		buf2.close();
	}

}
