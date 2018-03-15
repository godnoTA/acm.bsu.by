//package t03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class T03 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        int s = in.nextInt();
        int[] ms = new int[s + 1];
        for (int i = 0; i < s; i++) {
            ms[i] = in.nextInt();
            ms[i + 1] = in.nextInt();
        }
        in.close();
        long tabl[][] = new long[s][];
        for (int i = 0; i < s; i++) {
            tabl[i] = new long[s - i];
        }
        for (int j = 1; j < s; j++) {
            tabl[j - 1][1] = ms[j - 1] * ms[j] * ms[j + 1];
            for (int i = j - 2; i >= 0; i--) {
                long t = tabl[i + 1][j - i - 1] + ms[i] * ms[i + 1] * ms[j + 1];
                for (int k = 1; k < j - i; k++) {
                    long e = tabl[i][k] + tabl[i + k + 1][j - i - k - 1];
                    e += ms[i] * ms[i + k + 1] * ms[j + 1];
                    if (e < t) {
                        t = e;
                    }
                }
                tabl[i][j - i] = t;
            }
        }
        PrintWriter out = new PrintWriter(new File("output.txt"));
        out.print(tabl[0][s - 1]);
        out.close();
    }

}
