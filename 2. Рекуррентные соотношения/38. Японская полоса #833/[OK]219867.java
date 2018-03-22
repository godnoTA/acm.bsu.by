import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
 
public class Main {
    private BigInteger[][] c;
 
    private BigInteger c(int n, int k) {
        if (!c[n][k].equals(BigInteger.ZERO))
            return c[n][k];
        if (k == 0 || k == n)
            return c[n][k] = BigInteger.ONE;
        return c[n][k] = c(n-1, k-1).add(c(n-1, k));
    }
 
    private void solve() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("in.txt"));
        PrintWriter pw = new PrintWriter(new File("out.txt"));
        int n = sc.nextInt();
        int k = sc.nextInt();
        int s = 0;
        for (int i = 0; i < k; i++)
            s += sc.nextInt();
        if (n < k+s-1)
            pw.print(0);
        else {
            c = new BigInteger[n-s+2][k+1];
            for (int i = 0; i < n-s+2; i++)
                for (int j = 0; j < k+1; j++)
                    c[i][j] = BigInteger.ZERO;
            pw.print(c(n-s+1, k));
        }
        pw.flush();
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        new Main().solve();
    }
}