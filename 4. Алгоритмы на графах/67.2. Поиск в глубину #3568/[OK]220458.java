import java.io.*;
import java.util.Scanner;

public class Main {

    public static int n;
    public static int matrix[][];
    public static int visited[];
    public static int label = 0;

    public static void dfs(int vertex) {
        visited[vertex]=label;
        for (int j=0; j<n; j++)
            if ((matrix[vertex][j]!=0) && (visited[j] == 0)) {
                ++ label;
                dfs(j);
            }
    }
    public static void main(String[] args) {
       /* new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run()
    {*/
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Файл не найден!");
            return;
        }

        n = scanner.nextInt();
        matrix = new int[n][n];
        visited = new int[n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                matrix[i][j] = scanner.nextInt();

        for (int i=0; i < n; ++i)
            if (visited[i] == 0) {
                ++label;
                dfs(i);
            }

        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            for (int i = 0; i < n; i++) {
                String text = String.valueOf(visited[i]);
                fileWriter.write(text);
                fileWriter.append(" ");
            }
            fileWriter.flush();
        } catch (Exception ex) {
        }
    }



}