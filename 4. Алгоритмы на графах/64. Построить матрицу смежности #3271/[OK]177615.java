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
        while (sc.hasNext()) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            a[i - 1][j - 1] = 1;
            a[j - 1][i - 1] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fw.write(a[i][j] + " ");

            }
            fw.write("\n");
        }
        sc.close();
        fw.close();

    }
}