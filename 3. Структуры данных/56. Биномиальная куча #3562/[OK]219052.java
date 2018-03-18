import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class BinomialHeap {
    private static BigInteger numberOfPeaks;
    public static void main(String[] args) {
        input();
        ArrayList<Integer> result = new ArrayList<>();
        String binString = numberOfPeaks.toString(2);
        for( int i = binString.length()-1; i >= 0; i--){
            if( binString.charAt(i) == '1'){
                result.add(binString.length()-1-i);
            }
        }
        output(result);
    }
    private static void input(){
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr);
            if( sc.hasNext()){
                numberOfPeaks = new BigInteger(sc.next());
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File input doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void output( ArrayList<Integer> result){
        try (FileWriter fw = new FileWriter("output.txt")) {
            for( int i = 0; i < result.size(); i++){
                fw.write(result.get(i).toString() + "\n");
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
