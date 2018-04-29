import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Task64 {
    public void printMatrix(int[][] matrix){
        StringBuffer out = null;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
           for(int[] i : matrix){
               out = new StringBuffer();
               for(int j : i){
                   out.append(String.valueOf(j));
                   out.append(" ");
               }
               out.deleteCharAt(out.length() - 1);
               writer.write(out.toString());
               if(i != matrix[matrix.length - 1])
                   writer.newLine();
           }
           writer.close();
        }
        catch(IOException ex){
            System.out.print("IO error");
        }
    }
    public static void main(String[] args) {
        int[][] matrix = null;
        Task64 output = new Task64();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            int number = 0;
            int size = 0;
            String[] buffer_str = reader.readLine().split(" ");
            number = Integer.parseInt(buffer_str[1]);
            size = Integer.parseInt(buffer_str[0]);
            matrix = new int[size][size];
            for(int i = 0; i < number; i++){
                buffer_str = reader.readLine().split(" ");
                matrix[Integer.parseInt(buffer_str[0]) - 1][Integer.parseInt(buffer_str[1]) - 1] = 1;
                matrix[Integer.parseInt(buffer_str[1]) - 1][Integer.parseInt(buffer_str[0]) - 1] = 1;
            }
            reader.close();
        }catch(IOException ex){
            System.out.print("IO error");
        }
        output.printMatrix(matrix);
    }
    
}
