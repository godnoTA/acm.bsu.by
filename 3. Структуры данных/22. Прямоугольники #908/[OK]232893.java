import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main{

    static class Rectangle implements Comparable<Rectangle>{
        int color;
        int sqr;

        public Rectangle(int color, int sqr) {
            this.color = color;
            this.sqr = sqr;
        }

        @Override
        public String toString() {
            return String.valueOf(this.color) + " " + String.valueOf(this.sqr);
        }

        @Override
        public int compareTo(Rectangle o) {
            if(this.color > o.color){
                return 1;
            } else if(this.color < o.color){
                return -1;
            } else{
                return this.sqr - o.sqr;
            }
        }
    }
    static class Coordinates{
        int x;
        int y;

        public Coordinates(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static Coordinates buf;
    public static int a;
    public static int b;
    public static int square = 0;
    public static int[][] matrix;
    public static int[][] vertexes;
    public static void findSquare(int color, Queue<Coordinates> q){
        while (!q.isEmpty()){
            buf = q.poll();
            matrix[buf.y][buf.x] = -1;
            square ++;
            if(buf.x + 1 < b && matrix[buf.y][buf.x + 1] == color){
                matrix[buf.y][buf.x + 1] = -1;
                q.add(new Coordinates(buf.x + 1, buf.y));
            }
            if(buf.x + 1 < b && buf.y + 1 < a && matrix[buf.y + 1][buf.x + 1] == color &&
                    vertexes[buf.y + 1][buf.x + 1] == color){
                matrix[buf.y + 1][buf.x + 1] = -1;
                q.add(new Coordinates(buf.x + 1, buf.y + 1));
            }
            if(buf.y + 1 < a && matrix[buf.y + 1][buf.x] == color){
                matrix[buf.y + 1][buf.x] = -1;
                q.add(new Coordinates(buf.x, buf.y + 1));
            }
            if(buf.x - 1 >= 0 && buf.y + 1 < a && matrix[buf.y + 1][buf.x - 1] == color &&
                    vertexes[buf.y + 1][buf.x] == color){
                matrix[buf.y + 1][buf.x - 1] = -1;
                q.add(new Coordinates(buf.x - 1, buf.y + 1));
            }
            if(buf.x - 1 >= 0 && matrix[buf.y][buf.x - 1] == color){
                matrix[buf.y][buf.x - 1] = -1;
                q.add(new Coordinates(buf.x - 1, buf.y));
            }
            if(buf.x - 1 >= 0 && buf.y - 1 >= 0 && matrix[buf.y - 1][buf.x - 1] == color &&
                    vertexes[buf.y][buf.x] == color){
                matrix[buf.y - 1][buf.x - 1] = -1;
                q.add(new Coordinates(buf.x - 1, buf.y - 1));
            }
            if(buf.y - 1 >= 0 && matrix[buf.y - 1][buf.x] == color){
                matrix[buf.y - 1][buf.x] = -1;
                q.add(new Coordinates(buf.x, buf.y - 1));
            }
            if(buf.x + 1 < b && buf.y - 1 >= 0 && matrix[buf.y - 1][buf.x + 1] == color &&
                    vertexes[buf.y][buf.x + 1] == color){
                matrix[buf.y - 1][buf.x + 1] = -1;
                q.add(new Coordinates(buf.x + 1, buf.y - 1));
            }
        }
    }
    public static void main(String[] args) {
        try {
            int n;
            int xStart, yStart, xEnd, yEnd, color;
            BufferedReader br = new BufferedReader(new FileReader("in.txt"));
            FileWriter fw = new FileWriter("out.txt");
            Queue<Coordinates> q = new LinkedList<>();
            List<Rectangle> list = new ArrayList<>();
            StringTokenizer st;
            st = new StringTokenizer(br.readLine(), " ");
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            matrix = new int[a][b];
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    matrix[i][j] = 1;
                }
            }
            vertexes = new int[a + 1][b + 1];
            for (int i = 0; i < a + 1; i++) {
                for (int j = 0; j < b + 1; j++) {
                    vertexes[i][j] = 1;
                }
            }
            int k = 0;
            while(k++ < n){
                st = new StringTokenizer(br.readLine(), " ");
                xStart = Integer.parseInt(st.nextToken()) + b/2;
                yStart = a/2 - Integer.parseInt(st.nextToken());
                xEnd = Integer.parseInt(st.nextToken()) + b/2;
                yEnd = a/2 - Integer.parseInt(st.nextToken());
                color = Integer.parseInt(st.nextToken());
                for (int i = yEnd; i <= yStart; i++) {
                    for (int j = xStart; j <= xEnd; j++) {
                        vertexes[i][j] = color;
                    }
                }
                for (int i = yEnd; i < yStart; i++) {
                    for (int j = xStart; j < xEnd; j++) {
                        matrix[i][j] = color;
                    }
                }
            }
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    if(matrix[i][j] > 0){
                        color = matrix[i][j];
                        q.add(new Coordinates(j, i));
                        findSquare(color, q);
                        list.add(new Rectangle(color, square));
                        square = 0;
                    }
                }
            }
            Collections.sort(list);
            list.forEach((element) ->{
                try {
                    fw.write(element.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
