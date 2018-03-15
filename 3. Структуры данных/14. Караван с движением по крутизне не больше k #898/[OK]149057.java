import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

class Point{
    public Integer x;
    public Integer y;
    public Integer step;
    Point(Integer x, Integer y, Integer step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }
}

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("in.txt"));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int matrix[][] = new int[n][m];
        boolean used[][] = new boolean[n][m];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                matrix[i][j] = sc.nextInt();
        int k = sc.nextInt(), x1 = sc.nextInt()-1, y1 = sc.nextInt()-1,
                x2 = sc.nextInt()-1, y2 = sc.nextInt()-1;
        sc.close();
        PrintWriter pw = new PrintWriter(new File("out.txt"));
        LinkedList <Point> queue = new LinkedList<>();
        queue.add(new Point(x1, y1, 0));
        used[x1][y1] = true;
        while (!queue.isEmpty()) {
            Point v = queue.remove();
            if (v.x > 0)
                if (!used[v.x-1][v.y] && Math.abs(matrix[v.x-1][v.y] - matrix[v.x][v.y]) <= k) {
                    if (v.x-1 == x2 && v.y == y2) {
                        pw.println("Yes");
                        pw.print(v.step + 1);
                        pw.close();
                        System.exit(0);
                    }
                    used[v.x-1][v.y] = true;
                    queue.add(new Point(v.x-1, v.y, v.step+1));
                }
            if (v.x < n-1)
                if (!used[v.x+1][v.y] && Math.abs(matrix[v.x+1][v.y] - matrix[v.x][v.y]) <= k) {
                    if (v.x+1 == x2 && v.y == y2) {
                        pw.println("Yes");
                        pw.print(v.step+1);
                        pw.close();
                        System.exit(0);
                    }
                    used[v.x+1][v.y] = true;
                    queue.add(new Point(v.x+1, v.y, v.step+1));
                }
            if (v.y > 0)
                if (!used[v.x][v.y-1] && Math.abs(matrix[v.x][v.y-1] - matrix[v.x][v.y]) <= k) {
                    if (v.x == x2 && v.y-1 == y2) {
                        pw.println("Yes");
                        pw.print(v.step+1);
                        pw.close();
                        System.exit(0);
                    }
                    used[v.x][v.y-1] = true;
                    queue.add(new Point(v.x, v.y-1, v.step+1));
                }
            if (v.y < m-1)
                if (!used[v.x][v.y+1] && Math.abs(matrix[v.x][v.y+1] - matrix[v.x][v.y]) <= k) {
                    if (v.x == x2 && v.y+1 == y2) {
                        pw.println("Yes");
                        pw.print(v.step+1);
                        pw.close();
                        System.exit(0);
                    }
                    used[v.x][v.y+1] = true;
                    queue.add(new Point(v.x, v.y+1, v.step+1));
                }
        }
        pw.print("No");
        pw.close();
    }
}