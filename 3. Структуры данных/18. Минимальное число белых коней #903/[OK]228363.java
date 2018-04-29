import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static qPoint[] kletki;
    public static int[][] matrix;
    
    public static void main(String[] args) throws IOException {
        double time = System.nanoTime();
        Scanner sc = new Scanner(new File("in.txt"));
        FileWriter fw = new FileWriter("out.txt");

        int n = sc.nextInt();
        int m = sc.nextInt();

        int horse = 0;

        matrix = new int[n][m];
        kletki = new qPoint[n * m];

        for (int i = 0; i < n * m; i++) {
            kletki[i] = new qPoint();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    horses(i, j, matrix, n, m);
                    horse++;
                }
            }
        }

        fw.append(String.valueOf(horse));
        sc.close();
        fw.close();

        double last = System.nanoTime();

        System.out.println(last - time);
    }

    public static void horses(int a, int b, int mat[][], int n, int m) {
        
        qPoint temp = new qPoint(0);
        int current = 0;
        int last = 1;
        int to_check_I = 0;
        int to_check_J = 0;

        

        kletki[0].x = b;
        kletki[0].y = a;
        mat[a][b] = 2;
        do {
            to_check_I = kletki[current].y;
            to_check_J = kletki[current].x;

            if ((to_check_I - 1 >= 0) && (to_check_J - 2 >= 0) && (to_check_I - 1 < n) && (to_check_J - 2 < m) && (mat[to_check_I - 1][to_check_J - 2] == 0)) {
                mat[to_check_I - 1][to_check_J - 2] = 2;
                temp.x = to_check_J - 2;
                temp.y = to_check_I - 1;
                kletki[last++].copy(temp);
            }

            if ((to_check_I - 2 >= 0) && (to_check_J - 1 >= 0) && (to_check_I - 2 < n) && (to_check_J - 1 < m) && (mat[to_check_I - 2][to_check_J - 1] == 0)) {
                mat[to_check_I - 2][to_check_J - 1] = 2;
                temp.x = to_check_J - 1;
                temp.y = to_check_I - 2;
                kletki[last++].copy(temp);
            }

            if ((to_check_I - 2 >= 0) && (to_check_J + 1 >= 0) && (to_check_I - 2 < n) && (to_check_J + 1 < m) && (mat[to_check_I - 2][to_check_J + 1] == 0)) {
                mat[to_check_I - 2][to_check_J + 1] = 2;
                temp.x = to_check_J + 1;
                temp.y = to_check_I - 2;
                kletki[last++].copy(temp);
            }

            if ((to_check_I - 1 >= 0) && (to_check_J + 2 >= 0) && (to_check_I - 1 < n) && (to_check_J + 2 < m) && (mat[to_check_I - 1][to_check_J + 2] == 0)) {
                mat[to_check_I - 1][to_check_J + 2] = 2;
                temp.x = to_check_J + 2;
                temp.y = to_check_I - 1;
                kletki[last++].copy(temp);
            }

            if ((to_check_I + 1 >= 0) && (to_check_J + 2 >= 0) && (to_check_I + 1 < n) && (to_check_J + 2 < m) && (mat[to_check_I + 1][to_check_J + 2] == 0)) {
                mat[to_check_I + 1][to_check_J + 2] = 2;
                temp.x = to_check_J + 2;
                temp.y = to_check_I + 1;
                kletki[last++].copy(temp);
            }

            if ((to_check_I + 2 >= 0) && (to_check_J + 1 >= 0) && (to_check_I + 2 < n) && (to_check_J + 1 < m) && (mat[to_check_I + 2][to_check_J + 1] == 0)) {
                mat[to_check_I + 2][to_check_J + 1] = 2;
                temp.x = to_check_J + 1;
                temp.y = to_check_I + 2;
                kletki[last++].copy(temp);
            }

            if ((to_check_I + 2 >= 0) && (to_check_J - 1 >= 0) && (to_check_I + 2 < n) && (to_check_J - 1 < m) && (mat[to_check_I + 2][to_check_J - 1] == 0)) {
                mat[to_check_I + 2][to_check_J - 1] = 2;
                temp.x = to_check_J - 1;
                temp.y = to_check_I + 2;
                kletki[last++].copy(temp);
            }

            if ((to_check_I + 1 >= 0) && (to_check_J - 2 >= 0) && (to_check_I + 1 < n) && (to_check_J - 2 < m) && (mat[to_check_I + 1][to_check_J - 2] == 0)) {
                mat[to_check_I + 1][to_check_J - 2] = 2;
                temp.x = to_check_J - 2;
                temp.y = to_check_I + 1;
                kletki[last++].copy(temp);
            }

            current++;


        } while (current != last);

    }

}

class qPoint {
    int x;
    int y;

    public qPoint() {
        this.x = -1;
        this.y = -1;
    }

    public qPoint(int val) {
        this.x = val;
        this.y = val;
    }

    public void copy(qPoint point) {
        this.x = point.x;
        this.y = point.y;
    }
}