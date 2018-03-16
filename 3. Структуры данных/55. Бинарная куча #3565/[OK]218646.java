import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        int[] array = {};
        try (Scanner sc = new Scanner(new FileReader("input.txt"))) {
            count = sc.nextInt();
            array = new int[count + 1];
            int i = 1;
            while (sc.hasNext()) {
                array[i] = sc.nextInt();
                i++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            PrintWriter pw = new PrintWriter(new File("output.txt"));
            for (int i = count; i >= 2; ) {
                if (array[i] >= array[i / 2]) i--;
                else {
                    pw.print("No");
                    pw.close();
                    System.exit(0);
                }
            }
            pw.print("Yes");
            pw.close();
        } catch (Exception ex) { }
    }
}