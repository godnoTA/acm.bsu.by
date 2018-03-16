import java.io.*;

public class Nails {
    public static double min(double a, double b){
        if (a < b) return a;
        else return b;
    }


    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        int N = Integer.parseInt(br.readLine());
        double [] nails = new double[N];
        String s;
        int i = 0;
        while((s = br.readLine()) != null){
            nails[i++] = Double.parseDouble(s);
        }
        br.close();

        double [] dp = new double[N];
        for(i = 0; i < N; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        dp[1] = nails[1] - nails[0];
        for(i = 1; i < N-1; i++){
            dp[i+1] = min(dp[i] + nails[i+1] - nails[i], dp[i-1] + nails[i+1] - nails[i]);
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
        String template = String.format("%.2f", dp[N-1]);
        bw.write(template);
        bw.close();
    }
}
