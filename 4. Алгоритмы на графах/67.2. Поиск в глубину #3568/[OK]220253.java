import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main{

    static int n;
    static int time = 1;


    static int[][] matrix;

    static int[] marks;

    public static void dfs(int v){
        if (marks[v] != 0) return;

        marks[v] = time;
        time++;

        for (int i = 0; i < n; i++){
            if (matrix[v][i] == 1) {
                dfs(i);
            }
        }

    }

    public static void main(String[] args) throws Exception{
            List<String> lines = Files.readAllLines(Paths.get("input.txt"));
            String line1 = lines.get(0);
            n = Integer.parseInt(line1);

            matrix = new int[n][n];
            int arr[] = new int[n];

            marks = new int[n];

            for (int i = 1; i <= n; i++){
                String[] line = lines.get(i).split(" ");
                for (int j = 0; j < n; j++){
                    int v = Integer.parseInt(line[j]);
                    matrix[i - 1][j] = v;
                }
            }

            for (int i = 0; i < n; i++){
                if (marks[i] == 0) dfs(i);
            }

            Files.createFile(Paths.get("output.txt"));
            PrintWriter pw = new PrintWriter("output.txt");


            for (int i = 0; i < n; i++){
                pw.print(marks[i]);
                if (i != n - 1) pw.print(" ");
            }


            pw.flush();


        }

}
