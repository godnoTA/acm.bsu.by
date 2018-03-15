//package t68;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import static java.lang.Integer.parseUnsignedInt;
import java.util.StringTokenizer;

public class T68 {

    public static void main(String[] args) throws Exception {
        //System.out.println(System.currentTimeMillis());
        BufferedReader in = new BufferedReader(new FileReader("in.txt"));
        int n = parseUnsignedInt(in.readLine());
        ArrayList<ArrayList<Integer>> graf = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graf.add(new ArrayList<>());
        }
        String s;
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            s = in.readLine();
            st = new StringTokenizer(s, " ", false);
            st.nextToken();
            ArrayList<Integer> list = graf.get(i);
            while (st.hasMoreTokens()) {
                list.add(parseUnsignedInt(st.nextToken()) - 1);
            }
        }
        in.close();
        //System.out.println(System.currentTimeMillis());

        LinkedList<Integer> bfs = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int[] time = new int[n];
        boolean possible = false;
        int maxDropTime = -1, start = -1;

        for (int i = 0; i < n; i++) {
            int localMaxTime = 0;
            for (int j = 0; j < n; j++) {
                visited[j] = false;
            }
            time[i] = 0;
            visited[i] = true;
            bfs.add(i);
            int dropped = 1;
            while (!bfs.isEmpty()) {
                int v = bfs.remove();
                for (Integer sm : graf.get(v)) {
                    if (!visited[sm]) {
                        bfs.add(sm);
                        dropped++;
                        visited[sm] = true;
                        time[sm] = time[v] + 1;
                        if (time[sm] > localMaxTime) {
                            localMaxTime = time[sm];
                        }
                    }
                }
            }
            if (dropped == n) {
                possible = true;
                if (localMaxTime >= maxDropTime) {
                    maxDropTime = localMaxTime;
                    start = i;
                }
            }
        }
        //System.out.println(System.currentTimeMillis());

        PrintWriter out = new PrintWriter("out.txt");
        if (possible) {
            out.println(maxDropTime);
            out.print(start + 1);
        } else {
            out.print("impossible");
        }
        out.close();
    }

}
