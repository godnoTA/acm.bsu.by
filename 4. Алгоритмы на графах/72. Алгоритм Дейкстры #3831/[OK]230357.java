

import java.io.*;
import java.util.*;

public class Main {
    private static class Edge {
        private int vert;
        private long weight;

        public Edge(int vert, long weight) {
            this.vert = vert;
            this.weight = weight;
        }
    }

    private static class Vertex {
        private int num;
        private long d;

        public Vertex(int num, long d) {
            this.num = num;
            this.d = d;
        }
    }

    private void solve() throws FileNotFoundException {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))) {
            String line;
            line = reader.readLine();
            int n = 0;
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
            }

            ArrayList<ArrayList<Edge>> ar = new ArrayList<>(n);
            for (int i = 0; i < n; i++)
                ar.add(new ArrayList<>());

            boolean blocked[] = new boolean[n];
            long d[] = new long[n];

            PriorityQueue<Vertex> que = new PriorityQueue<>((o1, o2) -> {
                if (o1.d == o2.d)
                    return 0;
                else if (o1.d < o2.d)
                    return -1;
                return 1;
            });


            while ((line = reader.readLine()) != null) {
                st = new StringTokenizer(line);

                while (st.hasMoreTokens()) {
                    int firstVertex = Integer.parseInt(st.nextToken());
                    int secondVertex = Integer.parseInt(st.nextToken());
                    int cost = Integer.parseInt(st.nextToken());
                    if (firstVertex != secondVertex) {
                        ar.get(firstVertex - 1).add(new Edge(secondVertex - 1, cost));
                        ar.get(secondVertex - 1).add(new Edge(firstVertex - 1, cost));
                    }
                }
            }

        /*
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new File("output.txt"));
        int n = in.nextInt(), m = in.nextInt();

        ArrayList<ArrayList<Edge>> ar = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            ar.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = in.nextInt()-1, v = in.nextInt()-1, w = in.nextInt();
            ar.get(u).add(new Edge(v, w));
            ar.get(v).add(new Edge(u, w));
        }
        */


        /*
        for (int i = 0; i < n; i++)
            d[i] = Long.MAX_VALUE;
        d[0] = 0;
       */


            que.add(new Vertex(0, 0));
            while (!que.isEmpty()) {
                Vertex v = que.poll();
                if (!blocked[v.num]) {
                    blocked[v.num] = true;
                    d[v.num] = v.d;
                    for (int i = 0; i < ar.get(v.num).size(); i++) {
                        //  if ( !blocked[e.vert] && e.weight + d[v.num] < d[e.vert]) {
                        //    d[e.vert] = d[v.num] + e.weight;
                        que.offer(new Vertex(ar.get(v.num).get(i).vert, ar.get(v.num).get(i).weight + v.d));
                        // }
                    }
                }
            }

            FileWriter writer = new FileWriter("output.txt");
            writer.write(Long.toString(d[n - 1]));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().solve();
    }
}
