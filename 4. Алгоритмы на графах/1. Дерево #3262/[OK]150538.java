import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main{

    static Scanner sc;
    static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        sc = new Scanner(new File("input.txt"));
        pw = new PrintWriter(new File("output.txt"));
        int n = sc.nextInt();
        int g[][] = new int[n][n];
        int parent[] = new int[n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                g[i][j] = sc.nextInt();
        sc.close();
        boolean used[] = new boolean[n];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(0);
        used[0] = true;
        parent[0] = 0;
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < n; ++i) {
                if (parent[u] != i && g[u][i] == 1) {
                    if (used[i]) {
                        pw.print("No");
                        pw.close();
                        System.exit(0);
                    } else {
                        used[i] = true;
                        parent[i] = u;
                        queue.push(i);
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                pw.print("No");
                pw.close();
                System.exit(0);
            }
        }
        pw.print("Yes");
        pw.close();
    }
}
