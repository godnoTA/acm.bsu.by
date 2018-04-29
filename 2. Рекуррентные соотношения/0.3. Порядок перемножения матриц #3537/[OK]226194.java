import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        int[][] dp = new int[228][228];
        int[][] matrix = new int[228][2];
        int s = in.nextInt();
        for(int i = 0; i < s; ++i) {
            matrix[i][0] = in.nextInt();
            matrix[i][1] = in.nextInt();
        }
        for(int i = 0; i < s; ++i) {
            dp[i][i] = 0;
            if (i != s - 1) {
                dp[i][i + 1] = matrix[i][0] * matrix[i][1] * matrix[i + 1][1];
            }
        }
        for (int i = s - 3; i >= 0; --i) {
            for (int j = i + 2; j < s; ++j) {
                int value = 2147483647;
                for (int k = i; k < j; ++k) {
                    int temp = dp[i][k] + dp[k + 1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1];
                    if(temp < value) {
                        value = temp;
                    }
                }
                dp[i][j] = value;
            }
        }
        out.println(dp[0][s - 1]);
        out.flush();
    }
}