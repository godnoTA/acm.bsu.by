import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new FileReader(new File("in.txt")));
        String sizes = br.readLine();
        st = new StringTokenizer(sizes, " \n", false);
        File file = new File("out.txt");
        FileWriter fr = null;
        fr = new FileWriter(file);
        BufferedWriter pr = new BufferedWriter(fr);
        int top = Integer.parseInt(st.nextToken());
        long[][] matrix = new long[top][top];
        Queue<Integer> coord = new ArrayDeque<Integer>();
        int startI = Integer.parseInt(st.nextToken()) - 1;
        int startJ = Integer.parseInt(st.nextToken()) - 1;
        int finishI = Integer.parseInt(st.nextToken()) - 1;
        int finishJ = Integer.parseInt(st.nextToken()) - 1;
        StringBuilder sb = new StringBuilder();

        String str;
        while((str = br.readLine()) != null)
            sb.append(str + " ");
        st = new StringTokenizer(sb.toString(), " \n", false);
        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        long[][] path = new long[top][top];
        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++) {
                path[i][j] = 0;
            }
        }
        coord.offer(startI);
        coord.offer(startJ);
        path[startI][startJ] = matrix[startI][startJ];
        while (!coord.isEmpty()) {
            int i = coord.remove();
            int j = coord.remove();

            if (i + 1 >= 0 && i + 1 < top) {
                if (matrix[i + 1][j] < matrix[i][j] && path[i + 1][j] == 0) {
                    coord.offer(i + 1);
                    coord.offer(j);
                    path[i+1][j] = matrix[i+1][j];
                }
            }
            if (i - 1 >= 0 && i - 1 < top) {
                if (matrix[i - 1][j] < path[i][j] && path[i - 1][j] == 0) {
                    coord.offer(i - 1);
                    coord.offer(j);
                    path[i-1][j] = matrix[i-1][j];
                }
            }
            if (j + 1 >= 0 && j + 1 < top) {
                if (matrix[i][j + 1] < path[i][j] && path[i][j + 1] == 0) {
                    coord.offer(i);
                    coord.offer(j + 1);
                    path[i][j+1] = matrix[i][j+1];
                }
            }
            if (j - 1 >= 0 && j - 1 < top) {
                if (matrix[i][j - 1] < path[i][j] && path[i][j - 1] == 0) {
                    coord.offer(i);
                    coord.offer(j - 1);
                    path[i][j-1] = matrix[i][j-1];
                }
            }
        }

        for (int i = 0; i < top; i++) {
            for (int j = 0; j < top; j++) {
                if (j != top - 1) {
                    pr.write(path[i][j] + " ");
                } else {
                    pr.write(path[i][j] + "");
                }
            }
            if (i != top - 1) {
                pr.newLine();
            }
        }
        pr.flush();
    }
}