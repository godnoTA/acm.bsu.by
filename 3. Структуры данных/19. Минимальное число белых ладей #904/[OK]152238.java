import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

class Point{
    public Integer x;
    public Integer y;
    Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    private static void Fill(int n, int m, int sx, int sy, boolean used[][]) {
        LinkedList <Point> queue = new LinkedList<>();
        queue.add(new Point(sx,sy));
        used[sx][sy] = true;
        while (!queue.isEmpty()) {
            Point v = queue.remove();
            if (v.x > 0)
                if (!used[v.x - 1][v.y]) {
                    used[v.x - 1][v.y] = true;
                    queue.add(new Point(v.x - 1, v.y));
                }

            if (v.x < n-1)
                if (!used[v.x+1][v.y]) {
                    used[v.x+1][v.y] = true;
                    queue.add(new Point(v.x+1, v.y));
                }

            if (v.y > 0)
                if (!used[v.x][v.y-1]) {
                    used[v.x][v.y-1] = true;
                    queue.add(new Point(v.x, v.y-1));
                }

            if (v.y < m-1)
                if (!used[v.x][v.y+1]) {
                    used[v.x][v.y+1] = true;
                    queue.add(new Point(v.x, v.y+1));
                }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("in.txt"));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int count = 1;
        int matrix[][] = new int[n][m];
        boolean used[][] = new boolean[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                matrix[i][j] = sc.nextInt();
                if (matrix[i][j] == -1) {
                    used[i][j] = true;
                }
            }
        sc.close();
        PrintWriter pw = new PrintWriter(new File("out.txt"));

        int ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                if (!used[i][j]) {
                    Fill(n, m, i, j, used);
                    ++ans;
                }
                //System.out.println(used[i][j]);
            }
        pw.print(Integer.toString(ans));
        pw.close();
    }
}