import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        Integer numberOfMatrix = Integer.parseInt(reader.readLine());

        Integer [] p = new Integer[numberOfMatrix + 1];

        for(int i = 0; i < numberOfMatrix; ++i){
            String temp[] = reader.readLine().split(" ");
            p[i] = Integer.parseInt(temp[0]);
            if(i == numberOfMatrix - 1){
                p[i + 1] = Integer.parseInt(temp[1]);
            }
        }

        Integer dp[][] = new Integer[numberOfMatrix][numberOfMatrix];
        for(int i = 0; i < numberOfMatrix; ++i){
            dp[i][i] = 0;
        }

        for (int l = 1; l <= numberOfMatrix; l++) {
            for (int i = 0; i < numberOfMatrix - l; i++) {
                int j = l + i;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + p[i] * p[k + 1] * p[j + 1]);
                }
            }
        }
        writer.write(dp[0][numberOfMatrix - 1].toString());

        writer.close();
    }
}
