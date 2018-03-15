import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class MinCostFlow {

    public static void main(String[] args) {
        int n, m, s, t;
        try {
            File f = new File("input.txt");
            PrintWriter bw = new PrintWriter("output.txt");
            Scanner scan = new Scanner(f);
            n = scan.nextInt();
            m = scan.nextInt();
            s = scan.nextInt();
            t = scan.nextInt();
            List<Edge>[] g = new List[n + 1];
            for (int i = 0; i < n + 1; i++)
                g[i] = new ArrayList<Edge>();
            for (int i = 0; i < m; i++) {
                int a, b, u, c;
                a = scan.nextInt();
                b = scan.nextInt();
                u = scan.nextInt();
                c = scan.nextInt();
                addEdge(g, a, b, u, c);
                addEdge(g, b, a, u, c);
            }

            int n1 = g.length;
            int[] dist = new int[n1];
            Arrays.fill(dist, 0, n1, Integer.MAX_VALUE);
            dist[s] = 0;
            boolean[] marks = new boolean[n1];
            int[] q = new int[n1];
            int qt = 0;
            q[qt++] = s;
            for (int qh = 0; (qh - qt) % n1 != 0; qh++) {
                int u = q[qh % n1];
                marks[u] = false;
                for (int i = 0; i < (int) g[u].size(); i++) {
                    Edge e = g[u].get(i);
                    if (e.u <= e.f)
                        continue;
                    int v = e.b;
                    if (dist[v] > dist[u] + e.c) {
                        dist[v] = dist[u] + e.c;
                        if (!marks[v]) {
                            marks[v] = true;
                            q[qt++ % n1] = v;
                        }
                    }
                }
            }
            int[] shortways = new int[n1];
            int[] curflow = new int[n1];
            int[] pe = new int[n1];
            int[] pv = new int[n1];
            int maxFlow = Integer.MAX_VALUE;
            int flow = 0;
            int cost = 0;
            while (flow < maxFlow) {
                Queue<Long> q1 = new PriorityQueue<Long>();
                Arrays.fill(shortways, 0, n1, Integer.MAX_VALUE);
                shortways[s] = 0;
                curflow[s] = Integer.MAX_VALUE;
                q1.add((long) s);
                while (!q1.isEmpty()) {
                    long cur = q1.remove();
                    int d = (int) (cur >>> 32);
                    int u = (int) cur;
                    if (d != shortways[u])
                        continue;
                    for (int i = 0; i < (int) g[u].size(); i++) {
                        Edge edge = g[u].get(i);
                        int v = edge.b;
                        if (edge.u <= edge.f)
                            continue;
                        int ndst = shortways[u] + edge.c + dist[u] - dist[v];
                        if (shortways[v] > ndst) {
                            shortways[v] = ndst;
                            q1.add(((long) ndst << 32) + v);
                            pv[v] = u;
                            pe[v] = i;
                            curflow[v] = Math.min(curflow[u], edge.u - edge.f);
                        }
                    }
                }
                if (shortways[t] == Integer.MAX_VALUE)
                    break;
                for (int i = 0; i < n1; i++)
                    dist[i] += shortways[i];
                int c = Math.min(curflow[t], maxFlow - flow);
                flow += c;
                for (int v = t; v != s; v = pv[v]) {
                    Edge edge = g[pv[v]].get(pe[v]);
                    edge.f += c;
                    g[v].get(edge.r).f -= c;
                    cost += c * edge.c;
                }
            }
            bw.println(flow);
            bw.println(cost);
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e1) {

        }
    }

    public static void addEdge(List<Edge>[] graph, int s, int t, int cap, int cost) {
        graph[s].add(new Edge(t, cap, cost, graph[t].size()));
        graph[t].add(new Edge(s, 0, -cost, graph[s].size() - 1));
    }

    static class Edge {
        int b, f, u, c, r;

        Edge(int v, int cap, int cost, int r) {
            this.b = v;
            this.u = cap;
            this.c = cost;
            this.r = r;
        }
    }

}