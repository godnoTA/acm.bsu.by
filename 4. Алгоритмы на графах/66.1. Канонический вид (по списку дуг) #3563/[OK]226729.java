import java.io.*;
import java.util.Scanner;

public class Main {

    static int size;
    static int[] res;

    private static void readFromFile() {
        File input = new File("input.txt");
        try {
            Scanner sc = new Scanner(new FileReader(input));
            size = sc.nextInt();
            res = new int[size];
            int tmp;
            while (sc.hasNextLine()) {
                tmp = sc.nextInt();
                res[sc.nextInt() - 1] = tmp;
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
        printResult();
    }
}
