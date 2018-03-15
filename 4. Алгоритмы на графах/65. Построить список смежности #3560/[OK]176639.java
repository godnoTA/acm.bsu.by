import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static public class Top{
		ArrayList<Integer> edges;
		public Top(){
			edges = new ArrayList<>();
		}
		public void addEdge(int edge){
			edges.add(edge);
		}
		public void printEdges(BufferedWriter buf) throws IOException{
			buf.write(Integer.toString(edges.size()));
			for(int i = 0;i < edges.size();i++){
				buf.write(" " + Integer.toString(edges.get(i)));
			}
		}
	}
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("input.txt"));
		int N = scan.nextInt();
		int M = scan.nextInt();
		Top tops[] = new Top[N + 1];
		for(int i = 1;i <= N;i++){
			tops[i] = new Top();
		}
		for(int i = 1;i <= M;i++){
			int num1 = scan.nextInt();
			int num2 = scan.nextInt();
			tops[num1].addEdge(num2);
			tops[num2].addEdge(num1);
		}
		BufferedWriter buf = new BufferedWriter(new FileWriter("output.txt"));
		for(int i = 1;i <= N;i++){
			tops[i].printEdges(buf);
			buf.newLine();
		}
		buf.close();
	}

}
