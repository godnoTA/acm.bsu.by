import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        int none = -1;
        int size = sc.nextInt();
        int[] lilies = new int[size];
        for (int i = 0; i < size; i++) {
            lilies[i] = sc.nextInt();
        }
        if (size == 1) {
            pr.print(lilies[0]);
        }
        if (size == 2) {
            pr.print(none);
        }
        if (size == 3) {
            pr.print(lilies[0] + lilies[2]);
        }
        if (size == 4) {
            pr.print(lilies[0] + lilies[3]);
        }
        if (size == 5) {
            pr.print(lilies[0] + lilies[2] + lilies[4]);
        }
        if (size > 5) {
            lilies[4] += lilies[0] + lilies[2];
            lilies[3] += lilies[0];
            lilies[2] += lilies[0];

            for (int i = 5; i < size; i++) {
                lilies[i] += Math.max(lilies[i - 2], lilies[i - 3]);
            }
            pr.print(lilies[size - 1]);
        }
        pr.close();
    }
}