import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        FileWriter fw = new FileWriter("output.txt");
        int n = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[n][n];
        int i = 0;
        int m = Integer.parseInt(st.nextToken());
        int j = -1;
        int k = -1;
        while(i++ < m){
            st = new StringTokenizer(br.readLine(), " ");
            j = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            matrix[k - 1][j - 1] = 1;
            matrix[j - 1][k - 1] = 1;
        }
        for (int l = 0; l < matrix.length; l++) {
            for (int o = 0; o < matrix[l].length; o++) {
                fw.write(matrix[l][o] + " ");
            }
            fw.write("\n");
        }
        fw.close();
    }
}
