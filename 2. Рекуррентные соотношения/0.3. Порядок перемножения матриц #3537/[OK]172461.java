import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Matrix {
    private List<Integer> sizes;
    private int n;
    private int[][] m;

    Matrix(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        n = sc.nextInt();
        m = new int[n][n];
        sizes = new ArrayList<Integer>();
        for (int i = 0; i < 2 * n; i++) {
            if (i % 2 == 0 || i == 2 * n - 1) sizes.add(sc.nextInt());
            else sc.nextInt();
        }
    }

    public int solution() {
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                int j = i + l;
                m[i][j] = Integer.MAX_VALUE;
                int tmp;
                for (int k = i; k < j; k++) {
                    tmp = m[i][k] + m[k + 1][j] + sizes.get(i) * sizes.get(k + 1) * sizes.get(j + 1);
                    if (tmp < m[i][j]) m[i][j] = tmp;
                }
            }
        }
        return m[0][n - 1];
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Matrix obj = new Matrix("input.txt");
        PrintStream ps = new PrintStream("output.txt");
        ps.println(obj.solution());
        ps.close();
    }
}