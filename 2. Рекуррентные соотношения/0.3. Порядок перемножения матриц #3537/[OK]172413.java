import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        int countMtr = sc.nextInt();
        long[] sizeMtr = new long[countMtr + 1];
        for (int i = 0; i < countMtr - 1; i++) {
            sizeMtr[i] = sc.nextInt();
            sc.nextInt();
        }
        sizeMtr[countMtr - 1] = sc.nextInt();
        sizeMtr[countMtr] = sc.nextInt();
        long[][] mtrMul = new long[countMtr][countMtr];
        for (int l = 1; l < countMtr; l++) {
            for (int i = 0; i < countMtr - l; i++) {
                mtrMul[i][i + l] = 2000000000;
                for (int k = i; k < (i + l); k++) {
                    mtrMul[i][i + l] = min(mtrMul[i][i + l], mtrMul[i][k] + mtrMul[k + 1][i + l] + sizeMtr[i] * sizeMtr[k + 1] * sizeMtr[i + l + 1]);

                }
            }
        }
        fw.write((int) mtrMul[0][countMtr - 1] + " ");
        fw.close();
        sc.close();
    }
}