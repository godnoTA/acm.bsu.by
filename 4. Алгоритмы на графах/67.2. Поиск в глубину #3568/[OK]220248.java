import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int currMark;
    static boolean visited[];
    static int marks[];
    static int graph[][];

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            n = scanner.nextInt();
            marks = new int[n];
            visited = new boolean[n];
            System.out.println(visited[0]);
            graph = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    graph[i][j] = scanner.nextInt();
                }
            }
            currMark = 0;
            DFS(0);
            for (int i = 0; i < n; ++i) {
                if (marks[i] == 0) {
                    DFS(i);
                }
            }
            try {
                FileWriter writer = new FileWriter("output.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                for (int i = 0; i < n; ++i)
                    writer.write(marks[i] + " ");
                bufferedWriter.close();
            }
            catch (Exception e) {}
        }
        catch(Exception e){}
    }


    public static void DFS(int startNode) {
        visited[startNode] = true;
        currMark++;
        marks[startNode] = currMark;
        for (int i = 0; i < n; ++i) {
            if (graph[startNode][i] != 0 && !visited[i]) {
                DFS(i);
            }
        }
    }
}