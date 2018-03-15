import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        int top = sc.nextInt();
        int[][] matrix = new int[top][top];
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        for (int j = 0; j < top; j++) {
            for (int i = 0; i < top; i++) {
                list.add(matrix[i][j]);
            }
            if (list.contains(1)) {
                pr.print((list.indexOf(1)+1) + " ");
            } else {
                pr.print(0 + " ");
            }
            list.clear();
        }
        pr.close();
    }
}