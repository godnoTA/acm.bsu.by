import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

class Figure implements Comparable<Figure> {
    public int color;
    public int square;

    public Figure(int c, int s) {
        color = c;
        square = s;
    }

    @Override
    public int compareTo(Figure figure) {
        if (this.color == figure.color) {
            return this.square - figure.square;
        }
        return this.color - figure.color;
    }
}
public class Main implements Runnable {
    public static int a, b;
    public static int[][] matrix;
    public static int[][] timeMatrix;
    public static int f(int counter, int x, int y, int color) {
        if (matrix[x][y] == color) {
            counter++;
            matrix[x][y] = 0;
            if ((x - 1 >= 0) && (y < b) && matrix[x - 1][y] == color) {
                counter = f(counter, x - 1, y, color);
            }
            if ((x - 1 >= 0) && (y + 1 <= b - 1) &&  (matrix[x - 1][y + 1] == color)
                    && (
                        (timeMatrix[x][y] >= timeMatrix[x - 1][y]) && (timeMatrix[x][y] >= timeMatrix[x][y + 1])
                        || ((timeMatrix[x - 1][y + 1] >= timeMatrix[x - 1][y]) && (timeMatrix[x - 1][y + 1] >= timeMatrix[x][y + 1])
                    ))
                    )
            {
                counter = f(counter, x - 1, y + 1, color);
            }
            if ((y + 1 <= b - 1) && (x < a) && matrix[x][y + 1] == color) {
                counter = f(counter, x, y + 1, color);
            }
            if ((x + 1 <= a - 1) && (y + 1 <= b - 1) && matrix[x + 1][y + 1] == color
                    && (
                    (timeMatrix[x][y] >= timeMatrix[x][y + 1]) && (timeMatrix[x][y] >= timeMatrix[x + 1][y])
                            || ((timeMatrix[x + 1][y + 1] >= timeMatrix[x][y + 1]) && (timeMatrix[x + 1][y + 1] >= timeMatrix[x + 1][y])
                    ))
                    )
            {
                counter = f(counter, x + 1, y + 1, color);
            }
            if ((x + 1 <= a - 1) && (y < b) && matrix[x + 1][y] == color) {
                counter = f(counter, x + 1, y, color);
            }
            if ((x + 1 <= a - 1) && (y - 1 >= 0) && matrix[x + 1][y - 1] == color
                    && (
                    (timeMatrix[x][y] >= timeMatrix[x][y - 1]) && (timeMatrix[x][y] >= timeMatrix[x + 1][y])
                            || ((timeMatrix[x + 1][y - 1] >= timeMatrix[x][y - 1]) && (timeMatrix[x + 1][y - 1] >= timeMatrix[x + 1][y])
                    ))
                    )
            {
                counter = f(counter, x + 1, y - 1, color);
            }
            if ((y - 1 >= 0) && (x < a) && matrix[x][y - 1] == color) {
                counter = f(counter, x, y - 1, color);
            }
            if ((x - 1 >= 0) && (y - 1 >= 0) && matrix[x - 1][y - 1] == color
                    && (
                    (timeMatrix[x][y] >= timeMatrix[x - 1][y]) && (timeMatrix[x][y] >= timeMatrix[x][y - 1])
                            || ((timeMatrix[x - 1][y - 1] >= timeMatrix[x - 1][y]) && (timeMatrix[x - 1][y - 1] >= timeMatrix[x][y - 1])
                    ))
                    )
            {
                counter = f(counter, x - 1, y - 1, color);
            }
        }
        return counter;
    }

    public static int toNewX(int x) {
        return b / 2 + x;
    }

    public static int toNewY(int y) {
        return a / 2 - y;
    }

    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {

            FileReader fr = new FileReader("in.txt");
            BufferedReader br = new BufferedReader(fr);

            FileWriter fwr = new FileWriter("out.txt", false);
            BufferedWriter writer = new BufferedWriter(fwr);

            ArrayList<Figure> figures = new ArrayList<>();

            String[] s1 = br.readLine().split(" ");
            a = Integer.valueOf(s1[0]);
            b = Integer.valueOf(s1[1]);

            int n = Integer.valueOf(s1[2]);

            matrix = new int[a][b];
            timeMatrix = new int[a][b];

            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    matrix[i][j] = 1;
                }
            }

            int time = 1;

            for (int i = 0; i < n; i++) {
                String[] s = br.readLine().split(" ");
                int x1 = Integer.valueOf(s[0]);
                int y1 = Integer.valueOf(s[1]);
                int x2 = Integer.valueOf(s[2]);
                int y2 = Integer.valueOf(s[3]);

                int color = Integer.valueOf(s[4]);

                for (int k = toNewY(y2); k < toNewY(y1); k++) {
                    for (int j = toNewX(x1); j < toNewX(x2); j++) {
                        matrix[k][j] = color;
                        timeMatrix[k][j] = time;
                    }
                }
                time++;
            }

            /*
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    System.out.print((matrix[i][j]) + " ");
                }
                System.out.println();
            }

            System.out.println("---------------------------");
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    System.out.print((timeMatrix[i][j]) + " ");
                }
                System.out.println();
            }

*/

            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    if (matrix[i][j] != 0) {
                        figures.add(new Figure(matrix[i][j], f(0, i, j, matrix[i][j])));
                    }
                }
            }

            Collections.sort(figures);

            for (int i = 0;  i < figures.size(); i++) {
                writer.append(figures.get(i).color + " " + figures.get(i).square);
                writer.append('\n');
            }

            writer.close();
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
