import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Point {
    double x;
    double y;

    Point(double _x, double _y) {
        x = _x;
        y = _y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}

public class Main implements Runnable {
    private static void readFromFile() {
        File input = new File("input.txt");
        try {
            Scanner sc = new Scanner(new FileReader(input));
            size = sc.nextInt();
            memory = new double[size][size];
            figure = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                figure.add(new Point(sc.nextDouble(), sc.nextDouble()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printResult(double result) {
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

    static int size;
    static ArrayList<Point> figure;
    static double[][] memory;

    public static double q(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        readFromFile();
        double min = Double.MAX_VALUE;
        if (figure.size() == 3) {
            min = 0;
        } else {
            for (int j = 2; j < size; j++) {
                for (int i = 0; i < size; i++) {
                    memory[i][(i + j) % size] = Double.MAX_VALUE;
                    for (int k = 1; k < j; k++) {
                        memory[i][(i + j) % size] = Math.min(
                                memory[i][(i + j) % size],
                                Math.max(
                                        Math.max(
                                                memory[i][(i + k) % size],
                                                memory[(i + k) % size][(i + j) % size]
                                        ),
                                        q(figure.get(i), figure.get((i + j) % size))
                                )
                        );
                    }
                }
            }
            for (int i = 1; i < size; i++) {
                min = Math.min(min, memory[i][i - 1]);
            }
            min = Math.min(memory[0][size - 1], min);
        }
        System.out.println(min);
        printResult(min);
    }
}
