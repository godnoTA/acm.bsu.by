import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        int top = sc.nextInt();
        int edge = sc.nextInt();
        int[][] matrix = new int[top][top];
        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++) {
                matrix[i][j] = 0;
            }
        }
            while (sc.hasNextInt()) {
                int i = sc.nextInt();
                int j = sc.nextInt();
                matrix[i - 1][j - 1] = 1;
                matrix[j - 1][i - 1] = 1;
            }
            for (int i = 0; i < top; i++) {
                for (int j = 0; j < top; j++) {
                    pr.print(matrix[i][j]+" ");
                }
                pr.println();
            }
            pr.close();
        }
    }