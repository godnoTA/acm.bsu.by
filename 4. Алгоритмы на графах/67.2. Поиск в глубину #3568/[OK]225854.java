import java.io.File;
import java.io.FileWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Arg_13 {
	
	public static int kek = 1;	
	
/*	public static class Line {
		int startPoint, endPoint;
		int flag;
		// 1-back edges
		//2-cross edges
		//3-forvard edges
		//4-tree edges
		
		Line(int startPoint, int endPoint) {
			this.startPoint = startPoint;
			this.endPoint= endPoint;
		}
	}*/

	public static class Node {
		public ArrayList<Integer> avaiableNode;
//		public ArrayList<Line> avaiableLines;
		int label;
		boolean flag = false;

		Node() {
			this.avaiableNode = new ArrayList<Integer>();
	//		this.avaiableLines = new ArrayList<Line>();
		}
	}
	
	public static void dfs(Node node, Node[] nodes) {
		node.label = kek;
		kek++;
		for(int i = 0; i < node.avaiableNode.size(); i++) {
			if(nodes[node.avaiableNode.get(i)].label == 0)
				dfs(nodes[node.avaiableNode.get(i)], nodes);
		}
	}

	public static void main(String[] args) {
		File fis = new File("input.txt");
		File fos = new File("output.txt");
		Scanner in;
		FileWriter out;
		try {
			in = new Scanner(fis);
			out = new FileWriter(fos);
			int count = Integer.parseInt(in.next());
			Node[] nodes = new Node[count];
			Stack<Node> stack = new Stack<Node>();
			for(int i = 0; i < count; i++) {
				nodes[i] = new Node();
				for(int j = 0; j < count; j++) {
					int k = Integer.parseInt(in.next());
					if(k == 1) {
						nodes[i].avaiableNode.add(j);
		//				Line tmp = new Line(i, j);
		//				nodes[i].avaiableLines.add(tmp);
					}
				}
			}
		//	int k = 1;	
		/*	for(int j = 0; j < count; j++) {
				if(nodes[j].label == 0) {
					nodes[j].label = k;
					k++;
					stack.add(nodes[j]);
					while(!stack.isEmpty()) {
						Node node = stack.get(0);
						boolean flag = false;
						for(int i = 0; i < node.avaiableLines.size(); i++) {
							Line line = node.avaiableLines.get(i);
							if((line.flag == 0) && (nodes[line.endPoint].label != 0)) {
								if(stack.contains(nodes[line.endPoint]))
									line.flag = 1;
								if((nodes[line.endPoint].flag == true) && (nodes[line.endPoint].label < node.label))
									line.flag = 2;
								if((nodes[line.endPoint].flag == true) && (nodes[line.endPoint].label > node.label))
									line.flag = 3;
								flag = true;
							}
							if((line.flag == 0) && (nodes[line.endPoint].label == 0)) {
								nodes[line.endPoint].label = k;
								k++;
								line.flag = 4;
								stack.add(nodes[line.endPoint]);
								flag = true;
							}
						}
						if(flag == false) {
							Node tmp = stack.pop();
							tmp.flag = true;
						}
					}
				}
			}*/
			for(int i = 0; i < count; i++) {
				if(nodes[i].label == 0)
					dfs(nodes[i], nodes);
			}
			for(int i = 0; i < count; i++) {
				out.write(nodes[i].label + " ");
			}
			out.close();
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
