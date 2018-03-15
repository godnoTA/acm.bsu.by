import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        int top = sc.nextInt();
        int[][] matrix = new int[top][top];
        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int result = 0;
        if (top > 1) {
            for (int i = 0; i < top; i++) {
                for (int j = i + 1; j < top; j++) {
                    result += matrix[i][j];
                }
            }
        }
        recursion(matrix, 0, top);

        if (list.size() == top && result == top - 1) {
            pr.print("Yes");
        } else {
            pr.print("No");
        }
        pr.close();
    }

    private static void recursion(int[][] matrix, int i, int top) {
        list.add(i);
        for (int j = 0; j < top; j++) {
            if (list.indexOf(j) == -1 && matrix[i][j] == 1) recursion(matrix, j, top);
        }
    }
}