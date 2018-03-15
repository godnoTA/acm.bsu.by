import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main (String [] args) throws IOException {
        Set<Integer> keys = new HashSet<>();
        long sum = 0;

        try (Scanner sc = new Scanner(new File("input.txt"))) {
            while (sc.hasNextInt()) {
                int number = sc.nextInt();
                if (!keys.contains(number)) {
                    keys.add(number);
                    sum += number;
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        try (PrintStream ps = new PrintStream(new FileOutputStream("output.txt"))) {
            ps.print(sum);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
