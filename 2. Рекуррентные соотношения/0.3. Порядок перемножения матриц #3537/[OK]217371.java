import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class MultiplyMatrix{
    
    private int numberMatrixes;
    private int[][] SizesMatrixes;
    private int[][] tableOperation;
    
    MultiplyMatrix(int numberMatrixes,int[][] SizesMatrixes){
        this.numberMatrixes = numberMatrixes;
        this.SizesMatrixes = SizesMatrixes;
        tableOperation = new int[numberMatrixes][numberMatrixes];
        for(int i = 0; i < tableOperation.length; i++){
            for(int j = i; j < tableOperation.length; j++){
                tableOperation[i][j] = 0;
            }
        }
    }
    
    private int countingMinOperation(int i, int j){
        if((i - j) == 0)
            return 0;
        else{
            if(tableOperation[i - 1][j - 1] == 0){
                int min = countingMinOperation(i, i) + countingMinOperation(i + 1, j)
                    + SizesMatrixes[0][i - 1] * SizesMatrixes[1][i-1] * SizesMatrixes[1][j - 1];
                for(int k = (i + 1); k < j; k++){
                    int minInFor = countingMinOperation(i, k) + countingMinOperation(k + 1, j)
                        + SizesMatrixes[0][i - 1] * SizesMatrixes[1][k - 1] * SizesMatrixes[1][j - 1];
                    if(minInFor < min)
                        min = minInFor;
                }
                tableOperation[i - 1][j - 1] = min;
                return min;
            }
            else return tableOperation[i - 1][j - 1];
        }
            
    }
    int getMinOperation(){
        return countingMinOperation(1, numberMatrixes);
    }
}

public class Alg03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String reading;
        int size = 0;
        int [][]SizeMatixesArray = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            size = Integer.parseInt(reader.readLine());
            SizeMatixesArray = new int [2][size];
            int count = 0;
            while((reading = reader.readLine()) != null){
                SizeMatixesArray[0][count] = Integer.parseInt(reading.split(" ")[0]);
                SizeMatixesArray[1][count] = Integer.parseInt(reading.split(" ")[1]);
                count++;
               }
        }catch(IOException ex){
            System.out.print("IO error");
        }
        MultiplyMatrix val = new MultiplyMatrix(size,SizeMatixesArray);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
           writer.write(String.valueOf(val.getMinOperation()));
           writer.close();
        }
        catch(IOException ex){
            System.out.print("IO error");
        }
    }
    
}