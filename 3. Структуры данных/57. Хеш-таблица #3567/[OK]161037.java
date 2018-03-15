import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Created by maxmxmx on 21.11.2016.
 */
public class Main {
    private static int m;
    private static int c;
    private static int n;
    private static Integer []x;
    private static int []res;
    private static LinkedHashSet<Integer> hs = new LinkedHashSet<>();

    public static void main(String[] args) throws  IOException {
        read();
        work();
        File fout = new File("output.txt");
        PrintStream ps = new PrintStream(fout);
        for (int k = 0; k < m; k++) {
            ps.print(res[k] + " ");
        }
    }

    public static void read() throws IOException{
        Scanner sc = new Scanner(new File("input.txt"));
        m = sc.nextInt();
        c = sc.nextInt();
        n = sc.nextInt();
        for(int i = 0; i < n; i++){
            hs.add(sc.nextInt());
        }
        x = hs.toArray(new Integer[hs.size()]);
        for(int i = 0; i < x.length; i++){
            System.out.println(x[i]);
        }
    }

    public static void work() {
        res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = -1;
        }
        int mm;
        for (int k = 0; k < x.length; k++) {
            for (int i = 0; i < m; i++) {
                mm = ((x[k] % m) + c * i) % m;
                if (res[mm] == -1) {
                    res[mm] = x[k];
                    break;
                }
            }
        }
    }
}
