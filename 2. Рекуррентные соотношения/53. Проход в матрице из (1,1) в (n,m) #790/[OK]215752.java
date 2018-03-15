import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Matrix {

    public static int[] cutStringSize(String str){
        int[] size = new int[2];
        StringTokenizer s = new StringTokenizer(str, " ");
        size[0] = Integer.parseInt(s.nextToken());
        size[1] = Integer.parseInt(s.nextToken());
        return size;
    }

    public static int[][] cutStringMatrix(String[] str, int n, int m){
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++){
            StringTokenizer s = new StringTokenizer(str[i], " \n");
            for (int j = 0; j < m; j++)
                matrix[i][j] = Integer.parseInt(s.nextToken());
        }
        return matrix;
    }

    public static int countRout(int[][] matrix, int n, int m) {
        if ((n > 1) && (m == 1)){
            int sum = 0;
            for (int i = 0; i < n; i++)
                sum += matrix[i][0];
            return sum;
        }
        if (n < m)
            return -1;
        else if (n == m) {
            int sum = 0;
            for (int i = 0; i < n; i++)
                sum += matrix[i][i];
            return sum;
        } else {
            int[][] newmatrix = new int[n - m + 1][m];
            int[][] summatrix = new int[n - m + 1][m];
            int k = 0;
            for (int i = 0; i < n - m + 1; i++) {
                for (int j = 0; j < m; j++)
                    newmatrix[i][j] = matrix[j + k][j];
                k++;
            }
            int sum1 = 0;
            int sum2 = 0;
            int sum3 = 0;
            summatrix[0][0] = newmatrix[0][0];
            for (int i = 0; i < n - m + 1; i++){
                for (int j = 0; j < m; j++){
                    if ((i >= 2) && (j < m - 1) && (j >= 1)) {
                        sum1 = newmatrix[i][j] + summatrix[i - 1][j];
                        sum2 = newmatrix[i][j] + summatrix[i][j - 1];
                        sum3 = newmatrix[i][j] + summatrix[i - 2][j + 1];
                        if (sum1 < sum2){
                            if (sum1 < sum3)
                                summatrix[i][j] = sum1;
                            else
                                summatrix[i][j] = sum3;
                        }
                        else {
                            if (sum2 < sum3)
                                summatrix[i][j] = sum2;
                            else
                                summatrix[i][j] = sum3;
                        }
                    }
                    if ((i >= 1) && (j >= 1) && ((i == 1) || (j == m - 1))) {
                        sum1 = newmatrix[i][j] + summatrix[i - 1][j];
                        sum2 = newmatrix[i][j] + summatrix[i][j - 1];
                        if (sum1 > sum2)
                            summatrix[i][j] = sum2;
                        else
                            summatrix[i][j] = sum1;
                    }
                    if ((i == 0) && (j >= 1))
                        summatrix[i][j] = newmatrix[i][j] + summatrix[i][j - 1];
                    if ((i == 1) && (j == 0))
                        summatrix[i][j] = newmatrix[i][j] + summatrix[i - 1][j];
                    if ((i >= 2) && (j == 0)){
                        sum2 = newmatrix[i][j] + summatrix[i - 1][j];
                        sum3 = newmatrix[i][j] + summatrix[i - 2][j + 1];
                        if (sum3 > sum2)
                            summatrix[i][j] = sum2;
                        else
                            summatrix[i][j] = sum3;
                    }
                }
            }
            return summatrix[n - m][m - 1];
        }
    }
    public static void main(String[] args) {
        int[] size = new int[2];
        int[][] matrix = new int[size[0]][size[1]];
        String str;
        try {
            Scanner in = new Scanner(new File("input.txt"));
            str = in.nextLine();
            size = cutStringSize(str);
            String[] s = new String[size[0]];
            int i = 0;
            while (in.hasNextInt()) {
                s[i] = in.nextLine();
                i++;
            }
            matrix = new int[size[0]][size[1]];
            matrix = cutStringMatrix(s, size[0], size[1]);
        }
        catch (java.io.FileNotFoundException e) {
            System.out.println("File was not found");
            System.err.println(e.toString());
        }

        try{
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            pw.print(countRout(matrix, size[0], size[1]));
            pw.close();
        }
        catch(FileNotFoundException e){e.printStackTrace();}
    }
}
