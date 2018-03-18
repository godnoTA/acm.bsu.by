import java.io.*;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class M4 {
    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(
                        new FileReader("input.txt"));
        PrintWriter pw = new PrintWriter("output.txt");

        int n = Integer.parseInt(br.readLine());
        Boolean flag = false;
        int M[] = new int[n + 2];
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s, " ");

        for (int i = 1; i <= n; i++) {
            M[i] = Integer.parseInt(st.nextToken());
        }

        if(n==1){
            pw.println("Yes");
            pw.close();
        }
        if(n == 2){
            if(M[1]<=M[2]){
                pw.println("Yes");
                pw.close();
            }
            else{
                pw.println("No");
                pw.close();
            }
        }
        else {
            if(n%2==1) {
                for (int i = 1; i < (n / 2)+1; i++) {
                    if ((M[i]<= M[2 * i])&&(M[i] <= M[2 * i + 1])) {
                        flag = true;
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    pw.println("Yes");
                }else {
                    pw.println("No");
                }
            }
            if(n%2==0) {
                for (int i = 1; i < (n/2); i++) {
                    if ((M[i] <= M[2 * i])&&(M[i]<= M[2 * i + 1])) {
                        flag = true;
                    }
                    else {
                        flag = false;
                        pw.println("No");
                        break;
                    }
                }
                if (flag) {
                    if (M[n/2]<= M[n]) {
                        pw.println("Yes");
                    }
                    else {
                        pw.println("No");
                    }
                }
            }
        }
        pw.close();
    }
}