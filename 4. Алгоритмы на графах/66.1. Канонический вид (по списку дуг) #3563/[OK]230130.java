import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
        int[]arr = new int[n];
        for(int i = 0; i < n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[b-1] = a;
        }
        for(int i = 0; i < n; i++){
            bw.write(arr[i] + " ");
        }
        bw.write("\n");
        bw.close();
    }
}
