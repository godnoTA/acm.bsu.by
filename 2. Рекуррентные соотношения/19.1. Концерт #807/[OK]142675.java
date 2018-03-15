import java.io.*;
import java.util.*;
 
public class Solution {
 
    public static void main(String[] args) {
        int N = 0, M = 0, D = 0;
        int[] musicLength = null;
        int[][] table = null;
 
        try (Scanner sc = new Scanner(new File("input.txt"))) {
 
            N = sc.nextInt();
            M = sc.nextInt();
            D = sc.nextInt();
            musicLength = new int[N + 1];
            table = new int[N + 1][M * D + 1];
 
            for (int i = 1; i <= N; i++)
                musicLength[i] = sc.nextInt();
        } catch (FileNotFoundException ex) {
        }
 
        for (int i = 1; i <= N; i++)
            for (int j = 1, val = 1; j <= M * D; j++, val = (val + 1 > D) ? 1 : val + 1)
                if (musicLength[i] <= val)
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - musicLength[i]] + 1);
                else
                    table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
 
        try (PrintWriter pw = new PrintWriter(new File("output.txt"))) {
            pw.println(table[N][M * D]);
        } catch (FileNotFoundException ex) {
        }
 
    }
}