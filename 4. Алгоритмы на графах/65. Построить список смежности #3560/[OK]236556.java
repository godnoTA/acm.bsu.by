import java.io.*;
import java.util.*;
public class Test {
    public void run(){
        try {
            int N, M, i;
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line = br.readLine();
            i = 0;
            StringTokenizer st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ArrayList< ArrayList<Integer> > arr = new ArrayList< ArrayList<Integer> >();
            while (i < N) {
                ArrayList<Integer> tmp = new ArrayList<Integer>();
                arr.add(tmp);
                i++;
            }
            i = 0;
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            for (; M > 0; M--) {
                line = br.readLine();
                st = new StringTokenizer(line);
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                arr.get(u-1).add(v);
                arr.get(v-1).add(u);
            }
            for (i = 0; i < N; i++)
            {
                int col = arr.get(i).size();
                out.print(col + " ");
                for (int j = 0; j < col; j++) out.print(arr.get(i).get(j) + " ");
                out.println();
            }

            out.flush();

        }
        catch(Exception e){}
    }
    public static void main(String[] args) throws IOException { new Test().run(); }
}