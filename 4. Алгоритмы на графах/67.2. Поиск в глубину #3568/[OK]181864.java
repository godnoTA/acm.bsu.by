import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    private static int top;
    private static int[][] matrix;
    private static int visited[];
    private static int result[];
    private static int count;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        top = sc.nextInt();
        matrix = new int[top][top];
        visited = new int[top];
        result = new int[top];
        for (int i = 0; i < top; i++) {
            visited[i] = 0;
            result[i] = 0;
            for (int j = 0; j < top; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        count = 1;
        for (int i = 0; i < top; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                recursion(i);
            }
        }
        for (int i = 0; i < top; i++) {
            pr.print(result[i] + " ");
        }
        pr.close();
    }

    private static void recursion(int k) {
        result[k] = count;
        count++;
        for (int i = 0; i < top; i++) {
            if (matrix[k][i] == 1 && visited[i] == 0) {
                visited[i] = 1;
                recursion(i);
            }
        }
    }
}