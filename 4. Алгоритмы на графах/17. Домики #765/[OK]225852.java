import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.StrictMath.sqrt;

class Point implements Comparable<Point>{
    public double x, y;
    public int metka;

    public Point (double x, double y){
        this.x=x;
        this.y=y;
        this.metka=0;
    }
    public Point (double x, double y, int metka){
        this.x=x;
        this.y=y;
        this.metka=metka;
    }
    public void setMetka(int metka) {
        this.metka = metka;
    }
    public int getMetka() {
        return metka;
    }

    public boolean samePointmet(Point a){
        if(x==a.x && y==a.y){
           return true;
        }
        return false;
    }
    public double dist(Point a){
          return sqrt((x-a.x)*(x-a.x) + (y-a.y)*(y-a.y));
    }

    public boolean isIntersect(Point a1, Point a2){  // а1 - коорд домика
        double x_inter;
        double y_inter;
         x_inter = ((a1.x*a2.x - x*a1.x + a1.y*a2.x-a1.y*x+5*a2.x -5*x - a2.x*y +x*a2.y)/(a2.y - y +a2.x - x));
         y_inter= a1.x+a1.y + 5 - x_inter;
         if( (x_inter>a1.x && x_inter<(a1.x+5)) && (y_inter>a1.y && y_inter<(a1.y+5)))
             return true;
         else return false;
    }

    public boolean intersectNumb(Point end, int it, int jt, Point[] pts){
        for(int i=it; i<=jt; i++ ){
            if(isIntersect(pts[i], end))
               return true;
        }
        return false;
    }

    @Override
    public int compareTo(Point o) {
        if (Double.compare(y, o.y) == 0)
            return Double.compare(x, o.x);
        return Double.compare(y, o.y);
    }
}

    class Edge{
        public Point vert1, vert2;
        public double weight;

        public Edge(Point a, Point b, double weight){
            this.vert1 =a;
            this.vert2=b;
            this.weight=weight;
        }
}

public class Main {

   static void printWay(int v, int pred[], ArrayList<Integer> allMet) { if (v == -1) { return; }
        printWay(pred[v], pred,  allMet);

        allMet.add(v+1);
      //  System.out.print((v + 1) + " ");
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Point beg = new Point(0, 0);
        Point end = new Point(100, 100);
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.in"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Файл не найден!");
            return;
        }

        int n = scanner.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(scanner.nextInt(), scanner.nextInt());
        }

        for (int i = points.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (points[j].compareTo(points[j + 1]) > 0) {
                    Point tmp = points[j];
                    points[j] = points[j + 1];
                    points[j + 1] = tmp;
                }
            }
        }
       /* for (int i = 0; i < points.length; i++) {
            System.out.print(points[i].x);
            System.out.print(" ");
            System.out.print(points[i].y + "___");
        }*/
        int met = 0;
        Point end1;
        Point end2;
        Point beg1;
        Point beg2;
        ArrayList<Edge> edges = new ArrayList<>();
        int m1 = 2, m2 = 3;

        for (int i = 0; i < n; i++) {
            end1 = new Point(points[i].x, points[i].y + 5);
            end2 = new Point(points[i].x + 5, points[i].y);
            if (!beg.intersectNumb(end1, 0, i, points)) {
                if (beg.metka == 0)
                    beg.setMetka(++met);
                if (end1.metka == 0)
                    end1.setMetka(++met);

                edges.add(new Edge(beg, end1, beg.dist(end1)));
            }
            if (!beg.intersectNumb(end2, 0, i, points)) {
                if (beg.metka == 0)
                    beg.setMetka(++met);
                if (end2.metka == 0)
                    end2.setMetka(++met);
                edges.add(new Edge(beg, end2, beg.dist(end2)));
            }
        }


        for (int i = 0; i < n; i++) {
            beg1 = new Point(points[i].x, points[i].y + 5, m1);
            beg2 = new Point(points[i].x + 5, points[i].y, m2);
            for (int j = i + 1; j < n; j++) {

                end1 = new Point(points[j].x, points[j].y + 5);
                end2 = new Point(points[j].x + 5, points[j].y);
                for (int s = 0; s < edges.size(); s++) {
                    if (end1.samePointmet(edges.get(s).vert2)) {
                        end1.setMetka(edges.get(s).vert2.metka);
                        break;
                    }
                }
                for (int ss = 0; ss < edges.size(); ss++) {
                    if (end2.samePointmet(edges.get(ss).vert2)) {
                        end2.setMetka(edges.get(ss).vert2.metka);
                        continue;
                    }
                }
                if (!beg1.intersectNumb(end1, i + 1, j, points)) {
                    if (beg1.metka == 0)
                        beg1.setMetka(++met);
                    if (end1.metka == 0)
                        end1.setMetka(++met);
                    edges.add(new Edge(beg1, end1, beg1.dist(end1)));
                }
                if (!beg1.intersectNumb(end2, i + 1, j, points)) {
                    if (beg1.metka == 0)
                        beg1.setMetka(++met);
                    if (end2.metka == 0)
                        end2.setMetka(++met);
                    edges.add(new Edge(beg1, end2, beg1.dist(end2)));
                }
                if (!beg2.intersectNumb(end1, i + 1, j, points)) {
                    if (beg2.metka == 0)
                        beg2.setMetka(++met);
                    if (end1.metka == 0)
                        end1.setMetka(++met);
                    edges.add(new Edge(beg2, end1, beg2.dist(end1)));
                }
                if (!beg2.intersectNumb(end2, i + 1, j, points)) {
                    if (beg2.metka == 0)
                        beg2.setMetka(++met);
                    if (end2.metka == 0)
                        end2.setMetka(++met);
                    edges.add(new Edge(beg2, end2, beg2.dist(end2)));
                }
                if (j == i + 1) {
                    m1 = end1.getMetka();
                    m2 = end2.getMetka();
                }
            }
        }


        for (int i = 0; i < n; i++) {
            beg1 = new Point(points[i].x, points[i].y + 5);
            beg2 = new Point(points[i].x + 5, points[i].y);
            for (int s = 0; s < edges.size(); s++) {
                if (beg1.samePointmet(edges.get(s).vert2)) {
                    beg1.setMetka(edges.get(s).vert2.metka);
                    break;
                } else if (beg1.samePointmet(edges.get(s).vert1)) {
                    beg1.setMetka(edges.get(s).vert1.metka);
                    break;
                }
            }
            for (int ss = 0; ss < edges.size(); ss++) {
                if (beg2.samePointmet(edges.get(ss).vert2)) {
                    beg2.setMetka(edges.get(ss).vert2.metka);
                    break;
                } else if (beg2.samePointmet(edges.get(ss).vert1)) {
                    beg2.setMetka(edges.get(ss).vert1.metka);
                    break;
                }
            }


            if (beg1.metka != 0) {
                if (!beg1.intersectNumb(end, i, n - 1, points)) {
                    if (end.metka == 0)
                        end.setMetka(++met);
                    edges.add(new Edge(beg1, end, beg1.dist(end)));
                }
            }
            if (beg2.metka != 0) {
                if (!beg2.intersectNumb(end, i, n - 1, points)) {
                    if (end.metka == 0)
                        end.setMetka(++met);
                    edges.add(new Edge(beg2, end, beg2.dist(end)));
                }
            }
        }

        if (!beg.intersectNumb(end, 0, n - 1, points)) {
            edges.add(new Edge(beg, end, beg.dist(end)));
        }


        double INF = Integer.MAX_VALUE / 2;
        int kolV = met;
        int m = edges.size();
        ArrayList adj[] = new ArrayList[kolV]; //список смежности
        for (int i = 0; i < kolV; ++i) {
            adj[i] = new ArrayList();
        }
        ArrayList<Double> weights[] = new ArrayList[kolV]; //вес ребра в орграфе
        for (int i = 0; i < kolV; ++i) {
            weights[i] = new ArrayList();
        }
        boolean used[] = new boolean[kolV]; //массив для хранения информации о пройденных и не пройденных вершинах
        double dist[]; //массив для хранения расстояния от стартовой вершины
//массив предков, необходимых для восстановления кратчайшего пути из стартовой вершины
        int pred[];
        int start = 0; //стартовая вершина, от которой ищется расстояние до всех других

        for (int i = 0; i < m; ++i) {
            int u = edges.get(i).vert1.metka;
            int v = edges.get(i).vert2.metka;
            double w = edges.get(i).weight;
            u--;
            v--;
            adj[u].add(v);
            weights[u].add(w);
        }
        Arrays.fill(used, false);
        pred = new int[kolV];
        Arrays.fill(pred, -1);
        dist = new double[kolV];
        Arrays.fill(dist, INF);


        dist[start] = 0;
        for (int i=0; i<kolV; ++i) {
            int v = -1;
            for (int j=0; j<kolV; ++j)
                if (!used[j] && (v == -1 || dist[j] < dist[v]))
                    v = j;
            if (dist[v] == INF)
                break;
            used[v] = true;

            for (int j=0; j<adj[v].size(); ++j) {
                int to = (int)adj[v].get(j);
                        double len = weights[v].get(j);
                if (dist[v] + len < dist[to]) {
                    dist[to] = dist[v] + len;
                    pred[to] = v;
                }
            }
        }

    System.out.print("\n");
    int k = end.metka-1;

            ArrayList<Integer> allMet = new ArrayList<>();
            if(dist[k]!=INF){
                { printWay(k, pred, allMet); }
            }
            ArrayList<Point> path = new ArrayList<>();
            for(int i=0; i<allMet.size(); i++){
                for (int s = 0; s < edges.size(); s++) {
                        if(allMet.get(i)==edges.get(s).vert2.metka)
                        {
                            Point point = new Point(edges.get(s).vert2.x, edges.get(s).vert2.y);
                            path.add(point);
                            break;
                        } else  if(allMet.get(i)==edges.get(s).vert1.metka)
                        {
                            Point point = new Point(edges.get(s).vert1.x, edges.get(s).vert1.y);
                            path.add(point);
                            break;
                        }
                }
            }
            double allLength=0;
            for(int i=0; i<path.size()-1; i++){
                allLength+=path.get(i).dist( path.get(i+1));
            }

           /* System.out.print("\n");
            System.out.print(allLength);*/



       /* System.out.print(beg.getMetka());
        System.out.print(end.getMetka());
        for(int i=0; i<edges.size(); i++){
            System.out.print("(" + edges.get(i).vert1.x + " " + edges.get(i).vert1.y + " _ "+ edges.get(i).vert1.metka+ ")");
            System.out.print("  (" + edges.get(i).vert2.x + " " + edges.get(i).vert2.y + " _ "+ edges.get(i).vert2.metka+ ")\n");
        }*/
        long timeSpent = System.currentTimeMillis() - startTime;
        try {
            FileWriter fileWriter = new FileWriter("output.out");
            DecimalFormatSymbols s = new DecimalFormatSymbols();
            s.setDecimalSeparator('.');
            DecimalFormat f = new DecimalFormat("#,##0.0", s);
            String res = f.format(allLength);
            fileWriter.write(res);
            fileWriter.append("\n");
            for (int i = 0; i < path.size(); i++) {
                fileWriter.write("(");
                String text = String.valueOf((int)(path.get(i).x));
                fileWriter.append(text);
                fileWriter.append(";");
                String text1 = String.valueOf((int)path.get(i).y);
                fileWriter.append(text1);
                fileWriter.write(") ");
            }
           /* fileWriter.append("\n");
            for(int i=0; i<edges.size(); i++){
                fileWriter.write("(");
                fileWriter.append(String.valueOf((int)edges.get(i).vert1.x));
                fileWriter.append(";");
                fileWriter.append(String.valueOf((int)edges.get(i).vert1.y));
                fileWriter.append(" - ");
                fileWriter.append(String.valueOf((int)edges.get(i).vert2.x));
                fileWriter.append(";");
                fileWriter.append(String.valueOf((int)edges.get(i).vert2.y));
              *//*  fileWriter.append(" - ");
                fileWriter.append(String.valueOf(edges.get(i).weight));*//*
                fileWriter.write("); ");

            }
            fileWriter.append("\n");
            fileWriter.append(String.valueOf(edges.size()));*/
           /* fileWriter.append("\n");
            fileWriter.append("программа выполнялась " + timeSpent + " миллисекунд");*/
            fileWriter.flush();
        } catch (Exception ex) {
        }
    }


    }
