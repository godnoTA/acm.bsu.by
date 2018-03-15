import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            int n = 0;
            int m = 0;
            int[][]matrix;
            Scanner scanner = new Scanner(new File("input.txt"));
            n = scanner.nextInt();
            n = n + 1;
            m = scanner.nextInt();
            matrix = new int[n][n];
            int v = 0;
            int w = 0;

            for(int i = 1;i < n;i++) {
                for(int j = 1;j < n;j++) {
                    matrix[i][j] = 0;
                }
            }


            for(int i = 0;i < m;i++) {
                v = scanner.nextInt();
                w = scanner.nextInt();
                matrix[v][w] = 1;
                matrix[w][v] = 1;
            }

            FileWriter writer = new FileWriter("output.txt");
            for(int i = 1;i < n;i++) {
                for(int j = 1;j < n;j++) {
                    writer.write(Integer.valueOf(matrix[i][j]).toString());
                    writer.write(" ");
                }
                writer.write('\n');
            }
            writer.close();





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
