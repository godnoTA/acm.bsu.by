import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("input.txt"));
		int N = scan.nextInt();
		int matrix[][] = new int[N][N];
		boolean visits[] = new boolean[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				matrix[i][j] = scan.nextInt();
			}
		}
		int edgeNumber = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (matrix[i][j] == 1) {
					edgeNumber++;
				}
			}
		}
		if (edgeNumber != N - 1) {
			BufferedWriter buf = new BufferedWriter(new FileWriter("output.txt"));
			buf.write("No");
			buf.close();
			return;
		}
		ArrayList<Integer> itherations = new ArrayList<>();
		ArrayList<Integer> nextItherations = new ArrayList<>();
		visits[0] = true;
		for (int i = 1; i < N; i++) {
			if (matrix[0][i] == 1) {
				matrix[i][0] = 0;
				itherations.add(i);
				visits[i] = true;
			}
		}
		while (true) {
			for (int i = 0; i < itherations.size(); i++) {
				for (int j = 0; j < N; j++) {
					if (j == itherations.get(i)) {
						continue;
					}
					if (visits[j] && matrix[itherations.get(i)][j] == 1) {
						BufferedWriter buf = new BufferedWriter(new FileWriter("output.txt"));
						buf.write("No");
						buf.close();
						return;
					}
					if (matrix[itherations.get(i)][j] == 1) {
						matrix[j][itherations.get(i)] = 0;
						visits[j] = true;
						nextItherations.add(j);
					}
				}
			}
			if (nextItherations.size() == 0) {
				BufferedWriter buf = new BufferedWriter(new FileWriter("output.txt"));
				for (int k = 0; k < N; k++) {
					if (!visits[k]) {
						buf.write("No");
						buf.close();
						return;
					}
				}
				buf.write("Yes");
				buf.close();
				return;
			}
			itherations = nextItherations;
			nextItherations = new ArrayList<>();
		}
	}
}
