import javafx.util.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

/**
 * Created by Евгения on 15.04.2017.
 */
public class Test {

    public static class MyPoint {
        int x;
        int y;

        MyPoint() {
            x = 0;
            y = 0;
        }

        MyPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean isInTheLab() {
            if ((x < 0) || (y < 0) || (x >= n) || (y >= m)) {
                return false;
            }
            return true;
        }

        MyPoint left() {
            return new MyPoint(this.x, this.y - 1);
        }

        MyPoint down() {
            return new MyPoint(this.x + 1, this.y);
        }

        MyPoint right() {
            return new MyPoint(this.x, this.y + 1);
        }

        MyPoint up() {
            return new MyPoint(this.x - 1, this.y);
        }

    }

    public static int n, m, ways = 0;
    public static int[][] labirinth;
    enum directions {left, down, right, up};
    //private static final int left = 0, down = 1, right = 2, up = 3;

    public static boolean makeWay(MyPoint startPoint) {
        Stack<Pair<MyPoint, directions>> stepStack = new Stack<>();
        stepStack.push(new Pair<>(startPoint, directions.up));

        Pair<MyPoint, directions> p;
        while (!stepStack.empty()) {
            p = stepStack.peek();
            MyPoint curr = p.getKey();
            labirinth[curr.x][curr.y] = 1;
            if(curr.x == n-1){
                stepStack.clear();
                return true;
            }

            MyPoint l = curr.left();
            MyPoint d = curr.down();
            MyPoint r = curr.right();
            MyPoint u = curr.up();
            switch (p.getValue()) {
                case up: {
                    if (l.isInTheLab() && labirinth[l.x][l.y] == 0) {
                        stepStack.push(new Pair<>(l, directions.right));
                    }
                    else if (d.isInTheLab() && labirinth[d.x][d.y] == 0) {
                        stepStack.push(new Pair<>(d, directions.up));
                    }
                    else if (r.isInTheLab() && labirinth[r.x][r.y] == 0) {
                        stepStack.push(new Pair<>(r, directions.left));
                    }
                    else {
                        labirinth[curr.x][curr.y] = 1;
                        stepStack.pop();
                    }
                    break;
                }
                case left: {
                    if (d.isInTheLab() && labirinth[d.x][d.y] == 0) {
                        stepStack.push(new Pair<>(d, directions.up));
                    }
                    else if (r.isInTheLab() && labirinth[r.x][r.y] == 0) {
                        stepStack.push(new Pair<>(r, directions.left));
                    }
                    else if (u.isInTheLab() && labirinth[u.x][u.y] == 0) {
                        stepStack.push(new Pair<>(u, directions.down));
                    }
                    else {
                        labirinth[curr.x][curr.y] = 1;
                        stepStack.pop();
                    }
                    break;
                }
                case down: {
                    if (r.isInTheLab() && labirinth[r.x][r.y] == 0) {
                        stepStack.push(new Pair<>(r, directions.left));
                    }
                    else if (u.isInTheLab() && labirinth[u.x][u.y] == 0) {
                        stepStack.push(new Pair<>(u, directions.down));
                    }
                    else if (l.isInTheLab() && labirinth[l.x][l.y] == 0) {
                        stepStack.push(new Pair<>(l, directions.right));
                    }
                    else {
                        labirinth[curr.x][curr.y] = 1;
                        stepStack.pop();
                    }
                    break;
                }
                case right: {
                    if (u.isInTheLab() && labirinth[u.x][u.y] == 0) {
                        stepStack.push(new Pair<>(u, directions.down));
                    }
                    else if (l.isInTheLab() && labirinth[l.x][l.y] == 0) {
                        stepStack.push(new Pair<>(l, directions.right));
                    }
                    else if (d.isInTheLab() && labirinth[d.x][d.y] == 0) {
                        stepStack.push(new Pair<>(d, directions.up));
                    }
                    else {
                        labirinth[curr.x][curr.y] = 1;
                        stepStack.pop();
                    }
                    break;
                }
                default: {
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt"), StandardCharsets.UTF_8));
        String line = reader.readLine();

        String[] lines = line.split(" ");
        n = Integer.parseInt(lines[0]);
        m = Integer.parseInt(lines[1]);

        labirinth = new int[n][m];

        for (int i = 0; i < n; i++) {
            line = reader.readLine();
            lines = line.split("");
            for (int j = 0; j < m; j++) {
                labirinth[i][j] = Integer.parseInt(lines[j]);
            }
        }

        ways = 0;
        for (int k = 0; k < m; k++) {
            if (labirinth[0][k] == 0) {
                if(makeWay(new MyPoint(0,k))){
                    ways++;
                }
                else {
                    ways = 0;
                    break;
                }
            }
        }

        File file = new File("out.txt");
        PrintWriter writer = new PrintWriter(file);
        if (ways == 0) {
            writer.write("Impossible");
        } else {
            writer.write("Possible");
        }
        writer.close();
    }
}