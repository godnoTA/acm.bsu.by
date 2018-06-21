import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class fishka2 {
   static long Sum(int n, int k){
        long[] Sum = new long [n + 1];
        if (k == 0) return 0;
        if (k == 1) return 1;
        if (n == 1) return 1;
        Sum[1] = 1;
        long glsum = 1;
        for(int i = 2; i <= k; i++) {
            Sum[i] = glsum%1000000007;
            glsum += Sum[i]%1000000007 ;
        }
        for(int i = k + 1; i <= n; i++) {
            glsum -= Sum[i-k-1]%1000000007;
            Sum[i] = glsum % 1000000007;
            glsum += Sum[i] %1000000007;
        }
        return Sum[n];
    }
    public static void main(String[] args){
        try{
            String path = "input.txt";
            File in = new File(path);
            Scanner sc = new Scanner(in);
            int t = sc.nextInt();

            FileWriter out = new FileWriter("output.txt");
            for(int i = 0; i < t; i++){
                int n = sc.nextInt();
                int k = sc.nextInt();
               long sum = Sum(n,k);
                out.write(Long.toString(sum) + "\r\n");
            }
            sc.close();
            out.flush();
            out.close();
        }
        catch(Exception e){
            System.err.println(e);
            System.exit(1);
        }
    }
}
