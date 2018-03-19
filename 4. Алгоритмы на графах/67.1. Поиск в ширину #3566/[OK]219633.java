import java.io.*;

public class Main {
    public static long[][] matrix;

    public static boolean[] visits;
    public static int[] fifo;
    public static int[] met;
    public static int start;
    public static int end;
    public static int n;

    public static void bfs(int u) {

    }
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            FileWriter fwr = new FileWriter("output.txt", false);
            BufferedWriter writer = new BufferedWriter(fwr);

            String s = br.readLine();
            n = Integer.valueOf(s);

            matrix = new long[n][n];

            for(int i = 0; i < n; i++) {
                String[] ss = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.valueOf(ss[j]);
                }
            }

            visits = new boolean[n];
            fifo = new int[n];
            met = new int[n];
            start = 0;
            end = 0;


            fifo[end++] = 0;
            visits[0] = true;
            met[0] = 1;
            int x = met[0];

            for (int i = 0; i < n; i++) {
                if (i != 0 && !visits[i]) {
                    visits[i] = true;
                    fifo[end++] = i;
                    x++;
                    met[i] = x;
                }

                while(start < end) {
                    int v = fifo[start];
                    start++;

                    for (int nearest = 0; nearest < n; nearest++) {
                        if (!visits[nearest] && matrix[v][nearest] == 1) {
                            fifo[end++] = nearest;
                            visits[nearest] = true;
                            x++;
                            met[nearest] = x;
                        }
                    }
                }
            }

            for (int i = 0 ;i < n; i++) {
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
