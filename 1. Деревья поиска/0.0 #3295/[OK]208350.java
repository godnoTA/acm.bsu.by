
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<Long> tree = new TreeSet<>();
        File file = new File("input.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                long temp = scanner.nextLong();
                tree.add(temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        try {
            FileWriter writer = new FileWriter("output.txt");
            Long sum = new Long(0);
            Iterator<Long> iterator = tree.iterator();
            while(iterator.hasNext()) {
                sum += iterator.next();
            }
            writer.write(sum.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("File Not Found");
        } catch (NullPointerException e) {
            System.out.println("Somehing Went Wrong");
        }


    }
}
