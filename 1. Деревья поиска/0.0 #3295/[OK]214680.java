import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;
public class SumOfValues{
    public static void main(String[] args) throws FileNotFoundException {
        try {
            Scanner scan = new Scanner(new File("input.txt"));
            TreeSet<Long> tr = new TreeSet<Long>();
            long sum = 0;
            while (scan.hasNextLong())
                tr.add(scan.nextLong());

                try(FileWriter writer = new FileWriter("output.txt")) {
                for (Long s : tr)
                {
                    sum += s;
                    System.out.println(s);
                }
                writer.write(Long.toString(sum));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}