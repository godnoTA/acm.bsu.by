import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BinaryHeap {
    private static int[] array;
    public static void main(String[] args) {
        String result = "Yes";
        input();
        for( int i = 1; i < array.length; i++){
            if(!( 2*i+1 > array.length || 2*i > array.length)){
                if( 2*i+1 < array.length){
                    if( array[i] > array[2*i] || array[i] > array[2*i+1]){
                        result = "No";
                        break;
                    }
                }
                else{
                    if (array[i] > array[2*i]){
                        result = "No";
                        break;
                    }
                }
            }
        }
        output(result);
    }
    private static void input(){
        int n = 0;
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr);
            if( sc.hasNext()){
                n = Integer.parseInt(sc.next());
            }
            array = new int[n+1];
            for( int i = 1; i < array.length; i++){
                if( sc.hasNext()){
                    array[i] = Integer.parseInt(sc.next());
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File input doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void output( String result){
        try (FileWriter fw = new FileWriter("output.txt")) {
            fw.write(result.toString());
        } catch (FileNotFoundException fnfe) {
            System.out.println("File doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
