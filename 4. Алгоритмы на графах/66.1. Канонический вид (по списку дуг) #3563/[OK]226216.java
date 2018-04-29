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
        for(int i = 0; i < n - 1; ++i) {
            int v = in.nextInt();
            int u = in.nextInt();
            g[u - 1] = v;
        }
        for(int i : g) {
            out.print(i + " ");
        }
        out.flush();
    }
}