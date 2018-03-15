import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class IsTreeTask {

    static void dfs(int i, int[][] arr, boolean[] col) {
        if (col[i]) {
            return;
        }
        col[i] = true;
        for (int j = 0; j < arr.length; ++j) {
            if (arr[i][j] != 0) {
                dfs(j, arr, col);
            }
        }
    }

    static boolean isTree(int[][] arr) {

        boolean[] col = new boolean[arr.length];

        dfs(0, arr, col);
        for (int i = 0; i < arr.length; ++i) {
            if (!col[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc;
        try {
            sc = new Scanner(new FileInputStream("input.txt"));
            int n = sc.nextInt();
            int[][] arr = new int[n][n];

            int edges = 0;
            for (int i = 0; i < arr.length; ++i) {
                for (int j = 0; j < arr.length; ++j) {
                    arr[i][j] = sc.nextInt();
                    if (arr[i][j] != 0) {
                        edges++;
                    }

                }

            }


            System.setOut(new PrintStream("output.txt"));
            if (edges % 2 == 0 && edges / 2 == n - 1)
                System.out.print(isTree(arr) ? "Yes" : "No");
            else
                System.out.print("No");

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}