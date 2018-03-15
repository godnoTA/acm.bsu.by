import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        int top = sc.nextInt();
        boolean[] isVisited = new boolean[top];
        ArrayDeque<Integer> que = new ArrayDeque<Integer>();
        int[] array = new int[top];
        int counter = 1;
        for (int i = 0; i < top; i++) {
            isVisited[i] = false;
        }
        int[][] matrix = new int[top][top];
        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < top; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                que.add(i);
                array[i] = counter;
                counter++;

            }
            while (!que.isEmpty()) {
                int temp = que.pollFirst();
                for (int j = 0; j < top; j++) {
                    if (matrix[temp][j] == 1 && !isVisited[j]) {
                        que.add(j);
                        isVisited[j] = true;
                        array[j] = counter;
                        counter++;
                    }
                }
            }
        }
        for (Object item : array) {
            pr.print(item + " ");
        }
        pr.close();
    }
}