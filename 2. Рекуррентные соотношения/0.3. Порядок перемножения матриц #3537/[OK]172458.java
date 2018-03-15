import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

class Matrix {
    private int n;
    private int m;

    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            int size = sc.nextInt();
            Matrix[] matrix = new Matrix[size];
            for (int i = 0; i < size; i++) {
                matrix[i] = new Matrix(sc.nextInt(), sc.nextInt());
            }
            int[][] table = new int[size][size];
            for (int i = 0; i < size; i++) {
                table[i][i] = 0;
                if (i < size - 1) {
                    table[i][i + 1] = matrix[i].getN() * matrix[i].getM() * matrix[i + 1].getM();
                }
            }
            int min = 0;
            int temp = 0;
            for (int i = 2; i < size; i++) {
                for (int j = 0; j < size - i; j++) {
                    min = table[j][j] + table[j + 1][i + j] + matrix[j].getN() * matrix[j].getM() * matrix[i + j].getM();
                    for (int k = 1; k < i; k++) {
                        temp = table[j][j + k] + table[j + k + 1][i + j] + matrix[j].getN() * matrix[j + k].getM() * matrix[i + j].getM();
                        if (min > temp) {
                            min = temp;
                        }
                    }
                    table[j][i + j] = min;
                }
            }
            PrintWriter pr = new PrintWriter(new File("output.txt"));
            pr.print(table[0][size - 1]);
            pr.close();
        } catch (Exception e) {
            System.out.print("error");
        }
    }
}