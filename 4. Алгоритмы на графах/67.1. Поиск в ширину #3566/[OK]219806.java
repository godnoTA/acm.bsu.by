import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch(
                FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        n = scanner.nextInt();
        adjacencyMatrix = new int[n][n];
        visited = new int[n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
               adjacencyMatrix[i][j] = scanner.nextInt();
        
        BFS(0);
        
        for (int i=0; i < n; ++i)
            if (visited[i] == 0) {
                ++iter;
                BFS(i);
            }

        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            for (int i = 0; i < n; ++i)
                fout.write(visited[i]+" ");
            bufferedWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
  
    public static int n;
    public static int visited[];
    public static int adjacencyMatrix[][];
    public static int iter = 1;

    public static void BFS(int pos) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.push(pos);
        visited[pos] = iter;
        while (!q.isEmpty()) {
            int vertex = q.peekLast();
            q.pollLast();
            for (int i = 0; i < n; ++i) {
                if (adjacencyMatrix[vertex][i] != 0 && visited[i] == 0) {
                    visited[i] = ++iter;
                    q.push(i);
                }
            }
        }
    }
    
}