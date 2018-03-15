import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HashSet<BigInteger> set = input();
        BigInteger result = new BigInteger("0");
        for(BigInteger item: set){
            result = result.add(item);
        }
        output(result);
    }
    private static HashSet<BigInteger> input(){
        HashSet<BigInteger> set = new HashSet<>();
        try (FileReader fr = new FileReader("input.txt")) {
            Scanner sc = new Scanner(fr).useDelimiter("\n");
            while ( sc.hasNext()){
                set.add(new BigInteger(sc.next().trim()));
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File input doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
    private static void output(BigInteger result){
        try (FileWriter fw = new FileWriter("output.txt")) {
            fw.write(result.toString());
        } catch (FileNotFoundException fnfe) {
            System.out.println("File doesn't exist.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
