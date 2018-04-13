import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int[] L;
    private static int k;
    private static int n;

    public static int numberOfWays(int n) {

        if (L[n] == -1){
            if (n == 1)
                L[n] = k;
            else if (n == 2)
                L[n] = k*k;
            else {
                L[n] = k*numberOfWays(n-1) + k*numberOfWays(n-2);
            }
        }
        return L[n];
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner("input.txt");

        k = fs.nextInt();
        n = fs.nextInt();

        L = new int[n+1];

        for (int i = 0; i < n+1; i++)
            L[i] = -1;

        int res = 0;

        for (int i = 1; i <= n; i++)
            res += numberOfWays(i);

        PrintWriter pw = new PrintWriter("output.txt");
        pw.print(res);
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
