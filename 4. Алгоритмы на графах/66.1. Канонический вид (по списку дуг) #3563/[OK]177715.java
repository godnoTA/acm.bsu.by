import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner sc = new FastScanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        int n = sc.nextInt();
        int u;
        int v;
        int[] p = new int[n];
        for (int i = 1; i < n ; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            p[v - 1] = u;
        }
        for(int i = 0; i < n-1; i++){
            fw.write(p[i] + " ");
        }
        fw.write(p[n-1] + "");
        fw.close();
    }
}
class FastScanner {
    BufferedReader reader;
    StringTokenizer tokenizer;

    public FastScanner(File fileName) throws IOException {
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