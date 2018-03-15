import java.io.*;
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

    public static void main(String [] args) throws Exception{
        FastScanner scanner = new FastScanner("input.txt");
        int N = scanner.nextInt();
        int p[] = new int [N+1];
        for (int i = 1; i <= N; i++) {
            for (int j =1; j<=N; j++){
                int v = scanner.nextInt();
                if (v == 1)
                  p[j] = i;
            }
            //p[v] = u;
        }
        PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());

        for (int i = 1; i <= N; i++){
            if (i != 1)
                out.print(" ");
            out.print(p[i]);
        }
        out.close();


    }
}