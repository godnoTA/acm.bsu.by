import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class Search {
    private int[][] Matrix;
    private int n;
    private boolean[] visited;
    private int[] result;
    private int counter = 1;

    public Search(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        n = sc.nextInt();
        Matrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                Matrix[i][j] = sc.nextInt();
        visited = new boolean[n];
        result = new int[n];
    }


    private void dfs(int start) {
        visited[start] = true;
        result[start] = counter++;
        for (int i = 0; i < n; i++)
            if (Matrix[start][i] == 1 && !visited[i])
                dfs(i);
    }

    public void dfsStart() {
        for (int i = 0; i < n; i++)
            if (!visited[i])
                dfs(i);
    }

    public void showResult(String path) throws FileNotFoundException {
        PrintStream ps = new PrintStream(path);
        for (int item : result) {
            ps.print(item);
            ps.print(" ");
        }
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Search obj = new Search("input.txt");
        obj.dfsStart();
        obj.showResult("output.txt");
    }
}