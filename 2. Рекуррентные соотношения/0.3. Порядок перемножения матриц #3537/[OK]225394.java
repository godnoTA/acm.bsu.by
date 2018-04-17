import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        
        int count = sc.nextInt();
        int[][] table = new int[count][count];
        int[][] matrx = new int[2][count];
        
        for (int i = 0; i < count; i++) {
            matrx[0][i] = sc.nextInt();
            matrx[1][i] = sc.nextInt();
        }
        sc.close();
        
        for (int i = 0; i < table.length; i++)
            for (int j = 0; j < table[i].length; j++)
                if (i != j)
                    table[i][j] = Integer.MAX_VALUE;
        for (int k = 1; k < count; k++)
            for (int i = 0; i < count; i++) {
                if (i + k >= count) break;
                for (int p = 0; p < k; p++) {
                    table[i][i + k] = Math.min(table[i][i + k],
                            table[i][i + p] + table[i + p + 1][i + k] + matrx[0][i] * matrx[0][i + p + 1] * matrx[1][i + k]);
                }
            }
        fw.append(String.valueOf(table[0][count - 1]));
        fw.close();
    }

}