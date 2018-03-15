import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {

            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            FileWriter fwr = new FileWriter("output.txt", false);
            BufferedWriter writer = new BufferedWriter(fwr);

            long n = Long.valueOf(br.readLine());
            List<Long> result = new ArrayList<>();

            long k = 0;

            while (n != 1) {
                long bit = n % 2;
                if (bit == 1) {
                    result.add(k);
                }
                n /= 2;
                k++;
            }

            result.add(k);
            Collections.sort(result);

            for (int i = 0; i < result.size(); i++) {
                writer.append(String.valueOf(result.get(i)));
                writer.append("\n");
            }

            writer.close();
            br.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
