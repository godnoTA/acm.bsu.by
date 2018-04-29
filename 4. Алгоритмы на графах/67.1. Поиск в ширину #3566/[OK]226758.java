import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int baseMark = 1;

    static Queue<Integer> fifo;
    static int[][] mx;
    static int[] visit;
    static int[] mark;
    static int[] path;
    static int size;

    private static void work(int index) {
        fifo.add(index + 1);
        visit[index] = 1;
        mark[index] = baseMark++;
        int u;
        while (!fifo.isEmpty()) {
            u = fifo.poll();
            for (int i = 0; i < size; i++) {
                if (mx[u - 1][i] == 1 && visit[i] == 0) {
                    fifo.add(i + 1);
                    visit[i] = 1;
                    mark[i] = baseMark++;
                    path[i] = u;
                }
            }
        }
    }

    private static void readFromFile() {
        File input = new File("input.txt");
        try {
            Scanner sc = new Scanner(new FileReader(input));
            size = sc.nextInt();
            fifo = new LinkedList<>();
            mx = new int[size][size];
            visit = new int[size];
            path = new int[size];
            mark = new int[size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    mx[i][j] = sc.nextInt();
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
                pw.print(mark[i] + " ");
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFromFile();
        for (int i = 0; i < size; i++) {
            if (visit[i] == 0) {
                work(i);
            }
        }
        printResult();
    }
}
