import java.io.*;
import java.util.Scanner;


    public class MatrixMul 
    {

         public static void main(String[]args) throws FileNotFoundException {
                FileWriter file;
                int vectorSize = 0;
                int [] VectRe = null;
                int i1 = 1;
                String test;
                String[]Array;
             Scanner sc = new Scanner(new File("input.txt"));
             vectorSize= sc.nextInt();
             VectRe = new int[vectorSize+1];
             test=sc.nextLine();
             test=sc.nextLine();
             Array = test.split(" ");
             for(int i = 0; i<2;i++){
             VectRe[i]= Integer.parseInt(Array[i]);
         }
             while (sc.hasNextLine()) {
                 if(!((test=sc.nextLine()).isEmpty()))
                 Array = test.split(" ");
             i1++;
             VectRe[i1]=Integer.parseInt(Array[1]);
                                     }

             sc.close();
             ;
            try 
        {
            file = new FileWriter("output.txt");
            file.write("" + MatrixMul.m1(VectRe));
            file.close();
        } catch (IOException e) {}
    }
    public static int m1(int[] p)
    {
        int n = p.length - 1;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) 
        {
            dp[i][i] = 0;
        }
        for (int l = 2; l <= n; l++) {
            int i = 1;
            while (i <= n - l + 1) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
                }
                i++;
            }
        }
        return dp[1][n];
    }
}