import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArcsList {
    private static int [] result;
    public static void main(String[] args) {
        input();
        output(result);
    }
    private static void input(){
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr);
            result = new int [Integer.parseInt(sc.next())];
            while (sc.hasNext()){
                int father = Integer.parseInt(sc.next());
                int child = Integer.parseInt(sc.next());
                result[child-1] = father;
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

