import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int point = 1;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));

        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        int[] points = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++)
            if (points[i] == 0) {
                points[i] = point++;
                dfs(arr, i, points);
            }

        FileWriter fw = new FileWriter("output.txt");
        for (int i : points)
            fw.append(String.valueOf(i)).append(" ");
        sc.close();
        fw.close();
    }

    static void dfs(int[][] arr, int v, int points[]) {
        for (int j = 0; j < arr[v].length; j++)
            if (arr[v][j] == 1 && points[j] == 0) {
                points[j] = point++;
                dfs(arr, j, points);
            }
    }
}