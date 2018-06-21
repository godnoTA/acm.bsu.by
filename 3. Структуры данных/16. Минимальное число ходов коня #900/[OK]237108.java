
import java.io.*;
import java.util.*;

public class Matr {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("in.txt"));
        FileWriter fw = new FileWriter("out.txt");
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] fild = new int[n + 4][m + 4];
        for (int i = 2; i < n + 2; i++) {
            for (int j = 2; j < m + 2; j++) {
                fild[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < n + 4; i++) {
            fild[i][0] = fild[i][1] = fild[i][m + 2] = fild[i][m + 3] = -1;
        }
        for (int j = 0; j < m + 4; j++) {
            fild[0][j] = fild[1][j] = fild[n + 2][j] = fild[n + 3][j] = -1;
        }
        int [] xx = {-2,-2,2,2,1,1,-1,-1};
        int [] yy = {1,-1,1,-1,2,-2,2,-2};

        int firstPosX = sc.nextInt();
        int firstPosY = sc.nextInt();
        int finishPosX = sc.nextInt();
        int finishPosY = sc.nextInt();
        Queue<Integer> posX = new LinkedList<>();
        Queue<Integer> posY = new LinkedList<>();
        if (fild[firstPosX + 1][firstPosY + 1] == 0) {
            fild[firstPosX + 1][firstPosY + 1] = 1;
            posX.add(firstPosX + 1);
            posY.add(firstPosY + 1);
        }
        while (!posX.isEmpty()) {
            int x = posX.poll();
            int y = posY.poll();
            for (int i = 0; i < 8; i++) {
                if (fild[xx[i] + x][yy[i] + y] == 0) {
                    fild[xx[i] + x][yy[i] + y] = fild[x][y] + 1;
                    posX.add(xx[i] + x);
                    posY.add(yy[i] + y);
                }
            }
        }
        if (fild[finishPosX + 1][finishPosY + 1] < 1) {
            fw.write("No" + "");
        } else {
            fw.write(fild[finishPosX + 1][finishPosY + 1] - 1 + "");
        }

        sc.close();
        fw.close();
    }
}