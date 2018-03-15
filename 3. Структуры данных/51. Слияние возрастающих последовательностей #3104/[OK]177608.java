import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        FastScanner sc = new FastScanner(new File("input.txt"));
        PrintStream fw = new PrintStream("output.txt");
        List<Integer> list = new ArrayList<>();
        int n = sc.nextInt();
        int m = sc.nextInt();
        try {
            while (true) {
                list.add(sc.nextInt());
            }
        }
        catch (EOFException e){}
        Collections.sort(list);
        for ( int item =0; item < list.size()-1;item++) {
            fw.print((list.get(item) + " "));
        }
        fw.print((list.get(list.size()-1) + "\n"));
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