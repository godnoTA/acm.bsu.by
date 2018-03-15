//package t51g;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import static java.lang.Integer.parseUnsignedInt;

public class T51G {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int n = parseUnsignedInt(in.readLine());
        int m = parseUnsignedInt(in.readLine());
        ArrayList<LinkedList<Integer>> graf = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graf.add(new LinkedList<>());
        }
        String s;
        while (m-- > 0) {
            s = in.readLine();
            int p = s.indexOf(' ');
            int a = parseUnsignedInt(s.substring(0, p)) - 1;
            int b = parseUnsignedInt(s.substring(p + 1)) - 1;
            graf.get(a).add(b);
            graf.get(b).add(a);
        }
        in.close();

        LinkedList<Integer> bfs = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                num++;
                bfs.clear();
                visited[i] = true;
                bfs.add(i);
                while (!bfs.isEmpty()) {
                    int v = bfs.remove();
                    for (Integer sm : graf.get(v)) {
                        if (!visited[sm]) {
                            bfs.add(sm);
                            visited[sm] = true;
                        }
                    }
                }
            }
        }
        num--;

        PrintWriter out = new PrintWriter("output.txt");
        out.print(num);
        out.close();
    }
}
