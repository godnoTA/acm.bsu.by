import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Matrix {

    public static int multiply(int[] p) {
        int n = p.length - 1;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) 
            dp[i][i] = 0;

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
        ArrayList<Integer> arrList=new ArrayList<Integer>();
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            while(sc.hasNext()){
                arrList.add(sc.nextInt());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int size=arrList.get(0)+1;
        int[] test = new int[size];
        test[0]=arrList.get(1);
        for (int i=1,j=1;i<size;i++,j++)
            test[i]=arrList.get(2*j);
        FileWriter writer;
        try {
            writer = new FileWriter("output.txt");
            writer.write(String.valueOf(Matrix.multiply(test)));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}