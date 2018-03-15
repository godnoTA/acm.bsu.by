import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Matrix{
        public Integer rows;
        public Integer cols;
        public Integer brckt;
        public Integer numOfOperations;

        public Matrix(){
            new Matrix(0, 0);
        }
        public Matrix(Integer nRows, Integer nCols){
            cols = nCols;
            rows = nRows;
        }
        public void setNumOfOperations(Integer aRows, Integer bRows, Integer bCols){
            numOfOperations = aRows * bRows * bCols;
            cols = bCols;
            rows = aRows;
        }
        public void setBorders(Integer nRows, Integer nCols){
            rows = nRows;
            cols = nCols;
        }
        public static Integer operations(Integer aRows, Integer bRows, Integer bCols){
            return aRows * bRows * bCols;
        }
    }

    public Integer minOfAll(Matrix[][] m, Integer i, Integer k){
        Integer min = 2_147_483_647;
        for (int j = 0; j < k - i; j++) {
            if(min > m[i][i + j].numOfOperations + m[i + j + 1][k].numOfOperations +
                    m[i][i + j].rows * m[i][i + j].cols * m[i + j + 1][k].cols){
                min = m[i][i + j].numOfOperations + m[i + j + 1][k].numOfOperations +
                        m[i][i + j].rows * m[i][i + j].cols * m[i + j + 1][k].cols;
            }
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        Integer numOfMatrix;
        int j = 0;
        String s;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        numOfMatrix = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Matrix[] A = new Matrix[numOfMatrix];
        Matrix[][] matrix = new Matrix[numOfMatrix][numOfMatrix];

        while ((s = br.readLine()) != null) {
            st = new StringTokenizer(s, " ");
            A[j++] = new Matrix(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }


        for (int i = 0; i < numOfMatrix; i++) {
            for (int k = i; k < numOfMatrix; k++) {
                matrix[i][k] = new Matrix();
                matrix[i][k].rows = A[i].rows;
                matrix[i][k].cols = A[k].cols;
                if(k == i){
                    matrix[i][i].rows = A[i].rows;
                    matrix[i][i].cols = A[i].cols;
                    matrix[i][i].numOfOperations = 0;
                }
                if(k == i + 1){
                    matrix[i][k].setNumOfOperations(A[i].rows, A[i+1].rows, A[i+1].cols);
                }
            }
        }

        for (int i = 2; i < A.length; i++) {
            for (int k = i, p = 0; k + p < A.length; p++) {
                matrix[p][k + p].numOfOperations = new Main().minOfAll(matrix, p, k + p);
            }
        }
        PrintWriter pw = new PrintWriter("output.txt");
        pw.write(matrix[0][A.length - 1].numOfOperations.toString());
        pw.close();
    }
}
