import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class KillMe {
    static int[][] matrix;
    public static void main(String[] args) {
        input();
        if( matrix.length < matrix[0].length-2){
            output(-1);
        }
        else{
            output(minimalPenWay());
        }
        for( int i = 0; i < matrix.length; i++){
            for( int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static void input(){
        int n = 0;
        int m = 0;
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr);
            if( sc.hasNext()){
                n = Integer.parseInt(sc.next());
            }
            if( sc.hasNext()){
                m = Integer.parseInt(sc.next());
            }
            matrix = new int[n][m+2];
            for( int i = 0; i < matrix.length; i++){
                for( int j = 1; j < matrix[0].length-1; j++){
                    if (sc.hasNext()){
                        matrix[i][j] = Integer.parseInt(sc.next());
                    }
                }
                matrix[i][0] = matrix[i][matrix[0].length-1] = 0x7FFFFFFF;
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
    private static int minOfThree( int a, int b, int c){
        if( a <= b){
            if( a <= c)
                return a;
            else
                return c;
        }
        else{
            if( b <= c)
                return b;
            else
                return c;
        }
    }
    private static int minimalPenWay(){
        for( int i = 0; i < matrix[0].length-2; i++){
            for( int j = i+2; j < matrix[0].length-1; j++){
                matrix[i][j] = 0x7FFFFFFF;
            }
        }
        for( int i = 1; i < matrix.length; i++){
            for( int j = 1; j < matrix[0].length-1; j++){
                if( i+1 >= j){
                    matrix[i][j] += minOfThree(matrix[i-1][j-1],
                            matrix[i-1][j], matrix[i-1][j+1]);
                }
            }
        }
        return matrix[matrix.length-1][matrix[0].length-2];
    }
}
