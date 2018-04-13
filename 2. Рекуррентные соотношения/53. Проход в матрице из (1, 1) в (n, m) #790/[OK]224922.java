
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int[][] A;
    private static int[][] L;
    private static int m;
    private static int n;

    private static int findMin(int[] a) {
        int res = -1;

        for (int i = 0; i < 3; i++)
            if (a[i] != -1 && (res == -1 || a[i] < res))
                res = a[i];

        return res;
    }

    private static int wayCost(int i, int j) {
        if (j == -1 || j == m)
            return -1;

        if (L[i][j] == -1) {
            if (i < j)
                L[i][j] = -1;
            else if (i == j) {
                int sum = 0;

                for (int k = i; k >= 0; k--)
                    sum += A[k][k];
                L[i][j] = sum;

            } else {
                int way1 = j == 0 ? -1 : wayCost(i - 1, j - 1);
                int way2 = wayCost(i - 1, j);
                int way3 = j == m - 1 ? -1 : wayCost(i - 1, j + 1);

                int min = findMin(new int[]{way1, way2, way3});
                L[i][j] = min == -1 ? -1 : A[i][j] + min;
            }
        }
        return L[i][j];
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner("input.txt");

        n = fs.nextInt();
        m = fs.nextInt();

        A = new int[n][m];
        L = new int[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                A[i][j] = fs.nextInt();
                L[i][j] = -1;
            }


        PrintWriter pw = new PrintWriter("output.txt");
        pw.print(wayCost(n - 1, m - 1));
        pw.close();
    }
}

class FastScanner {
    BufferedReader reader;
    StringTokenizer tokenizer;

    public FastScanner(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public String next() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) {
                throw new EOFException();
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}