import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        int n = in.nextInt();
        int[] g = new int[n];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                int v = in.nextInt();
                if(v == 1) {
                    g[j] = i + 1;
                }
            }
        }
        for(int i : g) {
            out.print(i + " ");
        }
        out.flush();
    }
}