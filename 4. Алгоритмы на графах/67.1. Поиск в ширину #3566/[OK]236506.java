

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static int p = 0;

    private void bfs(Queue<Integer> queue, boolean blocked[], int priority[], int mtr[][], int n, int v ){
        if (!blocked[v]) {
            queue.add(v);
            blocked[v] = true;
            p++;
            priority[v] = p;

            while (!queue.isEmpty()) {
                int vert = queue.poll();
                for (int i = 0; i < n; i++) {
                    if (!blocked[i] && mtr[vert][i] == 1) {
                        queue.add(i);
                        blocked[i] = true;
                        p++;
                        priority[i] = p;
                    }

                }
            }
        }

    }

    private void solve() throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new File("output.txt"));
        int n = in.nextInt();
        boolean blocked[] = new boolean[n];
        int priority[] = new int[n];
        int mtr[][] = new int[n][n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++){
            blocked[i] = false;
            priority[i] = 0;
            for (int j =0; j < n; j++){
                mtr[i][j] = in.nextInt();
            }

        }


        for (int i = 0; i < n; i++){
            bfs(queue, blocked,priority,mtr,n,i);
        }

        for (int i = 0; i < n - 1; i++){
            out.print(priority[i] + " ");
        }
        out.print(priority[n -1 ]);
        out.flush();

    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().solve();
    }
}
