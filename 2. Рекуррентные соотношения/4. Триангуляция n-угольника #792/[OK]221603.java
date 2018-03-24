import java.io.*;
import java.util.StringTokenizer;

public class Task1 {
    public static Double result = 0.0;
    public static class Point{
        Integer x;
        Integer y;
        public Point(String xy){
            StringTokenizer st = new StringTokenizer(xy, " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
        }
    }
    public static void min(Integer[][] min, Integer i, Integer j, Point[] p){
        Integer k = min[i][j];
        if(i < k - 1){
            result += distance(p[i], p[k]);
            min(min, i, k, p);
        }
        if(k < j - 1){
            result += distance(p[k], p[j]);
            min(min, k, j, p);
        }

    }
    public static double distance(Point a, Point b){
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        FileWriter fw = new FileWriter("output.txt");
        String sLength = br.readLine();
        Integer length = Integer.parseInt(sLength);
        Point[] array = new Point[length];
        Double[][] matrix = new Double[length][length];
        Integer[][] minDistances = new Integer[length][length];
        Double buffer = Double.MAX_VALUE;
        Integer i = 0;
        Integer j;
        String s;

        while ((s = br.readLine()) != null) {
            array[i++] = new Point(s);
        }

        for (i = 0; i < length; i++) {
            matrix[i][i] = 0.0;
            if(i < length - 1){
                matrix[i][(i + 1) % length] = 0.0;
            }
            if(i < length - 1){
                matrix[i][(i + 2) % length] = 0.0;
            }
        }

        for (int l = 3; l <= length; l++) {
            for (i = 0; i <= length - l; i++) {
                j = i + l - 1;
                matrix[i][j] = Double.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    buffer = matrix[i][k] + matrix[k][j] + distance(array[i],array[k]) + distance(array[k],array[j]);
                    if(buffer < matrix[i][j]){
                        matrix[i][j] = buffer;
                        minDistances[i][j] = k;
                    }
                }
            }
        }
        min(minDistances, 0, length - 1, array);
        fw.write(String.format("%.2f", result));
        fw.close();
    }
}
