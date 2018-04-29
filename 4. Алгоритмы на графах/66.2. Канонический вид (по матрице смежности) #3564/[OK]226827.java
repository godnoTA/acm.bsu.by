import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AdjMatrix {
    private static int[] result;
    public static void main(String[] args) {
        input();
        output(result);
    }
    private static void input(){
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr);
            result = new int [Integer.parseInt(sc.next())];
            for( int i = 0; i < result.length; i++){
                for( int j = 0; j < result.length; j++){
                    if(Integer.parseInt(sc.next()) == 1){
                        result[j] = i+1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void output( int[] result){
        try (FileWriter fw = new FileWriter("output.txt")) {
            for( int i = 0; i < result.length; i++){
                fw.write( result[i] + " ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
