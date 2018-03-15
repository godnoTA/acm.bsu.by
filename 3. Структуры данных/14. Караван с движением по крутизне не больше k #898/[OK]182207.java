import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Point2D {
    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + Integer.toString(this.x) + "," + Integer.toString(this.y) + ")";
    }
}

class MNode {
    public int heigth;
    public boolean isVisited;
    public int minWayLength;
    public Queue<Point2D> adjNodes;

    public MNode(int heigth) {
        this.heigth = heigth;
        this.isVisited = false;
        this.minWayLength = Integer.MAX_VALUE;
        this.adjNodes = new LinkedList<>();
    }

    @Override
    public String toString() {
        return Integer.toString(this.heigth);
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("in.txt")));
        FileWriter writer = new FileWriter("out.txt", false);


        int n = scan.nextInt();
        int m = scan.nextInt();
        MNode[][] heigths = new MNode[n][m];
        for (int i = 0; i < n; i++) {
            for (int g = 0; g < m; g++) {
                heigths[i][g] = new MNode(scan.nextInt());
            }
        }

        int K = scan.nextInt();

        Point2D p1 = new Point2D(scan.nextInt() - 1, scan.nextInt() - 1);
        Point2D p2 = new Point2D(scan.nextInt() - 1, scan.nextInt() - 1);


        for (int i = 0; i < n; i++) {
            for (int g = 0; g < m; g++) {
                if (checkTop(heigths,i,g,K)) {
                    heigths[i][g].adjNodes.add(new Point2D(i-1,g));
                }
                if (checkBot(heigths,i,g,K)) {
                    heigths[i][g].adjNodes.add(new Point2D(i+1,g));
                }
                if (checkRight(heigths,i,g,K)) {
                    heigths[i][g].adjNodes.add(new Point2D(i,g+1));
                }
                if (checkLeft(heigths,i,g,K)) {
                    heigths[i][g].adjNodes.add(new Point2D(i,g-1));
                }
            }
        }

        Queue<Point2D> iterationNodes = new LinkedList<>();



        MNode startNode = heigths[p1.getX()][p1.getY()];
        startNode.isVisited = true;
        startNode.minWayLength = 0;

        int adjNodesSize = startNode.adjNodes.size();
        for (int i = 0 ; i < adjNodesSize ; i++) {
            Point2D cPoint = startNode.adjNodes.remove();
            heigths[cPoint.getX()][cPoint.getY()].minWayLength = 1;
            iterationNodes.add(cPoint);
        }

        while(!iterationNodes.isEmpty()) {
            for (int i = 0; i < iterationNodes.size() ; i++) {
                Point2D cPoint = iterationNodes.remove();
                MNode cNode = heigths[cPoint.getX()][cPoint.getY()];
                cNode.isVisited = true;

                adjNodesSize = cNode.adjNodes.size();
                for (int g = 0 ; g < adjNodesSize ; g++) {
                    cPoint = cNode.adjNodes.remove();
                    if(!heigths[cPoint.getX()][cPoint.getY()].isVisited) {
                        heigths[cPoint.getX()][cPoint.getY()].minWayLength = cNode.minWayLength + 1;
                        iterationNodes.add(cPoint);
                    }
                }
            }
        }



        if(heigths[p2.getX()][p2.getY()].isVisited) {
            writer.write("Yes" + "\n");
            writer.write(Integer.toString(heigths[p2.getX()][p2.getY()].minWayLength));
        } else {
            writer.write("No");
        }


        writer.flush();
    }

    public static boolean checkTop(MNode[][] heigths,int i, int g, int K) {
        if( i-1 >= 0) {
            if(Math.abs(heigths[i][g].heigth - heigths[i-1][g].heigth) <= K ) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkBot(MNode[][] heigths,int i, int g, int K) {
        if( i+1 < heigths.length) {
            if(Math.abs(heigths[i][g].heigth - heigths[i+1][g].heigth) <= K ) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRight(MNode[][] heigths,int i, int g, int K) {
        if( g+1 < heigths[i].length) {
            if(Math.abs(heigths[i][g].heigth - heigths[i][g+1].heigth) <= K ) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkLeft(MNode[][] heigths,int i, int g, int K) {
        if( g-1 >= 0) {
            if(Math.abs(heigths[i][g].heigth - heigths[i][g-1].heigth) <= K ) {
                return true;
            }
        }
        return false;
    }
}