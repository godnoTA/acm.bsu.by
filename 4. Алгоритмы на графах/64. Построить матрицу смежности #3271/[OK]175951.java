import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Djohn on 14/04/2017.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileReader("input.txt"));
            int n = sc.nextInt();
            sc.nextInt();
            int resultMatrix[][] = new int [n][n];
            while(sc.hasNext()) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                resultMatrix[a-1][b-1] = 1;
                resultMatrix[b-1][a-1] = 1;
            }
            FileWriter out = new FileWriter("output.txt");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    out.write(resultMatrix[i][j] + " ");
                }
                out.write("\n");

            }
            sc.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

