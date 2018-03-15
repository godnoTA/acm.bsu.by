
import java.io.*;
import java.util.Scanner;

public class MatrixChain {

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

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new File("input.txt"));
        int n=in.nextInt();
        int[] test = new int[n+1];
        int i=0;
        test[i]=in.nextInt();
        i++;
        test[i]=in.nextInt();
        i++;
        while(in.hasNextInt())
        {
            in.nextInt();
            if(in.hasNext()){
            int m=in.nextInt();
            test[i]=m;
            i++;
        }
    }

        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        bw.write(String.valueOf(MatrixChain.multiplyOrder(test)));
      //  System.out.println(MatrixChain.multiplyOrder(test));
        bw.close();
    }
}