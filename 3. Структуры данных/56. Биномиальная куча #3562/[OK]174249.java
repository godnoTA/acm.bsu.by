import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Djohn on 31/03/2017.
 */
public class BinomHeap {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileReader("input.txt"));
            FileWriter out = new FileWriter("output.txt");
            long numberOfNodes = sc.nextLong();
            String binaryNumber = Long.toBinaryString(numberOfNodes);

            for (int i = binaryNumber.length() - 1; i >= 0; i--) {
                if (binaryNumber.charAt(i) == '1') {
                    out.write((binaryNumber.length() - i - 1) + "\n");
                }
            }
            out.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}