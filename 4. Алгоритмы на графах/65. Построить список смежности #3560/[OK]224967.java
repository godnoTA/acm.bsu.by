import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        FileWriter fw = new FileWriter("output.txt");
        int n = Integer.parseInt(st.nextToken());
        List[] matrix = new List[n];
        for (int i = 0; i < n; i++) {
            matrix[i] = new ArrayList();
        }

        int i = 0;
        int m = Integer.parseInt(st.nextToken());
        int j = -1;
        int k = -1;
        while(i++ < m){
            st = new StringTokenizer(br.readLine(), " ");
            j = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            matrix[k - 1].add(j);
            matrix[j - 1].add(k);
        }

        for (int l = 0; l < n; l++) {
            fw.write(matrix[l].size() + " ");
            matrix[l].forEach(el -> {
                try {
                    fw.write(el + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.write("\n");
        }
        fw.close();
    }
}
