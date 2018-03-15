import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MinSpanningTrees {

    static class Edge {
        private int first;
        private int second;
        private int weight;
        private int startIndex;
        private int newIndex;

        public Edge(int first, int second, int weight, int index) {
            this.first = first;
            this.second = second;
            this.weight = weight;
            this.startIndex = index;
            this.newIndex = -1;
        }

        @Override
        public String toString() {
            return (startIndex + 1) + "\t";
        }

    }


    static class Partition {
        private ArrayList<Edge> includedEdges = new ArrayList<>();
        private ArrayList<Edge> excludedEdges = new ArrayList<>();
        private ArrayList<Edge> mst = new ArrayList<>();

        private int mstCost = 0;

        public Partition(Partition partition) {
            this.includedEdges = new ArrayList<>(partition.includedEdges);
            this.excludedEdges = new ArrayList<>(partition.excludedEdges);
            this.mst = new ArrayList<>(partition.mst);
            this.mstCost = partition.mstCost;
        }

        public Partition() {
        }

    }

    private static LinkedList<Partition> partitionsList = new LinkedList<>();



    private static int numbOfVertices;
    private static int numbOfEdges;
    private static Edge[] edges = new Edge[0];

    private static int[] parent = new int[0];
    private static int[] rank = new int[0];

    private static StringBuilder builder = new StringBuilder();
    private static int amount = 0;
    private static int minCost = 0;

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        try {

            BufferedReader readData = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.txt")), "UTF-8"));

            String strData;
            StringTokenizer stringTokenizer = new StringTokenizer(readData.readLine(), " ", false);

            numbOfVertices = Integer.parseInt(stringTokenizer.nextToken());
            numbOfEdges = Integer.parseInt(stringTokenizer.nextToken());

            parent = new int[numbOfVertices];
            rank = new int[numbOfVertices];
            for (int i = 0; i < numbOfVertices; ++i) {
                make_set(i);
            }
            edges = new Edge[numbOfEdges];

            int count = 0;

            while ((strData = readData.readLine()) != null) {
                stringTokenizer = new StringTokenizer(strData, " ", false);
                int first = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int second = Integer.parseInt(stringTokenizer.nextToken()) - 1;
                int weight = Integer.parseInt(stringTokenizer.nextToken());
                edges[count] = new Edge(first, second, weight, count);
                ++count;
            }

            bubbleSort(edges);

            for (int i = 0; i < numbOfEdges; ++i) {
                edges[i].newIndex = i;
            }

            count = 0;

            LinkedList<Edge> mst = new LinkedList<>();

            for (int i = 0; i < numbOfEdges; ++i) {

                if (find_set(edges[i].first) != find_set(edges[i].second)) {

                    mst.add(edges[i]);

                    minCost += edges[i].weight;

                    union_sets(edges[i].first, edges[i].second);

                    ++count;
                    if (count == numbOfVertices - 1) {
                        ++amount;
                        for (int j = 0; j < mst.size(); ++j) {
                            builder.append(String.valueOf(mst.get(j).startIndex + 1)).append(" ");
                        }
                        builder.deleteCharAt(builder.length() - 1);
                        builder.append("\n");
                        break;
                    }
                }
            }

            for (int i = 0; i < numbOfVertices - 1; ++i) {
                Partition tempPart = new Partition();
                for (int j = 0; j < i; ++j) {
                    tempPart.includedEdges.add(mst.get(j));
                }
                tempPart.excludedEdges.add(mst.get(i));
                partitionsList.add(tempPart);
            }


            while (!partitionsList.isEmpty()) {
                Partition partition = partitionsList.poll();
                if (partition.mst.size() != numbOfVertices-1) {
                    kruskal(partition);
                }
                if (partition.mst.isEmpty() || partition.mstCost != minCost) {
                    continue;
                }
                partitionProc(partition);
            }

            FileWriter writer = new FileWriter("output.txt", false);
            writer.write(String.valueOf(amount) + "\n");
            writer.write(builder.toString());
            writer.flush();

        } catch (IOException ex) {
            ex.getStackTrace();
        }

        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("program was carried out -  " + timeSpent + "  - milliseconds ");

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

    //Disjoint-set data structure
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

    //------------------------------
    public static void swap(int a, int b) {
        int t = a;
        a = b;
        b = t;
    }

    public static void kruskal(Partition partition) {

        partition.mst.clear();

        for (int i = 0; i < numbOfVertices; ++i) {
            make_set(i);
        }

        int min = 0;

        for (Edge edge : partition.includedEdges) {
            union_sets(edge.first, edge.second);
            partition.mst.add(edge);
            min += edge.weight;
        }

        int count = partition.mst.size();

        for (int i = !partition.mst.isEmpty() ? partition.mst.get(partition.mst.size()-1).newIndex : 0; i < numbOfEdges; ++i) {

            if (!partition.excludedEdges.contains(edges[i])) {

                if (find_set(edges[i].first) != find_set(edges[i].second)) {

                    partition.mst.add(edges[i]);

                    min += edges[i].weight;

                    union_sets(edges[i].first, edges[i].second);

                    ++count;
                    if (count == numbOfVertices - 1) {
                        partition.mstCost = min;
                        if ( min == minCost) {
                            ++amount;
                            for (int j = 0; j < partition.mst.size(); ++j) {
                                builder.append(String.valueOf(partition.mst.get(j).startIndex + 1)).append(" ");
                            }
                            builder.deleteCharAt(builder.length() - 1);
                            builder.append("\n");
                        }
                        break;
                    }
                }
            }
        }
        if (count != numbOfVertices - 1 /*|| min != minCost*/) {
            partition.mst.clear();
        }
    }

    public static void partitionProc(Partition partition) {
        Partition p = new Partition(partition);
        int ind = p.includedEdges.size();
        for (int i = 0; i < numbOfVertices - 1 - ind; ++i) {
            p = new Partition(partition);
            for (int j = 0; j < i; ++j) {
                p.includedEdges.add(p.mst.get(j + ind));
            }
            p.excludedEdges.add(p.mst.get(i + ind));
            kruskal(p);
            if (!p.mst.isEmpty() && p.mstCost == minCost) {
                partitionsList.add(p);
            }
        }
    }

}
