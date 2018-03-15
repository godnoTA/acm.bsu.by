
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws java.lang.Exception {
        Set<Integer> arr = new TreeSet<Integer>();
        long sum = 0;

        final String in_path = "input.txt";
        final String out_path = "output.txt";

        Scanner sc = new Scanner(new File(in_path));
        while (sc.hasNextInt()) {
            arr.add(sc.nextInt());
        }
        for (int item : arr) {
            sum += item;
        }
        PrintStream ps = new PrintStream(out_path);
        ps.println(sum);
        ps.close();
    }
}
