import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        int n, m;
        try{
            scanner = new Scanner(new File("input.txt"));
            n = scanner.nextInt();
            m = scanner.nextInt();
            Integer[][] matrix = new Integer[n][n];
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    matrix[i][j] = 0;
                }
            }
            for (int i = 0; i < m; i++){
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                matrix[u - 1][v - 1] = 1;
                matrix[v - 1][u - 1] = 1;
            }
            try{
                FileWriter fileWriter = new FileWriter("output.txt");
                for (int i = 0; i < n; i++){
                    for (int j = 0; j < n; j++){
                        fileWriter.write(matrix[i][j].toString() + " ");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.flush();
            }
            catch(Exception e){}
        }
        catch(Exception e){}
    }
}