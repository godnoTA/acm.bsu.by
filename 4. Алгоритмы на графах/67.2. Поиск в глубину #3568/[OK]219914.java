import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        int n = Integer.parseInt(scanner.next());
        if (n >= 1 && n <= 100) {
            int mas[] = new int[n + 1];
            int matr[][] = new int[n + 1][n + 1];
            int i, j;
            for (i = 1; i < n + 1; ++i) {
                for (j = 1; j < n + 1; ++j) {
                    matr[i][j] = scanner.nextInt();
                }
            }
            for (int g = 1; g < n + 1; ++g) {
                if (mas[g] == 0) {
                    deepSearch(g, n, mas, matr);
                }
            }

            FileWriter fout = null;
            try {

                fout = new FileWriter("output.txt");
                for (i = 1; i < n + 1; ++i) {
                    fout.write(mas[i] + " ");
                }
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    static int any = 1;
    //static int n;
    public static void deepSearch (int hl, int n, int mas[], int matr[][]) {
        mas[hl] = any++;
        for (int i = 1; i < n + 1; ++i) {
            if (mas[i] == 0 ) {
                if (matr[hl][i] == 1) {
                    deepSearch(i, n, mas, matr);
                }
            }
        }
    }
}
