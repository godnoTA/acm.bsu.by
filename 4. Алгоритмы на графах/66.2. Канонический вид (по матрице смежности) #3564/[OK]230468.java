import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(br.readLine());
        int[]P = new int[n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int a = Integer.parseInt(st.nextToken());
                if(a == 1)
                    P[j] = i+1;
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        for(int i = 0; i < n; i++){
            bw.write(P[i] + " ");
        }
        bw.close();
    }
}
