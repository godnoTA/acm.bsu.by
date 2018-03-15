import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.in"));
        FileWriter fw = new FileWriter("output.out");
        int INF = 100000000;
        int n = sc.nextInt();
        int m = sc.nextInt();
        int Min;
        int x;
        int[][] a = new int[n + 1][n + 1];
        while (sc.hasNext()) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            int y = sc.nextInt();
            a[i][j] = y;
            a[j][i] = y;
        }

        boolean met[] = new boolean[n + 1];
        int len[][] = new int[n + 1][n + 1];
        int count[][] = new int[n + 1][n + 1];

        for (int j = 0; j <= n; j++) {
            x = j;

            for (int i = 1; i <= n; i++) {
                met[i] = false;
            }

            for (int i = 1; i <= n; i++) {
                len[j][i] = INF;
            }
            len[j][x] = 0;


            for (int i = 1; i <= n; i++) {
                count[j][i] = 0;
            }

            do {
                met[x] = true;


                for (int i = 1; i <= n; i++) {
                    if (a[x][i] != 0 && met[i] == false && len[j][i] > len[j][x] + a[x][i]) {
                        count[j][i] = x;
                        len[j][i] = len[j][x] + a[x][i];
                    }
                }
                Min = INF;
                for (int i = 1; i <= n; i++) {
                    if (met[i] == false && len[j][i] < Min) {
                        Min = len[j][i];
                        x = i;
                    }
                }

            }

            while (Min != INF);
        }

        int sum[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i] += len[i][j];
            }
        }
        Min = INF;
        x = 0;
        int num[] = new int[n + 1];
        int c = 0;
        for (int i = 1; i <= n; i++) {
            if (sum[i] < Min) {
                Min = sum[i];
                c = 1;
                num[c] = i;
            } else if (sum[i] == Min) {
                c++;
                num[c] = i;
            }
        }
        fw.write(num[1] + " " + Min);
            sc.close();
            fw.close();

        }
    }