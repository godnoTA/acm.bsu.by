import javafx.util.Pair;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        Long sum = 0L;

        HashSet<Long> tree = new HashSet<>();

        String temp = reader.readLine();

        while(temp!=null)
        {
            try {
                tree.add(Long.parseLong(temp));
            }
            catch (NumberFormatException ex) {
                continue;
            }

            temp = reader.readLine();
        }

        for (Long i: tree) {
            sum += i;
        }

        writer.write(sum.toString());

        writer.close();
    }
}