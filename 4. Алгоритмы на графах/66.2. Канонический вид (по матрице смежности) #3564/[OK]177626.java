import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        int n = sc.nextInt();
        int[] p = new int[n];
        while (sc.hasNext()) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 0; j < n; j++) {
                    if (sc.nextInt() == 1) {
                        p[j] = i;
                    }
                }
            }
        }
        for (int j = 0; j < n; j++) {
            fw.write(p[j] + " ");
        }
        sc.close();
        fw.close();
    }
}