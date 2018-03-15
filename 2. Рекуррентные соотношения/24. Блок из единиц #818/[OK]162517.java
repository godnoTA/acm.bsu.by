import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Максим on 05.12.2016.
 */
public class Main {
    private static int n;
    private static int m;
    private static int max = 0;
    private static int max_n;
    private static int max_m;
    private static int [][]x;

    public static void main(String[] args) throws IOException{
        read();
        work();
        out();
    }

    public static void read() throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));
        n = sc.nextInt();
        m = sc.nextInt();
        x = new int [n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                x[i][j] = sc.nextInt();
            }
        }
    }

    public static void work(){
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(x[i][j] != 0){
                    x[i][j] = min(x[i - 1][j],x[i - 1][j - 1],x[i][j - 1]) + 1;
                }
            }
        }
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (x[i][j] > max) {
                    max = x[i][j];
                    max_n = i;
                    max_m = j;
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(x[i][j] == max && j < max_m){
                    max_n = i;
                    max_m = j;
                }
                else {
                    if(x[i][j] == max && max_m == j){
                        if(i < max_n){
                            max_n = i;
                            max_m = j;
                        }
                    }
                }
            }
        }

    }

    public static void out() throws IOException{
        PrintStream ps = new PrintStream(new File("out.txt"));
        ps.println(max);
        ps.println(max_m - max + 2);
        ps.print(max_n - max + 2);

    }

    public static int min(int a, int b, int c){
        if(a <= b){
            if(a <= c){
                return a;
            }
            else return c;
        }
        else {
            if(b < c){
                return b;
            }
            else return c;
        }
    }
}
