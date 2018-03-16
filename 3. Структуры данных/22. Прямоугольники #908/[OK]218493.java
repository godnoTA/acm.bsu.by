import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Cell {
    int colour = 1;
    boolean marked = false;
}

class Figure{
    int square;
    int colour;
    Figure(int sq, int clr){
        square = sq;
        colour = clr;
    }
}

public class Main implements Runnable {
    int[][] boundaries;
    Cell[][] matrix;
    int acc;
    int a;
    int b;
    int n;

    void mark(int i, int j, int colour){
        matrix[i][j].marked = true;
        
        acc++;

        if (i != a - 1 && matrix[i + 1][j].colour == colour && !matrix[i + 1][j].marked) mark(i + 1, j, colour);
        if (i != 0 && matrix[i - 1][j].colour == colour && !matrix[i - 1][j].marked) mark(i - 1, j, colour);
        if (j != b - 1 && matrix[i][j + 1].colour == colour && !matrix[i][j + 1].marked) mark(i, j + 1, colour);
        if (j != 0 && matrix[i][j - 1].colour == colour && !matrix[i][j - 1].marked) mark(i, j - 1, colour);

        if (i != a - 1 && j != b - 1 && !matrix[i + 1][j + 1].marked) {
            if (boundaries[i + 1][j + 1] == colour && matrix[i + 1][j + 1].colour == colour)
                mark(i + 1, j + 1, colour);
        }
        if (i != a - 1 && j != 0 && !matrix[i + 1][j - 1].marked) {
            if (boundaries[i + 1][j] == colour && matrix[i + 1][j - 1].colour == colour)
                mark(i + 1, j - 1, colour);
        }
        if (i != 0 && j != 0 && !matrix[i - 1][j - 1].marked) {
            if (boundaries[i][j] == colour && matrix[i - 1][j - 1].colour == colour)
                mark(i - 1, j - 1, colour);
        }

        if (i != 0 && j != b - 1 && !matrix[i - 1][j + 1].marked) {
            if (boundaries[i][j + 1] == colour && matrix[i - 1][j + 1].colour == colour)
                mark(i - 1, j + 1, colour);
        }


    }

    @Override
    public void run(){
        try {
            List<String> lines = Files.readAllLines(Paths.get("in.txt"));
            String[] line1 = lines.get(0).split(" ");
            a = Integer.parseInt(line1[0]);
            b = Integer.parseInt(line1[1]);
            n = Integer.parseInt(line1[2]);

            int cx = b / 2;
            int cy = a / 2;


            matrix = new Cell[a][b];

            boundaries = new int[a + 1][b + 1];

            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    matrix[i][j] = new Cell();
                }
            }

            for (int i = 0; i < a + 1; i++) {
                for (int j = 0; j < b + 1; j++) {
                    boundaries[i][j] = 1;
                }
            }


            for (int i = 1; i <= n; i++) {
                int x1, x2, y1, y2, colour;
                String[] lineI = lines.get(i).split(" ");
                x1 = Integer.parseInt(lineI[0]);
                y1 = Integer.parseInt(lineI[1]);
                x2 = Integer.parseInt(lineI[2]);
                y2 = Integer.parseInt(lineI[3]);
                colour = Integer.parseInt(lineI[4]);

                for (int j = cy - y1; j > cy - y2; j--) {
                    for (int k = cx + x1; k < cx + x2; k++) {
                        matrix[j - 1][k].colour = colour;
                        boundaries[j - 1][k] = colour;
                        boundaries[j - 1][k + 1] = colour;
                        boundaries[j][k] = colour;
                        boundaries[j][k + 1] = colour;
                    }
                }
            }

            List<Figure> figures = new ArrayList<>(100);


            try {
                for (int i = 0; i < a; i++) {
                    for (int j = 0; j < b; j++) {
                        if (!matrix[i][j].marked) {
                            acc = 0;
                            int color = matrix[i][j].colour;
                            mark(i, j, color);
                            figures.add(new Figure(acc, color));
                        }
                    }
                }
            } catch (StackOverflowError e) {
            }

            Collections.sort(figures, (f1, f2) -> {
                if (f1.colour == f2.colour) return f1.square - f2.square;
                else return f1.colour - f2.colour;
            });

            Files.createFile(Paths.get("out.txt"));
            PrintWriter pw = new PrintWriter("out.txt");

            for (int i = 0; i < figures.size(); i++) {
                pw.println(figures.get(i).colour + " " + figures.get(i).square);
            }
            pw.flush();
        }
        catch (Exception e){}
    }

    public static void main(String[] args) {

        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
}
