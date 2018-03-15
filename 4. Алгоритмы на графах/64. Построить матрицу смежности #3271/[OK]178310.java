import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class Matrix {
    private int n;
    private int m;
    private int[][] M;

    public Matrix(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        n = sc.nextInt();
        m = sc.nextInt();
        M = new int[n][n];
        int a;
        int b;
        for (int i = 0; i < m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            M[a - 1][b - 1] = 1;
            M[b - 1][a - 1] = 1;
        }
    }

    public void show(String path) throws FileNotFoundException {
        PrintStream ps = new PrintStream(path);
        for (int[] item : M) {
            for (int elem : item) {
                ps.print(elem);
                ps.print(" ");
            }
            ps.println();
        }
        ps.close();
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Matrix obj = new Matrix("input.txt");
        obj.show("output.txt");
    }
}