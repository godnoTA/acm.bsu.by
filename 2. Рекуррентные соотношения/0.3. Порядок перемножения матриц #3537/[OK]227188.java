import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int min(int a, int b){
        if(a < b)
            return a;
        return b;
    }

    public static int matrixMult(int[] p, int n) {
        int[][] spreadsheet = new int[n + 1][n + 1];    //таблица для промежуточных рассчётов
        for (int i = 1; i < n + 1; i++)
            spreadsheet[i][i] = 0;      //заполняем диагональ нулями

        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= n - i + 1; j++) {
                int t = j + i - 1;
                spreadsheet[j][t] = 100500100;
                for (int k = j; k < t; k++) {
                    int tmp = spreadsheet[j][k] + spreadsheet[k+1][t] + p[j-1] * p[k] * p[t];
                    spreadsheet[j][t] = min(spreadsheet[j][t], tmp);
                }
            }
        }
        return spreadsheet[1][n];
    }

    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            int n = Integer.parseInt(br.readLine());
            int[] p = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            p[0] = Integer.parseInt(st.nextToken());
            p[1] = Integer.parseInt(st.nextToken());

            for(int i = 2; i < p.length; i++){
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                st1.nextToken();
                p[i] = Integer.parseInt(st1.nextToken());
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            bw.write(matrixMult(p, n) + "");
            bw.close();
        }
        catch(IOException e){}
    }
}
