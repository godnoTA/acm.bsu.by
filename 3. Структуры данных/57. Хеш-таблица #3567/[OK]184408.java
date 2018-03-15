import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        int m = sc.nextInt();
        int c = sc.nextInt();
        int N = sc.nextInt();
        int h = 0;
        int[] massive = new int[m];
        for (int i = 0; i < m; i++) {
            massive[i] = -1;
        }
        Set<Integer> list = new LinkedHashSet<>();
        while (sc.hasNextLong()) {
            list.add(sc.nextInt());
        }
        for (int item : list) {
            int x = item;
            for (int i = 0; i < m; i++) {
                h = (x % m + c * i) % m;
                if (massive[h] == -1) {
                    massive[h] = x;
                    break;
                }
            }
        }
        for (int j = 0; j < m - 1; j++) {
            pr.print(massive[j] + " ");
        }
        pr.print(massive[m - 1]);
        pr.close();
    }
}
