import java.io.*;
import java.util.*;

import static java.lang.Math.abs;

public class Main {

    private static class Vertex {
        int x;
        int y;
        List<Vertex> neighbours = new ArrayList<>();
        boolean blocked = false;
        int step = Integer.MAX_VALUE;

        Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Scanner in;
    private PrintWriter out;

    private Vertex[][] vertices;
    private Vertex start;
    private Vertex end;
    private int n, m;

    private void output(Vertex cur, LinkedList<Vertex> path) {
        path.add(cur);
        if (!cur.neighbours.isEmpty()) {
            for (Vertex v : cur.neighbours)
                output(v, path);
        } else {
            out.println(path.size()-1);
            for (Vertex v : path)
                out.println(v.x + " " + v.y);
        }
        path.removeLast();
    }

    private void solve() throws FileNotFoundException {
        in = new Scanner(new File("input.txt"));
        out = new PrintWriter(new File("output.txt"));
        n = in.nextInt();
        m = in.nextInt();
        int k = in.nextInt();
        boolean[][] matrix = new boolean[m][n];
        vertices = new Vertex[m][n];
        for (int i = 0; i < k; i++) {
            int x = in.nextInt(), y = in.nextInt();
            matrix[y][x] = true;
        }
        for (int y = 0; y < m; y++)
            for (int x = 0; x < n; x++)
                if (!matrix[y][x])
                    vertices[y][x] = new Vertex(x, y);
        int xs = in.nextInt(), ys = in.nextInt(), xe = in.nextInt(), ye = in.nextInt();
        start = vertices[ys][xs];
        end = vertices[ye][xe];
        bfs();
        for (Vertex[] vRow : vertices)
            for (Vertex v : vRow)
                sortPath(v);
        if (start.neighbours.isEmpty())
            out.print("no_solutions");
        else
            output(start, new LinkedList<>());
        out.flush();
    }

    private Vertex nextVertexByDir(Vertex v, int dir) {
        if (dir == 1)
            return v.x == n-1 ? null : vertices[v.y][v.x+1];
        if (dir == 2)
            return v.y == m-1 ? null : vertices[v.y+1][v.x];
        if (dir == 3)
            return v.x == 0 ? null : vertices[v.y][v.x-1];
        if (dir == 4)
            return v.y == 0 ? null : vertices[v.y-1][v.x];
        throw new RuntimeException();
    }

    private int getDir(Vertex a, Vertex b) {
        if (b.x > a.x)
            return 1;
        if (b.y > a.y)
            return 2;
        if (b.x < a.x)
            return 3;
        if (b.y < a.y)
            return 4;
        return 0;
    }

    private void sortPath(Vertex v) {
        if (v == null)
            return;
        v.neighbours.sort((v1, v2) -> {
            if (getDir(v, v1) < getDir(v, v2))
                return -1;
            if (getDir(v, v1) > getDir(v, v2))
                return 1;
            int len1 = abs(v1.y - v.y) + abs(v1.x - v.x);
            int len2 = abs(v2.y - v.y) + abs(v2.x - v.x);
            return len1 - len2;
        });
    }

    private void traverse(Vertex s, int dir, Queue<Vertex> queue) {
        Vertex cur = nextVertexByDir(s, dir);
        while(cur != null) {
            if (cur.step >= s.step + 1) {
                cur.step = s.step + 1;
                cur.neighbours.add(s);
                if (cur != start && !cur.blocked) {
                    queue.add(cur);
                    cur.blocked = true;
                }
            }
            cur = nextVertexByDir(cur, dir);
        }
    }

    private void bfs() {
        Queue<Vertex> queue = new LinkedList<>();
        end.step = 0;
        queue.add(end);
        end.blocked = true;
        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
            traverse(v, 1, queue);
            traverse(v, 2, queue);
            traverse(v, 3, queue);
            traverse(v, 4, queue);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Main().solve();
    }
}