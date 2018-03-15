import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] adjacencyMatrix = new int[n][n];
        int a,b;
        for (int i = 0; i < m; ++i){
            a = scanner.nextInt() - 1;
            b = scanner.nextInt() - 1;
            adjacencyMatrix[a][b] = adjacencyMatrix[b][a] = 1;
        }
        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (j != n-1)
                        fout.write(adjacencyMatrix[i][j] + " ");
                }
                fout.write(adjacencyMatrix[i][n-1]+"\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}