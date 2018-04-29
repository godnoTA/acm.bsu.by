import java.io.*;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][]matrix = new int[n][n];
            for(int i = 0; i < m; i++){
                String tmp = br.readLine();
                StringTokenizer st1 = new StringTokenizer(tmp);
                int a = Integer.parseInt(st1.nextToken());
                int b = Integer.parseInt(st1.nextToken());
                matrix[a-1][b-1] = 1;
                matrix[b-1][a-1] = 1;
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    bw.write(matrix[i][j] + " ");
                }
                bw.write("\n");
            }
            bw.close();

        }
        catch(IOException e){}
    }
}
