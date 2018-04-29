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
        int m = in.nextInt();
        Vector<Vector<Integer>> g = new Vector<>();
        for(int i = 0; i < n; ++i) {
            Vector<Integer> temp = new Vector<>();
            g.add(temp);
        }
        for(int i = 0; i < m; ++i) {
            int v = in.nextInt();
            int u = in.nextInt();
            g.get(v - 1).add(u);
            g.get(u - 1).add(v);
        }
        for(int i = 0; i < n; ++i) {
            out.print(g.get(i).size());
            for(Integer j : g.get(i)) {
                out.print(" " + j);
            }
            out.println("");
        }
        out.flush();
    }
}