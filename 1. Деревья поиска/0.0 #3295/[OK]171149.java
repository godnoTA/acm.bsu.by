import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            TreeSet<Integer> tset = new <Integer> TreeSet();
            long sum = 0;
            while (sc.hasNextInt()) {
                tset.add(sc.nextInt());
            }
            for (Integer item : tset) {
                sum += item;
            }
            PrintWriter bw = new PrintWriter(new File("output.txt"));
            bw.print(sum);
            bw.close();
        } catch (Exception e) {
            System.out.print("error");
        }
    }
}
