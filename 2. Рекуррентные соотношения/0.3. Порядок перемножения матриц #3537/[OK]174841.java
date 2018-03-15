import java.io.*;
import java.util.Scanner;

/**
 * Created by Andrey Belski on 10.03.2017.
 */
public class Work{

    public static void main(String[]args){
        FileWriter file;
        int vectorSize = 0;
        int [] vectorReq = null;
        int num = 1;
        String test;
        String[]arrT;
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            vectorSize= sc.nextInt();
            vectorReq = new int[vectorSize+1];
            test=sc.nextLine();
            test=sc.nextLine();
            arrT = test.split(" ");
            for(int i = 0; i<2;i++){
                vectorReq[i]= Integer.parseInt(arrT[i]);
            }
            while (sc.hasNextLine()) {
                if(!((test=sc.nextLine()).isEmpty()))
                    arrT = test.split(" ");
                num++;
                vectorReq[num]=Integer.parseInt(arrT[1]);
            }
            sc.close();
        } catch (FileNotFoundException e) {};
        try {
            file = new FileWriter("output.txt");
            file.write("" + Work.mO(vectorReq));
            file.close();
        } catch (IOException e) {}
    }
    public static int mO(int[] p){
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
}