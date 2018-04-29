import java.io.*;
import java.util.Scanner;

public class Main {

    static int[][] matrix;
    static int size;

    static int[] res;

    private static void work() {
        res = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[j][i] == 1) {
                    res[i] = j + 1;
                }
            }
        }
    }

    private static void readFromFile() {
        File input = new File("input.txt");
        try {
            Scanner sc = new Scanner(new FileReader(input));
            size = sc.nextInt();
            matrix = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResult() {
        try {
            File output = new File("output.txt");
            if (output.exists()) {
                output.delete();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(output, true), true);
            for (int i = 0; i < size; i++) {
                pw.print(res[i] + " ");
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFromFile();
        work();
        printResult();
    }
}
