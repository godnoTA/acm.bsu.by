import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Евгения on 07.04.2017.
 */

public class Test{

    static int n,m;
    static boolean [][] desk;
    static int [][] steps;

    public static class MyPoint {
        int x;
        int y;

        MyPoint(){
            x=0;
            y=0;
        }

        MyPoint(int x, int y){
            this.x=x;
            this.y=y;
        }

        boolean isOnTheDesk(){
            if ((x <= 0) || (y <= 0) || (x > n) || (y > m) || (!desk[x][y]))
                return false;
            desk[x][y] = false;
            return true;
        }
    }

    static MyPoint [] stepHorse = {new MyPoint(-2,-1), new MyPoint(-2,1), new MyPoint(-1,-2), new MyPoint(-1,2),
                            new MyPoint(1,-2),new MyPoint(1,2),new MyPoint(2,-1),new MyPoint(2,1)};
    static Queue<MyPoint> myPointQueue = new LinkedList <>();

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt"), StandardCharsets.UTF_8));
        String line = reader.readLine();

        String[] lines = line.split(" ");
        n = Integer.parseInt(lines[0]);
        m = Integer.parseInt(lines[1]);

        desk = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            line = reader.readLine();
            lines = line.split(" ");
            for (int j = 1; j < m + 1; j++) {
                if (Integer.parseInt(lines[j - 1]) == 0) {
                    desk[i][j] = true;
                } else {
                    desk[i][j] = false;
                }
            }
        }
        line = reader.readLine();
        lines = line.split(" ");
        int x1 = Integer.parseInt(lines[0]);
        int y1 = Integer.parseInt(lines[1]);
        int x2 = Integer.parseInt(lines[2]);
        int y2 = Integer.parseInt(lines[3]);

        MyPoint pStart = new MyPoint(x1, y1);
        MyPoint pFinish = new MyPoint(x2, y2);

        if (pStart.isOnTheDesk()) {
            myPointQueue.add(pStart);
            desk[x1][y1] = false;
        }
        steps = new int[n+1][m+1];
        int countSteps = 0;
        while (!myPointQueue.isEmpty()) {
            MyPoint p = myPointQueue.peek();
            if (p.x == pFinish.x && p.y == pFinish.y) {
                countSteps = steps[pFinish.x][pFinish.y];
                break;
            }
            myPointQueue.remove();

            for (int i = 0; i < 8; i++) {
                MyPoint pNear = new MyPoint(p.x - stepHorse[i].x, p.y - stepHorse[i].y);
                if (pNear.isOnTheDesk()){
                    steps[pNear.x][pNear.y] = steps[p.x][p.y]+1;
                    countSteps = steps[p.x][p.y];
                    myPointQueue.add(pNear);
                }
            }
        }

        File file = new File("out.txt");
        PrintWriter writer = new PrintWriter(file);
        if (myPointQueue.isEmpty()) {
            writer.write("No");
            System.out.println("No");
        } else {
            writer.write(String.valueOf(countSteps));
            System.out.println(countSteps);
        }
        writer.close();
    }
}
