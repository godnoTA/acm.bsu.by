        import java.io.*;
        import java.util.*;

        public class Main {
            static class ForList {
                public int vetexNumber;
                public int weight;

                public ForList(int a, int b) {
                    this.vetexNumber = a;
                    this.weight = b;
                }
            }

            static class Vertex {

                public int vertexNumber;
                public long weight;
                public int parent;

                public Vertex(int a, long b, int c) {
                    this.vertexNumber = a;
                    this.weight = b;
                    this.parent = c;
                }
            }

            public static Comparator<Vertex> idComparator = new Comparator<Vertex>() {
                @Override
                public int compare(Vertex c1, Vertex c2) {
                    return (c1.weight > c2.weight ? 1 : (c1.weight == c2.weight ? 0 : -1));
                }
            };

            public static void main(String[] args) throws IOException {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream("input.txt")))) {
                    String line;
                    line = reader.readLine();
                    int numberVertices = 0;
                    StringTokenizer st = new StringTokenizer(line);
                    while (st.hasMoreTokens()) {
                        numberVertices = Integer.parseInt(st.nextToken());
                        int m = Integer.parseInt(st.nextToken());
                    }
                    long weight[] = new long[numberVertices];
                    int parent[] = new int[numberVertices];
                    int visit[] = new int[numberVertices];
                    Queue<Vertex> priorityVertexQueue = new PriorityQueue<>(numberVertices, idComparator);
                    ArrayList<ArrayList<ForList>> mas = new ArrayList<>(numberVertices);
                    for (int i = 0; i < numberVertices; i++) {
                        mas.add(new ArrayList<>());
                    }
                    while ((line = reader.readLine()) != null) {
                        st = new StringTokenizer(line);

                        while (st.hasMoreTokens()) {
                            int firstVertex = Integer.parseInt(st.nextToken());
                            int secondVertex = Integer.parseInt(st.nextToken());
                            int cost = Integer.parseInt(st.nextToken());
                            if (firstVertex != secondVertex) {
                                mas.get(firstVertex - 1).add(new ForList(secondVertex, cost));
                                mas.get(secondVertex - 1).add(new ForList(firstVertex, cost));
                            }
                        }
                    }
                        priorityVertexQueue.add(new Vertex(1, 0, 0));
                        while (!priorityVertexQueue.isEmpty()) {
                            Vertex a = priorityVertexQueue.poll();
                            if (visit[a.vertexNumber - 1] != 1) {
                                visit[a.vertexNumber - 1] = 1;
                                weight[a.vertexNumber - 1] = a.weight;
                                parent[a.vertexNumber - 1] = a.parent;
                                for (int i = 0; i < mas.get(a.vertexNumber - 1).size(); i++) {
                                    priorityVertexQueue.offer(new Vertex(mas.get(a.vertexNumber - 1).get(i).vetexNumber,
                                            mas.get(a.vertexNumber - 1).get(i).weight + a.weight, a.vertexNumber));
                                }
                            }
                        }

                        FileWriter writer = new FileWriter("output.txt");
                        writer.write(Long.toString(weight[numberVertices - 1]));
                        writer.close();
                    }
                }
            }
