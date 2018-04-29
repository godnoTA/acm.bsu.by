import java.io.*;
import java.util.Scanner;

public class Main {

    static int[] arr;
    static int size;

    private static boolean isHeap() {
        for (int i = 1; i <= Math.ceil(size / 2); i++) {
            if (2 * i < size && arr[i - 1] > arr[2 * i] || arr[i - 1] > arr[2 * i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static void readFromFile() {
        File input = new File("input.txt");
        try {
            Scanner sc = new Scanner(new FileReader(input));
            size = sc.nextInt();
            arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = sc.nextInt();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResult(boolean result) {
        try {
            File output = new File("output.txt");
            if (output.exists()) {
                output.delete();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(output, true), true);
            pw.print(result ? "Yes" : "No");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFromFile();
        printResult(isHeap());
    }
}
