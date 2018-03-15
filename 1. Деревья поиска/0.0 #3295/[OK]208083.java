import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<Integer>();
        long result = 0;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))){
            String line;
            String[] data;
            while ((line = reader.readLine()) != null) {
                set.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            result += iterator.next();
        }
        try (FileWriter writer = new FileWriter("output.txt")){
            writer.write(Long.toString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}