import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] g = new int[420][228];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                g[i][j] = 0;
            }
        }
        for(int i = 0; i < m; ++i) {
            int v = in.nextInt();
            int u = in.nextInt();
            --v;
            --u;
            g[v][u] = 1;
            g[u][v] = 1;
        }
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                out.print(g[i][j] + " ");
            }
            out.println("");
        }
        out.flush();
    }
}