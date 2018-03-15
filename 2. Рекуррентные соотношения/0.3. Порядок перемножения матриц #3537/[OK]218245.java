import javafx.util.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Test {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("input.txt"), StandardCharsets.UTF_8))) {
            String line;
            if((line = reader.readLine()) == null)
                throw (new Exception("Empty file!"));
            int m = Integer.parseInt(line);
            int [] p = new int [m+1];
            String [] s;
            for(int i=0;i<m;i++){
                line = reader.readLine();
                s=line.split(" ");
                p[i]=Integer.parseInt(s[0]);
                if(i==m-1){
                    p[m]=Integer.parseInt(s[1]);
                }
            }

            int [][] dp = new int [p.length][p.length];
            for(int i=1;i<=p.length-1;i++){
                dp[i][i]=0;
            }

            for (int l = 2; l <= p.length-1; l++) {
                for (int i = 1; i <= p.length -1 - l + 1; i++) {
                    int j = i + l - 1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k <= j - 1; k++) {
                        dp[i][j] = Math.min(dp[i][j],
                                dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
                    }
                }
            }

            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(dp[1][p.length-1]+" ");
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
