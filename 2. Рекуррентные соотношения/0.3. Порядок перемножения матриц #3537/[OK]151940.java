import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class MatrixChain {

    // Динамическое программирование
    // Уравнение Беллмана

    public static int multiplyOrder(int[] p) {
        int n = p.length - 1;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][i] = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileInputStream("input.txt"));
            System.setOut(new PrintStream("output.txt"));

            int n = sc.nextInt();
            int[] mas = new int[n + 1];

            mas[0] = sc.nextInt();

            for (int i = 1; i < n + 1; i++) {
                mas[i] = sc.nextInt();
                if (i != n)
                    sc.nextInt();
            }

            System.out.print(MatrixChain.multiplyOrder(mas));
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
