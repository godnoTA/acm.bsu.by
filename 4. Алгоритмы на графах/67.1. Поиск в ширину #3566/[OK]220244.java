import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int currMark;
    static int marks[];
    static int graph[][];

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            n = scanner.nextInt();
            marks = new int[n];
            graph = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    graph[i][j] = scanner.nextInt();
                }
            }
            currMark = 1;
            BFS(0);
            for (int i = 0; i < n; ++i) {
                if (marks[i] == 0) {
                    currMark++;
                    BFS(i);
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


    public static void BFS(int startNode) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.push(startNode);
        marks[startNode] = currMark;
        while (!queue.isEmpty()) {
            int currNode = queue.peekLast();
            queue.pollLast();
            for (int i = 0; i < n; ++i) {
                if (graph[currNode][i] != 0 && marks[i] == 0) {
                    currMark++;
                    marks[i] = currMark;
                    queue.push(i);
                }
            }
        }
    }
}