import java.io.*;
import java.util.HashSet;

/**
 * Created by Andrey Belski on 15.02.2017.
 */
public class Work {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);
            String string;
            HashSet<Long> arrayKey = new HashSet<>();
            long key, sum = 0;

            while ((string = in.readLine()) != null) {
                key = Long.parseLong(string);
                arrayKey.add(key);
            }
            in.close();
            for (Long k: arrayKey) {
                sum += k;
            }
            FileWriter writer = new FileWriter("output.txt", false);
            String text = Long.toString(sum);
                    writer.write(text);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
