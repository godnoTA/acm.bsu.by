import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main{

    static int n;

    public static void main(String[] args) throws Exception{
            List<String> lines = Files.readAllLines(Paths.get("input.txt"));
            String line1 = lines.get(0);
            n = Integer.parseInt(line1);

            int[][] matrix = new int[n][n];
            int arr[] = new int[n];

            for (int i = 1; i <= n; i++){
                String[] line = lines.get(i).split(" ");
                for (int j = 0; j < n; j++){
                    int v = Integer.parseInt(line[j]);
                    matrix[i - 1][j] = v;
                }
            }

            Files.createFile(Paths.get("output.txt"));
            PrintWriter pw = new PrintWriter("output.txt");




            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (matrix[i][j] == 1){
                        arr[j] = i + 1;
                    }
                }
            }

            for (int i = 0; i < n; i++){
                pw.print(arr[i]);
                if (i != n - 1) pw.print(" ");
            }

            pw.flush();


        }

}
