import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static class MyComparator implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return Double.compare(a.time, b.time);
        }
    }

    static class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    throw new EOFException();
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static class Pair{
        double time;
        Node node, beforeNode;
        int device;
        public Pair(double time, Node node, int device){
            this.time = time;
            this.node = node;
            this.device = device;
        }
    }

    public static class Node{
        boolean isVisited;
        double time;
        Node beforeNode;
        int device;
        int num;
        ArrayList<Rib> ribs;

        public Node(int N){
            isVisited = false;
            time = 0;
            num = N;
            beforeNode = null;
            ribs = new ArrayList<>();
        }

        public void addRib(Rib rib){
            ribs.add(rib);
        }
    }

    public static class Rib{
        long weight;
        Node node;
        int device;
        public Rib(Node nod, long weight, int device){
            node = nod;
            this.weight = weight;
            this.device = device;
        }
    }

    public static class Graph{
        Node[] nodes;
        public Graph(int n){
           nodes = new Node [n + 1];
        }

        public void addRib(long weight, int firstNode, int secondNode, int device){
            if (nodes[firstNode] == null)
                nodes[firstNode] = new Node(firstNode);
            if (nodes[secondNode] == null)
                nodes[secondNode] = new Node(secondNode);
            Rib first  = new Rib(nodes[secondNode], weight, device);
            Rib second = new Rib(nodes[firstNode], weight, device);
            nodes[secondNode].addRib(second);
            nodes[firstNode].addRib(first);
        }
    }

    static long gcd(long a, long b){
        return b == 0 ? a : gcd(b,a % b);
    }

    static long lcm(long a, long b){
        return (a / gcd(a,b)) * b;
    }

    public static void readGraph(FastScanner fastScanner, int M, Graph graph) throws Exception{
        for (int i = 0; i < M; i++){
            int R1 = fastScanner.nextInt();
            int T1 = fastScanner.nextInt();
            int R2 = fastScanner.nextInt();
            long time = lcm(T1, fastScanner.nextInt());
            graph.addRib(time, R1, R2, i+1);
        }
    }

    public static void search(Graph graph, int N){
        Queue <Pair> queue = new PriorityQueue<>(7, new MyComparator());
        queue.add(new Pair(-0.5, graph.nodes[1],0));
        while (true){
            Pair currentPair = queue.poll();
            Node currentNode = currentPair.node;
            if (!currentNode.isVisited){
                currentNode.isVisited = true;
                currentNode.time = currentPair.time + 0.5;
                currentNode.device = currentPair.device;
                currentNode.beforeNode = currentPair.beforeNode;
                if (currentNode.num == N)
                    break;
                for (Rib rib : currentNode.ribs) {
                    if (!rib.node.isVisited){
                        long k = 1;
                        if (currentNode.num != 1)
                            k = (long)Math.ceil(currentNode.time/rib.weight);
                        Pair pa = new Pair(k * rib.weight, rib.node, rib.device);
                        pa.beforeNode = currentNode;
                        queue.add(pa);
                    }
                }
            }
        }
    }

    public static void out(Graph graph, int N) throws Exception{
        PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());
        BigDecimal val = new BigDecimal(graph.nodes[N].time);
        val.setScale(1);
        out.println(val);
        ArrayList<Integer> list = new ArrayList<>();
        Node node = graph.nodes[N];
        while (node.num != 1){
            list.add(node.device);
            node = node.beforeNode;
        }
        int k = list.size() - 1;
        for (int i = k; i >= 0; i--){
            if (i != k)
                out.print(" ");
            out.print(list.get(i));
        }
        out.close();
    }

    public static void main(String [] args) throws Exception{
        FastScanner fastScanner = new FastScanner("input.txt");
        int N = fastScanner.nextInt();
        int M = fastScanner.nextInt();
        Graph graph = new Graph(N);
        readGraph(fastScanner, M, graph);
        search(graph, N);
        out(graph, N);
    }
}
