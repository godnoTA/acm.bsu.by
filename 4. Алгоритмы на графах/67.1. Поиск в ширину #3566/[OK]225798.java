import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class Arg_12 {
	
	public static class Node {
		public ArrayList<Integer> avaiableNode;
		int label;
		boolean flag = false;

		Node() {
			this.avaiableNode = new ArrayList<Integer>();
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
			ArrayDeque<Node> queue = new ArrayDeque<Node>();
			for(int i = 0; i < count; i++) {
				nodes[i] = new Node();
			//	nodes[i].label = i + 1;
				for(int j = 0; j < count; j++) {
					int k = Integer.parseInt(in.next());
					if(k == 1)
						nodes[i].avaiableNode.add(j);
				}
			}
			int k = 1;
			for(int i = 0; i < count; i++) {
				if(nodes[i].flag == false) {					
					queue.add(nodes[i]);
					nodes[i].flag = true;				
					while(!queue.isEmpty()) {
						Node tmp = queue.pop();
						tmp.label = k;
						k++;
						
						for(int j = 0; j < tmp.avaiableNode.size(); j++) {
							if(nodes[tmp.avaiableNode.get(j)].flag == false) {
						//		nodes[tmp.avaiableNode.get(i)].label = tmp.label + 1;
								queue.add(nodes[tmp.avaiableNode.get(j)]);
								nodes[tmp.avaiableNode.get(j)].flag = true;
							}
						}
					}
				}
			}
			for(int i = 0; i < count; i++) {
				out.write(nodes[i].label + " ");
			}
			out.close();
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    /*    try {
	            FileReader fr = new FileReader("input.txt");
	            FileWriter fw = new FileWriter("output.txt");
	            Scanner sc = new Scanner(fr);
	            int n = sc.nextInt();
	            int matr[][] = new int[n][n];

	            for(int i = 0; i < n; i++)
	                for(int j = 0; j < n; j++)
	                    matr[i][j] = sc.nextInt();

	            boolean[] passed = new boolean[n];
	            int[] met = new int[n];
	            int k = 1;

	            ArrayList<Integer> q = new ArrayList<>();
	            for(int j = 0; j < n; j++) {
	                if(!passed[j]) {
	                    q.add(j);
	                    passed[j] = true;

	                    while (!q.isEmpty()) {
	                        int v = q.get(0);
	                        met[v] = k;
	                        k++;

	                        q.remove(0);
	                        for (int i = 0; i < n; i++)
	                            if (matr[v][i] == 1 && !passed[i]) {
	                                q.add(i);
	                                passed[i] = true;
	                            }
	                    }
	                }
	            }

	            for(int i = 0; i < n; i++)
	                fw.write(Integer.toString(met[i]) + ' ');

	            fw.close();


	        } catch (IOException e) {
	            e.printStackTrace();
	        }*/
	    }
}
