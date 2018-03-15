//package t70;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class T70 implements Runnable {

    public static void main(String[] args) throws Exception {
        new Thread(null, new T70(), "", 64 * 1024 * 1024).start();
    }

    int[][] mas1, mas2;
    byte[] a;
    int n;
    int m;

    @Override
    public void run() {
        try (Scanner s = new Scanner(new File("input.txt"))) {
            n = s.nextInt();
            m = s.nextInt();
            a = new byte[n];
            int su = 0;
            for (int i = 0; i < n; i++) {
                su += a[i] = s.nextByte();
            }
            mas1 = new int[m + 1][su + 1];
            mas2 = new int[m + 1][su + 1];
            for (int l = 0; l <= m; l++) {
                for (int sum = 0; sum <= su; sum++) {
                    mas1[l][sum] = sum * sum;
                }
            }
            for (int i = n - 1; i >= 1; i--) {
                for (int l = 0; l <= m; l++) {
                    for (int sum = 0; sum <= su; sum++) {
                        if (sum + a[i] > su) {
                            break;
                        }
                        if (l == 0) {
                            mas2[0][sum] = mas1[0][sum + a[i]];
                        } else {
                            mas2[l][sum] = Math.min(mas1[l][sum + a[i]], mas1[l - 1][a[i]] + sum * sum);
                        }
                    }
                }
                mas1 = mas2;
                mas2 = new int[m + 1][su + 1];
            }
        } catch (FileNotFoundException ex) {
        }
        int t = mas1[m][a[0]];
        for (int i = 0; i < n; i++) {
            t -= a[i] * a[i];
        }
        t /= 2;
        try (PrintWriter out = new PrintWriter("output.txt")) {
            out.print(t);
        } catch (FileNotFoundException ex) {
        }
    }

}
