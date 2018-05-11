import java.io.*;
import java.util.*;

public class Main {

    public static class Pair{
        int to;
        int length;

        public Pair(int toVertex, int lengthToVertex){
            to = toVertex;
            length = lengthToVertex;
        }
    }

    public static class Vertex{
        int number;
        long mark;

        public Vertex(int num, long mrk){
            number = num;
            mark = mrk;
        }
    }

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        int n;
        int m;
        String s;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[n];
        long[] routes = new long[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
            routes[i] = 0L;
        }

        Comparator<Vertex> comparator = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                if(o1.mark < o2.mark){
                    return  -1;
                } else if(o1.mark > o2.mark){
                    return 1;
                }

                return 0;
            }
        };
        PriorityQueue<Vertex> pq = new PriorityQueue<>(m == 0? 1 : m, comparator);

        int from, to, length;
        List<List<Pair>> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        while((s = br.readLine()) != null){
            st = new StringTokenizer(s, " ");

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            length = Integer.parseInt(st.nextToken());

            if(from != to){
                list.get(from - 1).add(new Pair(to - 1, length));
                list.get(to - 1).add(new Pair(from - 1, length));
            }
        }
        pq.add(new Vertex(0, 0L));
        Vertex buf;

        while(!pq.isEmpty()){
            buf = pq.poll();
            if(!visited[buf.number]){
                visited[buf.number] = true;
                if(buf.number == 0){
                    routes[buf.number] = 0L;
                }else{
                    routes[buf.number] = buf.mark;
                }
                for (int i = 0; i < list.get(buf.number).size(); i++) {
                    pq.add(new Vertex(list.get(buf.number).get(i).to, routes[buf.number] + list.get(buf.number).get(i).length));
                }
            }
        }
        fw.write(String.valueOf(routes[n - 1]));
        fw.close();
    }
}

