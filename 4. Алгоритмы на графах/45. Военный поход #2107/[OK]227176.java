import javafx.util.Pair;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Vertice{
    int num;
    int parent;
    long priority;
    Vertice(int num){
        this.num = num;
    }

    Vertice(int num, int parent, long priority, List<Integer> adjacent){
        this.num = num;
        this.parent = parent;
        this.priority = priority;
        this.adjacent = adjacent;
    }

    Vertice copy(){
        return new Vertice(num, parent, priority, adjacent);
    }

    List<Integer> adjacent = new ArrayList<>(2000);
}



strictfp class Main{

    // 2 ≤ n ≤ 2000, 1 ≤ m ≤ 50000

    static int n = 2000;
    static int m = 50000;


    public static void dfs_(Vertice v, Vertice[] vertices, int marks[]){
        marks[v.num] = 1;
        for (int i = 0; i < v.adjacent.size(); i++){
            if (marks[v.adjacent.get(i)] == 0){
                dfs_(vertices[v.adjacent.get(i)], vertices, marks);
            }
        }

    }


    public static void main(String[] args) throws Exception {


        long sum = 0;
        Files.createFile(Paths.get("campaign.out"));
        PrintWriter writer = new PrintWriter("campaign.out");
        List<String> lines = Files.readAllLines(Paths.get("campaign.in"));
        String line1[] = lines.get(0).split(" ");
        n = Integer.parseInt(line1[0]);
        m = Integer.parseInt(line1[1]);


        int weights[][] = new int[n][n];
        int[][] matrix = new int[n][n];
        long threshhold = Long.MAX_VALUE;
        int[] fee = new int[n];
        //long[] p = new long[n];
        int[] edges_array = new int[m];
        //long[] blocked = new long[n];
        long[] distances = new long[n];
        int[] marks = new int[n];
        int[] parents_array = new int[n];
        //vert *vertices = (vert*) malloc(n * sizeof(vert));
        Vertice vertices[] = new Vertice[n];

        for (int i = 1; i <= n; i++){
            vertices[i - 1] = new Vertice(i - 1);
            parents_array[i - 1] = -1;
            fee[i - 1] = Integer.parseInt(lines.get(i));
            distances[i - 1] = threshhold;
        }

        int shf = n + 1;

        for (int i = n + 1; i < m + n + 1; i++){

            int a, b, c, p;

            String line[] = lines.get(i).split(" ");
            //input >> a >> b >> p >> c;

            a = Integer.parseInt(line[0]);
            b = Integer.parseInt(line[1]);
            p = Integer.parseInt(line[2]);
            c = Integer.parseInt(line[3]);

            if (p == 1) {
                edges_array[i - shf] = -1;
                sum += c;
            }


            matrix[a - 1][b - 1] = matrix[b - 1][a - 1] = i - shf;

            vertices[a - 1].adjacent.add(b - 1);
            vertices[b - 1].adjacent.add(a - 1);

            weights[a - 1][b - 1] = c + fee[b - 1];
            weights[b - 1][a - 1] = c + fee[a - 1];
        }


        dfs_(vertices[0], vertices, marks);


        if (marks[n - 1] == 0){
            writer.print(-1);
        }



        else {

            PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>(Comparator.comparingLong(Pair::getKey));
            distances[0] = 0;
            pq.add(new Pair<>(0L, 0));


            while (!pq.isEmpty()) {
                Pair<Long, Integer> u = pq.poll();
                int value = u.getValue();
                for (int i = 0; i < vertices[u.getValue()].adjacent.size(); i++) {
                    int v__ = vertices[value].adjacent.get(i);
                    if (distances[v__] > distances[value] + weights[value][v__]) {
                        distances[v__] = distances[value] + weights[value][v__];
                        parents_array[v__] = value;
                        //p[to] = v;
                        pq.add(new Pair<>(distances[v__], v__));
                    }
                }
            }




            List<Integer>  s_t = new ArrayList<>();

            if (sum < distances[n - 1]) {
                writer.print(-1);
            }
            else {

                int v1 = n - 1;
                int v2 = parents_array[n - 1];
                // s_t.push_back()(v2);
                do {
                    s_t.add(v1 + 1);
                    edges_array[matrix[v1][v2]]++;
                    v1 = parents_array[v1];
                    v2 = parents_array[v2];
                } while (v2 != -1);

                s_t.add(1);

                List<Integer> sell = new ArrayList<>();
                List<Integer> buy = new ArrayList<>();

                for (int i = 0; i < m; i++) {
                    if (edges_array[i] == 1) {
                        buy.add(i + 1);
                    }
                    if (edges_array[i] == -1) {
                        sell.add(i + 1);
                    }
                }

                writer.print(sell.size() + " ");

                for (int i = 0; i < sell.size(); i++) {
                    writer.print(sell.get(i));
                    if (i != sell.size() - 1) writer.print(" ");
                }

                writer.println();

                writer.print(buy.size() + " ");

                for (int i = 0; i < buy.size(); i++) {
                    writer.print(buy.get(i));
                    if (i != buy.size() - 1) writer.print(" ");
                }

                writer.println();

                for (int i = s_t.size() - 1; i >= 0; i--) {
                    writer.print(s_t.get(i));
                    if (i != 0) writer.print(" ");
                }


            }



        }

        writer.close();


    }
}