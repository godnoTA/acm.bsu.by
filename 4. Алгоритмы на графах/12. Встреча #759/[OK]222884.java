import java.io.*;
import java.util.Scanner;
import java.util.stream.IntStream;
import static java.lang.Integer.min;

public class Main {
    public static void main(String[] args) {
            Scanner scanner;
            try {
                scanner = new Scanner(new File("input.in"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                return;
            }
            int n = Integer.parseInt(scanner.next());
            int m = Integer.parseInt(scanner.next());
            int adjacencyMatrix[][] = new int[n][n];
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        adjacencyMatrix[i][j] = 2 ^ 31 - 1;
                    }
                }
            }
                int u;
                int v;
                int weight;

            for (int i = 0; i < m; i++) {
                u = scanner.nextInt() - 1;
                v = scanner.nextInt() - 1;
                weight = scanner.nextInt();
                adjacencyMatrix[u][v] = weight;
                adjacencyMatrix[v][u] = weight;
            }

            for (int k = 0;k < n; ++k)
                for (int i = 0; i < n; ++i)
                    for (int j = 0; j < n; ++j)
                        adjacencyMatrix[i][j] = min(adjacencyMatrix[i][j],
                                adjacencyMatrix[i][k] + adjacencyMatrix[k][j]);
            int min_sum = Integer.MAX_VALUE;
            int houseNumber = -1;
            for (int i = 0; i < n; i++) {
                Integer current_sum = IntStream.of(adjacencyMatrix[i]).sum();
                if (current_sum < min_sum) {
                    min_sum = current_sum;
                    houseNumber = i + 1;
                }
            }

            FileWriter fout = null;
            try {
                fout = new FileWriter("output.out");
                FileOutputStream fos = new FileOutputStream(new File("output.out"));
                PrintStream ps = new PrintStream(fos);
                 ps.print(houseNumber);
                 ps.print(" ");
                 ps.print(min_sum);
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}