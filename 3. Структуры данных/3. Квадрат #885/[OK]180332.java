import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("in.txt"));
        PrintWriter pr = new PrintWriter(new File("out.txt"));
        int power = sc.nextInt();
        int counter = 1;
        int size = (int) Math.pow(2, power);
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.offer(0);
        queue.offer(0);
        while (!queue.isEmpty()) {
            int i = queue.remove();
            int j = queue.remove();
            if (matrix[i][j] == 0) {
                matrix[i][j] = counter;
                counter++;

                for (int k = 0; k < size; k++) {
                    if ((int) (size / Math.pow(2, k)) - j > 0) {
                        if (matrix[i][(int) (size / Math.pow(2, k)) - j - 1] == 0) {
                            queue.offer(i);
                            queue.offer((int) (size / Math.pow(2, k)) - j - 1);
                            break;

                        } else if ((int) (size / Math.pow(2, k)) - i > 0) {
                            if (matrix[(int) (size / Math.pow(2, k)) - i - 1][j] == 0) {
                                queue.offer((int) (size / Math.pow(2, k)) - i - 1);
                                queue.offer(j);
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i == size - 1 && j == size - 1 ) {
                    pr.print(matrix[i][j]);
                } else{
                    pr.print(matrix[i][j] + " ");
                }
            }
        }
        pr.close();
    }
}