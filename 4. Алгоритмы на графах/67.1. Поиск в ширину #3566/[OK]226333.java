import java.io.*;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static int num = 0;
    public static void qOperations(Queue<Integer> q, int vertex, int n, int[][] matrix, boolean[] visit, int[] mark){
        q.add(vertex);
        while (!q.isEmpty()){
            for (int i = 0; i < n; i++) {
                if(matrix[q.peek()][i] != 0){
                    if(!visit[i]){
                        visit[i] = true;
                        mark[i] = ++num;
                        q.add(i);
                    }
                }
            }
            q.remove();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        StringTokenizer st;
        Queue<Integer> q = new LinkedBlockingDeque<>();
        int n = Integer.parseInt(br.readLine());

        int[][] matrix = new int[n][n];
        boolean[] visit = new boolean[n];
        int[] mark = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            visit[i] = false;
            mark[i] = -1;
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            if(!visit[i]){
                mark[i] = ++num;
                visit[i] = true;
                qOperations(q, i, n, matrix, visit, mark);
            }
        }

        for (int i = 0; i < n; i++) {
            fw.write(mark[i] + " ");
        }

        fw.close();
    }
}
