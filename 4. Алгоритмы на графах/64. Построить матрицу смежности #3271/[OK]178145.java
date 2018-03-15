import java.io.*;
import java.util.*;


public class Test {
    public void run(){
        try{

            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int arr[][]=new int[N][N];

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++)
                    arr[i][j]=0;
            }

            while(M>0){
                line = br.readLine();
                st = new StringTokenizer(line);
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                arr[u-1][v-1] = 1;
                arr[v-1][u-1] = 1;
                M--;
            }

            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    out.print(arr[i][j] + " ");
                }
                out.print("\r\n");
            }

            out.flush();
        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}