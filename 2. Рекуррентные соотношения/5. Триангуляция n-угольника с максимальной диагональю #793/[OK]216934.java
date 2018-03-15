import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Polygon {

    static double calcDiagonals(int i, int j, int k, ArrayList<Point> point)
    {
        return Math.max(Math.max(point.get(i).distance(point.get(j)),point.get(j).distance(point.get(k))),
                point.get(k).distance(point.get(i)));
    }

    public static void main(String[] arg) throws IOException {
        try{
            ArrayList<Point> points = new ArrayList<>();

            Scanner in = new Scanner(new File("input.txt"));
            PrintWriter out = new PrintWriter("output.txt");
            int n = in.nextInt();

            if(n==3){
                out.println(0);
                out.flush();

            }

            else {
                while (in.hasNext()) {
                    points.add(new Point(in.nextInt(), in.nextInt()));
                }
                if(n==4){
                    out.format("%.2f%n", Math.min(points.get(0).distance(points.get(2)),points.get(1).distance(points.get(3))));
                    out.flush();
                }else {

                    double[][] m = new double[n + 1][n];

                    n--;
                    for (int i = 1; i <= n; i++)
                        m[i][i] = 0;

                    for (int p = 2; p <= n; p++) {
                        for (int i = 1; i <= n - p + 1; i++) {
                            int j = i + p - 1;
                            m[i][j] = Integer.MAX_VALUE;

                            for (int k = i; k <= j - 1; k++) {
                                double d = calcDiagonals(i - 1, k, j, points);
                                double dMax = Math.max(Math.max(m[i][k], m[k + 1][j]), d);
                                if (dMax < m[i][j]) {
                                    m[i][j] = dMax;
                                }
                            }
                        }
                    }
                    out.format("%.2f%n", m[1][n]);
                    out.flush();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}