import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

    static int[] met = new int[228];
    static int k = 0;

    static void dfs(int v, int[][] g, int n) {
        met[v] = ++k;
        for(int i = 0; i < n; ++i) {
            if(g[v][i] == 1 && met[i] == 0) {
                dfs(i,g,n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        int n = in.nextInt();
        int[][] g = new int[228][228];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                g[i][j] = in.nextInt();
            }
        }
        for(int i = 0; i < n; ++i) {
            if(met[i] == 0) {
                dfs(i, g, n);
            }
        }
        for(int i = 0; i < n; ++i) {
            out.print(met[i] + " ");
        }
        out.flush();
    }
}