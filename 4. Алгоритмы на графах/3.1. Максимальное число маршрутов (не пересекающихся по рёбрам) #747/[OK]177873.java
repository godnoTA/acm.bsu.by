import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TEST {
	static int N;
	static int V, W;

	static void newAB(int[] B, int[][] A) {
		int ab = 1;
		int k = W - 1;
		while (k != V - 1) {
			A[B[k]][k] -= ab;
			A[k][B[k]] += ab;
			k = B[k];
		}
	}

	static void bfs(int k, int[][] A, int[] B) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(--k);
		while (!que.isEmpty()) {
			for (int i = 0; i < N; i++) {
				if (A[que.peek()][i] != 0) {
					if (B[i] == -1) {
						B[i] = que.peek();
						que.add(i);
					}
					if (i == W - 1)
						return;
				}
			}
			que.remove();
		}
	}

	static int countRouts(int[][] A) {
		int[] B = new int[N];
		int count = 0;
		for (;;) {
			for (int i = 0; i < N; i++)
				B[i] = -1;
			bfs(V, A, B);
			if (B[W - 1] == -1)
				break;
			newAB(B, A);
		}
		for (int i = 0; i < N; i++)
			if (A[i][V - 1] == 2)
				count++;
		return count;
	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));
		int M;
		N = in.nextInt();
		M = in.nextInt();
		int[][] A = new int[N][M];
		int count;
		for (int i = 0; i < N; i++) {
			int n = 0;
			for (;;) {
				n = in.nextInt();
				if (n != 0)
					A[i][n - 1] = A[n - 1][i] = 1;
				else
					break;
			}
		}
		V = in.nextInt();
		W = in.nextInt();
		count = countRouts(A);
		out.print(count);
		out.close();
	}
}
