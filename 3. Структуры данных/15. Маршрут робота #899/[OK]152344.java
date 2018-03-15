import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Point implements Comparable<Point> {
    int x;
    int y;
    int way;

    public Point(int x, int y, int way) {
        this.x = x;
        this.y = y;
        this.way = way;
    }

    public Point(){
        this.x = 0;
        this.y = 0;
        this.way = 0;
    }

    @Override
    public String toString() {
        return  x + ", "+ y +", "+ way;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() == this.getClass()) {
            Point point = (Point) obj;
            if (point.x == this.x && point.y == this.y)
                return true;
        }
        return false;
    }


    @Override
    public int compareTo(Point o) {
        Integer a = this.way;
        return a.compareTo(o.way);
    }

    public boolean equal(Point p) {
        if (this.x == p.x && this.y == p.y)
            return true;
        else return false;
    }

    public static void main(String[] args) throws IOException {
        // write your code here

        Point startPoint = new Point();
        Point finishPoint = new Point();
        Point currentPoint;
        Point p;
        Point point;

        PriorityQueue<Point> points = new PriorityQueue<>();
        BufferedReader in = new BufferedReader(new FileReader("in.txt"));
        StringTokenizer s = new StringTokenizer(in.readLine()," ");
        int m = Integer.parseInt(s.nextToken());
        int n = Integer.parseInt(s.nextToken());
        int[][] weight = new int[m+1][n+1];
        int[][] ways = new int[m+1][n+1];
        for (int i = 0; i <= m; ++i)
            for (int j = 0; j <= n; ++j)
                ways[i][j] = Integer.MAX_VALUE;
        boolean[][] flag = new boolean[m+1][n+1];
        for (int i = 0; i <= m; ++i)
            for (int j = 0; j <= n; ++j)
                flag[i][j] = true;

        for (int i = 1; i < m+1; i++) {
            s =new StringTokenizer(in.readLine()," ");
            for (int j = 1; j < n+1; j++) {
                int num = Integer.parseInt(s.nextToken());
                weight[i][j] = num;
            }
        }

        int k = Integer.parseInt(in.readLine());

        s = new StringTokenizer(in.readLine()," ");
        startPoint.x = Integer.parseInt(s.nextToken());
        startPoint.y = Integer.parseInt(s.nextToken());

        finishPoint.x = Integer.parseInt(s.nextToken());
        finishPoint.y = Integer.parseInt(s.nextToken());

        in.close();

        currentPoint = startPoint;
        //flag[startPoint.x][startPoint.y] = false;
        points.add(currentPoint);


        while (!points.isEmpty()) {

            currentPoint = points.poll();
            while (!flag[currentPoint.x][currentPoint.y])
                currentPoint = points.poll();
            flag[currentPoint.x][currentPoint.y] = false;

            if (currentPoint.equals(finishPoint)) {
                PrintWriter writer = new PrintWriter(new File("out.txt"));
                writer.println(currentPoint.way);
                writer.close();
                break;
            }

            p = currentPoint;
            if(p.x > 1) {
                point = new Point(p.x - 1, p.y, p.way + Math.abs(weight[p.x][p.y] - weight[p.x - 1][p.y]) + k);
                if (ways[point.x][point.y] > point.way)
                {
                    ways[point.x][point.y] = point.way;
                    points.add(point);
                }

                //System.out.println("up Added: " + point);
            }

            //p = currentPoint;
            if (p.x < m) {
                point = new Point(p.x + 1, p.y, p.way + Math.abs(weight[p.x][p.y] - weight[p.x+1][p.y]) + k);
                if (ways[point.x][point.y] > point.way)
                {
                    ways[point.x][point.y] = point.way;
                    points.add(point);
                }
                //System.out.println("down Added: "+point);
            }

            //p = currentPoint;
            if (p.y > 1) {
                point = new Point(p.x, p.y - 1, p.way + Math.abs(weight[p.x][p.y] - weight[p.x][p.y-1]) + k);
                if (ways[point.x][point.y] > point.way)
                {
                    ways[point.x][point.y] = point.way;
                    points.add(point);
                }
                //System.out.println("left Added: "+point);

            }

            //p = currentPoint;
            if (p.y < n) {
                point = new Point(p.x, p.y + 1, p.way + Math.abs(weight[p.x][p.y] - weight[p.x][p.y+1]) + k);
                if (ways[point.x][point.y] > point.way)
                {
                    ways[point.x][point.y] = point.way;
                    points.add(point);
                }
                //System.out.println("right Added: "+point);

            }
            //System.out.println();

        }
    }

}