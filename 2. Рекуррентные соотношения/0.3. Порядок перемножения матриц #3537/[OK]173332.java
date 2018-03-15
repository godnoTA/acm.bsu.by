import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

class Matrix{
    private Integer mHeigth;
    private Integer mWidth;
    public static Integer MATRIX_COUNT;

    public Matrix(Integer height,Integer width){
        this.mHeigth = height;
        this.mWidth = width;
    }

    public Integer getmHeigth() {
        return mHeigth;
    }

    public Integer getmWidth() {
        return mWidth;
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("input.txt")));
        String line;
        line = scan.nextLine();

        Matrix.MATRIX_COUNT = Integer.parseInt(line);
        Matrix[] matrixMas = new Matrix[Matrix.MATRIX_COUNT+1];

        for(int i = 1 ; i <= Matrix.MATRIX_COUNT ; i++){
            matrixMas[i] = new Matrix(scan.nextInt(),scan.nextInt());
        }

        int[][] matrixOfOperationCounts = new int [Matrix.MATRIX_COUNT+1][Matrix.MATRIX_COUNT+1];

        for (int i = 1 ; i < Matrix.MATRIX_COUNT ; i++){
            matrixOfOperationCounts[i][i] = 0;
        }

        for(int p = 1 ; p <= Matrix.MATRIX_COUNT ; p++){
            for(int i = 1 ; i <= Matrix.MATRIX_COUNT - p ; i++){
                int  kol = Integer.MAX_VALUE;
                int j = i + p;
                for(int k = i ; k <= j-1 ; k++){
                    if(kol > matrixOfOperationCounts[i][k] + matrixOfOperationCounts[k+1][j] +
                            matrixMas[i].getmHeigth() * matrixMas[k].getmWidth() * matrixMas[j].getmWidth()){

                        kol = matrixOfOperationCounts[i][k] + matrixOfOperationCounts[k+1][j] +
                                matrixMas[i].getmHeigth() * matrixMas[k].getmWidth() * matrixMas[j].getmWidth();
                    }
                }
                matrixOfOperationCounts[i][j] = kol;
            }
        }



        FileWriter writer = new FileWriter("output.txt", false);
        writer.write(Integer.toString(matrixOfOperationCounts[1][Matrix.MATRIX_COUNT]));
        writer.flush();
    }
}