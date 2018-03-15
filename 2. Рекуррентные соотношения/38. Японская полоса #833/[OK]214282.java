import java.io.*;
import java.io.BufferedReader;
import java.math.BigDecimal;

public class M3 {
    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(
                        new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter("out.txt");

        int n;
        int k;
        int sum = 0;
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        br.readLine();
        for(int i =0;i<k;i++){
            sum+=Integer.parseInt(br.readLine());
        }
        if(sum>n-k+1){
           pw.println(0);
           pw.close();
        }
        else {
            int p = n - sum + 1;
            BigDecimal M[][] = new BigDecimal[k + 2][p + 2];
            for (int i = 0; i <= k; i++) {
                M[i][i] = BigDecimal.ONE;
            }
            for (int j = 1; j <= p; j++) {
                M[0][j] = BigDecimal.ONE;
            }
            for (int i = 1; i <= k; i++) {
                for (int j = i + 1; j <= p; j++) {
                    M[i][j] = M[i][j - 1].add(M[i - 1][j - 1]);
                }
            }
            pw.println(M[k][p]);
            pw.close();
        }
    }
}
