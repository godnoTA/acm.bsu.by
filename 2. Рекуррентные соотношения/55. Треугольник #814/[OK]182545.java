import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 13.05.2017.
 */
public class Main {
    public static int[][] forInputInfortaion(List<String> inputArr) {
        int [][] matrix = new int [inputArr.size() - 1][inputArr.size() - 1];
        int indexI = 0, indexJ = 0, indexForGettinElement = 0;
        List<Integer> forNumbers = new ArrayList<>(inputArr.size() - 1);
        for (int i = 1; i < inputArr.size(); i++) {

            for (String str: inputArr.get(i).split(" ")) {
                forNumbers.add(Integer.parseInt(str));
                matrix[indexI][indexJ] = forNumbers.get(indexForGettinElement);
                indexJ++;
                indexForGettinElement++;
            }
            indexJ = 0;
            indexI++;
        }
        return matrix;
    }
    public static void printMatrix(int [][] matrix, int n, int m) {
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
    public static int findMax(int [][] matrix, int n, int m) {
        int min = -1000000;
        for (int i = 0; i < m; i++) {
            if (matrix[n-1][i] > min) {
                min = matrix[n-1][i];
            }
        }
        return min;
    }
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            FileWriter out = new FileWriter("output.txt");
            String line;
            List<String> lines = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            int n = lines.size() - 1;

            int[][] matrix = new int[n][n];
            matrix = forInputInfortaion(lines);


            int[][] matrixForResult = new int[lines.size() - 1][lines.size() + 1];


            for (int i = 0; i < n; i++) {
                matrixForResult[i][0] = -1;
                matrixForResult[i][lines.size()] = -1;
            }

            int indexI = 0, indexJ = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < n + 1; j++) {
                    matrixForResult[i][j] = matrix[indexI][indexJ];
                    indexJ++;
                }
                indexJ = 0;
                indexI++;
            }



            for (int i = 1; i < lines.size() - 1; i++) {
                for (int j = 1; j < lines.size(); j++) {
                    matrixForResult[i][j] += Math.max(matrixForResult[i-1][j], matrixForResult[i-1][j-1]);
                }
            }



            printMatrix(matrixForResult, lines.size() - 1, lines.size() + 1);

            int result = findMax(matrixForResult, lines.size() - 1, lines.size() + 1);

            System.out.println(result);

            out.write(result + "");

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}