import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static class Vertex{
        int x;
        int y;
        long mark;
        int height;

        public Vertex(int x, int y, long mark, int height){
            this.x = x;
            this.y = y;
            this.mark = mark;
            this.height = height;
        }
    }

    public static long minLength(int a, int b, Integer[][] matrix, int xStart, int yStart, int xEnd, int yEnd, int step){

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
        PriorityQueue<Vertex> pq = new PriorityQueue<>(a * b, comparator);
        int j, k;
        long[][] routes = new long[a + 2][b + 2];
        boolean[][] visited = new boolean[a + 2][b + 2];
        for (int i = 0; i < a + 2; i++) {
            for (int l = 0; l < b + 2; l++) {
                routes[i][l] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i <= a + 1; i++) {
            visited[i][0] = true;
            visited[i][b + 1] = true;
        }
        for (int i = 0; i < b + 1; i++) {
            visited[0][i] = true;
            visited[a + 1][i] = true;
        }

        Vertex buf;

        pq.add(new Vertex(xStart, yStart,0, matrix[xStart][yStart]));
        visited[xStart][yStart] = true;

        while(!pq.isEmpty()){
            buf = pq.poll();
            j = buf.x;
            k = buf.y;
            routes[j][k] = buf.mark;
            visited[j][k] = true;

            if(visited[xEnd][yEnd]){
                break;
            }
            if(!visited[j + 1][k] && Math.abs(matrix[j][k] - matrix[j + 1][k]) + step + routes[j][k] < routes[j + 1][k]){
                pq.add(new Vertex(j + 1, k, Math.abs(matrix[j][k] - matrix[j + 1][k]) + step + routes[j][k], matrix[j + 1][k]));
                routes[j + 1][k] = Math.abs(matrix[j][k] - matrix[j + 1][k]) + step + routes[j][k];
            }
            if(!visited[j - 1][k] && Math.abs(matrix[j][k] - matrix[j - 1][k]) + step + routes[j][k] < routes[j - 1][k]){
                pq.add(new Vertex(j - 1, k, Math.abs(matrix[j][k] - matrix[j - 1][k]) + step + routes[j][k], matrix[j - 1][k]));
                routes[j - 1][k] = Math.abs(matrix[j][k] - matrix[j - 1][k]) + step + routes[j][k];
            }
            if(!visited[j][k + 1] && Math.abs(matrix[j][k] - matrix[j][k + 1]) + step + routes[j][k] < routes[j][k + 1]){
                pq.add(new Vertex(j , k + 1, Math.abs(matrix[j][k] - matrix[j][k + 1]) + step + routes[j][k], matrix[j][k + 1]));
                routes[j][k + 1] = Math.abs(matrix[j][k] - matrix[j][k + 1]) + step + routes[j][k];
            }
            if(!visited[j][k - 1] && Math.abs(matrix[j][k] - matrix[j][k - 1]) + step + routes[j][k] < routes[j][k - 1]){
                pq.add(new Vertex(j , k - 1, Math.abs(matrix[j][k] - matrix[j][k - 1]) + step + routes[j][k], matrix[j][k - 1]));
                routes[j][k - 1] = Math.abs(matrix[j][k] - matrix[j][k - 1]) + step + routes[j][k];
            }

        }
        return routes[xEnd][yEnd];
    }

    public static void main(String[] args) throws IOException {
        Integer a, b;
        Integer xStart;
        Integer xEnd;
        Integer yStart;
        Integer yEnd;
        Integer step;
        BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        PrintWriter pw = new PrintWriter("out.txt");
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        Integer[][] matrix = new Integer[a + 2][b + 2];

        for (int i = 1; i <= a; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= b; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        step = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        xStart = Integer.parseInt(st.nextToken());
        yStart = Integer.parseInt(st.nextToken());
        xEnd = Integer.parseInt(st.nextToken());
        yEnd = Integer.parseInt(st.nextToken());
        long result = minLength(a, b, matrix, xStart, yStart, xEnd, yEnd, step);
        pw.write(String.valueOf(result));
        pw.close();
    }
}
