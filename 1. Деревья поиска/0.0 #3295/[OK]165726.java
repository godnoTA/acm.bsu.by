import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Set<Integer> set = new HashSet<>();
        long sum = 0;
        Scanner sc = new Scanner(new File("input.txt"));
        PrintStream fw = new PrintStream("output.txt");
        while (sc.hasNext()){
            set.add(sc.nextInt());
        }
        for(Integer item : set){
            sum += item;
        }
        fw.print(sum);
        fw.close();
    }
}