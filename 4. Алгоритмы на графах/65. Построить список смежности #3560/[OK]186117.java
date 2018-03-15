import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String [] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String string = reader.readLine();
        int N, M;
        String [] my =  string.split(" ");
        N = Integer.parseInt(my[0]);
        M = Integer.parseInt(my[1]);

        ArrayList<Integer>[] set = new ArrayList[N];
        for (int j = 0; j < M; j++)
        {
            int i = 0;
            string = reader.readLine();
            while (string.charAt(i) != ' ')
                i++;
            int a = Integer.parseInt(string.substring(0, i));
            int b = Integer.parseInt(string.substring(i+1, string.length()));
            if (set[a-1] == null)
                set[a-1] = new ArrayList<>();
            if (set[b-1] == null)
                set[b-1] = new ArrayList<>();
            set[a-1].add(b);
            set[b-1].add(a);
        }

        reader.close();
        PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());

        for (int i =0; i < N ; i++) {
            if (i != 0) {
                out.println();
                if (set[i] != null)
                    out.print(set[i].size());
                else
                    out.print(0);
            }
            else {
                if (set[i] != null)
                    out.print(set[i].size());
                else
                    out.print(0);
            }
            if (set[i] != null) {
                for (int a: set[i] ) {
                    out.print(" " + a);
                }
            }

        }
        out.close();
    }
}
