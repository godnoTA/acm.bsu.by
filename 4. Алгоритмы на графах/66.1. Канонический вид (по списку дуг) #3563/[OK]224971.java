import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static final int NUM_COLS_E = 3;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        FileWriter fw = new FileWriter("output.txt");
        int n = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[NUM_COLS_E][n - 1];
        int[] vertex = new int[n];
        int[] p = new int[n];
        int i = 0;
        int j = -1;
        int k = -1;
        while(i < n - 1){
            st = new StringTokenizer(br.readLine(), " ");
            j = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            matrix[0][i] = k;
            matrix[1][i] = j;
            matrix[2][i] = vertex[j - 1];
            vertex[j - 1] = i + 1;
            i++;
        }

        for (int l = 0; l < n; l++) {
            if(vertex[l] != 0){
                p[matrix[0][vertex[l] - 1] - 1] = matrix[1][vertex[l] - 1];
                vertex[l] = matrix[2][vertex[l] - 1];
                l--;
            }
        }
        for (int l = 0; l < n; l++) {
            fw.write(p[l] + " ");
        }
        fw.close();
    }
}
