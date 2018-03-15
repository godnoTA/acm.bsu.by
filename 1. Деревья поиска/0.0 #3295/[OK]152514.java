import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static Set<Long> s = new HashSet<>();
    private static long sum = 0;

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("input.txt"));
        while (sc.hasNextLong()){
            s.add(sc.nextLong());
        }
        for(long item : s){
            sum += item;
        }

        FileOutputStream fos = new FileOutputStream(new File("output.txt"));
        PrintStream ps = new PrintStream(fos);
        ps.println(sum);
    }
}