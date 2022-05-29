import java.io.*;
import java.util.*;

public class Main {
    public static boolean [] proceeded;
    public static double [] dist;
    public static ArrayList<ArrayList<dv>> g = new ArrayList<>();
    public static ArrayList<Integer[]> directions;
    static class FastScanner {
        BufferedReader r;
        StringTokenizer t;

        public FastScanner(String fn) throws IOException {
            r = new BufferedReader(new FileReader(fn));
        }

        public String next() throws IOException {
            while (t == null || !t.hasMoreTokens()) {
                String line = r.readLine();
                if (line == null) {
                    throw new EOFException();
                }
                t = new StringTokenizer(line);
            }
            return t.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    static class dv{
        public int v;
        public  double w;
        public  int counter;
        public int prev;
        public dv(int v, double w, int prev, int counter) {
            this.v = v;
            this.w = w;
            this.counter = counter;
            this.prev = prev;

        }
    }
    static double gcd(double a, double b){
        return b == 0 ? a : gcd(b,a % b);
    }
    public static double lcm(double a, double b) {
        return a / gcd(a, b) * b;
    }
    public static void distances(int start){
        Comparator<dv> comparator = (o1, o2) -> (int) (o1.w - o2.w);
        PriorityQueue<dv> q = new PriorityQueue<>(comparator);
        boolean flag = false;
        double cu;
        q.add(new dv(start, 0, 1, 0));
        while(!q.isEmpty()){
            dv rebroo = q.poll();
            double dv = rebroo.w;
            int v = rebroo.v;
            int counter = rebroo.counter;
            if(proceeded[v-1]) continue;
            if (proceeded[proceeded.length-1]) break;
            proceeded[v - 1] = true;
            if(proceeded[rebroo.prev-1]){
                directions.get(v-1)[0] = counter;
                directions.get(v-1)[1] = rebroo.prev-1;
            }
            dist[v-1] = dv;
            for(dv rebro: g.get(v-1)){
                double w = rebro.w;
                int u = rebro.v;
                if(!flag){
                    cu = w;
                    flag = true;
                }
                else{
                    double spent_time = dist[v - 1];
                    if(spent_time!=0)  cu = (w - spent_time % w) % w;
                    else cu = w - spent_time % w;
                }
                if (!proceeded[u - 1] && dv + cu + 0.5 < dist[u - 1]){
                    q.add(new dv(u, dv + cu + 0.5, rebro.prev, rebro.counter));
                }

            }
        }
    }


    public static void main(String[] args) throws IOException {

        PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
        FastScanner fastScanner = new FastScanner("input.txt");
        int n = fastScanner.nextInt();
        int m = fastScanner.nextInt();
        g = new ArrayList<>();
         proceeded = new boolean[n];
         dist = new double[n];
         directions = new ArrayList<>();
         for (int i = 0; i<n; i++){
             directions.add(new Integer[2]);
            g.add(new ArrayList<>());
            dist[i] = Double.MAX_VALUE;
         }

        for (int i = 0; i < m; i++){
            int v = fastScanner.nextInt();
            int t1 = fastScanner.nextInt();
            int u = fastScanner.nextInt();
            int t2 = fastScanner.nextInt();
            g.get(v - 1).add(new dv(u,lcm(t2,t1), v, i+1));
            g.get(u-1).add(new dv(v, lcm(t2, t1), u, i+1));
        }
        dist[0] = 0;
        distances(1);

        out.print(dist[dist.length-1]+"\n");
        ArrayList<Integer> answer = new ArrayList<>();
        int i = n-1;
        while (i != 0) {
            answer.add(directions.get(i)[0]);
            i = directions.get(i)[1];
        }
      for (i = answer.size()-1; i>-1; i--) out.print(answer.get(i)+" ");
      out.close();

    }
}
