import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

class Matrix {
    private boolean[][][][][][][] A;
    private int[] x;

    public Matrix(int n) {
        A = new boolean[10][10][10][10][10][10][10];
        x = new int[7];
        for (int i = 6; i >= 0; i--, n /= 10) {
            x[i] = n % 10;
        }
    }

    private boolean count(int x0, int x1, int x2, int x3, int x4, int x5, int x6) {
        if (x0 == 9 && x1 == 9 && x2 == 9 && x3 == 9 && x4 == 9 && x5 == 9 && x6 == 9)
            return false;
        boolean result = false;
        if (x0 < 9) result = result || A[x0 + 1][x1][x2][x3][x4][x5][x6];
        if (x0 < 8) result = result || A[x0 + 2][x1][x2][x3][x4][x5][x6];
        if (x0 < 7) result = result || A[x0 + 3][x1][x2][x3][x4][x5][x6];

        if (x1 < 9) result = result || A[x0][x1 + 1][x2][x3][x4][x5][x6];
        if (x1 < 8) result = result || A[x0][x1 + 2][x2][x3][x4][x5][x6];
        if (x1 < 7) result = result || A[x0][x1 + 3][x2][x3][x4][x5][x6];

        if (x2 < 9) result = result || A[x0][x1][x2 + 1][x3][x4][x5][x6];
        if (x2 < 8) result = result || A[x0][x1][x2 + 2][x3][x4][x5][x6];
        if (x2 < 7) result = result || A[x0][x1][x2 + 3][x3][x4][x5][x6];

        if (x3 < 9) result = result || A[x0][x1][x2][x3 + 1][x4][x5][x6];
        if (x3 < 8) result = result || A[x0][x1][x2][x3 + 2][x4][x5][x6];
        if (x3 < 7) result = result || A[x0][x1][x2][x3 + 3][x4][x5][x6];

        if (x4 < 9) result = result || A[x0][x1][x2][x3][x4 + 1][x5][x6];
        if (x4 < 8) result = result || A[x0][x1][x2][x3][x4 + 2][x5][x6];
        if (x4 < 7) result = result || A[x0][x1][x2][x3][x4 + 3][x5][x6];

        if (x5 < 9) result = result || A[x0][x1][x2][x3][x4][x5 + 1][x6];
        if (x5 < 8) result = result || A[x0][x1][x2][x3][x4][x5 + 2][x6];
        if (x5 < 7) result = result || A[x0][x1][x2][x3][x4][x5 + 3][x6];

        if (x6 < 9) result = result || A[x0][x1][x2][x3][x4][x5][x6 + 1];
        if (x6 < 8) result = result || A[x0][x1][x2][x3][x4][x5][x6 + 2];
        if (x6 < 7) result = result || A[x0][x1][x2][x3][x4][x5][x6 + 3];
        return !result;

    }

    public void fill() {
        for (int x0 = 9; x0 >= x[0]; x0--) {
            for (int x1 = 9; x1 >= x[1]; x1--) {
                for (int x2 = 9; x2 >= x[2]; x2--) {
                    for (int x3 = 9; x3 >= x[3]; x3--) {
                        for (int x4 = 9; x4 >= x[4]; x4--) {
                            for (int x5 = 9; x5 >= x[5]; x5--) {
                                for (int x6 = 9; x6 >= x[6]; x6--) {
                                    A[x0][x1][x2][x3][x4][x5][x6] = count(x0, x1, x2, x3, x4, x5, x6);
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    public void future(PrintStream ps) throws FileNotFoundException {
        if (!A[x[0]][x[1]][x[2]][x[3]][x[4]][x[5]][x[6]]) {
            ps.println("\"First win\"");
            StringBuilder sb = new StringBuilder();
            if (x[6] < 9 && A[x[0]][x[1]][x[2]][x[3]][x[4]][x[5]][x[6] + 1]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6] + 1);
                sb.append(" ");
            }
            if (x[6] < 8 && A[x[0]][x[1]][x[2]][x[3]][x[4]][x[5]][x[6] + 2]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6] + 2);
                sb.append(" ");
            }
            if (x[6] < 7 && A[x[0]][x[1]][x[2]][x[3]][x[4]][x[5]][x[6] + 3]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6] + 3);
                sb.append(" ");
            }

            if (x[5] < 9 && A[x[0]][x[1]][x[2]][x[3]][x[4]][x[5] + 1][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + (x[5] + 1) * 10 + x[6]);
                sb.append(" ");
            }
            if (x[5] < 8 && A[x[0]][x[1]][x[2]][x[3]][x[4]][x[5] + 2][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + (x[5] + 2) * 10 + x[6]);
                sb.append(" ");
            }
            if (x[5] < 7 && A[x[0]][x[1]][x[2]][x[3]][x[4]][x[5] + 3][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + (x[5] + 3) * 10 + x[6]);
                sb.append(" ");
            }

            if (x[4] < 9 && A[x[0]][x[1]][x[2]][x[3]][x[4] + 1][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + (x[4] + 1) * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            if (x[4] < 8 && A[x[0]][x[1]][x[2]][x[3]][x[4] + 2][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + (x[4] + 2) * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            if (x[4] < 7 && A[x[0]][x[1]][x[2]][x[3]][x[4] + 3][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + (x[4] + 3) * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }

            if (x[3] < 9 && A[x[0]][x[1]][x[2]][x[3] + 1][x[4]][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + (x[3] + 1) * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            if (x[3] < 8 && A[x[0]][x[1]][x[2]][x[3] + 2][x[4]][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + (x[3] + 2) * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            if (x[3] < 7 && A[x[0]][x[1]][x[2]][x[3] + 3][x[4]][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + x[2] * 10000 + (x[3] + 3) * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }

            if (x[2] < 9 && A[x[0]][x[1]][x[2] + 1][x[3]][x[4]][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + (x[2] + 1) * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            if (x[2] < 8 && A[x[0]][x[1]][x[2] + 2][x[3]][x[4]][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + (x[2] + 2) * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            if (x[2] < 7 && A[x[0]][x[1]][x[2] + 3][x[3]][x[4]][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + x[1] * 100000 + (x[2] + 3) * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }

            if (x[1] < 9 && A[x[0]][x[1] + 1][x[2]][x[3]][x[4]][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + (x[1] + 1) * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            if (x[1] < 8 && A[x[0]][x[1] + 2][x[2]][x[3]][x[4]][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + (x[1] + 2) * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            if (x[1] < 7 && A[x[0]][x[1] + 3][x[2]][x[3]][x[4]][x[5]][x[6]]) {
                sb.append(x[0] * 1000000 + (x[1] + 3) * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }

            if (x[0] < 9 && A[x[0] + 1][x[1]][x[2]][x[3]][x[4]][x[5]][x[6]]) {
                sb.append((x[0] + 1) * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            if (x[0] < 8 && A[x[0] + 2][x[1]][x[2]][x[3]][x[4]][x[5]][x[6]]) {
                sb.append((x[0] + 2) * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            if (x[0] < 7 && A[x[0] + 3][x[1]][x[2]][x[3]][x[4]][x[5]][x[6]]) {
                sb.append((x[0] + 3) * 1000000 + x[1] * 100000 + x[2] * 10000 + x[3] * 1000 + x[4] * 100 + x[5] * 10 + x[6]);
                sb.append(" ");
            }
            ps.print(sb.substring(0, sb.length()-1));
        } else ps.print("\"Second win\"");
        ps.close();
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Matrix m = new Matrix(sc.nextInt());
        sc.close();
        m.fill();
        m.future(new PrintStream("output.txt"));
    }
}