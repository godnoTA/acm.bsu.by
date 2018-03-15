import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by vlad on 22.05.2016.
 */
public class Main {
    static Scanner sc;
    static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        sc = new Scanner(new File("input.txt"));
        pw = new PrintWriter(new File("output.txt"));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int a = 1;
        int b = 1;
        int c = 1;
        int z= 0;
        int x=0;
        int t[][] = new int [n][n];
        while(sc.hasNextInt()){
           int k = sc.nextInt();
           int l = sc.nextInt();
            if( k > l){
                int tmp = k;
                k = l;
                l = tmp;
            }
            t[k-1][l-1] +=1;
        }

        sc.close();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if( i == j && t[i][j] != 0){
                    z = 1;
                }
                if( t[i][j] > 1){
                   x = 1;
                }
            }
        }
        if (x == 1){
            a = 0;
            b = 1;
            c = 1;
        }
        if(z == 1){
            a = 0;
            b = 0;
            c = 1;
        }
        if(a == 1){
            pw.print("Yes" + "\n");
        }
        else{
            pw.print("No" + "\n");
        }
if(b == 0) {
    pw.print("No" + "\n");
}
        else{
    pw.print("Yes" + "\n");
        }
        if(c == 0) {
            pw.print("No" + "\n");
        }
        else {
            pw.print("Yes" + "\n");
        }
        pw.close();
    }
}