import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner;
        int n;
        try{
            scanner = new Scanner(new File("input.txt"));
            n = scanner.nextInt();
            Integer[][] matrix = new Integer[n][n];
            Integer[] list = new Integer[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++){
                    matrix[i][j] = scanner.nextInt();
                    if (matrix[i][j] == 1){
                        list[j] = i + 1;
                    }
                }
            }
            try{
                FileWriter fileWriter = new FileWriter("output.txt");
                for (int i = 0; i < n; i++){
                    if (list[i] != null)
                        fileWriter.write(list[i].toString() + " ");
                    else fileWriter.write(0 + " ");
                }
                fileWriter.flush();
            }
            catch(Exception e){}
        }
        catch(Exception e){}
    }
}