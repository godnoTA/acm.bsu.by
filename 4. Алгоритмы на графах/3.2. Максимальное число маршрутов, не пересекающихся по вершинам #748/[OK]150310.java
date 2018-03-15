import java.io.*;
import java.util.*;

/**
 * Created by 123 on 05.05.2016.
 */
public class Potok {
    int n,m;
    boolean doubled[];
    static Deque<Integer> queue=new ArrayDeque<>();
    //  static Stack<Integer> stack=new Stack<>();
    static ArrayList<Integer>[] adjacencyList;
    static int[] visit;
    static ArrayList<Integer> puti;
    static int w;
    static int v;
    static Map<Edge,Integer> edges=new HashMap<>();

    public static void main(String[] args) {
        Potok one = new Potok();
    }

    final  public void read(){
        try{
            FastScanner scan=new FastScanner("input.in");
            n=scan.nextInt();
            m=scan.nextInt();
            visit=new int[2*n+1];
            doubled=new boolean[2*n+1];
            puti=new ArrayList<>();
            adjacencyList=new ArrayList[2*n+1];
            for(int i=1;i<n+1;i++){
                adjacencyList[i]=new ArrayList<>();
            }

            for(int i=1;i<n+1;i++){
                int temp=scan.nextInt();
                while(temp!=0) {
                    adjacencyList[i].add(temp);
                    temp=scan.nextInt();
                }
            }

            Edge duga;
            for(int i=1;i<n+1;i++){
                for(int j=0;j<adjacencyList[i].size();j++){
                    duga=new Edge(i,adjacencyList[i].get(j));
                    edges.put(duga, 1);
                }
            }
            v=scan.nextInt();
            w=scan.nextInt();
        }catch (IOException e){System.exit(1);}
    }


    Potok() {
        long k=0;
        read();
        visit[v] = 1;
        queue.addFirst(v);
        int l;
        Edge edge;
        while (!queue.isEmpty()) {
            for (int j = 0; j < adjacencyList[queue.getFirst()].size(); j++) {
                l = queue.getFirst();
                edge=new Edge(l,adjacencyList[l].get(j));
                if (visit[adjacencyList[l].get(j)] == 0 ) {
                    if(edges.containsKey(edge)){
                        if(edges.get(edge) ==1){
                            queue.addLast(adjacencyList[l].get(j));
                            visit[adjacencyList[l].get(j)] = l;
                        }
                    }
                }
                if (adjacencyList[l].get(j) == w && visit[w]!=0 ){
                    int y=visit[w];
                    while (y!=v){
                        puti.add(y);
                        y=visit[y];
                    }
                    y=w;
                    while(y!=v){
                        edge=new Edge(y,visit[y]);
                        edges.replace(edge,1);
                        edge=new Edge(visit[y],y);
                        edges.replace(edge, 0);
                        if(!doubled[y]){
                        if(y!=w && y!=v){
                            doubled[y]=doubled[n+1]=true;
                            adjacencyList[n+1]=new ArrayList<>();
                            adjacencyList[n+1].add(y);
                            adjacencyList[y].add(n+1);
                            edge=new Edge(y,n+1);
                            edges.put(edge,0);
                            edge=new Edge(n+1,y);
                            edges.put(edge, 1);
                            ArrayList<Integer> templist=(ArrayList)adjacencyList[y].clone();
                            for(int smezhnaya :templist){
                                if(smezhnaya!=n+1 && !puti.contains(smezhnaya) ){
                                    edge=new Edge(y,smezhnaya);
                                    edges.remove(edge);
                                    if(adjacencyList[y].contains(smezhnaya))
                                        adjacencyList[y].remove(adjacencyList[y].indexOf(smezhnaya));
                                    edge=new Edge(n+1,smezhnaya);
                                    edges.put(edge,1);
                                    adjacencyList[n+1].add(smezhnaya);
                                }else if(puti.contains(smezhnaya)){
                                    if (y < smezhnaya) {
                                        edge=new Edge(y,smezhnaya);
                                        edges.remove(edge);
                                        if(adjacencyList[y].contains(smezhnaya))
                                            adjacencyList[y].remove(adjacencyList[y].indexOf(smezhnaya));
                                        edge=new Edge(smezhnaya,y);
                                        edges.remove(edge);
                                        if(adjacencyList[smezhnaya].contains(y))
                                            adjacencyList[smezhnaya].remove(adjacencyList[smezhnaya].indexOf(y));
                                        edge=new Edge(n+1,smezhnaya);
                                        edges.put(edge,0);
                                        adjacencyList[n+1].add(smezhnaya);
                                        edge=new Edge(smezhnaya,n+1);
                                        adjacencyList[smezhnaya].add(n+1);
                                        edges.put(edge,1);
                                    }

                                }
                            }
                            n++;
                        }
                        }
                        y=visit[y];
                    }
                    for (int i = 0; i < visit.length; i++) {
                        visit[i] = 0;
                    }
                    puti.clear();
                    queue.clear();
                    k++;
                    queue.addFirst(v);
                    queue.addFirst(v);
                    visit[v] = 1;
                    break;
                }
            }
            queue.removeFirst();
        }
        // }
        try{
            FileWriter printWriter = new FileWriter("output.out");
            printWriter.write(String.valueOf(k));
            printWriter.close();}catch(IOException e){System.exit(1);}
    }
    class Edge {
        int from;
        int to;
        // int weight;
        public Edge(int f, int t) {
            this.from = f;
            this.to = t;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (from != edge.from) return false;
            return to == edge.to;

        }

        @Override
        public int hashCode() {
            int result = from;
            result = 31 * result + to;
            return result;
        }
    }
    class FastScanner {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public FastScanner(String fileName) throws IOException {
            reader = new BufferedReader(new FileReader(fileName));
        }

        final   public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    throw new EOFException();
                }
                tokenizer = new StringTokenizer(line);
            }
            return tokenizer.nextToken();
        }

        final   public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}