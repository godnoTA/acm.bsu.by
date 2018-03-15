import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by redho on 10.06.2016.
 */
public class Main {
    private static Set<Long> s = new HashSet<>();
    private static long sum = 0;

    public static void read() throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        while (sc.hasNextLong()){
            s.add(sc.nextLong());
            System.out.println(s);
        }
        for(long item : s){
            sum += item;
        }
    }

    public static void print() throws IOException{
        FileOutputStream fos = new FileOutputStream(new File("output.txt"));
        PrintStream ps = new PrintStream(fos);
        ps.println(sum);
    }

    public static void main(String[] args) throws IOException{
        read();
        print();
    }
}
