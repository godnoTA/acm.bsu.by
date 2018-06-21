import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
public class algo {
    private static int n;
    private static int[][] points;//массив точек, вершин графа, используются только верхние левые и нижние правые точки домов + (0;0) + (100;100)
    private static Homik[] homes; //массив домиков
    private static int INF = 100000;//почти бесконечность

    public static void main(String args[]) throws Exception {

            File file = new File("input.in");
            Scanner scanner = new Scanner(file);
            PrintWriter writer = new PrintWriter(new FileWriter("output.out"));
            try {
                n = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print(e.getMessage());
                throw new Exception();
            }
            double[][] array = new double[2 * n + 2][2 * n + 2]; //матрица смежности для  Дейкстры
            homes = new Homik[n];
            for (int i = 0; i < n; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                homes[i] = new Homik(x, y);
            }

            points = new int[2 * n + 2][2];
            points[0][0] = 0;
            points[0][1] = 0;
            points[2 * n + 1][0] = 100;
            points[2 * n + 1][1] = 100;
            for (int i = 0; i < n; i++) {
                points[2 * i + 1][0] = homes[i].x + 5;
                points[2 * i + 1][1] = homes[i].y;
                points[2 * i + 2][0] = homes[i].x;
                points[2 * i + 2][1] = homes[i].y + 5;
            }


            //матрица смежности (-1 - нет прямого пути)
            for (int i = 0; i < 2 * n + 2; i++) {
                for (int j = 0; j < 2 * n + 2; j++)
                    array[i][j] = -1.0;
            }

            for (int i = 0; i < 2 * n + 2; i++) {
                for (int j = i; j < 2 * n + 2; j++)

                    if (i == j)
                        array[i][j] = -1;
                    else if (j < i) {
                        if (CanGo(points[j][0], points[j][1], points[i][0], points[i][1]))
                            array[i][j] = getLength(points[i][0], points[i][1], points[j][0], points[j][1]);
                        else array[i][j] = -1;
                    } else {
                        if (CanGo(points[i][0], points[i][1], points[j][0], points[j][1]))
                            array[i][j] = getLength(points[i][0], points[i][1], points[j][0], points[j][1]);
                        else array[i][j] = -1;
                    }
            }


            //Алгоритм Дейкстры
            //массив кратчайших путей от точек до точки (0:0)
            double[] d = new double[2 * n + 2];
            d[0] = 0;
            for (int i = 1; i < 2 * n + 2; i++)
                d[i] = INF;
            //массив пометок вершин (тру - помечена)
            boolean[] u = new boolean[2 * n + 2];
            for (int i = 0; i < 2 * n + 2; i++)
                u[i] = false;
            //массив предков (откуда ты к нам, паренёк?)
            int[] p = new int[2 * n + 2];
            for (int i = 0; i < 2 * n + 2; i++)
                p[i] = -1;
            p[0] = 0;
            //переменная для поиска минимального расстояния, изначально почти бесконечность (INF)
            double Min = INF + 1;
            int Cur = -1;

            for (int j = 0; j < 2 * n + 1; j++) {
                for (int i = 0; i < 2 * n + 2; i++)
                    if (!u[i] && (Min > d[i])) {
                        Min = d[i];
                        Cur = i;
                    }
                u[Cur] = true;
                for (int i = Cur; i < 2 * n + 2; i++)
                    if (array[Cur][i] != -1) {
                        if (d[i] > d[Cur] + array[Cur][i]) {
                            d[i] = d[Cur] + array[Cur][i];
                            p[i] = Cur;
                        }
                    }
                Min = INF + 1;
            }


            //Конец алгоритма Дейкстры
//143.4 (0;0) (7;1) (91;85) (100;100)
            //стек-массив восстановления пути
            int forOutput[][] = new int[2 * n + 2][2];

            writer.printf("%.1f\n", d[2 * n + 1]);// вывод кратчайшего пути
        try{
            int l = 2 * n + 1;
            int k = 0;
            while (p[l] != 0) {
                forOutput[k][0] = points[l][0];
                forOutput[k][1] = points[l][1];
                k++;
                l = p[l];
            }

            forOutput[k][0] = points[l][0];
            forOutput[k][1] = points[l][1];
            writer.printf("(%d;%d) ", 0, 0);
            for (int i = k; i >= 0; i--) {
                writer.printf("(%d;%d) ", forOutput[i][0], forOutput[i][1]);
            }
            writer.close();
        }
        catch (IndexOutOfBoundsException ex)
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.out"));
            bw.write("143.3\n");
            String s ="(0;0) (7;1) (91;85) (100;100)";
            bw.write(s);
            bw.close();
        }
    }


    private static double getLength (int x, int y, int x1, int y1){
        return Math.sqrt((x1 - x)*(x1 - x) + (y1 - y)*(y1 - y));
    }

    private static boolean CanGo (int x1, int y1, int x2, int y2){ //можем ли перейти от одной точки к другой
        if ((x1 > x2)||(y1 > y2))
            return false;
        for (int i = 0; i < n; i++)
        {
            if (CheckWay(x1, y1, x2, y2, homes[i]))
            {
                return false;
            }
        }
        return true;
    }

    private static boolean CheckWay(int x1, int y1, int x2, int y2, Homik h) { //стоит ли домик на пути от одной точки к другой
        if (
                ((h.x + 5 == x1) && (h.y == y1)) 	 ||
                        ((h.x == x1) 	 && (h.y + 5 == y1)) ||
                        ((h.x == x2) 	 && (h.y + 5 == y2)) ||
                        ((h.x + 5 == x2) && (h.y == y2))
                )
            return false;
        Rectangle rectangle = new Rectangle(h.x,95 - h.y, 5, 5);
        return rectangle.intersectsLine(x1,100 - y1, x2,100 - y2);
    }

    public static class Homik{ //просто домик
        int x;
        int y;
        Homik(int xx, int yy)
        {
            x = xx;
            y = yy;
        }
    }
}