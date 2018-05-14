import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by User on 11.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileReader("input.txt"));
            FileWriter out = new FileWriter("output.txt");
            int n, m = 0;
            n = sc.nextInt();
            m = sc.nextInt();
            int matrix [][] = new int [n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = sc.nextInt();
                }
            }
            if (m > n) {
                out.write("-1");
                out.close();
            }

            if (m == n) {
                int finalResult = 0;
                for (int i = 0; i < n; i++) {
                    finalResult += matrix[i][i];
                }
                out.write(finalResult + "");
                out.close();
            }
            if (m <= n) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (j > i) {
                            matrix[i][j] = Integer.MAX_VALUE;
                        }
                    }
                }
                int matrixForResult [][] = new int [n+1][m+2];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m+2; j++) {
                        matrixForResult[i][j] = 0;
                    }
                }
                for (int i = 0; i < n; i++) {
                    matrixForResult[i][0] = Integer.MAX_VALUE;
                    matrixForResult[i][m+1] = Integer.MAX_VALUE;
                }
                for (int j = 0; j < m + 2; j++) {
                    matrixForResult[n][j] = Integer.MAX_VALUE;
                }
                for (int i = 0; i < n + 1; i++) {
                    for (int j = 0; j < m + 2; j++) {
                        System.out.print(matrixForResult[i][j] + " ");
                    }
                    System.out.println();
                }
                int indexI = 0;
                int indexJ = 0;
                for (int i = 0 ; i < n; i++) {
                    for (int j = 1; j < m + 1; j++) {
                        matrixForResult[i][j] = matrix[indexI][indexJ];
                        indexJ++;
                    }
                    indexJ = 0;
                    indexI++;
                }
                for (int i = 1; i < n; i++) {
                    for (int j = 1; j < m + 1; j++) {

                        if (matrixForResult[i][j] == Integer.MAX_VALUE) {
                            matrixForResult[i][j] = Integer.MAX_VALUE;
                        } else {
                            matrixForResult[i][j] += Math.min(Math.min(matrixForResult[i-1][j], matrixForResult[i-1][j-1]), matrixForResult[i-1][j+1]);
                        }
                    }
                }
                int result = matrixForResult[n-1][m];
                System.out.println(result);
                out.write(result + "");
                out.close();
                for (int i = 0; i < n + 1; i++) {
                    for (int j = 0; j < m + 2; j++) {
                        System.out.print(matrixForResult[i][j] + " ");
                    }
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}