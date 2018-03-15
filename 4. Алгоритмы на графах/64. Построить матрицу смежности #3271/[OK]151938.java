/**
 * Created by VORON on 03.06.2016.
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by vlad on 22.05.2016.
 */
public class matrix {
    static Scanner sc;
    static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        sc = new Scanner(new File("input.txt"));
        pw = new PrintWriter(new File("output.txt"));
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int t[][] = new int [n][n];
        while(sc.hasNextInt()){
            int k = sc.nextInt();
            int l = sc.nextInt();
            t[k-1][l-1] = 1 ;
            t[l-1][k-1] = 1;
        }

        sc.close();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                sb.append(t[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        pw.print(sb);
        pw.close();
    }
}