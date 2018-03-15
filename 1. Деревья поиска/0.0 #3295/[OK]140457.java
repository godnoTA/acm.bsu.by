import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        long sum = 0;
        Set<Integer> tree = new HashSet<>();
        Scanner sc = new Scanner(new File("input.txt"));
            while (sc.hasNextInt()) {
                tree.add(sc.nextInt());
            }

            for (int item : tree) {
                sum += item;
            }
                FileOutputStream fileOutputStream = new FileOutputStream(new File("output.txt"));
                PrintStream printStream = new PrintStream(fileOutputStream);

                printStream.println(sum);
    }
}
