
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.lang.String;
import java.util.*;

public class HamiltonianCycle {

    static class Node {
        private Integer key;
        private int coordX;
        private int coordY;

        public Node(Integer key, int coordX, int coordY) {
            this.key = key;
            this.coordX = coordX;
            this.coordY = coordY;
        }


        @Override
        public String toString() {
            return key.toString();
        }
    }

    static class Edge {
        private int first;
        private int second;
        private int weight;

        public Edge(int first, int second, int weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "("+(first+1)+", "+(second+1)+")" + "\t";
        }
    }

    private static int numbOfVertices;
    private static int[][] dist = new int[0][0];
    private static Node[] nodes = new Node[0];
    private static Edge[] edges = new Edge[0];
    private static int[] parent = new int[0];
    private static int[] rank = new int[0];

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        try {

            BufferedReader readData = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.txt")), "UTF-8"));
            String strData;

            numbOfVertices = Integer.parseInt(readData.readLine());
            int numbOfEdges = (numbOfVertices*(numbOfVertices-1))/2;


            dist = new int[numbOfVertices][numbOfVertices];
            nodes = new Node[numbOfVertices];
            edges = new Edge[numbOfEdges];
            parent = new int[numbOfVertices];
            rank = new int[numbOfVertices];
            for (int i = 0; i < numbOfVertices; ++i) {
                make_set(i);
            }

            int count = 0;
            while ((strData = readData.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(strData, " ", false);
                nodes[count] = new Node(count, Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
                ++count;
            }

            count = 0;
            for (int i = 0; i < numbOfVertices; ++i) {
                for (int j = i + 1; j < numbOfVertices; ++j) {
                    dist[i][j] = Math.abs(nodes[i].coordX - nodes[j].coordX) + Math.abs(nodes[i].coordY - nodes[j].coordY);
                    dist[j][i] = dist[i][j];
                    edges[count] = new Edge(i, j, dist[i][j]);
                    ++count;
                }
            }

            bubbleSort(edges);

            LinkedList<Edge> mst = new LinkedList<>();
            count = 0;
            for (int i = 0; i < numbOfEdges; ++i) {
                if (find_set(edges[i].first) != find_set(edges[i].second)) {
                    mst.add(edges[i]);
                    union_sets(edges[i].first, edges[i].second);
                    ++count;
                    if (count == numbOfVertices - 1) {
                        break;
                    }
                }
            }


            List<Integer>[] g = new List[numbOfVertices];
            for (int i = 0; i < numbOfVertices ; i++) {
                g[i] = new ArrayList<>();
            }

            for (Edge edge:mst){
                g[edge.first].add(edge.second);
                g[edge.second].add(edge.first);
            }

            List<Integer> euler = eulerCycleUndirected(g, mst.peekFirst().first);
            List<Integer> answer = new ArrayList<>();
            boolean[] visited = new boolean[numbOfVertices];
            while (!euler.isEmpty()){
                int temp = euler.get(0);
                euler.remove(0);
                if (!visited[temp]){
                    answer.add(temp);
                }
            }

            int cost = 0;
            for (int i = 0 ; i < numbOfVertices-1 ; ++i){
                cost+=dist[answer.get(i)][answer.get(i+1)];
            }
            cost+=dist[answer.get(0)][answer.get(numbOfVertices-1)];
            System.out.println(cost);

            StringBuilder builder = new StringBuilder();
            builder.append(String.valueOf(cost)).append("\n");
            for (int i = 0 ; i < numbOfVertices ; ++i){
                builder.append(String.valueOf(answer.get(i)+1)).append(" ");
            }
            builder.append(String.valueOf(answer.get(0)+1));

            FileWriter writer = new FileWriter("output.txt", false);
            writer.write(builder.toString());
            writer.flush();

        } catch (IOException ex) {
            ex.getStackTrace();
        }

        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("program was carried out -  " + timeSpent + "  - milliseconds ");
    }

    public static void make_set(int v) {
        parent[v] = v;
        rank[v] = 0;
    }

    public static int find_set(int v) {
        if (v == parent[v])
            return v;
        return parent[v] = find_set(parent[v]);
    }

    public static void union_sets(int a, int b) {
        a = find_set(a);
        b = find_set(b);
        if (a != b) {
            if (rank[a] < rank[b])
                swap(a, b);
            parent[b] = a;
            if (rank[a] == rank[b])
                ++rank[a];
        }
    }
    public static void swap(int a, int b) {
        int t = a;
        a = b;
        b = t;
    }

    public static void bubbleSort(Edge[] a) {
        for (int i = 0; i + 1 < a.length; i++) {
            for (int j = 0; j + 1 < a.length; j++) {
                if (a[j].weight > a[j + 1].weight) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    static void swap(Edge[] a, int i, int j) {
        Edge t = a[j];
        a[j] = a[i];
        a[i] = t;
    }

    public static List<Integer> eulerCycleUndirected(List<Integer>[] graph, int u) {
        Set<Long> usedEdges = new HashSet<>();
        int n = graph.length;
        int[] curEdge = new int[n];
        List<Integer> res = new ArrayList<>();
        dfs(graph, curEdge, usedEdges, res, u);
        Collections.reverse(res);
        return res;
    }

    static void dfs(List<Integer>[] graph, int[] curEdge, Set<Long> usedEdges, List<Integer> res, int u) {
        while (curEdge[u] < graph[u].size()) {
            int v = graph[u].get(curEdge[u]++);
            if (usedEdges.add(((long) Math.min(u, v) << 32) + Math.max(u, v)))
                dfs(graph, curEdge, usedEdges, res, v);
        }
        res.add(u);
    }


}

