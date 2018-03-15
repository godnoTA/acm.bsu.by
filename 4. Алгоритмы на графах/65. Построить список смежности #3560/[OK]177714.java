import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        FastScanner sc = new FastScanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        Iterator<Integer> it;
        int n = sc.nextInt();
        int m = sc.nextInt();
        int u;
        int v;
        List<Integer>[] list = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new LinkedList<>();
        }
        for (int i = 0; i < m; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            list[u - 1].add(v);
            list[v - 1].add(u);
        }
        for (int i = 0; i < n; i++) {
            fw.write(list[i].size() + "");
            if (list[i].size() != 0) {
                fw.write(" ");
            }
            it = list[i].iterator();
            while (it.hasNext()) {
                fw.write(it.next() + "");
                if (it.hasNext()) {
                    fw.write(" ");
                }
            }
            fw.write("\n");
        }
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