import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void isBinary(int[]arr, int n){
        try {
            arr[n] = Integer.MAX_VALUE;
            for (int i = 0; i < n/2; i++) {
                if ((arr[i] <= arr[2*(i+1) - 1] && arr[i] <= arr[2*(i+1)]))
                    continue;
                else {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
                    bw.write("NO" + "\n");
                    bw.close();
                    return;
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            bw.write("YES" + "\n");
            bw.close();
            return;
        }
        catch(IOException e){}
    }

    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            int n = Integer.parseInt(br.readLine());
            int[]arr = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            isBinary(arr, n);
        }
        catch (IOException e){}
    }
}
