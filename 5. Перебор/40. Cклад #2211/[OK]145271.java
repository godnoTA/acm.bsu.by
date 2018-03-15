import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Depot {
	
	public static Scanner sc;
	
	public static PrintWriter writer;
	
	public static Deque<Integer> stack;
	
	static {
		try {
			stack = new LinkedList<Integer>();
			sc = new Scanner(new File("input.txt"));
			writer = new PrintWriter("output.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static int findToRemove(Integer[][] a, int i) {
		int toRemove = a[i][a[i][0]];
		for(int j = i-1; j >=1; j--) {
			int x = a[j][0];
			for(int k = 1; k < a[j][0]; k++) {
				if(a[j][k] < toRemove && a[j][k+1] > toRemove) {
					x = k;
				}
			}
			int tmp = a[j][x];
			a[j][x] = toRemove;
			toRemove = tmp;
		}
		return toRemove;
	}
	
	static void add(Integer[][] a, int next) {
		int i = 1;
		while(true) {
			int j = 1;
			while(j <= a[i][0] && a[i][j] < next) {
				j++;
			}
			if(a[i][0] != 0 && j <= a[i][0]) {
				int tmp = a[i][j];
				a[i][j] = next;
				next = tmp;
				i++;
			} else {
				a[i][0]++;
				a[i][a[i][0]] = next;
				return;
			}
		}
	}
	
	static void process(Integer[][] a, int n) {
		if(a[1][0] == 0) {
			writeStack();
		}
		for(int i = n; i >= 1; i--) {
			if(a[i][0] != 0 && a[i+1][a[i][0]] == 0) {
				int next = findToRemove(a, i);
				stack.push(next);
				a[i][a[i][0]] = 0;
				a[i][0]--;
				process(a, n);
				next = stack.pop();
				add(a, next);
			}
		}
	}
	
	static void writeStack() {
		Iterator<Integer> it = stack.iterator();
		if(it.hasNext()) {
			writer.print(it.next());
		}
		while(it.hasNext()) {
			writer.print(" " + it.next());
		}
		writer.println();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Integer[][] a = new Integer[14][14];
		for(int i = 0; i <= 13; i++) {
			for(int j = 0; j <= 13; j++) {
				a[i][j] = 0;
			}
		}
		int n = sc.nextInt();
		for(int i = 1; i <= n; i++) {
			a[i][0] = sc.nextInt();
			for(int j = 1; j <= a[i][0]; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		process(a, n);
		sc.close();
		writer.close();
	}

}
