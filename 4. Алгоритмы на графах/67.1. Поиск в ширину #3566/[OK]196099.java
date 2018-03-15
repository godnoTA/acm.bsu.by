import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
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
        //int p[] = new int [N+1];
        int [][] matrix = new int[N+1][N+1];
        boolean [] visited = new boolean[N+1];
        int [] marks = new int [N+1];
        for (int i = 1; i <= N; i++) {
            for (int j =1; j<=N; j++)
                matrix[i][j] =  scanner.nextInt();
        }
        PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());
        Queue<Integer> queue = new LinkedList<Integer>();
        int  k = 1;
        for (int t = 1; t <= N; t++){
            if (visited[t] == false){
                queue.add(t);
                visited[t] = true;

                while (queue.size() != 0){
                    int a = queue.poll();
                    marks[a] = k;
                    k++;
                    for (int i = 1; i <= N; i++)
                        if (matrix[a][i] == 1){
                            if (!visited[i]) {

                                visited[i] = true;
                                queue.add(i);
                            }
                        }
                }
            }

        }

        for (int i = 1; i <= N; i++){
            if (i != 1)
                out.print(" ");
             out.print(marks[i]);
        }
        out.close();


    }
}