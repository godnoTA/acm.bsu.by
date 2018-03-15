//Порядок перемножения матриц

import java.io.*;
import java.util.*;

public class Test {

    public static int multiply(int[] arr) {
        int n = arr.length - 1;
        int[][] arr2 = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            arr2[i][i] = 0;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n - i + 1; j++) {
                int count = j + i - 1;
                arr2[j][count] = Integer.MAX_VALUE;
                for (int k = j; k <= count - 1; k++) {
                    arr2[j][count] = Math.min(arr2[j][count], arr2[j][k] + arr2[k + 1][count] + arr[j - 1] * arr[k] * arr[count]);
                }
            }
        }
        return arr2[1][n];
    }

    public void run(){
        try{

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            int[] arr = new int[N + 1];

            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));

            String str;
            for (int i = 0; i < N; i++) {
                str = br.readLine();
                int value = Integer.valueOf(str.substring(0, str.indexOf(" ")));
                arr[i] = value;
                if (i == N - 1) {
                    value = Integer.valueOf(str.substring(str.indexOf(" ") + 1, str.length()));
                    arr[N] = value;
                }
            }
            out.print(String.valueOf(multiply(arr)));
            out.flush();
        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}