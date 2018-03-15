import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
 
public class Main {
    private int f[][][];
    private boolean set[][][];
    private int q[];
    private int sum[][];
 
    private int f(int k, int n, int s) {
        if (set[k][n][s])
            return f[k][n][s];
        set[k][n][s] = true;
        if (n == 1)
            f[k][n][s] = 0;
        else if (k == 0)
            f[k][n][s] = f(k, n-1, s+1);
        else
            f[k][n][s] = Math.min(f(k, n-1, s+1), f(k-1, n-1, 0));
        if (s > 0)
            f[k][n][s] += q[n]*sum[n+1][n+s];
        return f[k][n][s];
    }
 
    private void solve() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        int n = sc.nextInt(), m = sc.nextInt();
 
        q = new int[n+1];
        for (int i = 1; i <= n; i++)
            q[i] = sc.nextInt();
 
        sum = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            sum[i][i] = q[i];
            for (int j = i+1; j <= n; j++)
                sum[i][j] = sum[i][j-1] + q[j];
        }
 
        f = new int[m+1][n+1][n+1];
        set = new boolean[m+1][n+1][n+1];
        pw.print(f(m, n, 0));
        pw.flush();
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        new Main().solve();
    }
}