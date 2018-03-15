import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MatrixMultipl {
    private static int[][] data;
    private static int[][] matrix;
    public static void main(String[] args) {
        input();
        output(findMinNumberOfMultiplies());
    }
    private static void input(){
        int n = 0;
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr);
            if( sc.hasNext()){
                n = Integer.parseInt(sc.next());
            }
            data = new int[n][2];
            matrix = new int[n][n];
            for( int i = 0; i < data.length; i++){
                for( int j = 0; j < data[0].length; j++){
                    if (sc.hasNext()){
                        data[i][j] = Integer.parseInt(sc.next());
                    }
                }
                matrix[i][i] = 0;
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File input doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void output( Integer result){
        try (FileWriter fw = new FileWriter("output.txt")) {
            fw.write(result.toString());
        } catch (FileNotFoundException fnfe) {
            System.out.println("File doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static int findMinNumberOfMultiplies(){
        for( int j = 1; j < matrix[0].length; j++){
            int f = j;
            for( int i = 0; i < matrix.length-j; i++){
                matrix[i][f++] = minMultiplNum( i, f-1);
            }
        }
        return matrix[0][matrix[0].length-1];
    }
    private static int minMultiplNum( int rowNum, int colNum){
        int min = 1000000000;
        for( int k = rowNum; k < colNum; k++){
            if( min > matrix[rowNum][k] + matrix[k+1][colNum] + data[rowNum][0]*data[k][1]*data[colNum][1]){
                min = matrix[rowNum][k] + matrix[k+1][colNum] + data[rowNum][0]*data[k][1]*data[colNum][1];
            }
        }
        return min;
    }
}
