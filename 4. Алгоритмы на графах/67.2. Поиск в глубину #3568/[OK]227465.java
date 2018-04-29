import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int metka = 1;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        int[] metki = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++)
            if (metki[i] == 0) {
                metki[i] = metka++;
                dfs(arr, i, metki);
            }

        FileWriter fw = new FileWriter("output.txt");
        for (int i : metki)
            fw.append(String.valueOf(i)).append(" ");
        sc.close();
        fw.close();
    }

    static void dfs(int[][] arr, int v, int metki[]) {
        for (int j = 0; j < arr[v].length; j++)
            if (arr[v][j] == 1 && metki[j] == 0) {
                metki[j] = metka++;
                dfs(arr, j, metki);
            }
    }
}