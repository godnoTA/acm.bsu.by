import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MatrixChain {
    public static int multiplyMatrix(ArrayList<Integer> p) {
        int n = p.size() - 1;
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
                            dp[i][k] + dp[k + 1][j] + p.get(i - 1) * p.get(k) * p.get(j));
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        try{
            ArrayList<Integer> sizeMatrix = new ArrayList<>();

            Scanner in = new Scanner(new File("input.txt"));
            int n = in.nextInt();

            for(int i=0; i<n-1; i++){
                sizeMatrix.add(in.nextInt());
                in.nextInt();
            }
            sizeMatrix.add(in.nextInt());
            sizeMatrix.add(in.nextInt());

            PrintWriter out = new PrintWriter("output.txt");
            out.print(MatrixChain.multiplyMatrix(sizeMatrix));
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}