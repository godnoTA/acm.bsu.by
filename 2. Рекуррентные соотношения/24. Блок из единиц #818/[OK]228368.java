import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int[][] resmat;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));
        FileWriter fw = new FileWriter("out.txt");

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] arr = new int[n][m];
        resmat = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            resmat[i][0] = arr[i][0];
        }
        for (int j = 0; j < m; j++) {
            resmat[0][j] = arr[0][j];
        }

        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (arr[i][j] == 0) {
                    resmat[i][j] = 0;
                } else {
                    resmat[i][j] = Math.min(Math.min(resmat[i - 1][j], resmat[i][j - 1]), resmat[i - 1][j - 1]) + 1;
                    if (resmat[i][j] > max)
                        max = resmat[i][j];
                }
            }
        }
        //System.out.println(max);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (resmat[i][j] > max)
                    max = resmat[i][j];
            }
            System.out.println();
        }

        result(fw, n, m, max);


        fw.close();
        sc.close();
    }

    static void result(FileWriter fw, int n, int m, int max) throws IOException {
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (resmat[i][j] == max) {
                    if (resmat[i][j] == 0){
                        fw.append(String.valueOf(0)).append("\n");
                        fw.append(String.valueOf(0)).append("\n");
                        fw.append(String.valueOf(0)).append("\n");
                        return;
                    }

                    fw.append(String.valueOf(max)).append("\n");
                    fw.append(String.valueOf(j + 1 - max + 1)).append("\n");
                    fw.append(String.valueOf(i + 1 - max + 1)).append("\n");
                    return;
                }
            }
            System.out.println();
        }
    }
}