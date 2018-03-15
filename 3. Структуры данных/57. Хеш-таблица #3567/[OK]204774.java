import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class FastScanner {
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

    static int c = 0, m = 0;
    static int hash(int x, int i){
        return ((x % m) + c*i) % m;
    }

    public static void main(String [] args) throws Exception{
        FastScanner fstScanner = new FastScanner("input.txt");
        m = fstScanner.nextInt();
        c = fstScanner.nextInt();
        int N = fstScanner.nextInt();
        int [] path = new int [m];
        Arrays.fill(path, -1);
        for (int i = 0; i < N; i++){
            int x = fstScanner.nextInt();
            int j = 0;
            int ind = hash(x, j);
            while (path[ind] != -1 && path[ind] != x){
                j++;
                ind = hash(x, j);
            }

            if (path[ind] != x)
                path[ind] = x;
        }

        PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());

        out.print(path[0]);
        for (int i = 1; i < m; i++)
            out.print(" " + path[i]);
        out.close();
    }
}
