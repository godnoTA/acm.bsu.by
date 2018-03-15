import java.io.*;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class M3 {
    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(
                        new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter("output.txt");

        String temp;
        int n = Integer.parseInt(br.readLine());
        int A[]=new int[n+1];
        int B[]=new int[n+1];
        for(int i=1;i<=n;i++){
            temp=br.readLine();
            StringTokenizer st = new StringTokenizer(temp, " ");
            A[i]=Integer.parseInt(st.nextToken());
            B[i]=Integer.parseInt(st.nextToken());
        }
        int M[][]=new int[n+1][n+1];
        for(int i =1;i<=n;i++){
            for(int j=1;j<=n;j++){
                M[i][j]= 2147300000;
            }
        }
        for(int i=1;i<=n;i++){
            M[i][i]=0;
        }
        for(int i =n-1;i>0;i--){
            for(int j=i-1;j<=n;j++){
                for(int k =i;k<j;k++){
                    M[i][j]=Math.min(A[i]*B[k]*B[j]+M[i][k]+M[k+1][j],M[i][j]);
                }
            }
        }

        pw.println(M[1][n]);
        pw.close();
    }
}