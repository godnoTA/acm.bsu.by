import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        FileWriter fw = new FileWriter("output.txt");
        int n = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[n][n];
        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == 1){
                    p[j] = i + 1;
                }
            }
        }
        for (int l = 0; l < n; l++) {
            fw.write(p[l] + " ");
        }
        fw.close();
    }
}
