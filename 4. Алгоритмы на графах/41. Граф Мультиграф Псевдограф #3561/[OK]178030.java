import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[n][n];
        boolean flag = false;
        boolean flagM = false;
        boolean flagP = false;
        while (sc.hasNext()) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            if (a[i - 1][j - 1] != 1 && a[j - 1][i - 1] != 1) {
                a[i - 1][j - 1] = 1;
                a[j - 1][i - 1] = 1;

            } else {
                a[i - 1][j - 1] = n;
                a[j - 1][i - 1] = n;
            }

        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == a[j][i] && a[i][j] == n && a[j][i] == n && (i != j)) {
                    flagM = true;
                }
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == a[j][i] && a[i][j] == 1 && a[j][i] == 1 && (i == j)) {
                    flagP = true;
                }
            }
        }
        if (flagM == true) {
            fw.write("No" + "\n");
            if (flagP == false) {
                fw.write("Yes" + "\n");
                fw.write("Yes" + "\n");
            }
            else {
                fw.write("No" + "\n");
                fw.write("Yes" + "\n");
            }


        }

        if (flagM == false) {
            if (flagP == true) {
                fw.write("No" + "\n");
                fw.write("No" + "\n");
                fw.write("Yes" + "\n");

            } else {
                fw.write("Yes" + "\n");
                fw.write("Yes" + "\n");
                fw.write("Yes" + "\n");
            }
        }

        sc.close();
        fw.close();

    }
}