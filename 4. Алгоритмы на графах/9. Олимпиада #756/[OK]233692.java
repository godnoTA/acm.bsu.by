import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("input.in"));
        PrintWriter writer = new PrintWriter(new FileWriter("output.out"));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer st;
        int matrix[][] = new int[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++){
            matrix[i][i] = 1;
        }
        int res[][] = new int[N][N];
        int tmp[][] = new int[N][N];
        for (int i = 0; i<N; i++){
            for (int j = 0; j<N; j++) {
                res[i][j] = matrix[i][j];
            }
        }
        for (int p = 0; p<N;p++) {
            for (int i = 0; i<N; i++){
                for (int j = 0; j<N; j++){
                    tmp[i][j] = 0;
                    for (int k = 0; k<N; k++) {
                        tmp[i][j] += res[i][k] * matrix[k][j];
                    }
                }
            }
            for (int i = 0; i<N; i++){
                for (int j = 0; j<N; j++) {
                    res[i][j] = tmp[i][j];
                }
            }
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++) {
                if (res[i][j] == 0) {
                    writer.print("NO");
                    writer.close();
                    return;
                }
            }
        }
        writer.print("YES");
        writer.close();
        return;
    }
}