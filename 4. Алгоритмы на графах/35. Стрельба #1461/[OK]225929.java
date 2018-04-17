import java.io.*;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Vector;

public class Main implements Runnable {

    public static int CONNECTED = -1;
    public static int NOEDGES = -2;
    public static int STOP = -3;

    class Pair {
        public int first;
        public int second;

        public Pair() {
            first = 0;
            second = 0;
        }

        public Pair(int tfirst, int tsecond) {
            first = tfirst;
            second = tsecond;
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 256 * 1024 * 1024).start();
    }

    public void run() {

        int r, c;
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        r = scanner.nextInt();
        c = scanner.nextInt();


        Vector<Vector<Pair>> adjacencyList = new Vector<>(r);
        for (int i = 0; i < r; ++i){
            adjacencyList.add(new Vector<>());
        }


        int[] sequence = new int[c];

        boolean[] visited = new boolean[r];
        boolean[] connected = new boolean[r];

        int i, from, to;
        for (i = 0; i < c; ++i) {
            sequence[i] = NOEDGES;
            from = scanner.nextInt();
            to = scanner.nextInt();
            adjacencyList.get(--from).add(new Pair(--to, i));
            adjacencyList.get(to).add(new Pair(from, i));
        }

        ArrayDeque<Integer> CCcomponentVertexes = new ArrayDeque<Integer>();

        for (i = 0; i < r; ++i) {
            if (!(visited[i] | connected[i])) {
                //if (dfs(i, NOEDGES, visited, connected, adjacencyList, sequence, CCcomponentVertexes) == STOP)
                // return 0;
                dfs(i, NOEDGES, visited, connected, adjacencyList, sequence, CCcomponentVertexes);
            }
        }


        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            for (i = 0; i < c - 1; ++i) {
                fout.write(Integer.toString(sequence[i] + 1) + " ");
            }
            fout.write(Integer.toString(sequence[c - 1] + 1));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int dfs(int currentVertex, int previousVertex, boolean[] visited, boolean[] connected,
                          Vector<Vector<Pair>> adjacencyList, int[] sequence, ArrayDeque<Integer> CCcomponentVertexes) {

        visited[currentVertex] = true;

        for (Pair adjacentVertex : adjacencyList.get(currentVertex)) {
            if (adjacentVertex.second != previousVertex) {
                if (visited[adjacentVertex.first]) {

                    sequence[adjacentVertex.second] = adjacentVertex.first;
                    CCcomponentVertexes.add(currentVertex);

                    return adjacentVertex.first;
                }

                int tempVertex = dfs(adjacentVertex.first, adjacentVertex.second, visited, connected, adjacencyList,
                        sequence, CCcomponentVertexes);
                //if (tempVertex == STOP)
                //    return STOP;

                if ((tempVertex != NOEDGES && tempVertex != CONNECTED)) {

                    sequence[adjacentVertex.second] = adjacentVertex.first;
                    connected[currentVertex] = true;
                    CCcomponentVertexes.add(currentVertex);

                    if (tempVertex != currentVertex) {
                        return tempVertex;
                    } else {
                        while (!CCcomponentVertexes.isEmpty()) {
                            connectivityComponentToSequence(CCcomponentVertexes.pollFirst(), NOEDGES, connected, adjacencyList,
                                    sequence);
                        }
                        return CONNECTED;
                    }
                }

                if (tempVertex == CONNECTED) {
                    return CONNECTED;
                }
            }
        }

        if (previousVertex == NOEDGES) {
            FileWriter fout;
            try {
                fout = new FileWriter("output.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(fout);
                fout.write("No");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
            //return STOP;
        }
        return NOEDGES;
    }

    public static void connectivityComponentToSequence(int currentVertex, int previousVertex, boolean[] connected,
                                                       Vector<Vector<Pair>> adjacencyList, int[] sequence) {

        connected[currentVertex] = true;
        for (Pair adjacentVertex : adjacencyList.get(currentVertex)) {
            if (adjacentVertex.second != previousVertex && !connected[adjacentVertex.first]) {
                sequence[adjacentVertex.second] = adjacentVertex.first;
                connectivityComponentToSequence(adjacentVertex.first, adjacentVertex.second, connected, adjacencyList,
                        sequence);
            }

            if (connected[adjacentVertex.first] && sequence[adjacentVertex.second] == NOEDGES) {
                sequence[adjacentVertex.second] = adjacentVertex.first;
            }
        }
    }

}
