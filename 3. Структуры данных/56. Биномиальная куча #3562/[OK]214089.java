import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        long n = Long.parseLong(scanner.next());

        ArrayList<Long> bin = new ArrayList<>();
        while (n != 0) {
            bin.add(n % 2);
            n /= 2;
        }


        FileWriter fout = null;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            for (int i = 0; i < bin.size(); ++i) {
                if (bin.get(i)==1)
                    fout.write(i + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {

        }
    }
}
