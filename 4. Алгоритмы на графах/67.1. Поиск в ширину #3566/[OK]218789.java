import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        Integer n = Integer.parseInt(reader.readLine());

        Integer[][] matrix = new Integer[n][n];
        Boolean[] used = new Boolean[n];
        Integer[] d = new Integer[n];

        String temp[];
        for (int i = 0; i < n; ++i) {
            temp = reader.readLine().split(" ");
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = Integer.parseInt(temp[j]);
            }
            used[i] = false;
            d[i] = 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        used[0] = true;
        d[0] = 1;

        int counter = 0;

        for (int v = 0; v < n; ++v) {
            if (used[v] == false) {
                queue.add(v);
                used[v] = true;
            }
            while (!queue.isEmpty()) {
                int node = queue.poll();
                d[node] = ++counter;
                for (int i = 0; i < n; ++i) {
                    if (matrix[node][i] == 1 && used[i] == false) {
                        used[i] = true;
                        queue.add(i);
                        //p[to] = v;
                    }
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            writer.write(d[i] + " ");
        }

        writer.close();
    }
}
