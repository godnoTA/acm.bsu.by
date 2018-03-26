import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main implements Runnable {

    private static void readFromFile() {
        File input = new File("input.txt");
        list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new FileReader(input));
            size = sc.nextInt();
            memory = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = size - 1; j >= i; j--) {
                    memory[i][j] = -1;
                }
            }
            for (int i = 0; i < size; i++) {
                list.add(sc.nextInt());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResult(int result) {
        try {
            File output = new File("output.txt");
            if (output.exists()) {
                output.delete();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(output, true), true);
            pw.print(result);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Integer> list;
    static int[][] memory;
    static int size;

    public static int turn(int left, int right) {
        if (memory[left][right] < 0) {
            int turnNumber = size - 1 - (right - left);
            int f = list.get(left);
            int l = list.get(right);
            if (right - left != 0) {
                if (turnNumber % 2 != 0) {
                    memory[left][right] = Math.max(f + turn(left + 1, right), l + turn(left, right - 1));
                } else {
                    memory[left][right] = Math.min(turn(left + 1, right), turn(left, right - 1));
                }
            } else {
                if (turnNumber % 2 != 0) {
                    memory[left][right] = list.get(left);
                } else {
                    memory[left][right] = 0;
                }
            }
        }
        return memory[left][right];
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        readFromFile();
        int sum = 0;
        for (int el : list) {
            sum += el;
        }
        int result = 0;
        if (size > 0) {
            result = sum - turn(0, size - 1);
        }
        System.out.println(result);
        printResult(result);
    }
}
