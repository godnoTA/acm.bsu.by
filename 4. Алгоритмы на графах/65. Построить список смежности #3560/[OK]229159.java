import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            List<Integer>[] A = new ArrayList[n];
            for(int i = 0; i < n; i++)
                A[i] = new ArrayList<>();
            for(int i = 0; i < m; i++) {
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st1.nextToken());
                int v = Integer.parseInt(st1.nextToken());
                A[u-1].add(v);
                A[v-1].add(u);
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
            for(int i = 0; i < n; i++){
                bw.write(A[i].size() + "");
                for(int j = 0; j < A[i].size(); j++){
                    bw.write(" "+ A[i].get(j));
                }
                bw.write("\n");
            }
            bw.close();
        }
        catch(IOException e){}
    }
}
