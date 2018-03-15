import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Circle[] circles;

	public class Couple {
		int x;
		int y;

		public Couple(int _x, int _y) {
			x = _x;
			y = _y;
		}
	}

	public class Circle {
		int color;
		ArrayList<Integer> edges;
		ArrayList<Integer> edgeColors;

		public Circle(int color) {
			this.color = color;
			edges = new ArrayList<Integer>();
			edgeColors = new ArrayList<Integer>();
		}

		public void addEdge(int top, int color) {
			edges.add(top);
			edgeColors.add(color);
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("input.in"));
		Main main = new Main();
		int N = scan.nextInt();
		int L = scan.nextInt();
		int K = scan.nextInt();
		int Q = scan.nextInt();
		circles = new Circle[N + 1];
		for (int i = 1; i <= N; i++) {
			circles[i] = main.new Circle(scan.nextInt());
		}
		if (N == 1 || N == 0) {
			BufferedWriter buf = new BufferedWriter(new FileWriter("output.out"));
			buf.write("Yes");
			buf.newLine();
			buf.write("0");
			buf.close();
			return;
		}
		int M = scan.nextInt();
		int number;
		for (int i = 0; i < M; i++) {
			number = scan.nextInt();
			circles[number].addEdge(scan.nextInt(), scan.nextInt());
		}
		boolean wisited[][] = new boolean[N + 1][N + 1];
		wisited[L][K] = true;
		int length = 0;
		ArrayList<Couple> itherations = new ArrayList<Couple>();
		ArrayList<Couple> coordinates = new ArrayList<Couple>();
		itherations.add(main.new Couple(L, K));
		while (true) {
			for (int i = 0; i < itherations.size(); i++) {
				int _L = itherations.get(i).x;
				int _K = itherations.get(i).y;
				for (int j = 0; j < circles[_L].edges.size(); j++) {
					if ((circles[_L].edgeColors.get(j) == circles[_K].color) && (circles[_L].edges.get(j) != _K && !wisited[circles[_L].edges.get(j)][K])) {
						coordinates.add(main.new Couple(circles[_L].edges.get(j), _K));
						wisited[circles[_L].edges.get(j)][K] = true;
					}
				}
				for (int j = 0; j < circles[_K].edges.size(); j++) {
					if ((circles[_K].edgeColors.get(j) == circles[_L].color) && (circles[_K].edges.get(j) != _L && !wisited[_L][circles[_K].edges.get(j)])) {
						coordinates.add(main.new Couple(_L, circles[_K].edges.get(j)));
						wisited[_L][circles[_K].edges.get(j)] = true;
					}
				}
			}
			if (coordinates.size() == 0) {
				BufferedWriter buf = new BufferedWriter(new FileWriter("output.out"));
				buf.write("No");
				buf.close();
				return;
			}
			length++;
			for (int k = 0; k < coordinates.size(); k++) {
				if (coordinates.get(k).x == Q || coordinates.get(k).y == Q) {
					BufferedWriter buf = new BufferedWriter(new FileWriter("output.out"));
					buf.write("Yes");
					buf.newLine();
					buf.write(Integer.toString(length));
					buf.close();
					return;
				}
			}
			itherations = coordinates;
			coordinates = new ArrayList<Couple>();
		}
	}
}