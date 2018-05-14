import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] arr = new int[n][n];

        for (int[] i : arr) {
            for (Integer j : i) {
                j = 0;
            }
        }

        for (int a = 0; a < m; a++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            arr[i - 1][j - 1] = 1;
            arr[j - 1][i - 1] = 1;
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fw.append(String.valueOf(arr[i][j])).append(" ");
            }
            fw.append("\n");
        }

        sc.close();
        fw.close();
    }


}
