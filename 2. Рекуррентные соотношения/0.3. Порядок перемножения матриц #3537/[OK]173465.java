import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by User on 17.03.2017.
 */
public class Main {

    public static int findMin(int a, int b) {
        if (a <= b) {
            return a;
        }
        else {
            return b;
        }
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileReader("input.txt"));
            FileWriter out = new FileWriter("output.txt");
            int n = sc.nextInt();

            int [][] matrix = new int [n][n];
            int [][] matrixForSizes = new int [2][n];
            for (int i = 0; i < n; i++) {
                matrixForSizes[0][i] = sc.nextInt();
                matrixForSizes[1][i] = sc.nextInt();
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
            for(int j=1;j<n;j++){
                for(int i=0;i<n-j;i++){
                    matrix[i][j+i]=matrix[i][i]+matrix[i+1][j+i]+matrixForSizes[0][i]*matrixForSizes[1][i]*matrixForSizes[1][j+i];
                    for(int k=i;k<j+i;k++){
                        matrix[i][j+i]=findMin(matrix[i][j+i],matrix[i][k]+matrix[k+1][j+i]+matrixForSizes[0][i]*matrixForSizes[1][k]*matrixForSizes[1][j+i]);
                    }
                }
            }

            out.write(matrix[0][n-1] + "");
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}