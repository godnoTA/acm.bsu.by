import java.io.*;

public class Main {
    public static long[][] matrix;
    public static boolean[] visits;
    public static int[] met;
    public static int n;
    public static  int time;

    public static void dfs(int v) {
        visits[v] = true;
        time++;
        met[v] = time;
        for (int i = 0;  i < n; i++) {
            if (matrix[v][i] == 1) {
                if (!visits[i]) {
                    dfs(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            FileWriter fwr = new FileWriter("output.txt", false);
            BufferedWriter writer = new BufferedWriter(fwr);

            String s = br.readLine();
            n = Integer.valueOf(s);
            time = 0;

            matrix = new long[n][n];

            for(int i = 0; i < n; i++) {
                String[] ss = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.valueOf(ss[j]);
                }
            }

            visits = new boolean[n];
            met = new int[n];

            for (int i = 0; i < n; i++) {
                if (!visits[i]) {
                    dfs(i);
                }
            }


            for (int i = 0; i < n; i++) {
                writer.append(met[i] + " ");
            }


            writer.close();
            br.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
