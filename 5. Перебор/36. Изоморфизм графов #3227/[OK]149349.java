import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Graph {
	
	public static boolean answer = false;
	
	public static PrintWriter writer;
	
	public static Scanner sc;
	
	static {
		try {
			sc = new Scanner(new File("input.txt"));
			writer = new PrintWriter("output.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int[][] adjMatrix;
	
	public int numOfEdges;

	public int numOfVerticies;
	
	public Graph(int n) {
		adjMatrix = new int[n][n];
		numOfVerticies = n;
	}
	
	public static boolean processible(Graph a, Graph b) {
		if(a.numOfVerticies != b.numOfVerticies)
			return false;
		if(a.numOfEdges != b.numOfEdges)
			return false;
		int aDeg[] = a.getDegrees();
		int bDeg[] = b.getDegrees();
		for(int i = 0; i < aDeg.length; i++) {
			if(aDeg[i] != bDeg[i])
				return false;
		}
		return true;
	}
	
	public static boolean isEqual(Graph a, Graph b) {
		for(int i = 0; i < a.numOfVerticies; i++) {
			for(int j = 0; j <= i; j++) {
				if(a.adjMatrix[i][j] != b.adjMatrix[i][j])
					return false;
			}
		}
		return true;
	}
	
	public void add(int a, int b) {
		adjMatrix[a][b] = 1;
		adjMatrix[b][a] = 1;
	}
	
	public void change(int a, int b) {
		boolean equal = true;
		for(int i = 0; i < adjMatrix[a].length; i++) {
			if(adjMatrix[a][i] != adjMatrix[b][i]) {
				equal = false;
				break;
			}
		}
		if(equal)
			return;
		int[] tmp = adjMatrix[a];
		adjMatrix[a] = adjMatrix[b];
		adjMatrix[b] = tmp;
		for(int i = 0; i < numOfVerticies; i++) {
			int tmpl = adjMatrix[i][a];
			adjMatrix[i][a] = adjMatrix[i][b];
			adjMatrix[i][b] = tmpl;
		}
	}
	
	public int[] getDegrees() {
		int[] array = new int[numOfVerticies];
		Vector<Integer> vector = new Vector<Integer>();
		for(int i = 0; i < numOfVerticies; i++) {
			vector.addElement(getDegree(i));
		}
		vector.sort(null);
		for(int i = 0; i < numOfVerticies; i++) {
			array[i] = vector.elementAt(i);
		}
		return array;
	}
	
	public void print() {
		for(int[] string : adjMatrix) {
			for( int el : string) {
				System.out.printf("%3d", el);
			}
			System.out.println();
		}
	}
	
	public Graph subGraph(int k) {
		if(k > numOfVerticies)
			return null;
		Graph res = new Graph(k);
		for(int i = 0; i < k; i++) {
			for(int j = 0; j < k; j++) {
				if(adjMatrix[i][j] != 0) {
					res.add(i, j);
				}
			}
		}
		return res;
	}
	
	public int getDegree(int v) {
		int counter = 0; 
		for(int i = 0; i < numOfVerticies; i++) {
			if(adjMatrix[v][i] != 0)
				counter++;
		}
		return counter;
	}
	
	public List<Integer> getVerticiesByDegree(int deg) {
		List<Integer> listToReturn = new LinkedList<Integer>();
		for(int i = 0; i < numOfVerticies; i++) {
			if(getDegree(i) == deg) {
				listToReturn.add(i);
			}
		}
		return listToReturn;
	}
	
	public void sort() {
		int[] degrees = getDegrees();
		int k = 0;
		while(k < degrees.length) {
			int curr = degrees[k];
			List<Integer> list = getVerticiesByDegree(curr);
			for(int j = k; j < list.size() + k; j++) {
				change(j, list.get(j - k));
			}
			k += list.size();
		}
	}
	
	public void deleteVertex(int a) {
		int _numOfEdges = 0;
		for(int i = 0; i < numOfVerticies; i++) {
			if(adjMatrix[a][i] != 0)
				_numOfEdges++;
		}
		int[][] newMatrix = new int[numOfVerticies -1 ][numOfVerticies - 1];
		int i2 = 0, j2 = 0;
		for(int i = 0; i < numOfVerticies; i++) {
			if(i == a)
				continue;
			for(int j = 0; j < numOfVerticies; j++) {
				if(j == a)
					continue;
				newMatrix[i2][j2] = adjMatrix[i][j];
				j2++;
			}
			j2 = 0;
			i2++;
		}
		adjMatrix = newMatrix;
		numOfVerticies--;
		numOfEdges -= _numOfEdges;
	}
	
	public static boolean isMatch(Graph a, Graph b, ArrayList<Integer> hm, int k, int v) {
		if(!(a.getDegree(k) == b.getDegree(v)))
			return false;
		for(int i = 0; i < k; i++) {
			if(a.adjMatrix[k][i] != b.adjMatrix[v][hm.get(i)])
				return false;
		}
		return true;
	}

	public static void isIs(Graph a, Graph b, ArrayList<Integer> hm, int k) {
		if(k == a.numOfVerticies) {
			answer = true;
		}
		if(answer) {
			return;
		}
		for(int i = 0; i < b.numOfVerticies; i++) {
			if(hm.contains(i)) {
				continue;
			} else {
				if(isMatch(a, b, hm, k, i)) {
					hm.set(k, i);
					isIs(a, b, hm, k+1);
					if(answer) {
						return;
					} else {
						hm.set(k, -1);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Graph g1, g2;
		int n1, n2, m1, m2;
		n1 = sc.nextInt();
		g1 = new Graph(n1);
		m1 = sc.nextInt();
		for(int i = 0; i < m1; i++) {
			int a, b;
			a = sc.nextInt();
			b = sc.nextInt();
			g1.add(a, b);
		}
		n2 = sc.nextInt();
		g2 = new Graph(n2);
		m2 = sc.nextInt();
		for(int i = 0; i < m2; i++) {
			int a, b;
			a = sc.nextInt();
			b = sc.nextInt();
			g2.add(a, b);
		}
		if(!processible(g1, g2)) {
			writer.println("NO");
		} else {
			ArrayList<Integer> hm = new ArrayList<Integer>();
			for(int i = 0; i < g1.numOfVerticies; i++) {
				hm.add(-1);
			}
			isIs(g1, g2, hm, 0);
			if(answer) {
				writer.println("YES");
			} else {
				writer.println("NO");
			}
		}
		writer.close();
	}

}
