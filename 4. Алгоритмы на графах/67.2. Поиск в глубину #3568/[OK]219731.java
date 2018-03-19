import java.io.*;
import java.util.Scanner;

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
        DFS(0);

       for (int i=0; i < n; ++i)
           if (visited[i] == 0) {
               ++iter;
               DFS(i);
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

    public static void DFS(int st) {
        visited[st]=iter;
        for (int r=0; r<n; r++)
            if ((adjacencyMatrix[st][r]!=0) && (visited[r] == 0)) {
                ++iter; DFS(r);
            }
    }
    
}