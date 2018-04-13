import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(new File("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        int N = in.nextInt();
        int a = 10;
        int b = 10;
        int c = 10;
        int d = 10;
        int f = 10;
        int e = 10;
        int t = 10;

        int[][][][][][][] mas = new int[a][b][c][d][f][e][t];
        for (int i = a - 1; i >= 0; i--)
            for (int j = b - 1; j >= 0; j--)
                for (int k = c - 1; k >= 0; k--)
                    for (int l = d - 1; l >= 0; l--)
                        for (int p = f - 1; p >= 0; p--)
                            for (int o = e - 1; o >= 0; o--)
                                for (int u = t - 1; u >= 0; u--)

                                {
                                    mas[i][j][k][l][p][o][u] = 0;

                                    if ((i == 9) && (j == 9) && (k == 9) && (l == 9) && (p == 9) && (o == 9) && (u == 9))
                                        mas[9][9][9][9][9][9][9] = 0;
                                    else {
                                        int tmp = 0;
                                        if (u < 9) tmp = tmp + mas[i][j][k][l][p][o][u + 1];
                                        if (u < 8) tmp = tmp + mas[i][j][k][l][p][o][u + 2];
                                        if (u < 7) tmp = tmp + mas[i][j][k][l][p][o][u + 3];

                                        if (o < 9) tmp = tmp + mas[i][j][k][l][p][o + 1][u];
                                        if (o < 8) tmp = tmp + mas[i][j][k][l][p][o + 2][u];
                                        if (o < 7) tmp = tmp + mas[i][j][k][l][p][o + 3][u];

                                        if (p < 9) tmp = tmp + mas[i][j][k][l][p + 1][o][u];
                                        if (p < 8) tmp = tmp + mas[i][j][k][l][p + 2][o][u];
                                        if (p < 7) tmp = tmp + mas[i][j][k][l][p + 3][o][u];

                                        if (l < 9) tmp = tmp + mas[i][j][k][l + 1][p][o][u];
                                        if (l < 8) tmp = tmp + mas[i][j][k][l + 2][p][o][u];
                                        if (l < 7) tmp = tmp + mas[i][j][k][l + 3][p][o][u];

                                        if (k < 9) tmp = tmp + mas[i][j][k + 1][l][p][o][u];
                                        if (k < 8) tmp = tmp + mas[i][j][k + 2][l][p][o][u];
                                        if (k < 7) tmp = tmp + mas[i][j][k + 3][l][p][o][u];

                                        if (j < 9) tmp = tmp + mas[i][j + 1][k][l][p][o][u];
                                        if (j < 8) tmp = tmp + mas[i][j + 2][k][l][p][o][u];
                                        if (j < 7) tmp = tmp + mas[i][j + 3][k][l][p][o][u];

                                        if (i < 9) tmp = tmp + mas[i + 1][j][k][l][p][o][u];
                                        if (i < 8) tmp = tmp + mas[i + 2][j][k][l][p][o][u];
                                        if (i < 7) tmp = tmp + mas[i + 3][j][k][l][p][o][u];
                                        if (tmp > 0)
                                            mas[i][j][k][l][p][o][u] = 0;
                                        else
                                            mas[i][j][k][l][p][o][u] = 1;

                                    }

                                }
                                int s=N / 1000000;
                                int q=(N % 1000000) / 100000;
                                int w=(N % 100000) / 10000;
                                int r=(N % 10000) / 1000;
                                int y=(N % 1000) / 100;
                                int x=(N % 100) / 10;
                                int n=(N % 10);
                                    if (mas[s][q][w][r][y][x][n] == 1)
                                        writer.write("The second wins\n");

                                    else {
                                        writer.write("The first wins\n");
                                        if ((n < 9) && (mas[s][q][w][r][y][x][n + 1]==1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + r*1000 + y*100 + x*10 + (n+1))+" ");
                                        if ((n < 8) && (mas[s][q][w][r][y][x][n + 2] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + r*1000 + y*100 + x*10 + (n+2))+" ");
                                        if ((n < 7) && (mas[s][q][w][r][y][x][n + 3] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + r*1000 + y*100 + x*10 + (n+3))+" ");

                                        if (((N % 100) / 10 < 9) && (mas[s][q][w][r][y][x+1][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + r*1000 + y*100 + (x+1)*10 + n)+" ");
                                        if (((N % 100) / 10 < 8) && (mas[s][q][w][r][y][x+2][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + r*1000 + y*100 + (x+2)*10 + n)+" ");
                                        if (((N % 100) / 10 < 7) && (mas[s][q][w][r][y][x+3][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + r*1000 + y*100 + (x+3)*10 + n)+" ");


                                        if (((N % 1000) / 100 < 9) && (mas[s][q][w][r][y+1][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + r*1000 + (y+1)*100 + x*10 + n)+" ");
                                        if (((N % 1000) / 100 < 8) && (mas[s][q][w][r][y+2][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + r*1000 + (y+2)*100 + x*10 + n)+" ");
                                        if (((N % 1000) / 100 < 7) && (mas[s][q][w][r][y+3][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + r*1000 + (y+3)*100 + x*10 + n)+" ");

                                        if (((N % 10000) / 1000 < 9) && (mas[s][q][w][r+1][y][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + (r+1)*1000 + y*100 + x*10 + n)+" ");
                                        if (((N % 10000) / 1000 < 8) && (mas[s][q][w][r+2][y][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + (r+2)*1000 + y*100 + x*10 + n)+" ");
                                        if (((N % 10000) / 1000 < 7) && (mas[s][q][w][r+3][y][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + w*10000 + (r+3)*1000 + y*100 + x*10 + n)+" ");

                                        if (((N % 100000) / 10000 < 9) && (mas[s][q][w+1][r][y][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + (w+1)*10000 + r*1000 + y*100 + x*10 + n)+" ");
                                        if (((N % 100000) / 10000 < 8) && (mas[s][q][w+2][r][y][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + (w+2)*10000 + r*1000 + y*100 + x*10 + n)+" ");
                                        if (((N % 100000) / 10000 < 7) && (mas[s][q][w+3][r][y][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + q*100000 + (w+3)*10000 + r*1000 + y*100 + x*10 + n)+" ");

                                        if (((N % 1000000) / 100000 < 9) && (mas[s][q+1][w][r][y][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + (q+1)*100000 + w*10000 + r*1000 + y*100 + x*10 + n)+" ");
                                        if (((N % 1000000) / 100000 < 8) && (mas[s][q+2][w][r][y][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + (q+2)*100000 + w*10000 + r*1000 + y*100 + x*10 + n)+" ");
                                        if (((N % 1000000) / 100000 < 7) && (mas[s][q+3][w][r][y][x][n] == 1))
                                            writer.write(Long.toString(s*1000000 + (q+3)*100000 + w*10000 + r*1000 + y*100 + x*10 + n)+" ");

                                        if ((N / 1000000 < 9) && (mas[s+1][q][w][r][y][x][n]== 1))
                                            writer.write(Long.toString((s+1)*1000000 + q*100000 + w*10000 + r*1000 + y*100 + x*10 + n)+" ");
                                        if ((N / 100000 < 8) && (mas[s+2][q][w][r][y][x][n] == 1))
                                            writer.write(Long.toString((s+2)*1000000 + q*100000 + w*10000 + r*1000 + y*100 + x*10 + n)+" ");
                                        if ((N / 1000000 < 7) && (mas[s+3][q][w][r][y][x][n] == 1))
                                            writer.write(Long.toString((s+3)*1000000 + q*100000 + w*10000 + r*1000 + y*100 + x*10 + n)+" ");
                    
                                    }
                                                                                                                        writer.close();
                                    in.close();
                                }
    }