import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        String temp[] = reader.readLine().split(" ");
        Integer n = Integer.parseInt(temp[0]), e = Integer.parseInt(temp[1]);

        Integer graph[][] = new Integer[n][n];

        for (int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                graph[i][j] = 0;
            }
        }

        Integer u, v;
        for(int i = 0; i < e; ++i){
            temp = reader.readLine().split(" ");
            u = Integer.parseInt(temp[0]) - 1;
            v = Integer.parseInt(temp[1]) - 1;
            graph[u][v] = graph[v][u] = 1;
        }

        for (int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                writer.write(graph[i][j].toString() + " ");
            }
            writer.write("\n");
        }


        writer.close();
    }
}