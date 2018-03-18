import java.io.*;
import java.io.BufferedReader;

public class M5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(
                        new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter("output.txt");

        long n = Long.parseLong(br.readLine());
        int M[] = new int[66];
        int A[] = new int[66];
        for(int i =65;i>=0;i--){
            if((long)Math.pow(2,i)<=n){
                n=n-(long)Math.pow(2,i);
                M[i]=1;
                A[i]=i;
            }
        }
        for(int j=0;j<=65;j++){
            if(M[j]==1){
                pw.println(A[j]);
            }
        }
        pw.close();
    }
}