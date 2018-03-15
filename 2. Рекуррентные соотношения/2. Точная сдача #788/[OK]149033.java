import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    Scanner sc = new Scanner(new File("input.txt"));
        int n, m, s;
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();
        int sum[] = new int[10001];
        sum[0] = 1;
        int change[] = new int[10001];
        change[0] = 1;
        for (int i = 0; i < n; ++i) {
            int tmp;
            tmp = sc.nextInt();
            for (int j = 0; j < 10001; ++j)
                if (sum[j] > 0 && sum[j] < i+2)
                    if (sum[j+tmp] == 0)
                        sum[j+tmp] = i+2;
        }
        for (int i = 0; i < m; ++i) {
            int tmp;
            tmp = sc.nextInt();
            for (int j = 0; j < 10001; ++j)
                if (change[j] > 0 && change[j] < i+2)
                    if (change[j+tmp] == 0)
                        change[j+tmp] = i+2;
        }
        sc.close();
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        for (int i = s; i < 10001; ++i)
            if (sum[i] > 0 && change[i-s] > 0) {
                pw.print("Yes");
                pw.close();
                System.exit(0);
            }
        pw.print("No");
        pw.close();
    }
}
