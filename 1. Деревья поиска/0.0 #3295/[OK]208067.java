import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;

public class Sum {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        long sum=0;
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.print("Файл не найден!");
            return;
        }
        while (scanner.hasNext()) {
            int  key = scanner.nextInt();
            treeSet.add(key);
        }
        Iterator<Integer> itr = treeSet.iterator();
        while (itr.hasNext()){
            sum+=itr.next();
        }
        try(FileWriter writer = new FileWriter("output.txt", false))
        {

            writer.write(String.valueOf(sum));
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
