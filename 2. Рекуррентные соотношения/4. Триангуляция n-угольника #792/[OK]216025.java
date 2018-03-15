
import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static Double q;
    static Integer j;
    static class Point {
        private long x;
        private long y;

        public Point() {x = 0; y= 0;}
        public Point(long x1, long y1) {x = x1; y = y1;}
        public Point (Point p) {x = p.getX(); y = p.getY();}

        public long getX() {return x;}
        public long getY() {return y;}
        public void setX(long x1) {x = x1;}
        public void setY(long y1) {y = y1;}
        public String toString () {
            return x + " " + y;
        }
        public double dist(Point x/*,boolean b*/)
        {
            /*
            if (b == true)
                return 0;
            else*/
            return Math.sqrt((x.getX() - this.getX())*(x.getX() - this.getX()) + (x.getY() - this.getY())*(x.getY() - this.getY()));
        }
    }

    public static void RetriveEdges (Integer e[][], int i, int j, ArrayList<Double> arr1, ArrayList<Point> arr2)
    {
        int k = e[i][j];
        if (i < k - 1) {
            arr1.add(arr2.get(i).dist(arr2.get(k)));
            RetriveEdges(e,i,k,arr1,arr2);
        }
        if (k < j - 1)
        {
            arr1.add(arr2.get(k).dist(arr2.get(j)));
            RetriveEdges(e,k,j,arr1, arr2);
        }

    }

    public static void main(String[] args) {


        File fin = new File("input.txt");

        try {
            FileReader r = new FileReader(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = null;
        try {
            sc = new Scanner(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int number = sc.nextInt();

        Double w[][] = new Double[number][number + 2];
        Integer e[][] = new Integer[number][number];

        ArrayList<Point> arr = new ArrayList<>();
        while (sc.hasNext())
        {
            arr.add(new Point(sc.nextLong(),sc.nextLong()));
        }



        for (int i = 0; i < number; i++)
        {
            w[i][i] = 0.0;
            w[i][i+1] = 0.0;
            w[i][i+2] = 0.0;

        }

        // int j = 0;
        //double q;

        for (int m = 3; m <= number; m++)
        {
            for (int i = 0; i <= number - m; i++)
            {
                j = i + m - 1;
                w[i][j] = Double.MAX_VALUE;
                for (int k = i +1; k <= j - 1; k++)
                {
                    q = w[i][k] + w[k][j] + arr.get(i).dist(arr.get(k)/*, Math.abs(i - k) < 1*/) + arr.get(k).dist(arr.get(j)/*, Math.abs(j - k) <= 1)*/);
                    if (q < w[i][j])
                    {
                        w[i][j] = q;
                        e[i][j] = k;
                    }
                }

            }
        }

        /*
        for (int i = 0; i < number; i++)
        {
            System.out.println();
            for (j = 0; j < number; j++)
            {
                System.out.print(w[i][j] + " ");
            }
        }

        System.out.println();

        for (int i = 0; i < number; i++)
        {
            System.out.println();
            for (int j = 0; j < number; j++)
            {
                System.out.print(e[i][j] + " ");
            }
        }
*/


        ArrayList<Double> ari = new ArrayList<>();
        RetriveEdges(e,0,number - 1,ari,arr);
        double sol = 0.0;
        for ( int i = 0; i < ari.size(); i++)
        {
            sol += ari.get(i);
        }

        DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));

        FileWriter f = null;
        try {
            f = new FileWriter("output.txt");
            f.write(df.format(sol));
            f.close();


        } catch (IOException ee){}
    }
}
