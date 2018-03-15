import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int time = 0;

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

    private static void dfs(int [][]matrix, boolean [] visited, int N, int[] path, Stack<Integer> stack){
        int[] index = new int [N];
       while (stack.size() != 0){
          int v = stack.peek();
          boolean flag = false;
           for (int i = index[v]; i < N; i++){
               if (matrix[v][i] == 1 && visited[i] == false){
                   visited[i] = true;
                   time++;
                   path[i] = time;
                   stack.push(i);
                   index[v] = i;
                   flag = true;
                   break;
               }
           }
           if (!flag)
                stack.pop();
       }
    }

    public static void main(String [] args) throws Exception{
        FastScanner scanner = new FastScanner("input.txt");
        PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());
        int N = scanner.nextInt();
        int [][]matrix = new int [N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                matrix[i][j] = scanner.nextInt();

        boolean visited[] = new boolean[N];
        int[]path = new int[N];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++){
            if (visited[i] == false){
                visited[i] = true;
                time++;
                path[i] = time;
                stack.push(i);
                dfs(matrix, visited, N, path, stack);
            }
        }

        for (int i = 0; i < N; i++){
            if (i != 0)
                out.print(" ");
            out.print(path[i]);
        }
        out.close();


    }
}
