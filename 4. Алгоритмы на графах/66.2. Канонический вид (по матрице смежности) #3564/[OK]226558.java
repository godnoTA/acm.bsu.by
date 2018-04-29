import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

public class Task66_2 {
    public int[][] readMatrix(){
        int[][] matrix = null;
         try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String[] buffer_str = null;
            int size = Integer.parseInt(reader.readLine());
            matrix = new int[size][size];
            for(int i = 0; i < size; i++){
                buffer_str = reader.readLine().split(" ");
                for(int j = 0; j < size; j++){
                    matrix[i][j] = Integer.parseInt(buffer_str[j]);
                }
            }
            reader.close();
        }catch(IOException ex){
            System.out.print("IO error");
        }
        return matrix;
    }
    public static void main(String[] args) {
        Task66_2 input = new Task66_2();
        int[][] matrix = input.readMatrix();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
           for(int i = 0; i < matrix.length; i++){
               for(int j = 0; j < matrix.length; j++){
                   if(matrix[j][i] == 1){
                       writer.write(String.valueOf(j + 1));
                       if(i != matrix.length - 1)
                           writer.write(" ");
                       break;
                   }
                   if(j == matrix.length - 1){
                       writer.write(String.valueOf(0));
                       if(i != matrix.length - 1)
                           writer.write(" ");
                   }
               }
           }
           writer.close();
        }
        catch(IOException ex){
            System.out.print("IO error");
        }
    }
    
}
